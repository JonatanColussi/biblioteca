<%@include file="includes/header.jsp" %>
<main role="main" class="container">
  <div class="row">
    <div class="col-md-4 offset-md-4">
      <div class="card">
        <div class="card-body">
          <form action="">
            <div class="form-group">
              <label for="nome">Nome</label>
              <input type="text" name="nome" class="form-control">
            </div>
            <div class="form-group">
              <label for="endereco">Endereço</label>
              <input type="text" name="endereco" class="form-control">
            </div>
            <div class="form-group text-right">
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