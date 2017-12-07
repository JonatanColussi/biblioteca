<%@include file="includes/header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.fadergs.biblioteca.entidades.Biblioteca" %>
<main role="main" class="container">
	<div class="row">
		<div class="col-md-12">
			<h1>Bibliotecas</h1>
			<a href="BibliotecaController.do?method=inserir" class="btn btn-primary mb_10">Cadastrar Biblioteca</a>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>Código</th>
						<th>Nome</th>
						<th>Endereço</th>
						<th>Ações</th>
					</tr>
				</thead>
				<tbody>
					<%
						List<Biblioteca> bibliotecasLista = (List<Biblioteca>) request.getAttribute("bibliotecas");
						for (Biblioteca bib: bibliotecasLista) {
							%>
								<tr>
									<td><%= bib.getCodbib() %></td>
									<td><%= bib.getNome() %></td>
									<td><%= bib.getEndereco() %></td>

									<td>
										<a href="BibliotecaController.do?method=editar&id=<%= bib.getCodbib() %>" class="btn btn-warning">Editar</a>
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