<%@include file="includes/header.jsp" %>
<main role="main" class="container">
  <div class="row">
    <div class="col-md-4 offset-md-4" style="border: 1px solid #ccc; border-radius: 5px; margin-top: 100px;">
      <form action="">
        <div class="form-group">
          <label for="usuario">Usuário</label>
          <input type="text" name="usuario" class="form-control">
        </div>
        <div class="form-group">
          <label for="senha">Senha</label>
          <input type="password" name="senha" class="form-control">
        </div>
        <div class="form-group text-right">
          <a href="cadastroFuncionario.html" class="btn btn-link">Criar conta</a>
          <input type="submit" class="btn btn-primary" value="Entrar">
        </div>
      </form>
    </div>
  </div>
</main>
<%@include file="includes/footer.jsp" %>