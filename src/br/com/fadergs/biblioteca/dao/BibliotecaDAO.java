package br.com.fadergs.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fadergs.biblioteca.entidades.Biblioteca;
import br.com.fadergs.biblioteca.jdbc.Conexao;

public class BibliotecaDAO {
	
	private Connection con = Conexao.getConnection();
	
	public boolean cadastrar (Biblioteca biblioteca) {
		
		String sql = "INSERT INTO biblioteca (nome, endereco) values (?, ?)";
		boolean retorno = false;
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setString(1, biblioteca.getNome());
			preparador.setString(2, biblioteca.getEndereco());
			
			preparador.execute();
			preparador.close();
			
			retorno = true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return retorno;
	}
	
	
	public boolean editar (Biblioteca biblioteca) {
	
		String sql = "UPDATE biblioteca SET nome = ?, endereco = ? where codbib = ?";
		boolean retorno = false;
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setString(1, biblioteca.getNome());
			preparador.setString(2, biblioteca.getEndereco());
			preparador.setInt(3, biblioteca.getCodbib());
			
			preparador.execute();
			preparador.close();
			
			retorno = true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return retorno;
	}
	
	
	public boolean remover (Biblioteca biblioteca) {
		
		String sql = "DELETE from biblioteca where codbib = ?";
		boolean retorno = false;
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, biblioteca.getCodbib());
			
			preparador.execute();
			preparador.close();
			
			retorno = true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return retorno;
	}
	
	
	public List<Biblioteca> buscarTodos () {
		
		String sql = "Select * from biblioteca";
		List<Biblioteca> bibliotecasLista = new ArrayList<Biblioteca>();
		try {
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			
			while (result.next()) {
				Biblioteca biblioteca = new Biblioteca();
			
				biblioteca.setCodbib(result.getInt(1));
				biblioteca.setNome(result.getString(2));
				biblioteca.setEndereco(result.getString(3));
				
				bibliotecasLista.add(biblioteca);
				
			}
			statement.close();
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return bibliotecasLista;
		
	}
	
	
	public Biblioteca buscarBibliotecaPorCod (Integer codbib) {
		
		String sql = "Select * from biblioteca where codbib = ?";
		Biblioteca biblioteca = new Biblioteca();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, codbib);
			ResultSet result = preparador.executeQuery();
			
			
			while (result.next()) {
				
				biblioteca.setCodbib(result.getInt(1));
				biblioteca.setNome(result.getString(2));
				biblioteca.setEndereco(result.getString(3));
								
			}
			preparador.close();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return biblioteca;
		
	}
	
	
	public ArrayList<Biblioteca> buscarBibliotecaPorNome (String nome) {
		
		String sql = "Select * from biblioteca where nome LIKE ?";
		ArrayList<Biblioteca> bibliotecasLista = new ArrayList<Biblioteca>();
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, '%' + nome + '%');
			ResultSet result = statement.executeQuery();
			
			
			while (result.next()) {
				
				Biblioteca biblioteca = new Biblioteca();
				
				biblioteca.setCodbib(result.getInt(1));
				biblioteca.setNome(result.getString(2));
				biblioteca.setEndereco(result.getString(3));
				
				bibliotecasLista.add(biblioteca);
				
			}
			statement.close();
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return bibliotecasLista;
		
	}

}
