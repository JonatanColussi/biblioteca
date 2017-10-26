package br.com.fadergs.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.fadergs.biblioteca.jdbc.Conexao;
import br.com.fadergs.biblioteca.entidades.Livro;

public class LivroDAO {
	
	private Connection con = Conexao.getConnection();
	
	public void cadastrar (Livro livro) {
		
		String sql = "INSERT INTO livros (titulo, editora, valor, codcategoria, codbib, situacao) values (?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, livro.getTitulo());
			preparador.setString(2, livro.getEditora());
			preparador.setDouble(3, livro.getValor());
			preparador.setInt(4, livro.getCodcategoria());
			preparador.setInt(5, livro.getCodbib());
			preparador.setString(6, livro.getSituacao());
			
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Cadastrado com sucesso!");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
	}
	
	
	public void editar (Livro livro) {
		
		String sql = "UPDATE livros SET titulo = ?, editora = ?, valor = ?, codcategoria = ?, codbib = ?, situacao = ? where codlivro = ?";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, livro.getTitulo());
			preparador.setString(2, livro.getEditora());
			preparador.setDouble(3, livro.getValor());
			preparador.setInt(4, livro.getCodcategoria());
			preparador.setInt(5, livro.getCodbib());
			preparador.setString(6, livro.getSituacao());
			preparador.setInt(7, livro.getCodlivro());
			
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Alterado com sucesso!");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	public void remover (Livro livro) {
		
		String sql = "DELETE from livros where codlivro = ?";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, livro.getCodlivro());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Apagado com sucesso!");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
	}
	
	
	public ArrayList<Livro> buscarTodos () {
		
		String sql = "Select * from livros";
		ArrayList<Livro> livrosLista = new ArrayList<Livro>();
		try {
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			
			while (result.next()) {
				Livro livro = new Livro();
			
				livro.setCodlivro(result.getInt(1));
				livro.setTitulo(result.getString(2));
				livro.setEditora(result.getString(3));
				livro.setValor(result.getDouble(4));
				livro.setCodcategoria(result.getInt(5));
				livro.setCodbib(result.getInt(6));
				livro.setSituacao(result.getString(7));
				
				livrosLista.add(livro);
				
			}
			statement.close();
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return livrosLista;
		
	}
	
	/*
	public ArrayList<Livro> buscaLivroPorFiltro (Integer codlivro, String titulo, String editora, Integer codcategoria, Integer codbib, String situacao) {
		
		String sql = "Select * from livros where codlivro = ISNULL(?, codlivro) and titulo LIKE ISNULL(?, %%) and editora LIKE ISNULL(?, %%) and codcategoria = ISNULL(?, codcategoria) and codbib = ISNULL(?, codbib) and situacao = ISNULL(?, *)";
		ArrayList<Livro> livrosLista = new ArrayList<Livro>();
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, codlivro);
			statement.setString(2, '%' + titulo + '%');
			statement.setString(3, '%' + editora + '%');
			statement.setInt(4, codcategoria);
			statement.setInt(5, codbib);
			statement.setString(6, situacao);
			ResultSet result = statement.executeQuery();
			
			
			while (result.next()) {
				Livro livro = new Livro();
			
				livro.setCodlivro(result.getInt(1));
				livro.setTitulo(result.getString(2));
				livro.setEditora(result.getString(3));
				livro.setValor(result.getDouble(4));
				livro.setCodcategoria(result.getInt(5));
				livro.setCodbib(result.getInt(6));
				livro.setSituacao(result.getString(7));
				
				livrosLista.add(livro);
				
			}
			statement.close();
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return livrosLista;
		
	}*/

}
