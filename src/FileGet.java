import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileGet {

  private File file;
  private int rows;
  private int columns;
  private boolean fileExist;
  private boolean fileIsDirectory;
  private List<String> lines = new ArrayList<>();

  public FileGet(File file) {
    this.file = file;
    this.fileExist = file.exists();
    this.fileIsDirectory = file.isDirectory();
  }

  public boolean isValidFile() {

    if (file == null || !fileExist || fileIsDirectory) {

      return false;
    }
    return true;
  }

  public boolean readFile() {

    try {
      FileInputStream fstream = new FileInputStream(file);
      BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
      String strLine;
      while ((strLine = br.readLine()) != null) {
        lines.add(strLine);
      }

      fstream.close();
    } catch (Exception e) {
      return false;
    }

    if (lines.isEmpty()) {
      return false;
    }

    String[] dimensions = lines.get(0).split(" ");
    rows = Integer.parseInt(dimensions[0]);
    columns = Integer.parseInt(dimensions[1]);

    return true;
  }

  public int getRows() {
    return rows;
  }

  public int getColumns() {
    return columns;
  }

  public List<String> getLines() {
    return lines;
  }

  public File getFile() {
    return file;
  }

  public boolean isFileIsDirectory() {
    return fileIsDirectory;
  }

  public boolean isFileExist() {
    return fileExist;
  }

}
