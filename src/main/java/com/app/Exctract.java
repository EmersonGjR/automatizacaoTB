package com.app;

import com.app.Apply;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Exctract {
	String url;
	Integer good = 0;
	Integer page = 0;
	Integer bad = 0;

	public Exctract(String url) {
		this.url = url;
	}

	public String getGood() {
		return good.toString();
	}

	public String getBad() {
		return bad.toString();
	}

	public void setExctract(Integer pageF) {
		try {
			String urlFinal = this.url;
			// checa se é a primeira para nao adicionar a pagina
			if (pageF != 0) {
				urlFinal = this.url + "?pagina=" + pageF;
			}
			Document document = Jsoup.connect(urlFinal).get();
			Elements elements = document.getElementsByClass("jg__job");
			// Para cada elemento pega o href da pagina para candidatar
			for (Element ads : elements) {
				Elements a = ads.getElementsByTag("a");
				String argApply = a.attr("href");
				System.out.println(argApply);
				Apply apply = new Apply(argApply);
				if (apply.sendApply()) {

					System.out.println("mandou certinho");
					good++;
				} else {

					System.out.println("mandou errado");
					bad++;
				}
				System.out.println(argApply);
			}
			// recursividade para ir passando as paginas
			if (this.page < 644) {
				this.page++;
				setExctract(this.page);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
