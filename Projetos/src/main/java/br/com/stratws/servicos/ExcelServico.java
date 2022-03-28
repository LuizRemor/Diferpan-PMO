package br.com.stratws.servicos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.stratws.dao.ProjetosDaoJDBC;
import br.com.stratws.entidades.EstatisticasProjeto;

public class ExcelServico {

	ArquivoServico arquivoEscolhido = new ArquivoServico();
	private String caminhoArquivo = arquivoEscolhido.escolheArquivo().toString();
	private ProjetosDaoJDBC projetosDaoJDBC = new ProjetosDaoJDBC();
	// = "C:\\Users\\luiz.neto\\Desktop\\Programa Stratws\\Relatorio Detalhado de
	// Solucao de Problema.xlsx";

	public List<EstatisticasProjeto> populaEstatisticasProjeto() throws IOException, ParseException {

		List<EstatisticasProjeto> listaEstatisticasProjetos = new ArrayList<EstatisticasProjeto>();

		try {

			FileInputStream arquivo = new FileInputStream(new File(caminhoArquivo));
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(arquivo);
			XSSFSheet sheetLambda = workbook.getSheetAt(0);
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

			Iterator<Row> rowIterator = sheetLambda.iterator();

			//ProjetosDaoJDBC projetosDaoJDBC = new ProjetosDaoJDBC();

			// pula a primeira linha
			rowIterator.next();

			while (rowIterator.hasNext()) {

				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				EstatisticasProjeto estatisticasProjeto = new EstatisticasProjeto();

				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();

					switch (cell.getColumnIndex()) {

					// ID DO PROJETO
					case 0:

						estatisticasProjeto.setId(new BigDecimal(cell.getNumericCellValue()));
						break;

					// ANO
					case 4:

						estatisticasProjeto.setAno(cell.getNumericCellValue());
						break;

					// DESCRICAO
					case 5:

						estatisticasProjeto.setDescricao(cell.getStringCellValue());
						break;

					// AREA DE RESULTADOS
					case 8:

						estatisticasProjeto.setAreaDeResultados(cell.getStringCellValue());
						break;

					// ENCERRAMENTO PREVISTO
					case 12:
						if (!cell.getStringCellValue().isEmpty()) {
							estatisticasProjeto.setEncerramentoPrevisto(formato.parse(cell.getStringCellValue()));
						}
						break;

					// ENCERRAMENTO
					case 13:

						if (!cell.getStringCellValue().isEmpty()) {
							estatisticasProjeto.setEncerramento(formato.parse(cell.getStringCellValue()));
						}
						break;

					// PERCENTUAL REALIZADO
					case 16:
						estatisticasProjeto.setPercentualRealizado(cell.getNumericCellValue());
						break;

					// PERCENTUAL PREVISTO
					case 17:
						estatisticasProjeto.setPercentualPrevisto(cell.getNumericCellValue());
						break;

					// ETAPA
					case 19:
						estatisticasProjeto.setEtapa(cell.getStringCellValue());
						break;

					// RESULTADOS ALCANÇADOS
					case 35:
						estatisticasProjeto.setResultadosAlcancados(cell.getStringCellValue());
						break;

					// ANÁLISE CRÍTICA
					case 37:
						estatisticasProjeto.setAnaliseCritica(cell.getStringCellValue());
						break;
					}

				}

				listaEstatisticasProjetos.add(estatisticasProjeto);
				//projetosDaoJDBC.update(estatisticasProjeto);
			}

			for (EstatisticasProjeto obj : listaEstatisticasProjetos) {
				
				if (projetosDaoJDBC.findById(obj) != null) {

				EstatisticasProjeto falsaEstatisticasProjeto = projetosDaoJDBC.findById(obj);

					if (falsaEstatisticasProjeto.getEmail1() != null) {
						if (falsaEstatisticasProjeto.getEmail2() != null) {
							obj.setEmail2(falsaEstatisticasProjeto.getEmail2());
						}
						obj.setEmail1(falsaEstatisticasProjeto.getEmail1());
					}
					projetosDaoJDBC.update(obj);
				}
			}

			arquivo.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Arquivo Excel não encontrado!");
		}
		return listaEstatisticasProjetos;
	}
}