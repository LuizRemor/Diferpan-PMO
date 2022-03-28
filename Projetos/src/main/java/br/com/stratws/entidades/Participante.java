package br.com.stratws.entidades;

import java.math.BigDecimal;

import javax.persistence.EntityResult;
import javax.persistence.SqlResultSetMapping;

import com.google.gson.annotations.SerializedName;

@SqlResultSetMapping(name = "Participante", entities = {
		@EntityResult(entityClass = br.com.stratws.entidades.EstatisticasProjeto.class) })
public class Participante {

	@SerializedName("Id")
	private BigDecimal id;
	@SerializedName("Nome")
	private String nome;
	@SerializedName("Email")
	private String email;
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Participante [id=" + id + ", nome=" + nome + ", email=" + email + "]";
	}

}