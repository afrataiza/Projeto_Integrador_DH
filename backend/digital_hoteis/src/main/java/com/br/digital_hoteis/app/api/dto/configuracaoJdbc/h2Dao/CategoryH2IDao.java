package com.br.digital_hoteis.app.api.dto.configuracaoJdbc.h2Dao;


import com.br.digital_hoteis.app.api.dto.configuracaoJdbc.ConfiguracaoJdbc;
import com.br.digital_hoteis.app.api.dto.configuracaoJdbc.IDao.IDao;
import com.br.digital_hoteis.domain.entity.Category;
import com.br.digital_hoteis.domain.entity.RatingEnum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.logging.Logger;

import static com.br.digital_hoteis.domain.entity.Category.newCategory;

public class CategoryH2IDao implements IDao<Category, UUID> {

    private ConfiguracaoJdbc configuracaoJdbc = new ConfiguracaoJdbc();
    private static final Logger log = Logger.getLogger(String.valueOf(CategoryH2IDao.class));

    private static final String SQL_FIND_BY_ID = "SELECT * FROM Category WHERE id = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM Category WHERE id = ?";
    private static final String SQL_FIND_ALL = "SELECT * FROM Category";

    private static final String SQL_CREATING = """
    INSERT INTO Category(
    id, ratings, description, image_url) VALUES(RANDOM_UUID(), ?, ?, ?);
    """;

    private static final String SQL_UPDATING = """
    UPDATE Category SET ratings = ?, description = ?, image_url = ?;
    """;

    @Override
    public Category create(Category category) {
        log.info("[h2]: Creating a category: " + category);
        Connection connection = configuracaoJdbc.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATING)) {
            statement.setString(1, category.getId().toString());
            statement.setString(2, category.getRatings().name());
            statement.setString(3, category.getDescription());
            statement.setString(4, category.getImage_url());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public Category findById(UUID id) {
        log.info("Searching for connection");
        Connection connection = configuracaoJdbc.getConnection();
        log.info("[h2]: searching for id: " + id);
        Category category = null;

        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            statement.setString(1, id.toString());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String ratingsString = resultSet.getString(2);
                RatingEnum ratings = RatingEnum.valueOf(ratingsString);

                String description = resultSet.getString(3);
                String image_url = resultSet.getString(4);

                category = newCategory(ratings, description, image_url);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        log.info("[h2]: Register found: " + category);
        return category;
    }



    @Override
    public void delete(UUID id) {
        log.info("Searching for connection");
        Connection connection = configuracaoJdbc.getConnection();
        log.info("[h2]: finding and deleting by id: " + id);
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            statement.setObject(1, id);
            statement.executeUpdate();
            log.info("Id " + id + " deleted with success.");
        } catch (Exception e) {
            log.info((Supplier<String>) e);
            System.err.println(log);
        }
    }

    @Override
    public Category update(UUID id, Category category) {
        log.info("Updating category with the id: " + id + "  in the database.");
        Connection connection = configuracaoJdbc.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATING)) {
            statement.setString(1, category.getRatings().name());
            statement.setString(2, category.getDescription());
            statement.setObject(3, category.getImage_url());
            statement.setString(4, id.toString());
            int atualizacao = statement.executeUpdate();
            if (atualizacao > 0) {
                log.info("Database updated successfully");
                return category;
            }
        } catch (SQLException e) {
            log.info("An error occurred while updating the category: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Category> findAll() {
        log.info("Finding all the categories in the database.");

        Connection connection = configuracaoJdbc.getConnection();
        List<Category> foundCategories = new ArrayList<>();

        log.info("[h2]: searching for all registers");

        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString(1));
                RatingEnum ratings = RatingEnum.valueOf(resultSet.getString(2));
                String description = resultSet.getString(3);
                String image_url = resultSet.getString(4);

                Category category = newCategory(ratings, description, image_url);
                foundCategories.add(category);

                log.info("| id: %s | ratings: %s | description: %s | image_url: %s |"
                        .formatted(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4)
                        ));
            }
        } catch (SQLException e) {
            log.info("An error happened while searching the categories: " + e.getMessage());
        }
        return foundCategories;
    }



    @Override
    public List<Category> createCategory(List<Category> categories) {
        log.info("[h2]: Creating multiple categories at once.");
        Connection connection = configuracaoJdbc.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATING)) {
            for (Category category : categories) {
                statement.setString(1, category.getId().toString());
                statement.setString(2, category.getRatings().name());
                statement.setString(3, category.getDescription());
                statement.setString(4, category.getImage_url());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return categories;
    }


}

