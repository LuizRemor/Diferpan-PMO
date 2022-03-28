package br.com.stratws.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "estatisticasprojeto")
@SqlResultSetMapping(name = "EstatisticasProjeto", entities = {
		@EntityResult(entityClass = br.com.stratws.entidades.EstatisticasProjeto.class) })
public class EstatisticasProjeto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private BigDecimal id;
	private String descricao;
	private Double ano;
	private String etapa;
	@Temporal(TemporalType.DATE)
	private Date encerramentoPrevisto;
	private Date encerramento;
	private Double percentualRealizado;
	private Double percentualPrevisto;
	private String resultadosAlcancados;
	private String analiseCritica;
	private String status;
	private String areaDeResultados;
	private Date ultimaVerificacao = new Date();
	private Date email1;
	private Date email2;
	
	public Date getEmail1() {
		return email1;
	}

	public Date getEmail2() {
		return email2;
	}

	public void setEmail1(Date email1) {
		this.email1 = email1;
	}

	public void setEmail2(Date email2) {
		this.email2 = email2;
	}

	public Date getUltimaVerificacao() {
		return ultimaVerificacao;
	}

	public void setUltimaVerificacao(Date ultimaVerificacao) {
		this.ultimaVerificacao = ultimaVerificacao;
	}

	public String getAreaDeResultados() {
		return areaDeResultados;
	}

	public void setAreaDeResultados(String areaDeResultados) {
		this.areaDeResultados = areaDeResultados;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getEncerramentoPrevisto() {
		return encerramentoPrevisto;
	}

	public void setEncerramentoPrevisto(Date string) {
		this.encerramentoPrevisto = string;
	}

	public Date getEncerramento() {
		return encerramento;
	}

	public void setEncerramento(Date encerramento) {
		this.encerramento = encerramento;
	}

	public Double getAno() {
		return ano;
	}

	public void setAno(Double ano) {
		this.ano = ano;
	}

	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Double getPercentualRealizado() {
		return percentualRealizado;
	}

	public void setPercentualRealizado(Double percentualRealizado) {
		this.percentualRealizado = percentualRealizado;
	}

	public Double getPercentualPrevisto() {
		return percentualPrevisto;
	}

	public void setPercentualPrevisto(Double percentualPrevisto) {
		this.percentualPrevisto = percentualPrevisto;
	}

	public String getResultadosAlcancados() {
		return resultadosAlcancados;
	}

	public void setResultadosAlcancados(String resultadosAlcancados) {
		this.resultadosAlcancados = resultadosAlcancados;
	}

	public String getAnaliseCritica() {
		return analiseCritica;
	}

	public void setAnaliseCritica(String analiseCritica) {
		this.analiseCritica = analiseCritica;
	}

	@Override
	public String toString() {
		return "id=" + id + ", etapa=" + etapa + ", percentualRealizado=" + percentualRealizado 
				+ ", percentualPrevisto=" + percentualPrevisto;
	}

}