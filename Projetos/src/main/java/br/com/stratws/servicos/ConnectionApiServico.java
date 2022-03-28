package br.com.stratws.servicos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import br.com.stratws.entidades.Projeto;
import br.com.stratws.util.Util;

public class ConnectionApiServico {

	public String webService;// = "https://diferpan.api.stratws.com/api/SolucoesDeProblemas/";
	public String id;// = "1120120442928";
	public int codigoSucesso = 200;
	public Projeto projeto = new Projeto();
	
	public String token = "ZCr6rVhF-FBy88QsUu_DHy5kIvpDwKBuAdGeb-30bDDwiXkUzheeQojY9arCPCHnw65IlsxVxFZqPSHm5TJTB9HnmpH49EYlJUd8e3Btdf"
			  			+ "8zW5hl5LSSFxom-LvGroMr3Bjg3sKvJLAtr64b_xqiIpVTrP-XfHAVz-JpBzw2k86CFKl67aQT_eEiDc3Ir5RqfawbWcntbyQFK3gXAS1V"
			  			+ "XLfsfozhFTwzF5eVYuufQG_rtCgGo4uXq6Jq4ev1ArG4OaUFmEgimOO-oYTzCx8ZI009YowefWCkhp1-leqcXfYmmZuBU7nwQEMciDc0A3"
			  			+ "j4HZ5CWyVlhTIbUAoHfnDfcqL7hvSiSx9dNfyJWM8ISPaybwwJXThHg_VIMvEN2-mVhPZ4I4cNCd0rm5Inw_P87g";

	public Projeto conectaSite() throws Exception {
		
		try {
					
			URL url = new URL(webService + id);
			HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
			conexao.setRequestProperty("Authorization", "Bearer " + token);

			if (conexao.getResponseCode() != codigoSucesso) {
				throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());
			}

			BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
			String jsonEmString = Util.ConverteJsonEmString(resposta);

			Gson gson = new Gson();
			projeto = gson.fromJson(jsonEmString, Projeto.class);

			//System.out.println(jsonEmString);
			//System.out.println(projeto.toString());
			
			return projeto;

		} catch (Exception e) {
			throw new Exception("ERRO: " + e);
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWebService() {
		return webService;
	}

	public void setWebService(String webService) {
		this.webService = webService;
	}
	
}