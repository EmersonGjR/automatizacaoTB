package com.app;

/**
 *	Made By Ém3R$1n Fitipaldi, arch and nvim user rsrsrs 
 *
 */
import com.app.Exctract;
import java.io.FileWriter;

public class App {
	public static void main(String[] args) {
		// pesquisando vaga para programador *_*
		String programador = "vagas-empregos/programador";
		String site = "https://www.trabalhabrasil.com.br/";
		String search = site + programador;
		Exctract exctract = new Exctract(search);
		exctract.setExctract(0);
		String good = exctract.getGood();
		String bad = exctract.getBad();
		// Apply apply = new Apply(
		// "https://www.trabalhabrasil.com.br/Jobs/Candidatar?idVaga=10396858&userId=19112281");
		// output para saber quantas aplicaram certo ou não
		try (FileWriter fileWriter = new FileWriter(
				"/yourPathHere/outProgram.txt")) {
			fileWriter.write("good");
			fileWriter.write(good);
			fileWriter.write("bad");
			fileWriter.write(bad);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
