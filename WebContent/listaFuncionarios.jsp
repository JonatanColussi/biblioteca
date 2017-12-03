<%@include file="includes/header.jsp" %><%@ page import="java.util.List" %><%@ page import="br.com.fadergs.biblioteca.entidades.Funcionario" %><%@ page import="br.com.fadergs.biblioteca.entidades.Biblioteca" %><%@ page import="br.com.fadergs.biblioteca.dao.BibliotecaDAO" %><main role="main" class="container">	<div class="row">		<div class="col-md-12">			<h1>Funcion�rios</h1>			<a href="FuncionarioController.do?method=inserir" class="btn btn-primary mb_10">Cadastar Funcion�rio</a>		</div>	</div>	<div class="row">		<div class="col-md-12">			<table class="table table-striped table-bordered table-hover">				<thead>					<tr>						<th>Biblioteca</th>						<th>Nome</th>						<th>Usu�rio</th>						<th>A��es</th>					</tr>				</thead>				<tbody>					<%						List<Funcionario> funcionariosLista = (List<Funcionario>) request.getAttribute("funcionarios");						BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();						for (Funcionario func: funcionariosLista) {							Biblioteca biblioteca = bibliotecaDAO.buscarBibliotecaPorCod(func.getCodbib());							%>								<tr>									<td><%= biblioteca.getNome() %></td>									<td><%= func.getNome() %></td>									<td><%= func.getLogin() %></td>									<td>										<a href="FuncionarioController.do?method=editar&id=<%= func.getCodfunc() %>" class="btn btn-warning">Editar</a>										<a href="FuncionarioController.do?method=excluir&id=<%= func.getCodfunc() %>" class="btn btn-danger">Deletar</a>									</td>								</tr>							<%						}					%>				</tbody>			</table>		</div>	</div></main><%@include file="includes/footer.jsp" %>