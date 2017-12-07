<%@include file="includes/header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.fadergs.biblioteca.entidades.Aluno" %>
<main role="main" class="container">
	<div class="row">
		<div class="col-md-12">
			<h1>Alunos</h1>
			<a href="AlunoController.do?method=inserir" class="btn btn-primary mb_10">Cadastrar Aluno</a>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>Matricula</th>
						<th>Nome</th>
						<th>Endereço</th>
						<th>Situação</th>
						<th>Ações</th>
					</tr>
				</thead>
				<tbody>
				<%
						List<Aluno> alunosLista = (List<Aluno>) request.getAttribute("alunos");
						for (Aluno alu: alunosLista) {
							%>
								<tr>
									<td><%= alu.getCodmatricula() %></td>
									<td><%= alu.getNome() %></td>
									<td><%= alu.getEndereco() %></td>
									<td>
										<%if (alu.getSituacao().equals("Inativo")) { %>
											<span class="text-danger"><%= alu.getSituacao() %></span>
										<% } else { %>
											<span class="text-success"><%= alu.getSituacao() %></span>
										<% } %>
									</td>
									<td>
										<a href="AlunoController.do?method=editar&id=<%= alu.getCodmatricula() %>" class="btn btn-warning">Editar</a>
										<%if (alu.getSituacao().equals("Ativo")) { %>
											<a href="EmprestaController.do?method=inserir&aluno=<%= alu.getCodmatricula() %>" class="btn btn-success">Reservar livro</a>
										<% } %>
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