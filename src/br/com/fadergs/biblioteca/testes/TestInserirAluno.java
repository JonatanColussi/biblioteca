package br.com.fadergs.biblioteca.testes;

import br.com.fadergs.biblioteca.entidades.Aluno;
import br.com.fadergs.biblioteca.dao.AlunoDAO;

public class TestInserirAluno {
	
	public static void main(String[] args) {
		
		Aluno aluno = new Aluno();
		
		aluno.setCodmatricula(552);
		aluno.setNome("Peter Park");
		aluno.setEndereco("Queens");
		aluno.setSituacao("Ativo");
		
		AlunoDAO alunoDAO = new AlunoDAO();
		alunoDAO.cadastrar(aluno);
		
	}

}
