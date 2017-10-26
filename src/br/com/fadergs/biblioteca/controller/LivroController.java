package br.com.fadergs.livro.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fadergs.livro.dao.LivroDAO;
import br.com.fadergs.livro.entidades.Livro;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		String valor = request.getParameter("valor");

		Livro liv = new Livro();
		if(codliv > 0){
			liv.setCodliv(codliv);
		}

		liv.setCodbib(codbib);
		liv.setCodcar(codcat);
		liv.setTitulo(titulo);
		liv.setEditora(editora);
		liv.setValor(valor);
		
		LivroDAO livDAO = new LivroDAO();


		boolean resultado = false;

		if(liv.getCodliv() != null){
			resultado = livDAO.cadastrar(liv);
	
		}else{
			resultado = livDAO.editar(liv);
		}

		String resposta = (resultado) ? "true" : "false";

		response.sendRedirect("/listaLivros.jsp?success"+resposta);
	}

}
