/**
 * Classe responsável por estabelecer a conexão com o banco de dados MySQL.
 * 
 * @author Thiago Milk
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/BitBook_DB";
    private static final String USUARIO = "root";
    private static final String SENHA = "milk1967";

    /**
     * Método responsável por conectar ao banco de dados.
     * 
     * @return Um objeto Connection se a conexão for bem-sucedida, ou null em caso de falha.
     */
    public Connection conectar() {
        Connection conn = null;

        try {
            // Carrega o driver do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Estabelece a conexão com o banco de dados
            conn = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conectado com sucesso!");

        } catch (ClassNotFoundException e) {
            System.out.println("Driver do banco de dados não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }

        return conn;
    }
}
