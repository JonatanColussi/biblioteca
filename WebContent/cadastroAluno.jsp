<%@include file="includes/header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.fadergs.biblioteca.entidades.Aluno" %>
<main role="main" class="container">
  <div class="row">
    <div class="col-md-4 offset-md-4">
      <div class="card">
        <div class="card-body">
          <form action="AlunoController.do" method="POST">
            <div class="form-group">
              <label for="codmatricula">Matricula</label>
              <input type="text" name="codmatricula" class="form-control" value="${aluno.getCodmatricula()}" ${aluno.getCodmatricula() != null ? 'readonly' : ''}>
            	${aluno.getCodmatricula() != null ? '<p class="help-block">O campo matricula não pode ser alterado</p>' : ''}
            </div>
            <div class="form-group">
              <label for="nome">Nome</label>
              <input type="text" name="nome" class="form-control" value="${aluno.getNome()}">
            </div>
            <div class="form-group">
              <label for="endereco">Endereço</label>
              <input type="text" name="endereco" class="form-control" value="${aluno.getEndereco()}">
            </div>
            <div class="form-group">
              <label for="situacao">Situação</label>
              <select name="situacao" class="form-control">
                <option value="Ativo" ${aluno.getSituacao() == 'Ativo' ? 'selected' : ''}>Ativo</option>
                <option value="Inativo" ${aluno.getSituacao() == 'Inativo' ? 'selected' : ''}>Inativo</option>
              </select>
            </div>
            <div class="form-group text-right">
            	<a href="AlunoController.do?method=listar" class="btn btn-link">Voltar</a>
                <input type="submit" class="btn btn-primary" value="Cadastrar">
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</main>
<%@include file="includes/footer.jsp" %>