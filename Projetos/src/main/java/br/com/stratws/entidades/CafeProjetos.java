package br.com.stratws.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

@Entity
@Table(name = "cafeprojetos")
@SqlResultSetMapping(name = "CafeProjetos", entities = {
		@EntityResult(entityClass = br.com.stratws.entidades.CafeProjetos.class) })
public class CafeProjetos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private BigDecimal id;
	private BigDecimal idProjeto;
	private Date dataGeracao;
	private Date dataExecucao;
	private BigDecimal colaboradorId;
	private String colaborador;
	private String colaboradoresProjeto;
	private String acompanhamento;
	
	public BigDecimal getId() {
		return id;
	}
	public BigDecimal getIdProjeto() {
		return idProjeto;
	}
	public Date getDataGeracao() {
		return dataGeracao;
	}
	public Date getDataExecucao() {
		return dataExecucao;
	}
	public BigDecimal getColaboradorId() {
		return colaboradorId;
	}
	public String getColaborador() {
		return colaborador;
	}
	public String getColaboradoresProjeto() {
		return colaboradoresProjeto;
	}
	public String getAcompanhamento() {
		return acompanhamento;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public void setIdProjeto(BigDecimal idProjeto) {
		this.idProjeto = idProjeto;
	}
	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}
	public void setDataExecucao(Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}
	public void setColaboradorId(BigDecimal colaboradorId) {
		this.colaboradorId = colaboradorId;
	}
	public void setColaborador(String colaborador) {
		this.colaborador = colaborador;
	}
	public void setColaboradoresProjeto(String colaboradoresProjeto) {
		this.colaboradoresProjeto = colaboradoresProjeto;
	}
	public void setAcompanhamento(String acompanhamento) {
		this.acompanhamento = acompanhamento;
	}

}