import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.swing.JOptionPane;

public class FileSave {

  File file;
  String folderPath;
  String fileName;
  String outputPath;
  List<String> resultado;

  public FileSave(File file, List<String> resultado) {
    this.file = file;
    this.folderPath = file.getParent();
    this.fileName = file.getName();
    this.outputPath = folderPath + "\\saida-" + fileName;
    this.resultado = resultado;
  }

  public void saveFile() {

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
