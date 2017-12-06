package br.com.fadergs.biblioteca.testes;


import java.text.ParseException;

import br.com.fadergs.biblioteca.dao.EmprestaDAO;
import br.com.fadergs.biblioteca.entidades.Empresta;


public class TestDevolverLivro {

	public static void main(String[] args) throws ParseException {
		
		EmprestaDAO emprestaDAO = new EmprestaDAO();
		Empresta empresta = new Empresta();//emprestaDAO.buscarEmprestaPorCod(5);
		
		empresta.setCodmatricula(300);
		empresta.setCodlivro(4);
		//empresta.setDtretirada("26/10/2017");
		
		//System.out.println(empresta.getCodempresta());
		
		//EmprestaDAO emprestaDAO = new EmprestaDAO();
		//emprestaDAO.devolverLivro(empresta);
		//emprestaDAO.prorrogarEmprestimo(empresta);
		emprestaDAO.cadastrar(empresta);

	}

}
