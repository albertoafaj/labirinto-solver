import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		// LE O ARQUIVO
		String filePath = JOptionPane.showInputDialog("Informe o caminho completo do arquivo de entrada do labirinto:");

		if (filePath == null || filePath.trim().equals("")) {
			JOptionPane.showMessageDialog(null,
					"Caminho do arquivo deve ser informado",
					"Alerta",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		File f = new File(filePath);
		if (!f.exists() || f.isDirectory()) {
			JOptionPane.showMessageDialog(null,
					"Caminho do arquivo informado � inv�lido",
					"Alerta",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		List<String> lines = new ArrayList<String>();
		try {
			FileInputStream fstream = new FileInputStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			String strLine;
			while ((strLine = br.readLine()) != null)
				lines.add(strLine);

			fstream.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"N�o foi poss�vel ler o arquivo de entrada",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		String[] dimensoes = lines.get(0).split(" ");
		int linhas = Integer.parseInt(dimensoes[0]);
		int colunas = Integer.parseInt(dimensoes[1]);

		// Preenche matriz do labirinto
		String[][] matriz = new String[linhas][colunas];
		int lAtual = -1; // Posi��o inicial: linha
		int cAtual = -1; // Posi��o inicial: coluna
		int lSaida = -1; // Sa�da: linha
		int cSaida = -1; // Sa�da: coluna

		// percorre toda a matriz (a partir da segunda linha do arquivo texto) para
		// identificar a posi��o inicial e a sa�da
		for (int l = 1; l < lines.size(); l++) {
			String[] line = lines.get(l).split(" ");
			for (int c = 0; c < line.length; c++) {
				String ll = line[c];
				matriz[l - 1][c] = ll;

				if (ll.equals("X")) {
					// Posi��o inicial
					lAtual = l - 1;
					cAtual = c;
				} else if (ll.equals("0") && (l == 1 || c == 0 || l == lines.size() - 1 || c == line.length - 1)) {
					// Sa�da
					lSaida = l - 1;
					cSaida = c;
				}
			}
		}

		// Posi��o m�xima de linha e coluna que pode ser movida (borda)
		int extremidadeLinha = linhas - 1;
		int extremidadeColuna = colunas - 1;

		System.out.println(lAtual);
		System.out.println(cAtual);
		System.out.println(lSaida);
		System.out.println(cSaida);

		// Guarda o trajeto em uma list de string e j� inicia com a posi��o de origem
		List<String> resultado = new ArrayList<String>();
		resultado.add("O [" + (lAtual + 1) + ", " + (cAtual + 1) + "]");

		// Percorre a matriz (labirinto) at� encontrar a sa�da, usando as regras de
		// prioridade e posi��es n�o visitadas, e vai armazenando o trajeto na list
		// resultado
		boolean achouSaida = lAtual == lSaida && cAtual == cSaida;

		int lAnterior = lAtual;
		int cAnterior = cAtual;
		while (!achouSaida) {
			// resultado
			// resultado
			// resultado
			matriz[lAtual][cAtual] = "1";

			if (lAtual > 0 && matriz[lAtual - 1][cAtual].equals("0")) {
				// if Pode ir para cima? Ent�o move e guarda o movimento C na list resultado
				System.out.println(lAtual + "|" + cAtual);
				System.out.println(matriz[lAtual - 1][cAtual].equals("0"));
				lAnterior = lAtual;
				cAnterior = cAtual;
				lAtual--;
				resultado.add("C [" + (lAtual + 1) + ", " + (cAtual + 1) + "]");
			} else if (cAtual > 0 && matriz[lAtual][cAtual - 1].equals("0")) {
				// else if Pode ir para esquerda? Ent�o move e guarda o movimento E na list
				lAnterior = lAtual;
				cAnterior = cAtual;
				cAtual--;
				resultado.add("E [" + (lAtual + 1) + ", " + (cAtual + 1) + "]");
			} else if (cAtual < extremidadeColuna && matriz[lAtual][cAtual + 1].equals("0")) {
				// else if Pode ir para direita? Ent�o move e guarda o movimento D na list
				lAnterior = lAtual;
				cAnterior = cAtual;
				cAtual++;
				resultado.add("D [" + (lAtual + 1) + ", " + (cAtual + 1) + "]");
			} else if (lAtual < extremidadeLinha && matriz[lAtual + 1][cAtual].equals("0")) {
				// else if Pode ir para baixo? Ent�o move e guarda o movimento B na list
				lAnterior = lAtual;
				cAnterior = cAtual;
				lAtual++;
				resultado.add("B [" + (lAtual + 1) + ", " + (cAtual + 1) + "]");
			} else {
				// else tem que voltar para a posi��o anterior
				System.out.println(matriz[lAnterior][cAnterior]);
				matriz[lAnterior][cAnterior] = "0";
				System.out.println(matriz[lAnterior][cAnterior]);
				System.out.println("Voltar uma casa");
			}

			// Achou a sa�da?
			System.out.println(resultado);

			achouSaida = lAtual == lSaida && cAtual == cSaida;
		}

		// Escreve no arquivo texto a sa�da
		String folderPath = f.getParent();
		String fileName = f.getName();
		String outputPath = folderPath + "\\saida-" + fileName;

		try {
			File fout = new File(outputPath);
			FileOutputStream fos = new FileOutputStream(fout);

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

			for (int i = 0; i < resultado.size(); i++) {
				bw.write(resultado.get(i));
				bw.newLine();
			}

			bw.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"N�o foi poss�vel escreve o arquivo de sa�da",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

}
