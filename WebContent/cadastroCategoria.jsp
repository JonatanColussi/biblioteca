<%@include file="includes/header.jsp" %>
  <main role="main" class="container">
    <div class="row">
      <div class="col-md-4 offset-md-4">
        <div class="card">
          <div class="card-body">
            <form action="CategoriaController.do" method="POST">
              <div class="form-group">
                <label for="descricao">Descrição</label>
                <input type="text" name="descricao" class="form-control">
              </div>
              <div class="form-group text-right">
                <input type="hidden" name="codcategoria" value="0">
                <a href="index.html" class="btn btn-link">Voltar</a>
                <input type="submit" class="btn btn-primary" value="Cadastrar">
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </main>
<%@include file="includes/footer.jsp" %>
