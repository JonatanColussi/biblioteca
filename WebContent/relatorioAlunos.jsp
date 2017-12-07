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
		<%
			List<Empresta> emprestimosLista = (List<Empresta>) request.getAttribute("emprestimos");
			AlunoDAO alunoDAO = new AlunoDAO();
			LivroDAO livroDAO = new LivroDAO();
			Aluno aluno = alunoDAO.buscarAlunoPorMatricula(emprestimosLista.get(0).getCodmatricula());
		%>
			<h1>Relatório de empréstimos por aluno: <%= aluno.getNome() %></h1>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>Livro</th>
						<th>Data Reserva</th>
						<th>Data Prevista</th>
						<th>Data Entrega</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (Empresta emp: emprestimosLista) {
							Livro livro = livroDAO.buscarLivroPorCod(emp.getCodlivro());
							%>
								<tr>
									<td><%= livro.getTitulo() %></td>
									<td><%= emp.getDtretirada() %></td>
									<td><%= emp.getDtprevisao() %></td>
									<td><%= (emp.getDtentrega() != null) ? emp.getDtentrega() : "n/a" %></td>
								</tr>
							<% } %>
				</tbody>
			</table>
		</div>
	</div>
</main>
<%@include file="includes/footer.jsp" %>