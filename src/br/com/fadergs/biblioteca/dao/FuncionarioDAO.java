package br.com.fadergs.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fadergs.biblioteca.jdbc.Conexao;
import br.com.fadergs.biblioteca.entidades.Funcionario;


public class FuncionarioDAO {
	
	private Connection con = Conexao.getConnection();
	
	public boolean cadastrar (Funcionario funcionario) {
		
		String sql = "INSERT INTO funcionario (nome, endereco, telefone, salario, codbib, login, senha) values (?, ?, ?, ?, ?, ?, md5(?))";
		boolean retorno = false;
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, funcionario.getNome());
			preparador.setString(2, funcionario.getEndereco());
			preparador.setString(3, funcionario.getTelefone());
			preparador.setDouble(4, funcionario.getSalario());
			preparador.setInt(5, funcionario.getCodbib());
			preparador.setString(6, funcionario.getLogin());
			preparador.setString(7, funcionario.getSenha());
			
			
			preparador.execute();
			preparador.close();
			
			retorno = true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return retorno;
	}
	
	public boolean editar (Funcionario funcionario) {
		
		String sql = "UPDATE funcionario SET nome = ?, endereco = ?, telefone = ?, salario = ?, codbib = ?, login = ?, senha = md5(?) where codfunc = ?";
		boolean retorno = false;
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, funcionario.getNome());
			preparador.setString(2, funcionario.getEndereco());
			preparador.setString(3, funcionario.getTelefone());
			preparador.setDouble(4, funcionario.getSalario());
			preparador.setInt(5, funcionario.getCodbib());
			preparador.setString(6, funcionario.getLogin());
			preparador.setString(7, funcionario.getSenha());
			preparador.setInt(8, funcionario.getCodfunc());
			
			preparador.execute();
			preparador.close();
			
			retorno = true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return retorno;
	}
	
	public void remover (Funcionario funcionario) {
		
		String sql = "DELETE from funcionario where codfunc=?";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, funcionario.getCodfunc());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Apagado com sucesso!");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public List<Funcionario> buscarTodos () {
		
		String sql = "Select * from funcionario";
		List<Funcionario> funcionariosLista = new ArrayList<Funcionario>();
		try {
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			
			while (result.next()) {
				Funcionario funcionario = new Funcionario();
			
				funcionario.setCodfunc(result.getInt(1));
				funcionario.setNome(result.getString(2));
				funcionario.setEndereco(result.getString(3));
				funcionario.setTelefone(result.getString(4));
				funcionario.setSalario(result.getDouble(5));
				funcionario.setCodbib(result.getInt(6));
				funcionario.setLogin(result.getString(7));
				
				funcionariosLista.add(funcionario);
				
			}
			statement.close();
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return funcionariosLista;
		
	}
	
public Funcionario buscarFuncionarioPorCod (Integer codfunc) {
		
		String sql = "Select * from funcionario where codfunc = ?";
		Funcionario funcionario = new Funcionario();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, codfunc);
			ResultSet result = preparador.executeQuery();
			
			
			while (result.next()) {
				
				
				funcionario.setCodfunc(result.getInt(1));
				funcionario.setNome(result.getString(2));
				funcionario.setEndereco(result.getString(3));
				funcionario.setTelefone(result.getString(4));
				funcionario.setSalario(result.getDouble(5));
				funcionario.setCodbib(result.getInt(6));
				funcionario.setLogin(result.getString(7));
								
			}
			preparador.close();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return funcionario;
		
	}

public ArrayList<Funcionario> buscarFuncionarioPorNome (String nome) {
	
	String sql = "Select * from funcionario where nome LIKE ?";
	ArrayList<Funcionario> funcionariosLista = new ArrayList<Funcionario>();
	try {
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, '%' + nome + '%');
		ResultSet result = statement.executeQuery();
		
		
		while (result.next()) {
			Funcionario funcionario = new Funcionario();
		
			funcionario.setCodfunc(result.getInt(1));
			funcionario.setNome(result.getString(2));
			funcionario.setEndereco(result.getString(3));
			funcionario.setTelefone(result.getString(4));
			funcionario.setSalario(result.getDouble(5));
			funcionario.setCodbib(result.getInt(6));
			funcionario.setLogin(result.getString(7));
			
			funcionariosLista.add(funcionario);
			
		}
		statement.close();
		
		
		
	} catch (SQLException e) {
		
		e.printStackTrace();
		
	}
	return funcionariosLista;
	
}

public Funcionario login (Funcionario func) {
	
	String sql = "Select * from funcionario where login = ? AND senha = MD5(?)";
	Funcionario funcionario = new Funcionario();
	
	try {
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setString(1, func.getLogin());
		preparador.setString(2, func.getSenha());
		ResultSet result = preparador.executeQuery();
		
		
		while (result.next()) {
			funcionario.setCodfunc(result.getInt(1));
			funcionario.setNome(result.getString(2));
			funcionario.setEndereco(result.getString(3));
			funcionario.setTelefone(result.getString(4));
			funcionario.setSalario(result.getDouble(5));
			funcionario.setCodbib(result.getInt(6));
			funcionario.setLogin(result.getString(7));
		}
		preparador.close();
		
		
	} catch (SQLException e) {
		
		e.printStackTrace();
		
	}
	return funcionario;
	
}

}
