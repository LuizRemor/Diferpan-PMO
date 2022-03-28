package br.com.stratws.bean;

import java.io.IOException;
import java.text.ParseException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.swing.UnsupportedLookAndFeelException;

import br.com.stratws.servicos.ArquivoServico;

@ViewScoped
@ManagedBean(name = "projetoBean")
public class ProjetoBean {

	public void btEscolheArquivo() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		ArquivoServico arquivo = new ArquivoServico();

		arquivo.escolheArquivo();
	}

	public void btIniciarGestaoDeProjetos() throws IOException, ParseException {

	}

}