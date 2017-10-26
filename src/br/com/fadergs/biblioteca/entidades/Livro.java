package br.com.fadergs.biblioteca.entidades;

public class Livro {
	
	private Integer Codlivro;
	private String Titulo;
	private String Editora;
	private Double Valor;
	private Integer Codcategoria;
	private Integer Codbib;
	private String Situacao;
	
	public Integer getCodlivro() {
		return Codlivro;
	}
	
	public void setCodlivro(Integer codlivro) {
		Codlivro = codlivro;
	}
	
	public String getTitulo() {
		return Titulo;
	}
	
	public void setTitulo(String titulo) {
		Titulo = titulo;
	}
	
	public String getEditora() {
		return Editora;
	}
	
	public void setEditora(String editora) {
		Editora = editora;
	}
	
	public Double getValor() {
		return Valor;
	}
	
	public void setValor(Double valor) {
		Valor = valor;
	}
	
	public Integer getCodcategoria() {
		return Codcategoria;
	}
	
	public void setCodcategoria(Integer codcategoria) {
		Codcategoria = codcategoria;
	}
	
	public Integer getCodbib() {
		return Codbib;
	}
	
	public void setCodbib(Integer codbib) {
		Codbib = codbib;
	}
	
	public String getSituacao() {
		return Situacao;
	}
	
	public void setSituacao(String situacao) {
		Situacao = situacao;
	}
	

}
