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

    final static String IN_FILE_NAME = "C:\\abstracts\\WICSA2001.txt";
    final static String PATH = "C:\\abstracts\\";

    final static String OUTPUT_FILE_NAME = "C:\\abstracts\\WICSA2001new.txt";

   // final static Charset ENCODING = StandardCharsets.UTF_8;
    final static Charset ENCODING = StandardCharsets.US_ASCII;

    public static void main(String[] args) {

        // uncomment these to test a specific file
        //Conference test = new Conference( IN_FILE_NAME, OUTPUT_FILE_NAME);
        //test.processAbstracts();


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
            Conference test = new Conference( inputFile, outFile);
            test.processAbstracts();
        }/*
*/



    }


}
