<%@include file="includes/header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.fadergs.biblioteca.entidades.Categoria" %>
<main role="main" class="container">
	<div class="row">
		<div class="col-md-12">
			<h1>Categorias</h1>
			<a href="CategoriaController.do?method=inserir" class="btn btn-primary mb_10">Cadastrar Categoria</a>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>Código</th>
						<th>Descrição</th>
						<th>Ações</th>
					</tr>
				</thead>
				<tbody>
					<%
						List<Categoria> categoriasLista = (List<Categoria>) request.getAttribute("categorias");
						for (Categoria cat: categoriasLista) {
							%>
								<tr>
									<td><%= cat.getCodcategoria() %></td>
									<td><%= cat.getDescricao() %></td>

									<td>
										<a href="CategoriaController.do?method=editar&id=<%= cat.getCodcategoria() %>" class="btn btn-warning">Editar</a>
										<a href="CategoriaController.do?method=excluir&id=<%= cat.getCodcategoria() %>" class="btn btn-danger">Deletar</a>
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