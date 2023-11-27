package com.br.digital_hoteis.app.api.dto.configuracaoJdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConfiguracaoJdbc {
    private String driver;
    private String urlBancoDeDados;
    private String usuario;
    private String senha;
    private Connection connection;

    public ConfiguracaoJdbc() {
        this.driver = "com.mysql.cj.jdbc.Driver";
        this.urlBancoDeDados = "jdbc:mysql://mysql:3306/digital_hoteis";
        this.usuario = "root";
        this.senha = "root";
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(urlBancoDeDados, usuario, senha);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
