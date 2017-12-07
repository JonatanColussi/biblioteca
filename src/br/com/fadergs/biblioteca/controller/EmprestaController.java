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

import br.com.fadergs.biblioteca.dao.AlunoDAO;
import br.com.fadergs.biblioteca.dao.LivroDAO;
import br.com.fadergs.biblioteca.entidades.Aluno;
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
		//Valida login
		HttpSession session = request.getSession(false);  
		if(session.getAttribute("name") == null){ 
			RequestDispatcher saida = request.getRequestDispatcher("index.jsp");
			saida.forward(request, response);
		}
		
		String method = request.getParameter("method");
		
		if (method.equals("listar")) {
			EmprestaDAO emprestaDAO = new EmprestaDAO();
			List<Empresta> emprestimosLista = emprestaDAO.buscarEmprestimos();
			
			request.setAttribute("emprestimos", emprestimosLista);
			
			RequestDispatcher saida = request.getRequestDispatcher("listaEmprestimos.jsp");
			saida.forward(request, response);
		} else if (method.equals("inserir")) {
			AlunoDAO alunoDAO = new AlunoDAO();
			LivroDAO livroDAO = new LivroDAO();
			
			List<Aluno> alunos = alunoDAO.buscarTodos();
			List<Livro> livros = livroDAO.buscarDisponiveis();
			
			int aluno = 0;
			int livro = 0;
			
			if (request.getParameter("livro") != null) {
				livro = Integer.parseInt(request.getParameter("livro"));
			}
			
			if (request.getParameter("aluno") != null) {
				aluno = Integer.parseInt(request.getParameter("aluno"));
			}
			
			request.setAttribute("livros", livros);
			request.setAttribute("alunos", alunos);
			request.setAttribute("codlivro", livro);
			request.setAttribute("codmatricula", aluno);
			
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
		}else if (method.startsWith("devolver")) {
			try {
				int codempresta = Integer.parseInt(request.getParameter("id"));
				EmprestaDAO emprestaDAO = new EmprestaDAO();
				Empresta empresta = emprestaDAO.buscarEmprestaPorCod(codempresta);
				
				emprestaDAO.devolverLivro(empresta);
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
		//int codemp = Integer.parseInt(request.getParameter("codemp"));
		int codalu = Integer.parseInt(request.getParameter("codalu"));
		int codliv = Integer.parseInt(request.getParameter("codliv"));
		

		Empresta emp = new Empresta();
		/*if(codemp > 0){
			emp.setCodempresta(codemp);
		}*/

		emp.setCodlivro(codliv);
		emp.setCodmatricula(codalu);
		
		EmprestaDAO empDAO = new EmprestaDAO();

		try {
			//if(emp.getCodempresta() == null){
				empDAO.cadastrar(emp);
			//}else{
			//	empDAO.editar(emp);
			//}
		} catch (Exception e) {
			
		}

		response.sendRedirect("EmprestaController.do?method=listar");
		
	}

}
