package br.com.fadergs.biblioteca.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fadergs.biblioteca.dao.AlunoDAO;
import br.com.fadergs.biblioteca.dao.BibliotecaDAO;
import br.com.fadergs.biblioteca.dao.FuncionarioDAO;
import br.com.fadergs.biblioteca.dao.LivroDAO;
import br.com.fadergs.biblioteca.entidades.Aluno;
import br.com.fadergs.biblioteca.entidades.Biblioteca;
import br.com.fadergs.biblioteca.entidades.Funcionario;
import br.com.fadergs.biblioteca.entidades.Livro;
import br.com.fadergs.biblioteca.dao.EmprestaDAO;
import br.com.fadergs.biblioteca.entidades.Empresta;

/**
 * Servlet implementation class EmprestaController
 */
@WebServlet("/EmprestaController.do")
public class EmprestaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmprestaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		
		if (method.equals("listar")) {
			EmprestaDAO emprestaDAO = new EmprestaDAO();
			List<Empresta> emprestimosLista = emprestaDAO.buscarEmprestimos();
			
			request.setAttribute("emprestimos", emprestimosLista);
			
			RequestDispatcher saida = request.getRequestDispatcher("listaEmprestimos.jsp");
			saida.forward(request, response);
		} else if (method.equals("inserir")) {
			Empresta empresta = new Empresta();
			AlunoDAO alunoDAO = new AlunoDAO();
			LivroDAO livroDAO = new LivroDAO();
			
			List<Aluno> alunos = alunoDAO.buscarTodos();
			List<Livro> livros = livroDAO.buscarDisponiveis();
			
			empresta.setCodlivro(0);
			empresta.setCodmatricula(0);
			
			request.setAttribute("empresta", empresta);
			request.setAttribute("livros", livros);
			request.setAttribute("alunos", alunos);
			
			
			RequestDispatcher saida = request.getRequestDispatcher("cadastroEmprestimo.jsp");
			saida.forward(request, response);
		} else if (method.startsWith("renovar")) {
			try {
				int codempresta = Integer.parseInt(request.getParameter("id"));
				EmprestaDAO emprestaDAO = new EmprestaDAO();
				Empresta empresta = emprestaDAO.buscarEmprestaPorCod(codempresta);
				
				emprestaDAO.prorrogarEmprestimo(empresta);
				response.sendRedirect("EmprestaController.do?method=listar");
			} catch(Exception e) {
				response.sendRedirect("EmprestaController.do?method=listar");
			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}