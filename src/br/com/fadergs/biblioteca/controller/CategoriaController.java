package br.com.fadergs.biblioteca.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fadergs.biblioteca.dao.CategoriaDAO;
import br.com.fadergs.biblioteca.entidades.Categoria;

/**
 * Servlet implementation class CategoriaController
 */
@WebServlet("/CategoriaController.do")
public class CategoriaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoriaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		
		if (method.equals("listar")) {
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			List<Categoria> categoriasLista = categoriaDAO.buscarTodos();
			
			request.setAttribute("categorias", categoriasLista);
			
			RequestDispatcher saida = request.getRequestDispatcher("listaCategorias.jsp");
			saida.forward(request, response);
		} else if (method.equals("inserir")) {
			Categoria categoria = new Categoria();
			
			categoria.setCodcategoria(0);
			
			request.setAttribute("categoria", categoria);
			
			RequestDispatcher saida = request.getRequestDispatcher("cadastroCategoria.jsp");
			saida.forward(request, response);
		} else if (method.equals("editar")) {
			try {
				int codcategoria = Integer.parseInt(request.getParameter("id"));
				CategoriaDAO categoriaDAO = new CategoriaDAO();
				Categoria categoria = categoriaDAO.buscarCategoriaPorCod(codcategoria);
				
				request.setAttribute("categoria", categoria);
				
				RequestDispatcher saida = request.getRequestDispatcher("cadastroCategoria.jsp");
				saida.forward(request, response);
			} catch(Exception e) {
				response.sendRedirect("CategoriaController.do?method=listar");
			}
		} else if (method.startsWith("excluir")) {
			try {
				int codcategoria = Integer.parseInt(request.getParameter("id"));
				CategoriaDAO categoriaDAO = new CategoriaDAO();
				Categoria categoria = categoriaDAO.buscarCategoriaPorCod(codcategoria);
				
				categoriaDAO.remover(categoria);
				response.sendRedirect("CategoriaController.do?method=listar");
			} catch(Exception e) {
				response.sendRedirect("CategoriaController.do?method=listar");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int codcategoria = Integer.parseInt(request.getParameter("codcategoria"));
		String descricao = request.getParameter("descricao");

		Categoria cat = new Categoria();
		if(codcategoria > 0){
			cat.setCodcategoria(codcategoria);
		}

		cat.setDescricao(descricao);
		
		CategoriaDAO catDAO = new CategoriaDAO();


		if(cat.getCodcategoria() == null){
			catDAO.cadastrar(cat);
		}else{
			catDAO.editar(cat);
		}

		response.sendRedirect("CategoriaController.do?method=listar");
	}

}
