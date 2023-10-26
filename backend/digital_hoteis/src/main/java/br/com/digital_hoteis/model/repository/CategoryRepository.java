package br.com.digital_hoteis.model.repository;

import br.com.digital_hoteis.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID>,
        JpaSpecificationExecutor<Category> {
}
