<%@include file="includes/header.jsp" %>
  <main role="main" class="container">
    <div class="row">
      <div class="col-md-4 offset-md-4">
        <div class="card">
          <div class="card-body">
            <form action="">
              <div class="form-group">
                <label for="codbib">Biblioteca</label>
                <select name="codbib" class="form-control">
                  <option value="1">Blibliteca A</option>
                  <option value="2">Blibliteca B</option>
                  <option value="3">Blibliteca C</option>
                </select>
              </div>
              <div class="form-group">
                <label for="nome">Nome</label>
                <input type="text" name="nome" class="form-control">
              </div>
              <div class="form-group">
                <label for="endereco">Endereço</label>
                <input type="text" name="endereco" class="form-control">
              </div>
              <div class="form-group">
                <label for="telefone">Telefone</label>
                <input type="text" name="telefone" class="form-control">
              </div>
              <div class="form-group">
                <label for="salario">Salário</label>
                <input type="text" name="salario" class="form-control">
              </div>
              <div class="form-group">
                <label for="usuario">Usuário</label>
                <input type="text" name="usuario" class="form-control">
              </div>
              <div class="form-group">
                <label for="senha">Senha</label>
                <input type="password" name="senha" class="form-control">
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
<%@include file="includes/footer.jsp" %>