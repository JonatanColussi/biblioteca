package br.com.fadergs.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fadergs.biblioteca.entidades.Aluno;
import br.com.fadergs.biblioteca.jdbc.Conexao;

public class AlunoDAO {
	
private Connection con = Conexao.getConnection();
	
	public boolean cadastrar (Aluno aluno) {
		
		String sql = "INSERT INTO alunos (codmatricula, nome, endereco, situacao) values (?, ?, ?, ?)";
		boolean retorno = false;
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setInt(1, aluno.getCodmatricula());
			preparador.setString(2, aluno.getNome());
			preparador.setString(3, aluno.getEndereco());
			preparador.setString(4, aluno.getSituacao());

			
			preparador.execute();
			preparador.close();
			
			retorno = true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return retorno;
	}
	
	
	public boolean editar (Aluno aluno) {
		
		String sql = "UPDATE alunos SET codmatricula = ?, nome = ?, endereco = ?, situacao = ? where codmatricula = ?";
		boolean retorno = false;
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setInt(1, aluno.getCodmatricula());
			preparador.setString(2, aluno.getNome());
			preparador.setString(3, aluno.getEndereco());
			preparador.setString(4, aluno.getSituacao());
			preparador.setInt(5, aluno.getCodmatricula());

			
			preparador.execute();
			preparador.close();
			
			retorno = true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return retorno;
	}
	
	
	public void remover (Aluno aluno) {
		
		String sql = "DELETE from alunos where codmatricula = ?";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, aluno.getCodmatricula());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Apagado com sucesso!");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	public List<Aluno> buscarTodos () {
		this.vefificarAlunos();
		String sql = "Select * from alunos";
		List<Aluno> alunosLista = new ArrayList<Aluno>();
		try {
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			
			while (result.next()) {
				Aluno aluno = new Aluno();
			
				aluno.setCodmatricula(result.getInt(1));
				aluno.setNome(result.getString(2));
				aluno.setEndereco(result.getString(3));
				aluno.setSituacao(result.getString(4));
				
				alunosLista.add(aluno);
				
			}
			statement.close();
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return alunosLista;
		
	}
	
	
	public Aluno buscarAlunoPorMatricula (Integer codmatricula) {
		
		String sql = "Select * from alunos where codmatricula = ?";
		Aluno aluno = new Aluno();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, codmatricula);
			ResultSet result = preparador.executeQuery();
			
			
			while (result.next()) {
				
				aluno.setCodmatricula(result.getInt(1));
				aluno.setNome(result.getString(2));
				aluno.setEndereco(result.getString(3));
				aluno.setSituacao(result.getString(4));
								
			}
			preparador.close();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return aluno;
		
	}
	
	
	public ArrayList<Aluno> buscarAlunoPorNome (String nome) {
		
		String sql = "Select * from alunos where nome LIKE ?";
		ArrayList<Aluno> alunosLista = new ArrayList<Aluno>();
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, '%' + nome + '%');
			ResultSet result = statement.executeQuery();
			
			
			while (result.next()) {
				
				Aluno aluno = new Aluno();
				
				aluno.setCodmatricula(result.getInt(1));
				aluno.setNome(result.getString(2));
				aluno.setEndereco(result.getString(3));
				aluno.setSituacao(result.getString(4));
				
				alunosLista.add(aluno);
				
			}
			statement.close();
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return alunosLista;
		
	}
	
	public void vefificarAlunos() {
		String sql = "SELECT COUNT(*) AS atrasados, codmatricula FROM empresta WHERE dtprevisao < current_date AND dtentrega IS NULL GROUP BY codmatricula";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet result = preparador.executeQuery();
		
			while (result.next()) {
				if (result.getInt(1) > 0) {
					String sqlAluno = "UPDATE alunos SET situacao = 'Inativo' WHERE codmatricula = ?";
					try {
						PreparedStatement preparador2 = con.prepareStatement(sqlAluno);
						preparador2.setInt(1, result.getInt(2));
						preparador2.executeQuery();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
								
			}
			preparador.close();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
	}

}
