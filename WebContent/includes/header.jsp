<!doctype html>
<html lang="pt-br">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="Jonatan Colussi, Rene Danni">

	<title>Biblioteca Fadergs</title>
	<link href="assets/css/bootstrap.min.css" rel="stylesheet">
	<link href="assets/css/style.css" rel="stylesheet">
</head>

<body>
	<div class="container">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">Biblioteca</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-right">
						<li class="active"><a href="index.jsp">Home</a></li>
						<li><a href="BibliotecaController.do?method=listar">Bibliotecas</a></li>
						<li><a href="AlunoController.do?method=listar">Alunos</a></li>
						<li><a href="LivroController.do?method=listar">Livros</a></li>
						<li><a href="CategoriaController.do?method=listar">Categorias</a></li>
						<li><a href="FuncionarioController.do?method=listar">Funcionários</a></li>
						<li><a href="EmprestaController.do?method=listar">Reservas</a></li>
					</ul>
				</div><!--/.nav-collapse -->
			</div><!--/.container-fluid -->
		</nav>
	</div>