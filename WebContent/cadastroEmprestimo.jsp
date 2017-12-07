<%@include file="includes/header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.fadergs.biblioteca.entidades.Livro" %>
<%@ page import="br.com.fadergs.biblioteca.entidades.Aluno" %>
<%@ page import="br.com.fadergs.biblioteca.entidades.Empresta" %>
<main role="main" class="container">
<div class="row">
	<div class="col-md-12">
		<h1>Reservas</h1>
	</div>
</div>
  <div class="row">
    <div class="col-md-4 offset-md-4">
      <div class="card">
        <div class="card-body">
          <form action="EmprestaController.do" method="POST">
            <div class="form-group">
                <label for="codalu">Aluno</label>
                <select name="codalu" class="form-control" required>
                    <%
                        List<Aluno> AlunosLista = (List<Aluno>) request.getAttribute("alunos");
                        int codmatricula = (int) request.getAttribute("codmatricula");
                        for (Aluno aluno: AlunosLista) {
                        	if(aluno.getSituacao().equals("Ativo")){
	                            if (aluno.getCodmatricula() == codmatricula){
	                                %>
	                                <option selected value="<%= aluno.getCodmatricula() %>"><%= aluno.getNome() %></option>
	                                <%
	                            } else {
	                                %>
	                                <option value="<%= aluno.getCodmatricula() %>"><%= aluno.getNome() %></option>
	                                <%
	                            }
                        	}
                        }
                    %>
                </select>
            </div>
            <div class="form-group">
              <label for="codliv">Livro</label>
              <select name="codliv" class="form-control" required>
                     <%
                        List<Livro> LivrosLista = (List<Livro>) request.getAttribute("livros");
            		    int codlivro = (int) request.getAttribute("codlivro");
                        for (Livro livro: LivrosLista) {
                        	if(livro.getSituacao().equals("Disponivel")){
	                            if (livro.getCodlivro() == codlivro){
	                                %>
	                                <option selected value="<%= livro.getCodlivro() %>"><%= livro.getTitulo() %></option>
	                                <%
	                            } else {
	                                %>
	                                <option value="<%= livro.getCodlivro() %>"><%= livro.getTitulo() %></option>
	                                <%
	                            }
                        	}
                        }
                    %>
              </select>
            </div>
            <div class="form-group text-right">
              <a href="EmprestaController.do?method=listar" class="btn btn-link">Voltar</a>
                <input type="hidden" name="codemp" value="${empresta.getCodempresta()}">
                <input type="submit" class="btn btn-primary" value="Cadastrar">
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</main>
<%@include file="includes/footer.jsp" %>