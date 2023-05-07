import java.util.ArrayList;
import java.util.List;

public class LabyrinthSolve {
  private String[][] matriz;
  private int lAtual;
  private int cAtual;
  private int lSaida;
  private int cSaida;
  private int extremidadeLinha;
  private int extremidadeColuna;
  private static final String ORIGEM = "X";
  private static final String CAMINHO_LIVRE = "0";
  private static final String CAMINHO_VISITADO = "P";

  public LabyrinthSolve(String[][] matriz, int lAtual, int cAtual, int lSaida, int cSaida, int linhas, int colunas) {
    this.matriz = matriz;
    this.lAtual = lAtual;
    this.cAtual = cAtual;
    this.lSaida = lSaida;
    this.cSaida = cSaida;
    this.extremidadeLinha = linhas - 1;
    this.extremidadeColuna = colunas - 1;
  }

  public void marcarCaminho(String proximoPasso) {

    if (!matriz[lAtual][cAtual].equals(ORIGEM) && proximoPasso.equals(CAMINHO_LIVRE)) {
      matriz[lAtual][cAtual] = CAMINHO_VISITADO;
    }
  };

  public void mover(List<String> resultado, List<Integer> lAnterior, List<Integer> cAnterior,
      List<String> movAnterior, String movAtual, String tipo) {

    if (podeMover("cima", tipo)) {
      // if Pode ir para cima? Ent�o move e guarda o movimento C na list resultado
      moverPara(resultado, lAnterior, cAnterior, movAnterior, "C", tipo, lAtual - 1, cAtual);
    } else if (podeMover("esquerda", tipo)) {
      // else if Pode ir para esquerda? Ent�o move e guarda o movimento E na list
      // resultado
      moverPara(resultado, lAnterior, cAnterior, movAnterior, "E", tipo, lAtual, cAtual - 1);
    } else if (podeMover("direita", tipo)) {
      // else if Pode ir para direita? Ent�o move e guarda o movimento D na list
      // resultado
      moverPara(resultado, lAnterior, cAnterior, movAnterior, "D", tipo, lAtual, cAtual + 1);
    } else if (podeMover("baixo", tipo)) {
      // else if Pode ir para baixo? Ent�o move e guarda o movimento B na list
      // resultado
      moverPara(resultado, lAnterior, cAnterior, movAnterior, "B", tipo, lAtual + 1, cAtual);
    } else {
      // else retorna o caminho
      marcarCaminho(tipo);
      lAtual = lAnterior.get(lAnterior.size() - 1);
      cAtual = cAnterior.get(cAnterior.size() - 1);
      movAtual = movAnterior.get(movAnterior.size() - 1);
      switch (movAtual) {
        case "C":
          resultado.add("B [" + (lAtual + 1) + ", " + (cAtual + 1) + "]");
          break;
        case "B":
          resultado.add("C [" + (lAtual + 1) + ", " + (cAtual + 1) + "]");
          break;
        case "D":
          resultado.add("E [" + (lAtual + 1) + ", " + (cAtual + 1) + "]");
          break;
        case "E":
          resultado.add("D [" + (lAtual + 1) + ", " + (cAtual + 1) + "]");
          break;
        default:
          break;
      }
      lAnterior.remove(lAnterior.size() - 1);
      cAnterior.remove(cAnterior.size() - 1);
      movAnterior.remove(movAnterior.size() - 1);
    }
  }

  // Verifica se pode mover seguindo a ordem de prioridade
  private boolean podeMover(String direcao, String tipo) {
    if (direcao.equals("cima")) {
      return lAtual > 0 && matriz[lAtual - 1][cAtual].equals(tipo);
    } else if (direcao.equals("esquerda")) {
      return cAtual > 0 && matriz[lAtual][cAtual - 1].equals(tipo);
    } else if (direcao.equals("direita")) {
      return cAtual < extremidadeColuna && matriz[lAtual][cAtual + 1].equals(tipo);
    } else if (direcao.equals("baixo")) {
      return lAtual < extremidadeLinha && matriz[lAtual + 1][cAtual].equals(tipo);
    } else {
      return false;
    }
  }

  // Movimenta para a direção selecionada;
  private void moverPara(List<String> resultado, List<Integer> lAnterior, List<Integer> cAnterior,
      List<String> movAnterior, String movimento, String tipo, int l, int c) {
    lAnterior.add(lAtual);
    cAnterior.add(cAtual);
    movAnterior.add(movimento);
    marcarCaminho(tipo);
    lAtual = l;
    cAtual = c;
    resultado.add(movimento + " [" + (lAtual + 1) + ", " + (cAtual + 1) + "]");
  }

  public List<String> procuraSaida() {
    List<String> resultado = new ArrayList<>();
    List<Integer> lAnterior = new ArrayList<>();
    List<Integer> cAnterior = new ArrayList<>();
    List<String> movAnterior = new ArrayList<String>();
    String movAtual = "O";
    boolean achouSaida = lAtual == lSaida && cAtual == cSaida;

    resultado.add("O [" + (lAtual + 1) + ", " + (cAtual + 1) + "]");

    while (!achouSaida) {
      this.mover(resultado, lAnterior, cAnterior, movAnterior, movAtual, CAMINHO_LIVRE);
      achouSaida = lAtual == lSaida && cAtual == cSaida;
    }

    return resultado;
  }

}
