package br.com.fadergs.biblioteca.testes;

import java.util.ArrayList;

import br.com.fadergs.biblioteca.dao.BibliotecaDAO;
import br.com.fadergs.biblioteca.entidades.Biblioteca;

public class TestBuscarTodasBibliotecas {
	
	public static void main(String[] args) {
		
		BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
		ArrayList<Biblioteca> bibliotecasLista = bibliotecaDAO.buscarTodos();
		
		for (int i = 0; i < bibliotecasLista.size(); i++) {
			System.out.println(bibliotecasLista.get(i).getNome());
		}
		
	}

}
