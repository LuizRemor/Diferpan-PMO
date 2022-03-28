package br.com.stratws.entidades;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ModelosEmail {

	private Date dataEncerramentoPrevisto;
	private String link;
	private String nomeDoProjeto;
	private Double percentualRealizado;
	private Double percentualPrevisto;
	private String assuntoTarefaAtrasada 	  = "PMO - Tarefa em atraso";
	private String assuntoProjetoAtrasado     = "PMO - Projeto atrasado";
	private String assuntoQuaseConcluido      = "PMO - Projeto Quase Concluído";
	private String assuntoConcluidoComSucesso = "PMO - Projeto Concluído com Sucesso!!";
	private String Atrasado;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Date ajustaDataHora(Date dataEntrada) {
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(dataEntrada);
		
		calendar.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
		
		calendar.add(Calendar.HOUR, 3);
				
		return calendar.getTime();
		
	}

	public void criaModeloEmailAtrasado() {
		
		this.Atrasado = 
				  "Olá pessoal, tudo bem?"
				+ "</br>"
				+ "</br>"
				+ "Sou o projeto " + "<b>" + nomeDoProjeto + "</b>"
				+ " e estou me sentindo abandonado. Tinha a expectativa de estar concluído na data " + "<b>"
				+ sdf.format(dataEncerramentoPrevisto) + "</b>" + "." + "<br/>" + "<br/>"
				+ "Bom, não conseguiremos voltar no tempo, porém, preciso de saber de você se teremos uma nova data de término?"
				+ "<br/>" + "<br/>"
				+ "Poderia me acessar clicando no link abaixo e ajustar o prazo para continuarmos nossa relação?"
				+ "<br/>" + "<br/>" + "<b>" + "<a href=\"" + link + "\"target=\"_blank\">" + link + "</a>" + "</b>"
				+ "<br/>" + "<br/>" + "<b>Atenciosamente," + "<br/>" + "Escritório de Projetos</b>";
	}

	private String finalizado;

	public void criaModeloEmailFinalizado() {

		this.finalizado = "Olá pessoal, tudo bem?</br>" + "</br>" + "Sou o projeto " + "<b>" + nomeDoProjeto + "</b>"
				+ "</br>"
				+ "</br>"
				+ "Na vida temos que tomar decisões difíceis, muitas vezes nos prendemos em relações sem futuro. Quem sabe em uma próxima oportunidade poderemos melhorar nossa relação? Por enquanto, agradeço todo aprendizado que tivemos. Até breve."
				+ "<br/>" + "<br/>" + "<b>Atenciosamente," + "<br/>" + "Escritório de Projetos</b>";
	}

	private String finalizadoComSucesso;

	public void criaModeloEmailConcluido() {
		this.finalizadoComSucesso = "Olá pessoal, tudo bem?</br>" + "</br>"

				+ "Sou o projeto " + "<b>" + nomeDoProjeto + "</b>" + "</br>"
				+ " e gostaria de compartilhar com vocês a minha felicidade. Concluímos com êxito a nossa missão!!\r\n"
				+ "</br>" + "Uhuull</br>" + "\\o/ \\o/ \\o/ \\o/ \\o/"
				+ "<br/>" + "<br/>" + "<b>Atenciosamente," + "<br/>" + "Escritório de Projetos</b>";
	}

	private String quaseConcluido;

	public void criaModeloEmailQuaseConcluido() {

		this.quaseConcluido =

				"Olá pessoal, tudo bem?</br>" + "</br>"
			  + "Sou o projeto " + "<b>" + nomeDoProjeto + "</b>"
			  + " e verifiquei que estamos quase lá!! Concluímos nossas tarefas, onde senti falta somente dos Resultados alcançados e Análise crítica. Conseguem me auxiliar preenchendo para eu me tornar um projeto 100% concluído?</br>"
			  + "<br/>" + "<b>" + "<a href=\"" + link + "\"target=\"_blank\">" + link + "</a>" + "</b>"
			  + "</br>"
			  + "</br>" + "<b>O que são Resultados alcançados?</b></br>"
			  + "Trata-se da verificação da eficácia, ou seja, o resultado alcançado após a implantação do projeto (a relação de ganho com a implementação do projeto).</br>"
			  + "É muito importante para entendermos a relação de investimento realizado X resultado alcançado.</br>"
			  + "</br>" + "<b>O que é uma Análise crítica?</b></br>"
			  + "Análise crítica, também chamada de Review, trata-se de uma avaliação geral do projeto tendo como objetivo a identificação de problemas/sucessos, a fim de deixar documentado o conhecimento adquirido ao longo da execução."
			  + "<br/>" + "<br/>" + "<b>Atenciosamente," + "<br/>" + "Escritório de Projetos</b>";
	}

	private String tarefaEmAtraso;

	public void criaModeloEmailTarefaEmAtraso() {

		this.tarefaEmAtraso =

				"Olá pessoal, tudo bem?</br>" + "</br>" + "Sou o projeto " + "<b>" + nomeDoProjeto + "</b>"
			  + " e senti que está me deixando de lado aos poucos. Estamos com " + percentualRealizado*100
			  + "% concluído, mas, deveríamos estar no percentual " + percentualPrevisto*100 + "%.</br>" + "</br>"
			  + "Poderia me acessar clicando neste link " + "<b>" + "<a href=\"" + link + "\"target=\"_blank\">" + link + "</a>" + "</b>" 
			  + " e ajustar o prazo das tarefas para que nossa relação tenha um final feliz?!"
			  + "<br/>" + "<br/>" + "<b>Atenciosamente," + "<br/>" + "Escritório de Projetos</b>";
	}

	public String getAtrasado() {
		return Atrasado;
	}

	public void setAtrasado(String Atrasado) {
		this.Atrasado = Atrasado;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getDataEncerramentoPrevisto() {
		return dataEncerramentoPrevisto;
	}

	public void setDataEncerramentoPrevisto(Date dataEncerramentoPrevisto) {
		this.dataEncerramentoPrevisto = dataEncerramentoPrevisto;
	}

	public String getNomeDoProjeto() {
		return nomeDoProjeto;
	}

	public void setNomeDoProjeto(String nomeDoProjeto) {
		this.nomeDoProjeto = nomeDoProjeto;
	}

	public void setFinalizado(String finalizado) {
		this.finalizado = finalizado;
	}

	public void setFinalizadoComSucesso(String finalizadoComSucesso) {
		this.finalizadoComSucesso = finalizadoComSucesso;
	}

	public void setQuaseConcluido(String quaseConcluido) {
		this.quaseConcluido = quaseConcluido;
	}

	public void setTarefaEmAtraso(String tarefaEmAtraso) {
		this.tarefaEmAtraso = tarefaEmAtraso;
	}

	public void setAssuntoTarefaAtrasada(String assuntoTarefaAtrasada) {
		this.assuntoTarefaAtrasada = assuntoTarefaAtrasada;
	}

	public void setAssuntoProjetoAtrasado(String assuntoProjetoAtrasado) {
		this.assuntoProjetoAtrasado = assuntoProjetoAtrasado;
	}

	public void setAssuntoQuaseConcluido(String assuntoQuaseConcluido) {
		this.assuntoQuaseConcluido = assuntoQuaseConcluido;
	}

	public void setAssuntoConcluidoComSucesso(String assuntoConcluidoComSucesso) {
		this.assuntoConcluidoComSucesso = assuntoConcluidoComSucesso;
	}

	public String getAssuntoTarefaAtrasada() {
		return assuntoTarefaAtrasada;
	}

	public String getAssuntoProjetoAtrasado() {
		return assuntoProjetoAtrasado;
	}

	public String getAssuntoQuaseConcluido() {
		return assuntoQuaseConcluido;
	}

	public String getAssuntoConcluídoComSucesso() {
		return assuntoConcluidoComSucesso;
	}

	public Double getPercentualRealizado() {
		return percentualRealizado;
	}

	public Double getPercentualPrevisto() {
		return percentualPrevisto;
	}

	public void setPercentualRealizado(Double percentualRealizado) {
		this.percentualRealizado = percentualRealizado;
	}

	public void setPercentualPrevisto(Double percentualPrevisto) {
		this.percentualPrevisto = percentualPrevisto;
	}

	public String getFinalizado() {
		return finalizado;
	}

	public String getFinalizadoComSucesso() {
		return finalizadoComSucesso;
	}

	public String getQuaseConcluido() {
		return quaseConcluido;
	}

	public String getTarefaEmAtraso() {
		return tarefaEmAtraso;
	}

}