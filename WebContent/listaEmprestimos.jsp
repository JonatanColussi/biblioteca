<%@include file="includes/header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.fadergs.biblioteca.entidades.Empresta" %>
<%@ page import="br.com.fadergs.biblioteca.entidades.Aluno" %>
<%@ page import="br.com.fadergs.biblioteca.entidades.Livro" %>
<%@ page import="br.com.fadergs.biblioteca.dao.AlunoDAO" %>
<%@ page import="br.com.fadergs.biblioteca.dao.LivroDAO" %>
<main role="main" class="container">
	<div class="row">
		<div class="col-md-12">
			<h1>Reservas</h1>
			<a href="EmprestaController.do?method=inserir" class="btn btn-primary mb_10">Cadastrar Reserva</a>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>Aluno</th>
						<th>Livro</th>
						<th>Data Reserva</th>
						<th>Data Prevista</th>
						<th>Ações</th>
					</tr>
				</thead>
				<tbody>
					<%
						List<Empresta> emprestimosLista = (List<Empresta>) request.getAttribute("emprestimos");
						AlunoDAO alunoDAO = new AlunoDAO();
						LivroDAO livroDAO = new LivroDAO();
						for (Empresta emp: emprestimosLista) {
							Aluno aluno = alunoDAO.buscarAlunoPorMatricula(emp.getCodmatricula());
							Livro livro = livroDAO.buscarLivroPorCod(emp.getCodlivro());
							%>
								<tr>
									<td><%= aluno.getNome() %></td>
									<td><%= livro.getTitulo() %></td>
									<td><%= emp.getDtretirada() %></td>
									<td><%= emp.getDtprevisao() %></td>
									<td>
										<a href="EmprestaController.do?method=renovar&id=<%= emp.getCodempresta() %>" class="btn btn-warning">Renovar</a>
										<a href="EmprestaController.do?method=devolver&id=<%= emp.getCodlivro() %>" class="btn btn-danger">Devolver</a>
									</td>
								</tr>
							<%
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
</main>
<%@include file="includes/footer.jsp" %>