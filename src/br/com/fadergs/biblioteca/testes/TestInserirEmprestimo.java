package br.com.fadergs.biblioteca.testes;

import br.com.fadergs.biblioteca.entidades.Empresta;

import java.text.ParseException;

import br.com.fadergs.biblioteca.dao.EmprestaDAO;

public class TestInserirEmprestimo {

	public static void main(String[] args) throws ParseException {
		
		Empresta empresta = new Empresta();
		
		empresta.setCodmatricula(320);
		empresta.setCodlivro(1);
		empresta.setDtretirada("26/10/2017");
		
		EmprestaDAO emprestaDAO = new EmprestaDAO();
		emprestaDAO.cadastrar(empresta);
		

	}

}
