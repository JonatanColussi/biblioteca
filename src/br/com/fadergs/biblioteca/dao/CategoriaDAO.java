package br.com.fadergs.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.fadergs.biblioteca.entidades.Categoria;
import br.com.fadergs.biblioteca.jdbc.Conexao;

public class CategoriaDAO {
	
	private Connection con = Conexao.getConnection();
	
	public void cadastrar (Categoria categoria) {
		
		String sql = "INSERT INTO categoria (descricao) values (?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setString(1, categoria.getDescricao());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Cadastrado com sucesso!");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	public void editar (Categoria categoria) {
		
		String sql = "UPDATE categoria SET descricao = ? where codcategoria = ?";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setString(1, categoria.getDescricao());
			preparador.setInt(2, categoria.getCodcategoria());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Alterado com sucesso!");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	public void remover (Categoria categoria) {
		
		String sql = "DELETE from categoria where codcategoria = ?";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, categoria.getCodcategoria());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Apagado com sucesso!");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
	}
	
	public ArrayList<Categoria> buscarTodos() {
		
		String sql = "Select * from categoria";
		ArrayList<Categoria> categoriasLista = new ArrayList<Categoria>();
		try {
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			
			while (result.next()) {
				Categoria categoria = new Categoria();
			
				categoria.setCodcategoria(result.getInt(1));
				categoria.setDescricao(result.getString(2));
				
				categoriasLista.add(categoria);
				
			}
			statement.close();
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return categoriasLista;
	}
	
	
	public Categoria buscarCategoriaPorCod (Integer codcategoria) {
		
		String sql = "Select * from categoria where codcategoria = ?";
		Categoria categoria = new Categoria();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, codcategoria);
			ResultSet result = preparador.executeQuery();
			
			
			while (result.next()) {
				
				categoria.setCodcategoria(result.getInt(1));
				categoria.setDescricao(result.getString(2));
								
			}
			preparador.close();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return categoria;
		
	}
	
	
	public ArrayList<Categoria> buscarCategoriaPorDescricao (String descricao) {
		
		String sql = "Select * from categoria where descricao LIKE ?";
		ArrayList<Categoria> categoriasLista = new ArrayList<Categoria>();
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, '%' + descricao + '%');
			ResultSet result = statement.executeQuery();
			
			
			while (result.next()) {
				
				Categoria categoria = new Categoria();
				
				categoria.setCodcategoria(result.getInt(1));
				categoria.setDescricao(result.getString(2));
				
				categoriasLista.add(categoria);
				
			}
			statement.close();
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return categoriasLista;
		
	}

}
