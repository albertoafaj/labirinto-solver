import java.util.List;

import javax.swing.JOptionPane;

public class LabyrinthGet {

  private String[][] matriz;
  private int lAtual;
  private int cAtual;
  private int lSaida;
  private int cSaida;

  public LabyrinthGet(int linhas, int colunas, List<String> lines) {
    matriz = new String[linhas][colunas];
    lAtual = -1;
    cAtual = -1;
    lSaida = -1;
    cSaida = -1;
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
    if (lSaida == -1 || cSaida == -1) {
      JOptionPane.showMessageDialog(null,
          "O labirinto não tem saída",
          "Alerta",
          JOptionPane.WARNING_MESSAGE);
      throw new Error("Labirinto sem Saída");
    }
  }

  // Getters e setters para as variáveis privadas

  public String[][] getMatriz() {
    return matriz;
  }

  public int getlAtual() {
    return lAtual;
  }

  public int getcAtual() {
    return cAtual;
  }

  public int getlSaida() {
    return lSaida;
  }

  public int getcSaida() {
    return cSaida;
  }

}
