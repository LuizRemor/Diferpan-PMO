package br.com.stratws.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.internet.InternetAddress;

import br.com.stratws.dao.ProjetosDaoJDBC;
import br.com.stratws.entidades.CafeProjetos;
import br.com.stratws.entidades.EstatisticasProjeto;
import br.com.stratws.entidades.ModelosEmail;
import br.com.stratws.entidades.Participante;
import br.com.stratws.entidades.Projeto;
import br.com.stratws.servicos.ConnectionApiServico;
import br.com.stratws.servicos.ExcelServico;
import br.com.stratws.servicos.ExchangeServico;

public class AnaliseProjetos {

	private ProjetosDaoJDBC projetosDao = new ProjetosDaoJDBC();
	private ExcelServico excelServico = new ExcelServico();

	public void atribuiStatus() throws Exception {

		Date date = new Date();

		List<EstatisticasProjeto> listaEstatisticasProjetos = new ArrayList<EstatisticasProjeto>();

		listaEstatisticasProjetos = excelServico.populaEstatisticasProjeto();

		for (EstatisticasProjeto obj : listaEstatisticasProjetos) {

			// Verifica se está concluído
			if (obj.getPercentualRealizado() == 1.0 && obj.getEtapa().equals("Concluída")
					&& obj.getAnaliseCritica() != null && obj.getResultadosAlcancados() != null) {
				obj.setStatus("Concluído");

				obj.setEmail1(null);
				obj.setEmail2(null);

			}
			// Verifica se está em análise
			else if (obj.getEtapa().equals("Em Análise")) {
				obj.setStatus("Em Análise");

				obj.setEmail1(null);
				obj.setEmail2(null);
			}
			// Verifica se está quase concluído
			else if (obj.getPercentualRealizado() == 1.0 && obj.getEtapa().equals("Concluída") /*|| obj.getEtapa().equals("Em Verificação")*/) {
				 if (obj.getAnaliseCritica() == null || obj.getResultadosAlcancados() == null) {
					obj.setStatus("Quase concluído");
				}
			}
			// Verifica se está atrasado
			else if (obj.getEncerramentoPrevisto().getTime() < date.getTime()) {
				obj.setStatus("Atrasado");
			}
			// Verifica se está com tarefa atrasada
			else if (obj.getPercentualRealizado() < obj.getPercentualPrevisto()) {
				obj.setStatus("Tarefa atrasada");
			} else {
				obj.setStatus("Em dia");

				// Se receber o status em dia, regularizou o projeto e pode seguir a nova vida.
				obj.setEmail1(null);
				obj.setEmail2(null);
			}
			
			obj.setUltimaVerificacao(date);
			
			projetosDao.update(obj);

		}

	}

	public void enviaEmailExchange() throws Exception {

		List<EstatisticasProjeto> listaProjetosEnvioEmail = new ArrayList<EstatisticasProjeto>();

		listaProjetosEnvioEmail = projetosDao.pesquisaEnvioEmailPorStatus();

		for (EstatisticasProjeto obj3 : listaProjetosEnvioEmail) {

			ConnectionApiServico connectionApiServico = new ConnectionApiServico();
			Projeto projeto = new Projeto();

			ModelosEmail modelosEmail = new ModelosEmail();

			ExchangeServico exchangeServico = new ExchangeServico();

			List<Participante> listaParticipantes = new ArrayList<Participante>();

			// LINK PARA CONSTAR NO E-MAIL
			String link = "https://diferpan.stratws.com/OportunidadesDeMelhoria/SolucoesDeProblemas/Edit/"
					+ obj3.getId().toString();

			// LINK PARA CONSUMIR A API
			connectionApiServico.setWebService("https://diferpan.api.stratws.com/api/SolucoesDeProblemas/");
			connectionApiServico.setId(obj3.getId().toString());

			System.out.println(connectionApiServico.getWebService() + connectionApiServico.getId());

			// CONSOME A API DO STRATWS
			projeto = connectionApiServico.conectaSite();

			listaParticipantes = projeto.getElaboradores();

			// INCLUI OS PARTICIPANTES NA LISTA DE E-MAIL
			for (Participante obj2 : listaParticipantes) {
				exchangeServico.listaEmail.add(new InternetAddress(obj2.getEmail(), obj2.getNome()));
			}

			if (obj3.getStatus().equals("Concluído")) {

				// E-MAIL DE CONCLUÍDO
				modelosEmail.setNomeDoProjeto(obj3.getDescricao());
				modelosEmail.criaModeloEmailConcluido();

				exchangeServico.setAssunto(modelosEmail.getAssuntoConcluídoComSucesso());
				exchangeServico.setTexto(modelosEmail.getFinalizadoComSucesso());
				exchangeServico.enviaEmail();

			} else if (obj3.getStatus().equals("Quase concluído")) {

				// E-MAIL DE QUASE CONCLUÍDO
				modelosEmail.setNomeDoProjeto(obj3.getDescricao());
				modelosEmail.setLink(link);
				modelosEmail.criaModeloEmailQuaseConcluido();

				exchangeServico.setAssunto(modelosEmail.getAssuntoQuaseConcluido());
				exchangeServico.setTexto(modelosEmail.getQuaseConcluido());
				
				exchangeServico.enviaEmail();

			} else if (obj3.getStatus().equals("Atrasado")) {

				// E-MAIL DE PROJETO ATRASADO
				modelosEmail.setNomeDoProjeto(obj3.getDescricao());
				modelosEmail.setDataEncerramentoPrevisto(modelosEmail.ajustaDataHora(obj3.getEncerramentoPrevisto()));
				modelosEmail.setLink(link);
				modelosEmail.criaModeloEmailAtrasado();

				exchangeServico.setAssunto(modelosEmail.getAssuntoProjetoAtrasado());
				exchangeServico.setTexto(modelosEmail.getAtrasado());

				exchangeServico.enviaEmail();

			} else {

				// E-MAIL DE TAREFA EM ATRASO
				modelosEmail.setNomeDoProjeto(obj3.getDescricao());
				modelosEmail.setPercentualRealizado(obj3.getPercentualRealizado());
				modelosEmail.setPercentualPrevisto(obj3.getPercentualPrevisto());
				modelosEmail.setLink(link);
				modelosEmail.criaModeloEmailTarefaEmAtraso();

				exchangeServico.setAssunto(modelosEmail.getAssuntoTarefaAtrasada());
				exchangeServico.setTexto(modelosEmail.getTarefaEmAtraso());

				exchangeServico.enviaEmail();

			}
			preencheDataDoEnvioDeEmails(obj3, listaParticipantes);
		}

	}

	public void preencheDataDoEnvioDeEmails(EstatisticasProjeto obj, List<Participante> colaboradoresProjeto) {

		CafeProjetos cafeProjetos = new CafeProjetos();

		if (obj.getEmail1() == null) {
			obj.setEmail1(new Date());
		} else if (obj.getEmail2() == null) {
			obj.setEmail2(new Date());
		} else {
			cafeProjetos.setIdProjeto(obj.getId());
			cafeProjetos.setDataGeracao(new Date());

			String concatenacao = "";

			for (Participante obj2 : colaboradoresProjeto) {

				concatenacao += obj2.getNome() + " | ";

			}

			cafeProjetos.setColaboradoresProjeto(concatenacao);

			projetosDao.updateCafeProjetos(cafeProjetos);

		}

		projetosDao.update(obj);

	}

	// MÉTODO INATIVADO, PARA ATIVAR NOVAMENTE UTILIZAR O ATALHO "Ctrl + /"
	// SELECIONANDO TODAS AS LINHAS DO MÉTODO
//	public void enviaEmailGoogle() throws Exception {
//
//		List<EstatisticasProjeto> listaProjetosEnvioEmail = new ArrayList<EstatisticasProjeto>();
//
//		listaProjetosEnvioEmail = projetosDao.pesquisaEnvioEmailPorStatus();
//
//		for (EstatisticasProjeto obj3 : listaProjetosEnvioEmail) {
//
//			ConnectionApiServico connectionApiServico = new ConnectionApiServico();
//			Projeto projeto = new Projeto();
//
//			ModelosEmail modelosEmail = new ModelosEmail();
//
//			GmailServico gmailServico = new GmailServico();
//
//			List<Participante> listaParticipantes = new ArrayList<>();
//
//			String link = "https://diferpan.stratws.com/OportunidadesDeMelhoria/SolucoesDeProblemas/Edit/"
//						+ "1120120459342";
//			connectionApiServico.setWebService("https://diferpan.api.stratws.com/api/SolucoesDeProblemas/");
//			connectionApiServico.setId("1120120459342"/* obj3.getId().toString() */);
//
//			System.out.println(connectionApiServico.getWebService() + connectionApiServico.getId());
//			
//			//CONSOME A API
//			projeto = connectionApiServico.conectaSite();
//
//			listaParticipantes = projeto.getElaboradores();
//
//			for (Participante obj2 : listaParticipantes) {
//				gmailServico.listaEmail.add(new InternetAddress(obj2.getEmail(), obj2.getNome()));
//			}
//
//			if (obj3.getStatus().equals("Concluído")) {
//				
//				//E-MAIL DE CONCLUÍDO
//				modelosEmail.setNomeDoProjeto(obj3.getDescricao());
//				modelosEmail.criaModeloEmailConcluido();
//
//				gmailServico.setAssunto(modelosEmail.getAssuntoConcluídoComSucesso());
//				gmailServico.setTexto(modelosEmail.getFinalizadoComSucesso());
//				gmailServico.enviaEmail();
//
//			} else if (obj3.getStatus().equals("Quase concluído")) {
//				
//				//E-MAIL DE QUASE CONCLUÍDO
//				modelosEmail.setNomeDoProjeto(obj3.getDescricao());
//				modelosEmail.setLink(link);
//				modelosEmail.criaModeloEmailQuaseConcluido();
//
//				gmailServico.setAssunto(modelosEmail.getAssuntoQuaseConcluido());
//				gmailServico.setTexto(modelosEmail.getQuaseConcluido());
//				gmailServico.enviaEmail();
//
//			} else if (obj3.getStatus().equals("Atrasado")) {
//				
//				//E-MAIL DE PROJETO ATRASADO
//				modelosEmail.setNomeDoProjeto(obj3.getDescricao());
//				modelosEmail.setDataEncerramentoPrevisto(obj3.getEncerramentoPrevisto());
//				modelosEmail.setLink(link);
//				modelosEmail.criaModeloEmailAtrasado();
//
//				gmailServico.setAssunto(modelosEmail.getAssuntoProjetoAtrasado());
//				gmailServico.setTexto(modelosEmail.getAtrasado());
//				gmailServico.enviaEmail();
//
//			} else {
//				
//				//E-MAIL DE TAREFA ATRASADA
//				modelosEmail.setNomeDoProjeto(obj3.getDescricao());
//				modelosEmail.setPercentualRealizado(obj3.getPercentualRealizado());
//				modelosEmail.setPercentualPrevisto(obj3.getPercentualPrevisto());
//				modelosEmail.setLink(link);
//				modelosEmail.criaModeloEmailTarefaEmAtraso();
//
//				gmailServico.setAssunto(modelosEmail.getAssuntoTarefaAtrasada());
//				gmailServico.setTexto(modelosEmail.getTarefaEmAtraso());
//				gmailServico.enviaEmail();
//
//			}
//
//		}
//
//	}
}