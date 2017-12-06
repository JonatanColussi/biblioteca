<%@include file="includes/header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.fadergs.biblioteca.entidades.Livro" %>
<%@ page import="br.com.fadergs.biblioteca.entidades.Biblioteca" %>
<%@ page import="br.com.fadergs.biblioteca.dao.BibliotecaDAO" %>
<%@ page import="br.com.fadergs.biblioteca.entidades.Categoria" %>
<%@ page import="br.com.fadergs.biblioteca.dao.CategoriaDAO" %>
<%@ page import="br.com.fadergs.biblioteca.entidades.Empresta" %>
<%@ page import="br.com.fadergs.biblioteca.dao.EmprestaDAO" %>
<main role="main" class="container">
	<div class="row">
		<div class="col-md-12">
			<h1>Livros</h1>
			<a href="LivroController.do?method=inserir" class="btn btn-primary mb_10">Cadastrar Livro</a>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>Biblioteca</th>
						<th>Categoria</th>
						<th>Título</th>
						<th>Editora</th>
						<th>Valor</th>
						<th>Situação</th>
						<th>Reserva</th>
						<th>Ações</th>
					</tr>
				</thead>
				<tbody>
					<%
						List<Livro> livrosLista = (List<Livro>) request.getAttribute("livros");
						BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();
						CategoriaDAO categoriaDAO = new CategoriaDAO();
						EmprestaDAO emprestaDAO = new EmprestaDAO();
						for (Livro liv: livrosLista) {
							Biblioteca biblioteca = bibliotecaDAO.buscarBibliotecaPorCod(liv.getCodbib());
							Categoria categoria = categoriaDAO.buscarCategoriaPorCod(liv.getCodcategoria());
							Empresta empresta = emprestaDAO.buscarEmprestaPorCodLivro(liv.getCodlivro(), "Indisponivel");
							%>
								<tr>
									<td><%= biblioteca.getNome() %></td>
									<td><%= categoria.getDescricao() %></td>
									<td><%= liv.getTitulo() %></td>
									<td><%= liv.getEditora() %></td>
									<td>R$ <%= liv.getValor() %></td>
									<td><%= liv.getSituacao() %></td>
									<td>
										<%if (liv.getSituacao().equals("Indisponivel")) {
										 %>
											<span class="text-warning">Reservado até <%= empresta.getDtprevisao() %></span>
										<%} else { %>
											n/a
										<%} %>
									</td>
									<td>
										<%if (liv.getSituacao().equals("Indisponivel")) {
										 %>
										<a href="EmprestaController.do?method=inserir&codlivro=<%= liv.getCodlivro() %>" class="btn btn-primary disabled">Reservar</a>
										<%} else {%>
										<a href="EmprestaController.do?method=inserir&codlivro=<%= liv.getCodlivro() %>" class="btn btn-primary">Reservar</a>
										<%} %>
										<a href="LivroController.do?method=editar&id=<%= liv.getCodlivro() %>" class="btn btn-warning">Editar</a>
										<a href="LivroController.do?method=excluir&id=<%= liv.getCodlivro() %>" class="btn btn-danger">Deletar</a>
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