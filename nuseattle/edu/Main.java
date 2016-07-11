package nuseattle.edu;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    final static String IN_FILE_NAME = "C:\\abstracts\\ECSA2007.txt";
    final static String PATH = "C:\\abstracts\\";

    final static String OUTPUT_FILE_NAME = "C:\\abstracts\\ECSA2007new.txt";
   // final static Charset ENCODING = StandardCharsets.UTF_8;
    final static Charset ENCODING = StandardCharsets.US_ASCII;

    public static void main(String[] args) {
        FilenameFilter filter  = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        } ;
        File folder = new File (PATH) ;

        File[] inputFileList = folder.listFiles(filter) ;

        for (File f : inputFileList ) {
            String inputFile = PATH + f.getName();
            String outFile = PATH +  "new" + f.getName();
            System.out.println(inputFile);
            System.out.println(outFile);
            //Conference test = new Conference( inputFile, outFile);
            //test.processAbstracts();
        }



     /*   try {
         //   List<String> lines =
           // readTextFile(FILE_NAME);
            System.out.println("File read ok");
            //writeTextFile(lines, OUTPUT_FILE_NAME);
            //System.out.println("File written - we're done");
        }catch( IOException ex){
            System.out.println (ex.toString());
            System.out.println("IO exception");
        } */
    }

    //public static List<String> readTextFile(String aFileName) throws IOException {
    public static void readTextFile(String aFileName) throws IOException {
        String line = null;
        FileReader fr = new FileReader(aFileName);
        BufferedReader br = new BufferedReader(fr);
        while ((line  = br.readLine())!= null) {
            if (line.trim().isEmpty()){ System.out.println("EMPTY LINE DUDE"); }
            System.out.println(line);
        }
        br.close();


        /*Path path = Paths.get(aFileName);
        System.out.println("opened");
        return Files.readAllLines(path, ENCODING);
        */
    }

    public static void writeTextFile(List<String> aLines, String aFileName) throws IOException {
        Path path = Paths.get(aFileName);
        Files.write(path, aLines, ENCODING);
    }
}
