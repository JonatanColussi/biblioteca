package br.com.fadergs.categoria.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fadergs.categoria.dao.CategoriaDAO;
import br.com.fadergs.categoria.entidades.Categoria;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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


		boolean resultado = false;

		if(cat.getCodcategoria() != null){
			resultado = catDAO.cadastrar(cat);
	
		}else{
			resultado = catDAO.editar(cat);
		}

		String resposta = (resultado) ? "true" : "false";

		response.sendRedirect("/listaCategorias.jsp?success"+resposta);
	}

}
