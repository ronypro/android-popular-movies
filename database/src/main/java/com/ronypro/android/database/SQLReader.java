package com.ronypro.android.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Classe para ler scripts SQL. <br>
 * Os scripts devem seguir as seguintes regras: <br>
 * - As instruções SQL devem ser terminadas com ; <br>
 * - Comentários devem ser feitos em linhas iniciadas com -- <br>
 * <br>
 * <br>
 * Os espaços no inicio e fim de cada linha do arquivo são desconsiderados.
 * 
 * @author Rahony
 * 
 */
public class SQLReader {

	private static final String COMMENT = "--";
	private static final String SQL_END = ";";

	private final BufferedReader reader;
	private String next;

	/**
	 * Abre um arquivo para que as instruções SQL possam ser obtidas.
	 * 
	 * @param file
	 * @throws FileNotFoundException
	 */
	public SQLReader(File file) throws FileNotFoundException {
		this(new FileInputStream(file));
	}

	/**
	 * Prepara o inputStream para que as instruções SQL possam ser obtidas.
	 * 
	 * @throws FileNotFoundException
	 */
	public SQLReader(InputStream input) throws FileNotFoundException {
		this.reader = new BufferedReader(new InputStreamReader(input));
	}

	/**
	 * Indica se existe uma próxima instrução SQL no arquivo.
	 * 
	 * @return
	 * @throws IOException
	 */
	public boolean hasNext() throws IOException {
		next = getNextSQL();
		return next != null;
	}

	/**
	 * Retona a próxima instrução SQL do arquivo.
	 * 
	 * @return Próxima instrução SQL ou null caso não exista.
	 */
	public String next() {
		return next;
	}

	/**
	 * Fecha o arquivo.
	 */
	public void close() {
		try {
			reader.close();
		} catch (IOException ignorar) {
		}
	}

	/**
	 * Obtem a próxima instrução SQL presente no arquivo baseada nas regras descritas nesta classe.
	 * 
	 * @return Próxima instrução SQL presente no arquivo ou null caso não exista mais instruções.
	 * @throws IOException
	 */
	private String getNextSQL() throws IOException {
		StringBuilder sql = new StringBuilder();

		String linha;
		while ((linha = reader.readLine()) != null) {
			linha = linha.trim();

			if (linha.isEmpty() || linha.startsWith(COMMENT)) {
				continue;
			}

			boolean sqlEnd = linha.endsWith(SQL_END);
			if (sqlEnd) {
				linha = linha.substring(0, linha.length() - 1);
			}

			if (sql.length() > 0)
				sql.append(' ');
			sql.append(linha);

			if (sqlEnd && sql.length() > 0) {
				break;
			}
		}

		if (sql.length() == 0)
			return null;

		return sql.toString();
	}

}
