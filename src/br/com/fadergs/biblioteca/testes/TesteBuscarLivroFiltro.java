package br.com.fadergs.biblioteca.testes;

import java.util.ArrayList;

import br.com.fadergs.biblioteca.dao.LivroDAO;
import br.com.fadergs.biblioteca.entidades.Livro;

public class TesteBuscarLivroFiltro {

	public static void main(String[] args) {
		
		LivroDAO livroDAO = new LivroDAO();
		ArrayList<Livro> livrosLista = livroDAO.buscarTodos();
		
		for (int i = 0; i < livrosLista.size(); i++) {
			System.out.println(livrosLista.get(i).getTitulo());
		}
		
		

	}

}
