package com.app;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;

public class Apply {
	String url;

	public Apply(String url) {
		this.url = url;
	}

	public Boolean sendApply() {
		HttpPost postRequest = new HttpPost(getUrl());
		setPost(postRequest);
		if (sendPost(postRequest)) {
			return true;
		}
		return false;
	}

	private String getUrl() {
		String[] urlSplited = this.url.split("/");
		for (int i = 0; i < urlSplited.length; i++) {
			System.out.println("Array: " + urlSplited[i] + "indice: " + i);
		}
		String urlFinal = "https://www.trabalhabrasil.com.br/Jobs/Candidatar?idVaga=" + urlSplited[3]
				+ "&userId=youUserId";
		// No userId parametro vc tem que colocar o seu id;
		return urlFinal;
	}

	private Boolean checkContainsTrue(CloseableHttpResponse response) {
		try {

			HttpEntity responseEntity = response.getEntity();
			String responseString = EntityUtils.toString(responseEntity);
			System.out.println(responseString);
			String[] arrayString = responseString.split(":");
			String[] arrayStringFinal = arrayString[1].split(",");
			if (arrayStringFinal[0].equals("true")) {
				System.out.println("true");
				return true;
			}
			// for (int i = 0; i < arrayStringFinal.length; i++) {
			// System.out.println("Array: " + arrayStringFinal[i] + "indice: " + i);
			// }
		} catch (IOException e) {

			System.out.println("fucking hell false");
			e.printStackTrace();
		}
		return false;

	}

	private Boolean checkResponseHealth(CloseableHttpResponse response) {
		if (response.getStatusLine().getStatusCode() == 200 && checkContainsTrue(response)) {
			System.out.println("checkResponseHealth true");
			return true;
		}
		return false;
	}

	private Boolean sendPost(HttpPost postRequest) {
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			CloseableHttpResponse response = httpClient.execute(postRequest);
			if (checkResponseHealth(response)) {
				// debugging
				System.out.println("true ae sim");
				return true;
			} else {
				// debugging
				System.out.println("Nop");
			}
			response.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void setPost(HttpPost postRequest) {
		postRequest.setHeader("Cookie", coockie());// coockie mto grande, por isso coloquei na função
		postRequest.setHeader("User-Agent",
				"Mozilla/5.0 (X11; Linux x86_64; rv:121.0) Gecko/20100101 Firefox/121.0");
		postRequest.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
		postRequest.setHeader("Content-Type", "application/json");
		postRequest.setHeader("X-Requested-With", "XMLHttpRequest");
		postRequest.setHeader("Sec-Fetch-Dest", "empty");
		postRequest.setHeader("Sec-Fetch-Mode", "cors");
		postRequest.setHeader("Sec-Fetch-Site", "same-origin");
		postRequest.setHeader("Te", "trailers");
		// ... other headers

		// Set content type and body:
		StringEntity entity = new StringEntity(
				"{\"confirmouVagaInativa\":false,\"confirmouCidadeDiferente\":true}", "UTF-8");
		entity.setContentType("application/json");
		postRequest.setEntity(entity);
	}

	private String coockie() {
		return "vc tem que colocar seus coockies aqui";
	}
}
