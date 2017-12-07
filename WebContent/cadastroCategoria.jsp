<%@include file="includes/header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.fadergs.biblioteca.entidades.Categoria" %>
  <main role="main" class="container">
  <div class="row">
	<div class="col-md-12">
		<h1>Categorias</h1>
	</div>
</div>
    <div class="row">
      <div class="col-md-4 offset-md-4">
        <div class="card">
          <div class="card-body">
            <form action="CategoriaController.do" method="POST">
              <div class="form-group">
                <label for="descricao">Descrição</label>
                <input type="text" name="descricao" class="form-control" value="${categoria.getDescricao()}">
              </div>
              <div class="form-group text-right">
                <a href="CategoriaController.do?method=listar" class="btn btn-link">Voltar</a>
              	<input type="hidden" name="codcategoria" value="${categoria.getCodcategoria()}">
                <input type="submit" class="btn btn-primary" value="Cadastrar">
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </main>
<%@include file="includes/footer.jsp" %>
