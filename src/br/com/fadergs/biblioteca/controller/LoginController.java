package br.com.fadergs.biblioteca.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fadergs.biblioteca.dao.FuncionarioDAO;
import br.com.fadergs.biblioteca.entidades.Funcionario;

/**
 * Servlet implementation class FuncionarioController
 */
@WebServlet("/LoginController.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");

		Funcionario func = new Funcionario();
		
		func.setLogin(usuario);
		func.setSenha(senha);
		
		FuncionarioDAO funcDAO = new FuncionarioDAO();
		
		Funcionario funcLogin = funcDAO.login(func);
		if(funcLogin.getCodfunc() != null){
			HttpSession session = request.getSession();  
	        session.setAttribute("name", funcLogin.getNome());
	        session.setAttribute("id", funcLogin.getCodfunc());
	        System.out.print(session.getAttribute("name"));
	        System.out.print(funcLogin.getNome());
	        
			response.sendRedirect("EmprestaController.do?method=listar");
		}else{
			response.sendRedirect("index.jsp");
		}
	}

}
