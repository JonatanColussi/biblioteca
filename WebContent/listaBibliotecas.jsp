<%@include file="includes/header.jsp" %>
<main role="main" class="container">
	<div class="row">
		<div class="col-md-12">
			<h1>Bibliotecas</h1>
			<a href="" class="btn btn-primary mb_10">Cadastar Biblioteca</a>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>Nome</th>
						<th>Endereço</th>
						<th>Ações</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Biblioteca Central</td>
						<td>Rua teste</td>
						<td>
							<a href="" class="btn btn-primary">Cadastrar Livro</a>
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