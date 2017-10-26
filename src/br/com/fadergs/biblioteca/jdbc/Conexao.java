package br.com.fadergs.biblioteca.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public static Connection getConnection() {
		Connection con=null;
		
		
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/biblioteca","postgres","molokovelocet");
			System.out.println("Conectado com sucesso");
		} catch (SQLException e) {
			System.out.println("Falha na conexão");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver não encontrado.");
		}
		
		return con;
	}
	
}