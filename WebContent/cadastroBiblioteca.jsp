<%@include file="includes/header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.fadergs.biblioteca.entidades.Biblioteca" %>
<main role="main" class="container">
  <div class="row">
    <div class="col-md-4 offset-md-4">
      <div class="card">
        <div class="card-body">
          <form action="BibliotecaController.do" method="POST">
            <div class="form-group">
              <label for="nome">Nome</label>
              <input type="text" name="nome" class="form-control" value="${biblioteca.getNome()}">
            </div>
            <div class="form-group">
              <label for="endereco">Endereço</label>
              <input type="text" name="endereco" class="form-control" value="${biblioteca.getEndereco()}">
            </div>
            <div class="form-group text-right">
              <a href="BibliotecaController.do?method=listar" class="btn btn-link">Voltar</a>
              <input type="hidden" name="codbib" value="${biblioteca.getCodbib()}">
                <input type="submit" class="btn btn-primary" value="Cadastrar">
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</main>
<%@include file="includes/footer.jsp" %>