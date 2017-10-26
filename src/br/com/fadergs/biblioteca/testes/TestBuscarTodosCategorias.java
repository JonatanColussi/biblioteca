package br.com.fadergs.biblioteca.testes;

import java.util.ArrayList;

import br.com.fadergs.biblioteca.entidades.Categoria;
import br.com.fadergs.biblioteca.dao.CategoriaDAO;

public class TestBuscarTodosCategorias {

	public static void main(String[] args) {
		
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		ArrayList<Categoria> categoriasLista = categoriaDAO.buscarTodos();
		
		for (int i = 0; i < categoriasLista.size(); i++) {
			System.out.println(categoriasLista.get(i).getDescricao());
		}
		

	}

}
