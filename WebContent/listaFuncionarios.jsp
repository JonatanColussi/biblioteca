<%@include file="includes/header.jsp" %>
<main role="main" class="container">
	<div class="row">
		<div class="col-md-12">
			<h1>Funcionários</h1>
			<a href="" class="btn btn-primary mb_10">Cadastar Funcionário</a>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>Biblioteca</th>
						<th>Nome</th>
						<th>Usuário</th>
						<th>Ações</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Biblioteca central</td>
						<td>João</td>
						<td>joaozinho0123</td>
						<td>
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