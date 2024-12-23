package application;

import db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {

    public static void main(String[] args) {

        // Define o formato da data a ser utilizada
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null; // Inicializa a conexão como nula
        PreparedStatement st = null; // Inicializa o PreparedStatement como nulo
        try {
            // Obtém a conexão com o banco de dados
            conn = DB.getConnection();

            // EXEMPLO 1: Inserção de um vendedor
            st = conn.prepareStatement(
                    "INSERT INTO seller "
                            + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                            + "VALUES "
                            + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS); // Solicita a geração de chaves

            // Define os valores a serem inseridos
            st.setString(1, "Carl Purple");
            st.setString(2, "carl@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime())); // Converte a data para o formato SQL
            st.setDouble(4, 3000.0);
            st.setInt(5, 4);

            // EXEMPLO 2: Inserção de departamentos (descomentado para uso)
            /*
            st = conn.prepareStatement(
                    "insert into department (Name) values ('D1'),('D2')",
                    Statement.RETURN_GENERATED_KEYS);
            // Não é necessário o tratamento de ParseException aqui, pois não há conversões de data
            */

            // Executa a atualização (inserção)
            int rowsAffected = st.executeUpdate();

            // Verifica se alguma linha foi afetada pela inserção
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys(); // Obtém as chaves geradas
                while (rs.next()) {
                    int id = rs.getInt(1); // Recupera o ID gerado
                    System.out.println("Done! Id: " + id); // Exibe o ID
                }
            } else {
                System.out.println("No rows affected!"); // Mensagem caso nenhuma linha seja afetada
            }
        } catch (SQLException | ParseException e) {
            // Trata exceções, imprimindo a stack trace
            e.printStackTrace(); // Chamada para 'printStackTrace()' deve ser substituída por um log mais robusto
        } finally {
            // Fecha o PreparedStatement e a conexão
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}