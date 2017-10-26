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
              <label for="codcat">Categoria</label>
              <select name="codcat" class="form-control">
                <option value="1">Categoria A</option>
                <option value="2">Categoria B</option>
              </select>
            </div>
            <div class="form-group">
              <label for="titulo">Título</label>
              <input type="text" name="titulo" class="form-control">
            </div>
            <div class="form-group">
              <label for="editora">Editora</label>
              <input type="text" name="editora" class="form-control">
            </div>
            <div class="form-group">
              <label for="valor">Valor</label>
              <input type="text" name="valor" class="form-control">
            </div>
            <div class="form-group text-right">
              <input type="hidden" name="codliv" value="0">
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