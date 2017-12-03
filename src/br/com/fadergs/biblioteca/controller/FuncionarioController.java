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
import br.com.fadergs.biblioteca.dao.FuncionarioDAO;
import br.com.fadergs.biblioteca.entidades.Biblioteca;
import br.com.fadergs.biblioteca.entidades.Funcionario;

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
		String method = request.getParameter("method");
		
		if (method.equals("listar")) {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			List<Funcionario> funcionariosLista = funcionarioDAO.buscarTodos();
			
			request.setAttribute("funcionarios", funcionariosLista);
			
			RequestDispatcher saida = request.getRequestDispatcher("listaFuncionarios.jsp");
			saida.forward(request, response);
		} else if (method.equals("inserir")) {
			Funcionario funcionario = new Funcionario();
			BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
			List<Biblioteca> bibliotecas = bibliotecaDAO.buscarTodos();
			
			funcionario.setCodfunc(0);
			
			request.setAttribute("funcionario", funcionario);
			request.setAttribute("bibliotecas", bibliotecas);
			
			RequestDispatcher saida = request.getRequestDispatcher("cadastroFuncionario.jsp");
			saida.forward(request, response);
		} else if (method.equals("editar")) {
			try {
				int codfunc = Integer.parseInt(request.getParameter("id"));
				FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
				Funcionario funcionario = funcionarioDAO.buscarFuncionarioPorCod(codfunc);
				
				BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
				List<Biblioteca> bibliotecas = bibliotecaDAO.buscarTodos();
				
				request.setAttribute("funcionario", funcionario);
				request.setAttribute("bibliotecas", bibliotecas);
				
				RequestDispatcher saida = request.getRequestDispatcher("cadastroFuncionario.jsp");
				saida.forward(request, response);
			} catch(Exception e) {
				response.sendRedirect("FuncionarioController.do?method=listar");
			}
		} else if (method.startsWith("excluir")) {
			try {
				int codfunc = Integer.parseInt(request.getParameter("id"));
				FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
				Funcionario funcionario = funcionarioDAO.buscarFuncionarioPorCod(codfunc);
				
				funcionarioDAO.remover(funcionario);
				response.sendRedirect("FuncionarioController.do?method=listar");
			} catch(Exception e) {
				response.sendRedirect("FuncionarioController.do?method=listar");
			}
		}
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
		double salario = 0;
		try {
			salario = Double.parseDouble(request.getParameter("salario"));
		} catch(Exception e) {
			
		}
		
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
		func.setLogin(usuario);
		func.setSenha(senha);
		
		FuncionarioDAO funcDAO = new FuncionarioDAO();

		if(func.getCodfunc() == null){
			funcDAO.cadastrar(func);
		}else{
			funcDAO.editar(func);
		}

		response.sendRedirect("FuncionarioController.do?method=listar");
	}

}
