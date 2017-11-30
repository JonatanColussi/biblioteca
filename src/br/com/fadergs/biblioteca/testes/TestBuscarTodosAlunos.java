package br.com.fadergs.biblioteca.testes;

import java.util.ArrayList;

import br.com.fadergs.biblioteca.dao.AlunoDAO;
import br.com.fadergs.biblioteca.entidades.Aluno;

public class TestBuscarTodosAlunos {
	
	public static void main(String[] args) {
		
		AlunoDAO alunoDAO = new AlunoDAO();
		ArrayList<Aluno> alunosLista = alunoDAO.buscarTodos();
		
		for (int i = 0; i < alunosLista.size(); i++) {
			System.out.println(alunosLista.get(i).getNome());
		}
		
	}

}
