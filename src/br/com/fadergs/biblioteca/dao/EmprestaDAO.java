package br.com.fadergs.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
			
			
			//String dtretiradaSTR = empresta.getDtretirada();
			//DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			//java.sql.Date dtretirada = new java.sql.Date(sdf.parse(dtretiradaSTR).getTime());
			java.sql.Date dtretirada = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
			//java.sql.Date dtprevisao = new java.sql.Date(sdf.parse(dtretiradaSTR).getTime() + 10l*24l*60l*60l*1000l);
			java.sql.Date dtprevisao = new java.sql.Date(Calendar.getInstance().getTimeInMillis() + 10l*24l*60l*60l*1000l);
			
		
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
		
		String sql = "UPDATE empresta SET dtentrega = ? where codempresta = ?";
		String sql_livro = "UPDATE livros SET situacao = ? where codlivro = ?";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			PreparedStatement preparador_livro = con.prepareStatement(sql_livro);
			
			
//			String dtretiradaSTR = empresta.getDtretirada();
//			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//			java.sql.Date dtretirada = new java.sql.Date(sdf.parse(dtretiradaSTR).getTime());
			java.sql.Date dtentrega = new java.sql.Date(Calendar.getInstance().getTimeInMillis());

			
			preparador.setDate(1, dtentrega);
			preparador.setInt(2, empresta.getCodempresta());
			
			
			preparador.execute();
			preparador.close();
			
			preparador_livro.setString(1, "Disponivel");
			preparador_livro.setInt(2, empresta.getCodlivro());
			
			preparador_livro.execute();
			preparador_livro.close();
			
			this.vefificarAluno(empresta.getCodmatricula());
			
			System.out.println("Devolvido com sucesso!");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public void prorrogarEmprestimo(Empresta empresta) throws ParseException {
		
		String sql = "UPDATE empresta SET dtprevisao = ? where codempresta = ?";
		String sql_select = "Select dtprevisao from empresta where codempresta = ?";
		
		try {

			//String dtretiradaSTR = empresta.getDtretirada();
			//DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat sdf_db = new SimpleDateFormat("yyyy-MM-dd");
			//java.sql.Date dtretirada = new java.sql.Date(sdf.parse(dtretiradaSTR).getTime());
			
			
			PreparedStatement preparador_select = con.prepareStatement(sql_select);
			preparador_select.setInt(1, empresta.getCodempresta());
			ResultSet result = preparador_select.executeQuery();
			
			Empresta empresta_select = new Empresta();
			
			while (result.next()) {
				empresta_select.setDtprevisao(result.getString(1));				
			}
			
			
			java.sql.Date dtprevisao = new java.sql.Date(sdf_db.parse(empresta_select.getDtprevisao()).getTime() + 10l*24l*60l*60l*1000l);
			
			PreparedStatement preparador = con.prepareStatement(sql);
		
			System.out.println(dtprevisao);
		
			preparador.setDate(1, dtprevisao);
			
			preparador.setInt(2, empresta.getCodempresta());
			
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Prorrogado com sucesso!");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("Prorrogado falhou!");
			
		}
		
	}
	
	public Empresta buscarEmprestaPorCodLivro (Integer codlivro, String _situacao) {
		
		String sql = "Select * from empresta em, livros lv where lv.codlivro = em.codlivro and em.codlivro = ? and lv.situacao = ?";
		Empresta empresta = new Empresta();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, codlivro);
			preparador.setString(2, _situacao);
			ResultSet result = preparador.executeQuery();
			
			
			while (result.next()) {
				
				empresta.setCodmatricula(result.getInt(1));
				empresta.setCodlivro(result.getInt(2));
				String _dtRetirada[] = result.getString(3).split("-");
				empresta.setDtretirada(_dtRetirada[2] + "/" + _dtRetirada[1] + "/" + _dtRetirada[0]);
				String _dtPrevisao[] = result.getString(4).split("-");
				empresta.setDtprevisao(_dtPrevisao[2] + "/" + _dtPrevisao[1] + "/" + _dtPrevisao[0]);
				try {
					String _dtEntrega[] = result.getString(5).split("-");
					empresta.setDtentrega(_dtEntrega[2] + "/" + _dtEntrega[1] + "/" + _dtEntrega[0]);
				} catch (Exception e) {
					empresta.setDtentrega(result.getString(5));
				}
								
			}
			preparador.close();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return empresta;
		
	}
	
	public List<Empresta> buscarEmprestimos (String dataini, String datafim) {
		
		String condition = "";
		int typeFiltro = 0;
		
		if (!dataini.equals("") && !datafim.equals("")) {
			condition = " WHERE dtretirada BETWEEN ?::date AND ?::date";
			typeFiltro = 1;
		} else if (!dataini.equals("") && datafim.equals("")) {
			condition = " WHERE dtretirada >= ?::date";
			typeFiltro = 2;
		} else if (dataini.equals("") && !datafim.equals("")) {
			condition = " WHERE dtretirada <= ?::date";
			typeFiltro = 3;
		}
		
		String sql = "Select * from empresta";
		List<Empresta> emprestimosLista = new ArrayList<Empresta>();
		try {
			Statement statement = con.createStatement();
			ResultSet result;
			if(typeFiltro == 0) {
				result = statement.executeQuery(sql);
			} else {
				PreparedStatement preparador = con.prepareStatement(sql+condition);
				if(typeFiltro == 1) {
					preparador.setString(1, dataini);
					preparador.setString(2, datafim);
				} else if(typeFiltro == 2) {
					preparador.setString(1, dataini);
				}else if(typeFiltro == 3) {
					preparador.setString(1, datafim);
				}
				result = preparador.executeQuery();
			}
			
			
			while (result.next()) {
				Empresta empresta = new Empresta();
			
				empresta.setCodmatricula(result.getInt(1));
				empresta.setCodlivro(result.getInt(2));
				String _dtRetirada[] = result.getString(3).split("-");
				empresta.setDtretirada(_dtRetirada[2] + "/" + _dtRetirada[1] + "/" + _dtRetirada[0]);
				String _dtPrevisao[] = result.getString(4).split("-");
				empresta.setDtprevisao(_dtPrevisao[2] + "/" + _dtPrevisao[1] + "/" + _dtPrevisao[0]);
				try {
					String _dtEntrega[] = result.getString(5).split("-");
					empresta.setDtentrega(_dtEntrega[2] + "/" + _dtEntrega[1] + "/" + _dtEntrega[0]);
				} catch (Exception e) {
					empresta.setDtentrega(result.getString(5));
				}
				empresta.setCodempresta(result.getInt(6));
				
				emprestimosLista.add(empresta);
				
			}
			statement.close();
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return emprestimosLista;
		
	}
	
	public Empresta buscarEmprestaPorCod (Integer codempresta) {
		
		String sql = "Select * from empresta where codEmpresta = ?";
		Empresta empresta = new Empresta();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, codempresta);
			ResultSet result = preparador.executeQuery();
			
			
			while (result.next()) {
				
				empresta.setCodmatricula(result.getInt(1));
				empresta.setCodlivro(result.getInt(2));
				String _dtRetirada[] = result.getString(3).split("-");
				empresta.setDtretirada(_dtRetirada[2] + "/" + _dtRetirada[1] + "/" + _dtRetirada[0]);
				String _dtPrevisao[] = result.getString(4).split("-");
				empresta.setDtprevisao(_dtPrevisao[2] + "/" + _dtPrevisao[1] + "/" + _dtPrevisao[0]);
				try {
					String _dtEntrega[] = result.getString(5).split("-");
					empresta.setDtentrega(_dtEntrega[2] + "/" + _dtEntrega[1] + "/" + _dtEntrega[0]);
				} catch (Exception e) {
					empresta.setDtentrega(result.getString(5));
				}
				empresta.setCodempresta(result.getInt(6));
								
			}
			preparador.close();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return empresta;
		
	}
	
public List<Empresta> buscarEmprestaPorAluno (Integer codmatricula) {
		List<Empresta> emprestimosLista = new ArrayList<Empresta>();
		String sql = "Select * from empresta where codmatricula = ?";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, codmatricula);
			ResultSet result = preparador.executeQuery();
			
			
			while (result.next()) {
				Empresta empresta = new Empresta();
				empresta.setCodmatricula(result.getInt(1));
				empresta.setCodlivro(result.getInt(2));
				String _dtRetirada[] = result.getString(3).split("-");
				empresta.setDtretirada(_dtRetirada[2] + "/" + _dtRetirada[1] + "/" + _dtRetirada[0]);
				String _dtPrevisao[] = result.getString(4).split("-");
				empresta.setDtprevisao(_dtPrevisao[2] + "/" + _dtPrevisao[1] + "/" + _dtPrevisao[0]);
				try {
					String _dtEntrega[] = result.getString(5).split("-");
					empresta.setDtentrega(_dtEntrega[2] + "/" + _dtEntrega[1] + "/" + _dtEntrega[0]);
				} catch (Exception e) {
					empresta.setDtentrega(result.getString(5));
				}
				empresta.setCodempresta(result.getInt(6));
				emprestimosLista.add(empresta);				
			}
			preparador.close();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return emprestimosLista;
		
	}

public List<Empresta> buscarEmprestaPorLivro (Integer codlivro) {
	List<Empresta> emprestimosLista = new ArrayList<Empresta>();
	String sql = "Select * from empresta where codlivro = ? ORDER BY dtretirada ASC";
	
	try {
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setInt(1, codlivro);
		ResultSet result = preparador.executeQuery();
		
		
		while (result.next()) {
			Empresta empresta = new Empresta();
			empresta.setCodmatricula(result.getInt(1));
			empresta.setCodlivro(result.getInt(2));
			String _dtRetirada[] = result.getString(3).split("-");
			empresta.setDtretirada(_dtRetirada[2] + "/" + _dtRetirada[1] + "/" + _dtRetirada[0]);
			String _dtPrevisao[] = result.getString(4).split("-");
			empresta.setDtprevisao(_dtPrevisao[2] + "/" + _dtPrevisao[1] + "/" + _dtPrevisao[0]);
			try {
				String _dtEntrega[] = result.getString(5).split("-");
				empresta.setDtentrega(_dtEntrega[2] + "/" + _dtEntrega[1] + "/" + _dtEntrega[0]);
			} catch (Exception e) {
				empresta.setDtentrega(result.getString(5));
			}
			empresta.setCodempresta(result.getInt(6));
			emprestimosLista.add(empresta);				
		}
		preparador.close();
		
		
	} catch (SQLException e) {
		
		e.printStackTrace();
		
	}
	return emprestimosLista;
	
}
	
	
	public void vefificarAluno(int codmatricula) {
		String sql = "SELECT COUNT(*) AS totalemprestimos FROM empresta WHERE dtentrega IS NULL AND codmatricula = ?";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, codmatricula);
			ResultSet result = preparador.executeQuery();
		
			while (result.next()) {
				if (result.getInt(1) == 0) {
					String sqlAluno = "UPDATE alunos SET situacao = 'Ativo' WHERE codmatricula = ?";
					try {
						PreparedStatement preparador2 = con.prepareStatement(sqlAluno);
						preparador2.setInt(1, codmatricula);
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
	
	public int countEmprestimos (int alunoAtivo) {
		String sql = "SELECT COUNT(e.*) AS total FROM empresta e INNER JOIN alunos a ON a.codmatricula = e.codmatricula WHERE dtentrega IS NULL AND a.situacao = ?;";
		int resultado = 0;
		
		try {
			Statement statement = con.createStatement();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, (alunoAtivo == 1) ? "Ativo" : "Inativo");
			ResultSet result = preparador.executeQuery();
			
			while (result.next()) {
				resultado = result.getInt(1);
			}
			statement.close();
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return resultado;
		
	}
}
