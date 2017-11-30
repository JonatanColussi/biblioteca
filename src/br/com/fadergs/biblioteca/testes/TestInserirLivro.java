package br.com.fadergs.biblioteca.testes;

import br.com.fadergs.biblioteca.dao.LivroDAO;
import br.com.fadergs.biblioteca.entidades.Livro;

public class TestInserirLivro {
	
	public static void main(String[] args) {
		
		Livro livro = new Livro();
		
		livro.setTitulo("Os Homens que não Amavam as Mulheres");
		livro.setEditora("Aleph");
		livro.setValor(40.5);
		livro.setCodcategoria(35);
		livro.setCodbib(1);
		livro.setSituacao("Disponivel");
		
		LivroDAO livroDAO = new LivroDAO();
		livroDAO.cadastrar(livro);
		
	}

}
