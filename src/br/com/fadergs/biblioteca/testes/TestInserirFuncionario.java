package br.com.fadergs.biblioteca.testes;

import br.com.fadergs.biblioteca.entidades.Funcionario;
import br.com.fadergs.biblioteca.dao.FuncionarioDAO;

public class TestInserirFuncionario {
	
	public static void main(String[] args) {
		
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome("Clark Kent");
		funcionario.setEndereco("Smallville");
		funcionario.setTelefone("4444.3333");
		funcionario.setSalario(100.50);
		funcionario.setCodbib(1);
		funcionario.setLogin("SuperLogin");
		funcionario.setSenha("SuperSenha");
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.cadastrar(funcionario);
		
	}

}
