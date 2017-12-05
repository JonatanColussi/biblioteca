package br.com.fadergs.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fadergs.biblioteca.jdbc.Conexao;
import br.com.fadergs.biblioteca.entidades.Livro;

public class LivroDAO {
	
	private Connection con = Conexao.getConnection();
	
	public boolean cadastrar (Livro livro) {
		
		String sql = "INSERT INTO livros (titulo, editora, valor, codcategoria, codbib, situacao) values (?, ?, ?, ?, ?, ?)";
		boolean retorno = false;
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, livro.getTitulo());
			preparador.setString(2, livro.getEditora());
			preparador.setDouble(3, livro.getValor());
			preparador.setInt(4, livro.getCodcategoria());
			preparador.setInt(5, livro.getCodbib());
			preparador.setString(6, "Disponivel");
			
			
			preparador.execute();
			preparador.close();
			
			retorno = true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return retorno;
	}
	
	
	public boolean editar (Livro livro) {
		
		String sql = "UPDATE livros SET titulo = ?, editora = ?, valor = ?, codcategoria = ?, codbib = ? where codlivro = ?";
		boolean retorno = false;
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, livro.getTitulo());
			preparador.setString(2, livro.getEditora());
			preparador.setDouble(3, livro.getValor());
			preparador.setInt(4, livro.getCodcategoria());
			preparador.setInt(5, livro.getCodbib());
			preparador.setInt(6, livro.getCodlivro());
			
			
			preparador.execute();
			preparador.close();
			
			retorno = true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return retorno;
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
	
	
	public List<Livro> buscarTodos () {
		
		String sql = "Select * from livros";
		List<Livro> livrosLista = new ArrayList<Livro>();
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
	
	public Livro buscarLivroPorCod (Integer codlivro) {
		
		String sql = "Select * from livros where codlivro = ?";
		Livro livro = new Livro();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, codlivro);
			ResultSet result = preparador.executeQuery();
			
			
			while (result.next()) {
				
				livro.setCodlivro(result.getInt(1));
				livro.setTitulo(result.getString(2));
				livro.setEditora(result.getString(3));
				livro.setValor(result.getDouble(4));
				livro.setCodcategoria(result.getInt(5));
				livro.setCodbib(result.getInt(6));
				livro.setSituacao(result.getString(7));
								
			}
			preparador.close();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return livro;
		
	}
	
	public List<Livro> buscarDisponiveis () {
		
		String sql = "Select * from livros where situacao = 'Disponivel'";
		List<Livro> livrosLista = new ArrayList<Livro>();
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
