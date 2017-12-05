package br.com.fadergs.biblioteca.entidades;

public class Empresta {
	
	private Integer Codempresta;
	private Integer Codmatricula;
	private Integer Codlivro;
	private String Dtretirada;
	private String Dtprevisao;
	private String Dtentrega;
	
	
	public Integer getCodempresta() {
		return Codempresta;
	}
	
	public void setCodempresta(Integer codempresta) {
		Codempresta = codempresta;
	}
	
	public Integer getCodmatricula() {
		return Codmatricula;
	}
	
	public void setCodmatricula(Integer codmatricula) {
		Codmatricula = codmatricula;
	}
	
	public Integer getCodlivro() {
		return Codlivro;
	}
	
	public void setCodlivro(Integer codlivro) {
		Codlivro = codlivro;
	}
	
	public String getDtretirada() {
		return Dtretirada;
	}
	
	public void setDtretirada(String dtretirada) {
		Dtretirada = dtretirada;
	}
	
	public String getDtprevisao() {
		return Dtprevisao;
	}
	
	public void setDtprevisao(String dtprevisao) {
		Dtprevisao = dtprevisao;
	}
	
	public String getDtentrega() {
		return Dtentrega;
	}
	
	public void setDtentrega(String dtentrega) {
		Dtentrega = dtentrega;
	}

}
