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
@Table(name = "logprojetos")
@SqlResultSetMapping(name = "LogProjetos", entities = {
		@EntityResult(entityClass = br.com.stratws.entidades.LogProjetos.class) })
public class LogProjetos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private BigDecimal id;
	private Date dataHora;
	private BigDecimal idProjeto;
	private String status;
	private String acao;
	private String tituloEmail;
	private String corpoEmail;
	private String para;
	
	public BigDecimal getId() {
		return id;
	}
	public Date getDataHora() {
		return dataHora;
	}
	public BigDecimal getIdProjeto() {
		return idProjeto;
	}
	public String getStatus() {
		return status;
	}
	public String getAcao() {
		return acao;
	}
	public String getTituloEmail() {
		return tituloEmail;
	}
	public String getCorpoEmail() {
		return corpoEmail;
	}
	public String getPara() {
		return para;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}
	public void setIdProjeto(BigDecimal idProjeto) {
		this.idProjeto = idProjeto;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public void setTituloEmail(String tituloEmail) {
		this.tituloEmail = tituloEmail;
	}
	public void setCorpoEmail(String corpoEmail) {
		this.corpoEmail = corpoEmail;
	}
	public void setPara(String para) {
		this.para = para;
	}

}