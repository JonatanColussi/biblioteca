package br.com.fadergs.biblioteca.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
		String method = request.getParameter("method");
		
		if (method.equals("listar")) {
			BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
			List<Biblioteca> bibliotecasLista = bibliotecaDAO.buscarTodos();
			
			request.setAttribute("bibliotecas", bibliotecasLista);
			
			RequestDispatcher saida = request.getRequestDispatcher("listaBibliotecas.jsp");
			saida.forward(request, response);
		} else if (method.equals("inserir")) {
			Biblioteca biblioteca = new Biblioteca();
			
			biblioteca.setCodbib(0);
			
			request.setAttribute("biblioteca", biblioteca);
			
			RequestDispatcher saida = request.getRequestDispatcher("cadastroBiblioteca.jsp");
			saida.forward(request, response);
		} else if (method.equals("editar")) {
			try {
				int codbib = Integer.parseInt(request.getParameter("id"));
				BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
				Biblioteca biblioteca = bibliotecaDAO.buscarBibliotecaPorCod(codbib);
				
				request.setAttribute("biblioteca", biblioteca);
				
				RequestDispatcher saida = request.getRequestDispatcher("cadastroBiblioteca.jsp");
				saida.forward(request, response);
			} catch(Exception e) {
				response.sendRedirect("BibliotecaController.do?method=listar");
			}
		} else if (method.startsWith("excluir")) {
			try {
				int codbib = Integer.parseInt(request.getParameter("id"));
				BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
				Biblioteca biblioteca = bibliotecaDAO.buscarBibliotecaPorCod(codbib);
				
				bibliotecaDAO.remover(biblioteca);
				response.sendRedirect("BibliotecaController.do?method=listar");
			} catch(Exception e) {
				response.sendRedirect("BibliotecaController.do?method=listar");
			}
		}
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


		if(bib.getCodbib() == null){
			bibDAO.cadastrar(bib);
		}else{
			bibDAO.editar(bib);
		}

		response.sendRedirect("BibliotecaController.do?method=listar");
	}

}
