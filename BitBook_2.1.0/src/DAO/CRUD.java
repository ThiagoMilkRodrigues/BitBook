package DAO;

import java.awt.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Classe responsável por realizar operações CRUD na tabela de podcasts do banco de dados.
 * 
 * @author Thiago Milk
 */
public class CRUD {
    
    public void inserirRegistro(String Usuario, String Tipo, String Data_registro, String Moeda, float ValorInvestido, float ValorNaCompra, float ValorNaVenda, String DataCompra, String DataVenda) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        if (conn != null) {
            String sql = "INSERT INTO Transacoes (Usuario, Tipo, Data_registro, Moeda, ValorInvestido, ValorNaCompra, ValorNaVenda, DataCompra, DataVenda) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, Usuario);
                stmt.setString(2, Tipo);
                stmt.setString(3, Data_registro);
                stmt.setString(4, Moeda);
                stmt.setFloat(5, ValorInvestido);
                stmt.setFloat(6, ValorNaCompra);
                stmt.setFloat(7, ValorNaVenda);
                stmt.setString(8, DataCompra);
                stmt.setString(9, DataVenda);
                
                int rowsInserted = stmt.executeUpdate();
                
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(null, "Transação inserida com sucesso no BD!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }
                
                stmt.close();
                conn.close();
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao inserir transação no BD: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    

    public void listarTransacoes(JTable tabela) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        
        if (conn != null) {
            String sql = "SELECT * FROM Transacoes";
            
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                
                DefaultTableModel model = (DefaultTableModel) tabela.getModel();
                model.setRowCount(0);
                
                while (rs.next()) {
                    int ID = rs.getInt("ID");
                    String Usuario = rs.getString("Usuario");
                    String Tipo = rs.getString("Tipo");
                    String Data_registro = rs.getString("Data_registro");
                    String Moeda = rs.getString("Moeda");
                    float ValorInvestido = rs.getFloat("ValorInvestido");
                    float ValorNaCompra = rs.getFloat("ValorNaCompra");
                    String DataCompra = rs.getString("DataCompra");
                    String DataVenda = rs.getString("DataVenda");
                    
                    model.addRow(new Object[]{ID, Usuario, Tipo, Data_registro, Moeda, ValorInvestido, ValorNaCompra, DataCompra, DataVenda });
                }
                
                rs.close();
                stmt.close();
                conn.close();
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao listar transações do BD: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


public boolean validarLogin(String Nome, String Senha) {
    Conexao conexao = new Conexao();
    Connection conn = conexao.conectar();
    
    if (conn != null) {
        String sql = "SELECT * FROM Usuarios WHERE Nome = ? AND Senha = ?";
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Nome);
            stmt.setString(2, Senha);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {

                return true;
                
            } else {
                JOptionPane.showMessageDialog(null, "Nome ou senha inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return false; // Login inválido
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao validar login: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}





    
}
