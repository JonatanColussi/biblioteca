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

				Livro livro = livroDAO.buscarLivroPorCod(emprestimosLista.get(0).getCodlivro());
			%>
			<h1>Relatório de empréstimos por livro: <%= livro.getTitulo() %></h1>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<table class="table table-striped table-bordered table-hover not-datatables">
				<thead>
					<tr>
						<th>Aluno</th>
						<th>Ação</th>
						<th>Data</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (Empresta emp: emprestimosLista) {
							Aluno aluno = alunoDAO.buscarAlunoPorMatricula(emp.getCodmatricula());
							%>
								<tr>
									<td><%= aluno.getNome() %></td>
									<td>Retirada</td>
									<td><%= emp.getDtretirada() %></td>
								</tr>
								<% if(emp.getDtentrega() != null){ %>
								<tr>
									<td><%= aluno.getNome() %></td>
									<td>Devolução</td>
									<td><%= emp.getDtentrega() %></td>
								</tr>
								<% } %>
							<% } %>
				</tbody>
			</table>
		</div>
	</div>
</main>
<%@include file="includes/footer.jsp" %>