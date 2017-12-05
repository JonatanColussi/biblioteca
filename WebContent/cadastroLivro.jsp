<%@include file="includes/header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.fadergs.biblioteca.entidades.Livro" %>
<%@ page import="br.com.fadergs.biblioteca.entidades.Biblioteca" %>
<%@ page import="br.com.fadergs.biblioteca.entidades.Categoria" %>
<main role="main" class="container">
  <div class="row">
    <div class="col-md-4 offset-md-4">
      <div class="card">
        <div class="card-body">
          <form action="LivroController.do" method="POST">
            <div class="form-group">
                <label for="codbib">Biblioteca</label>
                <select name="codbib" class="form-control">
                    <%
                        List<Biblioteca> BibliotecasLista = (List<Biblioteca>) request.getAttribute("bibliotecas");
                        Livro liv = (Livro) request.getAttribute("livro");
                        for (Biblioteca biblioteca: BibliotecasLista) {
                            if (liv.getCodbib() == biblioteca.getCodbib()){
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
              <label for="codcat">Categoria</label>
              <select name="codcat" class="form-control">
                     <%
                        List<Categoria> CategoriasLista = (List<Categoria>) request.getAttribute("categorias");
                        for (Categoria categoria: CategoriasLista) {
                            if (liv.getCodcategoria() == categoria.getCodcategoria()){
                                %>
                                <option selected value="<%= categoria.getCodcategoria() %>"><%= categoria.getDescricao() %></option>
                                <%
                            } else {
                                %>
                                <option value="<%= categoria.getCodcategoria() %>"><%= categoria.getDescricao() %></option>
                                <%
                            }
                        }
                    %>
              </select>
            </div>
            <div class="form-group">
              <label for="titulo">Título</label>
              <input type="text" name="titulo" class="form-control" value="${livro.getTitulo()}">
            </div>
            <div class="form-group">
              <label for="editora">Editora</label>
              <input type="text" name="editora" class="form-control" value="${livro.getEditora()}">
            </div>
            <div class="form-group">
              <label for="valor">Valor</label>
              <input type="text" name="valor" class="form-control" value="${livro.getValor()}">
            </div>
            <div class="form-group text-right">
              <a href="LivroController.do?method=listar" class="btn btn-link">Voltar</a>
                <input type="hidden" name="codliv" value="${livro.getCodlivro()}">
                <input type="submit" class="btn btn-primary" value="Cadastrar">
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</main>
<%@include file="includes/footer.jsp" %>