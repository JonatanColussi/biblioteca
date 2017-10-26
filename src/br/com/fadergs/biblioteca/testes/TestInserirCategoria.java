package br.com.fadergs.biblioteca.testes;

import br.com.fadergs.biblioteca.entidades.Categoria;
import br.com.fadergs.biblioteca.dao.CategoriaDAO;

public class TestInserirCategoria {

	public static void main(String[] args) {
		
		Categoria categoria = new Categoria();
		
		categoria.setDescricao("Juvenil");
		
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		categoriaDAO.cadastrar(categoria);
		

	}

}
