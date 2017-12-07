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
import br.com.fadergs.biblioteca.dao.EmprestaDAO;
import br.com.fadergs.biblioteca.entidades.Aluno;
import br.com.fadergs.biblioteca.entidades.Empresta;

/**
 * Servlet implementation class AlunoController
 */
@WebServlet("/AlunoController.do")
public class AlunoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlunoController() {
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
			AlunoDAO alunoDAO = new AlunoDAO();
			List<Aluno> alunosLista = alunoDAO.buscarTodos();
			
			request.setAttribute("alunos", alunosLista);
			
			RequestDispatcher saida = request.getRequestDispatcher("listaAlunos.jsp");
			saida.forward(request, response);
		} else if (method.equals("inserir")) {
			Aluno aluno = new Aluno();
			
			//aluno.setCodmatricula(0);
			
			request.setAttribute("aluno", aluno);
			
			RequestDispatcher saida = request.getRequestDispatcher("cadastroAluno.jsp");
			saida.forward(request, response);
		} else if (method.equals("editar")) {
			try {
				int codmatricula = Integer.parseInt(request.getParameter("id"));
				AlunoDAO alunoDAO = new AlunoDAO();
				Aluno aluno = alunoDAO.buscarAlunoPorMatricula(codmatricula);
				
				request.setAttribute("aluno", aluno);
				
				RequestDispatcher saida = request.getRequestDispatcher("cadastroAluno.jsp");
				saida.forward(request, response);
			} catch(Exception e) {
				response.sendRedirect("AlunoController.do?method=listar");
			}
		} else if (method.equals("relatorio")) {
			try {
				int codmatricula = Integer.parseInt(request.getParameter("id"));
				EmprestaDAO emprestaDAO = new EmprestaDAO();
				
				List<Empresta> emprestimos = emprestaDAO.buscarEmprestaPorAluno(codmatricula);
				
				request.setAttribute("emprestimos", emprestimos);
				
				RequestDispatcher saida = request.getRequestDispatcher("relatorioAlunos.jsp");
				saida.forward(request, response);
			} catch(Exception e) {
				response.sendRedirect("AlunoController.do?method=listar");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int codmatricula = Integer.parseInt(request.getParameter("codmatricula"));
		String nome = request.getParameter("nome");
		String endereco = request.getParameter("endereco");
		String situacao = request.getParameter("situacao");

		Aluno alu = new Aluno();
		AlunoDAO aluDAO = new AlunoDAO();
		
		alu.setCodmatricula(codmatricula);
		alu.setNome(nome);
		alu.setEndereco(endereco);
		alu.setSituacao(situacao);
		
		Aluno findAlu = aluDAO.buscarAlunoPorMatricula(codmatricula);

		if(findAlu.getCodmatricula() == null){
			aluDAO.cadastrar(alu);
		}else{
			aluDAO.editar(alu);
		}

		response.sendRedirect("AlunoController.do?method=listar");
	}

}
