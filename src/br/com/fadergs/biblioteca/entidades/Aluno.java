package br.com.fadergs.biblioteca.entidades;

public class Aluno {
	
	private Integer Codmatricula;
	private String Nome;
	private String Endereco;
	private String Situacao;
	
	public Integer getCodmatricula() {
		return Codmatricula;
	}
	
	public void setCodmatricula(Integer codmatricula) {
		Codmatricula = codmatricula;
	}
	
	public String getNome() {
		return Nome;
	}
	
	public void setNome(String nome) {
		Nome = nome;
	}
	
	public String getEndereco() {
		return Endereco;
	}
	
	public void setEndereco(String endereco) {
		Endereco = endereco;
	}
	
	public String getSituacao() {
		return Situacao;
	}
	
	public void setSituacao(String situacao) {
		Situacao = situacao;
	}

}
