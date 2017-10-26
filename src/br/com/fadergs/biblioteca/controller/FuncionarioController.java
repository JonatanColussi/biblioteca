package br.com.fadergs.Funcionario.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fadergs.Funcionario.dao.FuncionarioDAO;
import br.com.fadergs.Funcionario.entidades.Funcionario;

/**
 * Servlet implementation class FuncionarioController
 */
@WebServlet("/FuncionarioController.do")
public class FuncionarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FuncionarioController() {
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
		int codfunc = Integer.parseInt(request.getParameter("codfunc"));
		int codbib = Integer.parseInt(request.getParameter("codbib"));
		String nome = request.getParameter("nome");
		String endereco = request.getParameter("endereco");
		String telefone = request.getParameter("telefone");
		String salario = request.getParameter("salario");
		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");

		Funcionario func = new Funcionario();
		if(codfunc > 0){
			func.setCodfunc(codfunc);
		}

		func.setCodbib(codbib);
		func.setNome(nome);
		func.setEndereco(endereco);
		func.setTelefone(telefone);
		func.setSalario(salario);
		func.setUsuario(usuario);
		func.setSenha(senha);
		
		FuncionarioDAO funcDAO = new FuncionarioDAO();


		boolean resultado = false;

		if(func.getCodfunc() != null){
			resultado = funcDAO.cadastrar(func);
	
		}else{
			resultado = funcDAO.editar(func);
		}

		String resposta = (resultado) ? "true" : "false";

		response.sendRedirect("/listaFuncionarios.jsp?success"+resposta);
	}

}
