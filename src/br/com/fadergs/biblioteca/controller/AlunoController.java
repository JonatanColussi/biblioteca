package br.com.fadergs.aluno.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fadergs.aluno.dao.AlunoDAO;
import br.com.fadergs.aluno.entidades.Aluno;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		if(codmatricula > 0){
			alu.setCodmatricula(codmatricula);
		}

		alu.setNome(nome);
		alu.setEndereco(endereco);
		alu.setSituacao(situacao);
		
		AlunoDAO aluDAO = new AlunoDAO();


		boolean resultado = false;

		if(alu.getCodmatricula() != null){
			resultado = aluDAO.cadastrar(alu);
	
		}else{
			resultado = aluDAO.editar(alu);
		}

		String resposta = (resultado) ? "true" : "false";

		response.sendRedirect("/listaAlunos.jsp?success"+resposta);
	}

}
