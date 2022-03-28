package br.com.stratws.servicos;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;

import javax.mail.internet.InternetAddress;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import br.com.stratws.entidades.Projeto;
import br.com.stratws.model.AnaliseProjetos;

public class GmailServico {

	HtmlEmail htmlEmail = new HtmlEmail();
	public Collection<InternetAddress> listaEmail = new ArrayList<InternetAddress>();
	Projeto projeto;
	String assunto;
	String texto;
	AnaliseProjetos analiseProjetos;

	private Integer porta = 587;

	public void enviaEmail() throws EmailException, UnsupportedEncodingException {

		htmlEmail.setDebug(true);

		htmlEmail.setAuthentication("luizneto.diferpan@gmail.com", "Mijao0101.");

		htmlEmail.setStartTLSEnabled(true);

		htmlEmail.setHostName("smtp.gmail.com");

		htmlEmail.setSmtpPort(porta);

		htmlEmail.setAuthenticator(new DefaultAuthenticator("luizneto.diferpan@gmail.com", "Mijao0101."));

		htmlEmail.setFrom("luizneto.diferpan@gmail.com", "Luiz");

		htmlEmail.setSubject(assunto);

		htmlEmail.setHtmlMsg(texto);

		htmlEmail.setTo(listaEmail);

		htmlEmail.send();

		System.out.println("E-mails enviados!!!!");

	}

	public Projeto getProjeto() {
		return projeto;
	}

	public String getAssunto() {
		return assunto;
	}

	public String getTexto() {
		return texto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
}