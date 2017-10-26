<%@include file="includes/header.jsp" %>
<main role="main" class="container">
	<div class="row">
		<div class="col-md-12">
			<h1>Alunos</h1>
			<a href="" class="btn btn-primary mb_10">Cadastar Aluno</a>
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
					<tr>
						<td>201615060</td>
						<td>Jonatan Colussi</td>
						<td>Rua teste, 123</td>
						<td>
							<span class="text-success">Ativo</span>
						</td>
						<td>
							<a href="" class="btn btn-primary">Reservar livro</a>
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