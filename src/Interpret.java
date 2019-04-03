import java.io.*;
import java.util.HashSet;

public class Interpret {
    public static MyHashSet myHashSet = new MyHashSet();

    public static void main(String[] args) {
        Contex contex = new Contex();
  //      HashSet<String> my2hash = new HashSet<>();


         try {
            FileInputStream fstream = new FileInputStream("D:\\Google Диск\\`Учёба\\`6 семестр\\Системное ПО\\Моя лаба 4\\SPO2\\src\\in.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine = br.readLine();
            while (strLine != null) {
                strLine = strLine.replaceAll("\n", "");
                contex.evaluate(strLine);
                strLine = br.readLine();
            }
        } catch (FileNotFoundException e) {
          System.out.println("Ошибкa: файл не найден");
       } catch ( IOException e){
             System.out.println("Ошибка: иное");
         }
    }


}