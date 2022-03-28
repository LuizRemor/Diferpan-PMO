package br.com.stratws.servicos;

import java.util.ArrayList;
import java.util.Collection;

import javax.mail.internet.InternetAddress;

import org.apache.commons.codec.Charsets;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;

import br.com.stratws.entidades.Projeto;
import br.com.stratws.model.AnaliseProjetos;

public class ExchangeServico {

	HtmlEmail htmlEmail = new HtmlEmail();
	public Collection<InternetAddress> listaEmail = new ArrayList<InternetAddress>();
	public Collection<InternetAddress> listaCc = new ArrayList<InternetAddress>();
	Projeto projeto;
	String assunto;
	String texto;
	AnaliseProjetos analiseProjetos;

	private Integer porta = 25;

	public void enviaEmail() throws Exception {
		
		htmlEmail.setDebug(true);

		htmlEmail.setAuthentication("luiz.neto@diferpan.com.br", "lrn2028@");

		htmlEmail.setStartTLSEnabled(false);

		htmlEmail.setHostName("diferpan-com-br.mail.protection.outlook.com");

		htmlEmail.setSmtpPort(porta);

		htmlEmail.setAuthenticator(new DefaultAuthenticator("luiz.neto@diferpan.com.br", "lrn2028@"));

		htmlEmail.setFrom("luiz.neto@diferpan.com.br", "Escritório de Projetos");
		
		String textoEmail = new String(texto.getBytes(), Charsets.ISO_8859_1.name());

		htmlEmail.setSubject(assunto);
		
		htmlEmail.setHtmlMsg(textoEmail);
		
		listaEmail.add(new InternetAddress("luiz.neto@diferpan.com.br"));

		htmlEmail.setTo(listaEmail);
		
		listaCc.add(new InternetAddress("luiz.neto@diferpan.com.br"));
		
		htmlEmail.setCc(listaCc);

		htmlEmail.send();

		System.out.println("E-mails enviados!!!!");

	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

}