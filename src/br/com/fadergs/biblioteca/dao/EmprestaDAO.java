package br.com.fadergs.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.ArrayList;
import java.util.Calendar;

import br.com.fadergs.biblioteca.jdbc.Conexao;
import br.com.fadergs.biblioteca.entidades.Empresta;

public class EmprestaDAO {
	
	private Connection con = Conexao.getConnection();
	
	public void cadastrar (Empresta empresta) throws ParseException {
		
		String sql = "INSERT INTO empresta (codmatricula, codlivro, dtretirada, dtprevisao) values (?, ?, ?, ?)";
		String sql_livro = "UPDATE livros SET situacao = ? where codlivro = ?";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			PreparedStatement preparador_livro = con.prepareStatement(sql_livro);
			
			
			String dtretiradaSTR = empresta.getDtretirada();
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			java.sql.Date dtretirada = new java.sql.Date(sdf.parse(dtretiradaSTR).getTime());
			java.sql.Date dtprevisao = new java.sql.Date(sdf.parse(dtretiradaSTR).getTime() + 10l*24l*60l*60l*1000l);
			
		
			preparador.setInt(1, empresta.getCodmatricula());
			preparador.setInt(2, empresta.getCodlivro());
			preparador.setDate(3, dtretirada);
			preparador.setDate(4, dtprevisao);
			
			preparador.execute();
			preparador.close();
			
			preparador_livro.setString(1, "Indisponivel");
			preparador_livro.setInt(2, empresta.getCodlivro());
			
			preparador_livro.execute();
			preparador_livro.close();
			
			System.out.println("Cadastrado com sucesso!");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	public void editar (Empresta empresta) throws ParseException {
		
		String sql = "UPDATE empresta SET codmatricula = ?, codlivro = ?, dtretirada = ?, dtprevisao = ?, dtentrega = ? where codmatricula = ? and codlivro = ? and dtretirada = ?";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			String dtretiradaSTR = empresta.getDtretirada();
			String dtprevisaoSTR = empresta.getDtprevisao();
			String dtentregaSTR = empresta.getDtentrega();
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			java.sql.Date dtretirada = new java.sql.Date(sdf.parse(dtretiradaSTR).getTime());
			java.sql.Date dtprevisao = new java.sql.Date(sdf.parse(dtprevisaoSTR).getTime());
			java.sql.Date dtentrega = new java.sql.Date(sdf.parse(dtentregaSTR).getTime());
			
			preparador.setInt(1, empresta.getCodmatricula());
			preparador.setInt(2, empresta.getCodlivro());
			preparador.setDate(3, dtretirada);
			preparador.setDate(4, dtprevisao);
			preparador.setDate(5, dtentrega);
			
			preparador.setInt(6, empresta.getCodmatricula());
			preparador.setInt(7, empresta.getCodlivro());
			preparador.setDate(8, dtretirada);
			
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Alterado com sucesso!");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public void devolverLivro (Empresta empresta) throws ParseException {
		
		String sql = "UPDATE empresta SET dtentrega = ? where codmatricula = ? and codlivro = ? and dtretirada = ?";
		String sql_livro = "UPDATE livros SET situacao = ? where codlivro = ?";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			PreparedStatement preparador_livro = con.prepareStatement(sql_livro);
			
			
			String dtretiradaSTR = empresta.getDtretirada();
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			java.sql.Date dtretirada = new java.sql.Date(sdf.parse(dtretiradaSTR).getTime());
			java.sql.Date dtentrega = new java.sql.Date(Calendar.getInstance().getTimeInMillis());

			
			preparador.setDate(1, dtentrega);
			
			preparador.setInt(2, empresta.getCodmatricula());
			preparador.setInt(3, empresta.getCodlivro());
			preparador.setDate(4, dtretirada);
			
			
			preparador.execute();
			preparador.close();
			
			preparador_livro.setString(1, "Disponivel");
			preparador_livro.setInt(2, empresta.getCodlivro());
			
			preparador_livro.execute();
			preparador_livro.close();
			
			System.out.println("Devolvido com sucesso!");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public void prorrogarEmprestimo(Empresta empresta) throws ParseException {
		
		String sql = "UPDATE empresta SET dtprevisao = ? where codmatricula = ? and codlivro = ? and dtretirada = ?";
		String sql_select = "Select dtprevisao from empresta where codmatricula = ? and codlivro = ? and dtretirada = ?";
		
		try {

			String dtretiradaSTR = empresta.getDtretirada();
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat sdf_db = new SimpleDateFormat("yyyy-MM-dd");
			java.sql.Date dtretirada = new java.sql.Date(sdf.parse(dtretiradaSTR).getTime());
			
			
			PreparedStatement preparador_select = con.prepareStatement(sql_select);
			preparador_select.setInt(1, empresta.getCodmatricula());
			preparador_select.setInt(2, empresta.getCodlivro());
			preparador_select.setDate(3, dtretirada);
			ResultSet result = preparador_select.executeQuery();
			
			Empresta empresta_select = new Empresta();
			
			while (result.next()) {
				empresta_select.setDtprevisao(result.getString(1));				
			}
			
			
			java.sql.Date dtprevisao = new java.sql.Date(sdf_db.parse(empresta_select.getDtprevisao()).getTime() + 10l*24l*60l*60l*1000l);
			
			PreparedStatement preparador = con.prepareStatement(sql);
		
		
			preparador.setDate(1, dtprevisao);
			
			preparador.setInt(2, empresta.getCodmatricula());
			preparador.setInt(3, empresta.getCodlivro());
			preparador.setDate(4, dtretirada);
			
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Prorrogado com sucesso!");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	

}
