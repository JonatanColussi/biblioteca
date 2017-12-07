package br.com.fadergs.biblioteca.testes;

import java.util.List;

import br.com.fadergs.biblioteca.dao.AlunoDAO;
import br.com.fadergs.biblioteca.entidades.Aluno;

public class TestBuscarTodosAlunos {
	
	public static void main(String[] args) {
		
		AlunoDAO alunoDAO = new AlunoDAO();
		List<Aluno> alunosLista = alunoDAO.buscarTodos();
		
		for (int i = 0; i < alunosLista.size(); i++) {
			System.out.println(alunosLista.get(i).getNome());
		}
		
	}

}
