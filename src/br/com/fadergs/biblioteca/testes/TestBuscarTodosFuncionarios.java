package br.com.fadergs.biblioteca.testes;

import java.util.List;

import br.com.fadergs.biblioteca.dao.FuncionarioDAO;
import br.com.fadergs.biblioteca.entidades.Funcionario;

public class TestBuscarTodosFuncionarios {
	
public static void main(String[] args) {
		
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		List<Funcionario> funcionariosLista = funcionarioDAO.buscarTodos();
		
		for (int i = 0; i < funcionariosLista.size(); i++) {
			System.out.println(funcionariosLista.get(i).getNome());
		}
		
		
	}

}
