<%@include file="includes/header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.fadergs.biblioteca.entidades.Funcionario" %>
<%@ page import="br.com.fadergs.biblioteca.entidades.Biblioteca" %>
<main role="main" class="container">
    <div class="row">
      <div class="col-md-4 offset-md-4">
        <div class="card">
          <div class="card-body">
            <form action="FuncionarioController.do" method="POST">
              <div class="form-group">
                <label for="codbib">Biblioteca</label>
                <select name="codbib" class="form-control">
                    <%
                        List<Biblioteca> BibliotecasLista = (List<Biblioteca>) request.getAttribute("bibliotecas");
                        Funcionario func = (Funcionario) request.getAttribute("funcionario");
                        for (Biblioteca biblioteca: BibliotecasLista) {
                            if (func.getCodbib() == biblioteca.getCodbib()){
                                %>
                                <option selected value="<%= biblioteca.getCodbib() %>"><%= biblioteca.getNome() %></option>
                                <%
                            } else {
                                %>
                                <option value="<%= biblioteca.getCodbib() %>"><%= biblioteca.getNome() %></option>
                                <%
                            }
                        }
                    %>
                </select>
            </div>
            <div class="form-group">
                <label for="nome">Nome</label>
                <input type="text" name="nome" class="form-control" value="${funcionario.getNome()}">
            </div>
            <div class="form-group">
                <label for="endereco">Endereço</label>
                <input type="text" name="endereco" class="form-control" value="${funcionario.getEndereco()}">
            </div>
            <div class="form-group">
                <label for="telefone">Telefone</label>
                <input type="text" name="telefone" class="form-control" value="${funcionario.getTelefone()}">
            </div>
            <div class="form-group">
                <label for="salario">Salário</label>
                <input type="text" name="salario" class="form-control" value="${funcionario.getSalario()}">
            </div>
            <div class="form-group">
                <label for="usuario">Usuário</label>
                <input type="text" name="usuario" class="form-control" value="${funcionario.getLogin()}">
            </div>
            <div class="form-group">
                <label for="senha">Senha</label>
                <input type="password" name="senha" class="form-control" value="">
            </div>
            <div class="form-group text-right">
                <a href="FuncionarioController.do?method=listar" class="btn btn-link">Voltar</a>
                <input type="hidden" name="codfunc" value="${funcionario.getCodfunc()}">
                <input type="submit" class="btn btn-primary" value="Cadastrar">
            </div>
        </form>
    </div>
</div>
</div>
</div>
<%@include file="includes/footer.jsp" %>