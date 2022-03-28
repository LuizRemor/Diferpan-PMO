package br.com.stratws.entidades;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Projeto {

	@SerializedName("Id")
	private BigDecimal id;
	@SerializedName("Descricao")
	private String descricao;
	@SerializedName("Elaboradores")
	public List<Participante> elaboradores = new ArrayList<Participante>();

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Participante> getElaboradores() {
		return elaboradores;
	}

	public void setElaboradores(List<Participante> elaboradores) {
		this.elaboradores = elaboradores;
	}

	@Override
	public String toString() {
		return "Projeto [id=" + id + ", descricao=" + descricao + ", elaboradores=" + elaboradores + "]";
	}

}