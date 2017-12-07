package br.com.fadergs.biblioteca.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fadergs.biblioteca.dao.BibliotecaDAO;
import br.com.fadergs.biblioteca.dao.LivroDAO;
import br.com.fadergs.biblioteca.entidades.Biblioteca;
import br.com.fadergs.biblioteca.entidades.Livro;
import br.com.fadergs.biblioteca.dao.CategoriaDAO;
import br.com.fadergs.biblioteca.dao.EmprestaDAO;
import br.com.fadergs.biblioteca.entidades.Categoria;
import br.com.fadergs.biblioteca.entidades.Empresta;

/**
 * Servlet implementation class LivroController
 */
@WebServlet("/LivroController.do")
public class LivroController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LivroController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Valida login
		HttpSession session = request.getSession(false);  
		if(session.getAttribute("name") == null){ 
			RequestDispatcher saida = request.getRequestDispatcher("index.jsp");
			saida.forward(request, response);
		}
		
		String method = request.getParameter("method");
		
		if (method.equals("listar")) {
			LivroDAO livroDAO = new LivroDAO();
			List<Livro> livrosLista = livroDAO.buscarTodos();
			
			request.setAttribute("livros", livrosLista);
			
			RequestDispatcher saida = request.getRequestDispatcher("listaLivros.jsp");
			saida.forward(request, response);
		} else if (method.equals("inserir")) {
			Livro livro = new Livro();
			BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
			List<Biblioteca> bibliotecas = bibliotecaDAO.buscarTodos();
			
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			List<Categoria> categorias = categoriaDAO.buscarTodos();
			
			livro.setCodlivro(0);
			
			request.setAttribute("livro", livro);
			request.setAttribute("bibliotecas", bibliotecas);
			request.setAttribute("categorias", categorias);
			
			RequestDispatcher saida = request.getRequestDispatcher("cadastroLivro.jsp");
			saida.forward(request, response);
		} else if (method.equals("editar")) {
			try {
				int codlivro = Integer.parseInt(request.getParameter("id"));
				LivroDAO livroDAO = new LivroDAO();
				Livro livro = livroDAO.buscarLivroPorCod(codlivro);
				
				BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
				List<Biblioteca> bibliotecas = bibliotecaDAO.buscarTodos();
				
				CategoriaDAO categoriaDAO = new CategoriaDAO();
				List<Categoria> categorias = categoriaDAO.buscarTodos();
				
				request.setAttribute("livro", livro);
				request.setAttribute("bibliotecas", bibliotecas);
				request.setAttribute("categorias", categorias);
				
				RequestDispatcher saida = request.getRequestDispatcher("cadastroLivro.jsp");
				saida.forward(request, response);
			} catch(Exception e) {
				response.sendRedirect("LivroController.do?method=listar");
			}
		 }else if (method.equals("relatorio")) {
			try {
				int codlivro = Integer.parseInt(request.getParameter("id"));
				EmprestaDAO emprestaDAO = new EmprestaDAO();
				
				List<Empresta> emprestimos = emprestaDAO.buscarEmprestaPorLivro(codlivro);
				
				request.setAttribute("emprestimos", emprestimos);
				
				RequestDispatcher saida = request.getRequestDispatcher("relatorioLivros.jsp");
				saida.forward(request, response);
			} catch(Exception e) {
				response.sendRedirect("LivroController.do?method=listar");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int codliv = Integer.parseInt(request.getParameter("codliv"));
		int codbib = Integer.parseInt(request.getParameter("codbib"));
		int codcat = Integer.parseInt(request.getParameter("codcat"));
		String titulo = request.getParameter("titulo");
		String editora = request.getParameter("editora");
		double valor = 0; 
		
		try {		
			valor = Double.parseDouble(request.getParameter("valor"));
		} catch(Exception e) {
			
		}
		
		Livro liv = new Livro();
		if(codliv > 0){
			liv.setCodlivro(codliv);
		}

		liv.setCodbib(codbib);
		liv.setCodcategoria(codcat);
		liv.setTitulo(titulo);
		liv.setEditora(editora);
		liv.setValor(valor);
		
		LivroDAO livDAO = new LivroDAO();


		if(liv.getCodlivro() == null){
			livDAO.cadastrar(liv);
		}else{
			livDAO.editar(liv);
		}

		response.sendRedirect("LivroController.do?method=listar");
	}

}
