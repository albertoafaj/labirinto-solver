import java.io.File;
import java.util.List;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		// Ler o arquivo e armazenar número de linhas e colunas

		String filePath = JOptionPane.showInputDialog("Informe o caminho completo do arquivo de entrada do labirinto:");
		// String filePath =
		// "D:\\Projects\\Testes\\atos-desafio-labirinto\\Template-Java\\entrada-labirinto_sem_saida.txt";
		File f = new File(filePath);
		FileGet inputFileReader = new FileGet(f);
		inputFileReader.readFile();
		int linhas = inputFileReader.getRows();
		int colunas = inputFileReader.getColumns();
		List<String> lines = inputFileReader.getLines();
		if (!inputFileReader.isValidFile()) {
			JOptionPane.showMessageDialog(null,
					"Caminho do arquivo deve ser informado",
					"Alerta",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		System.out.println(linhas);
		System.out.println(colunas);
		System.out.println(lines);

		// Preencher matriz do labirinto e identificar a posição inicial e a saída

		LabyrinthGet labyrint = new LabyrinthGet(linhas, colunas, lines);
		String[][] matriz = labyrint.getMatriz();
		int lAtual = labyrint.getlAtual();
		int cAtual = labyrint.getcAtual();
		int lSaida = labyrint.getlSaida();
		int cSaida = labyrint.getcSaida();

		System.out.println("Main.main()" + lSaida);
		System.out.println("Main.main()" + cSaida);

		// Resolver labirinto e salvar resultado;

		LabyrinthSolve labyrinthResult = new LabyrinthSolve(matriz, lAtual, cAtual, lSaida, cSaida, linhas, colunas);
		List<String> resultado = labyrinthResult.procuraSaida();

		// Escreve no arquivo texto a sa�da

		FileSave outputFileGenerator = new FileSave(f, resultado);
		outputFileGenerator.saveFile();

	}

}
