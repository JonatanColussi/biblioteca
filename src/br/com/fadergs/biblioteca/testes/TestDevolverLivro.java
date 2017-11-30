package br.com.fadergs.biblioteca.testes;


import java.text.ParseException;

import br.com.fadergs.biblioteca.dao.EmprestaDAO;
import br.com.fadergs.biblioteca.entidades.Empresta;


public class TestDevolverLivro {

	public static void main(String[] args) throws ParseException {
		
Empresta empresta = new Empresta();
		
		empresta.setCodmatricula(320);
		empresta.setCodlivro(1);
		empresta.setDtretirada("26/10/2017");
		
		EmprestaDAO emprestaDAO = new EmprestaDAO();
		//emprestaDAO.devolverLivro(empresta);
		emprestaDAO.prorrogarEmprestimo(empresta);

	}

}
