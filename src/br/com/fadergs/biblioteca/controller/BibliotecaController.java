package br.com.fadergs.biblioteca.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fadergs.biblioteca.dao.BibliotecaDAO;
import br.com.fadergs.biblioteca.entidades.Biblioteca;

/**
 * Servlet implementation class BibliotecaController
 */
@WebServlet("/BibliotecaController.do")
public class BibliotecaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BibliotecaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int codbib = Integer.parseInt(request.getParameter("codbib"));
		String nome  = request.getParameter("nome");
		String endereco = request.getParameter("endereco");

		Biblioteca bib = new Biblioteca();
		if(codbib > 0){
			bib.setCodbib(codbib);
		}

		bib.setNome(nome);
		bib.setEndereco(endereco);
		
		BibliotecaDAO bibDAO = new BibliotecaDAO();


		boolean resultado = false;

		if(bib.getCodbib() == null){
			resultado = bibDAO.cadastrar(bib);
	
		}else{
			resultado = bibDAO.editar(bib);
		}

		String resposta = (resultado) ? "true" : "false";

		response.sendRedirect("listaBibliotecas.jsp?success"+resposta);
	}

}
