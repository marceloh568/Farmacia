package FACTORY;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
	private static final String USUARIO = "root";
	private static final String SENHA = "mysql";
	private static final String URL = "jdbc:mysql://localhost:3306/farmacia";

	// final = atributo constante
	// static para não precisar instanciar

	public static Connection con() throws SQLException {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		return conexao;
	}

	public static void main(String[] args) {
		try {
			Connection conexao = ConexaoFactory.con();
			System.out.println("Conexão estabelecida com êxito!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Não foi possível estabelecer a conexão!");
		}
	}

}