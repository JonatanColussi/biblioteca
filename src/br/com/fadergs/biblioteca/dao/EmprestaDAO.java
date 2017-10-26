package br.com.fadergs.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;

import br.com.fadergs.biblioteca.jdbc.Conexao;
import br.com.fadergs.biblioteca.entidades.Empresta;

public class EmprestaDAO {
	
	private Connection con = Conexao.getConnection();
	
	public void cadastrar (Empresta empresta) throws ParseException {
		
		String sql = "INSERT INTO empresta (codmatricula, codlivro, dtretirada, dtprevisao) values (?, ?, ?, ?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			
			String dtretiradaSTR = empresta.getDtretirada();
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			java.sql.Date dtretirada = new java.sql.Date(sdf.parse(dtretiradaSTR).getTime());
			dtretirada\
			
			
			preparador.setInt(1, empresta.getCodmatricula());
			preparador.setInt(2, empresta.getCodlivro());
			preparador.setDate(3, dtretirada);
			preparador.setDate(4, dtretirada);
			
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Cadastrado com sucesso!");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	public void editar (Empresta empresta) {
		
		String sql = "UPDATE empresta SET codmatricula = ?, codlivro = ?, dtretirada = ?, dtprevisao = ?, dtentrega = ? where codmatricula = ? and codlivro = ? and dtretirada = ?";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, empresta.getCodmatricula());
			preparador.setInt(2, empresta.getCodlivro());
			preparador.setString(3, empresta.getDtretirada());
			preparador.setString(4, empresta.getDtprevisao());
			preparador.setString(5, empresta.getDtentrega());
			
			preparador.setInt(6, empresta.getCodmatricula());
			preparador.setInt(7, empresta.getCodlivro());
			preparador.setString(8, empresta.getDtretirada());
			
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Alterado com sucesso!");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	

}
