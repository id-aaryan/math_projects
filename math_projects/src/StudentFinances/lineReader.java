package StudentFinances;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class lineReader {
  public void lineReader() throws FileNotFoundException {
      Scanner sc = new Scanner(new File("src/StudentFinances/DataExport.csv"));
      while(sc.hasNext()) {
          System.out.println(sc);
      }
  }



  public static void main(String[] args) throws FileNotFoundException {
    var lineReader = new lineReader();
    lineReader.lineReader();
  }
}
