package br.com.fadergs.biblioteca.testes;

import br.com.fadergs.biblioteca.dao.BibliotecaDAO;
import br.com.fadergs.biblioteca.entidades.Biblioteca;

public class TestIncluirBiblioteca {

	public static void main(String[] args) {
		
		Biblioteca biblioteca = new Biblioteca();
		
		biblioteca.setNome("Biblioteca Central");
		biblioteca.setEndereco("Virando a quadra");
		
		BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
		bibliotecaDAO.cadastrar(biblioteca);
		

	}

}
