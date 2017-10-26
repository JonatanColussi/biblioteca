<%@include file="includes/header.jsp" %>
<main role="main" class="container">
	<div class="row">
		<div class="col-md-12">
			<h1>Livros</h1>
			<a href="" class="btn btn-primary mb_10">Cadastar Livro</a>
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
						<th>Ações</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Biblioteca Central</td>
						<td>Romance</td>
						<td>A culpa é das estrelas</td>
						<td>Editora 1</td>
						<td>R$ 123,45</td>
						<td>
							<span class="text-warning">Reservado até 30/10/2017</span>
						</td>
						<td>
							<a href="" class="btn btn-primary">Reservar</a>
							<a href="" class="btn btn-warning">Editar</a>
							<a href="" class="btn btn-danger">Deletar</a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</main>
<%@include file="includes/footer.jsp" %>