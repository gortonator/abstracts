package nuseattle.edu;

import java.io.*;
import java.util.*;

/**
 * Created by Ian Gortan on 6/24/2016.
 */
public class Conference {
    // max expected papers in a single conference
    private static final int MAX_PAPERS = 1000;

    // array to hold titles and check for duplicates
    private String titles[] = new String[MAX_PAPERS];
    private int titlesIndex, dupCount, skippedCount, total, writeCount = 0;

    // input and output file names
    private String inputFile, outputFile;


    public  Conference (String inFile, String outFile) {
        inputFile = inFile;
        outputFile = outFile ;
    }

    public void processAbstracts() {

        boolean moreAbstracts = true;
        try{
            BufferedReader input =  new BufferedReader(  new FileReader(inputFile));
            BufferedWriter output = new BufferedWriter( new FileWriter(outputFile) ) ;

            while (moreAbstracts = getAbstract (input, output)) {
                System.out.println("still more abstracts");
            }
            System.out.println ("Total abstracts in file: " + total);
            System.out.println ("Total skipped: " + skippedCount);
            System.out.println ("Total duplcate: " + dupCount);
            System.out.println ("Total written: " + writeCount);
            System.out.println(" ===============TITLES======================") ;
            for ( int i= 0; i < titlesIndex ; i++ ) {
                System.out.println (i + ". " + titles[i]);
            }
            input.close();
            output.close();
        } catch (IOException ex) {
            System.out.println (ex.toString());
            System.out.println("IO exception");
        }

    }

    /* private BufferedReader openConferenceFile(String aFileName) throws IOException {

        FileReader fr = new FileReader(aFileName);
        BufferedReader br = new BufferedReader(fr);
        return br;
    }
*/
    private boolean checkDuplicateAbstract(String newTitle) {
        // checks to see if title passed as parameter exists in titles array.
        // if exists, return true
        // If doesn't exist - store in titles and return false

        //System.out.println("checking for duplicates" + titlesIndex);
        int i =0;
        String title = newTitle.toLowerCase();
        System.out.println (title + "Lower case????");
        while (i < titlesIndex) {
            if (titles[i].equals(title)) {
                System.out.println("duplicate found");
                dupCount++;
                return true;
            }
            i++;
        }
        // not a duplicate, so store
        System.out.println (title + "-----NOT DUPLICATE");
        titles[titlesIndex] = title;
        titlesIndex++;
        return false;
    }

    // We don't want this abstract as its duplicate or irrelevant,
    // so simply skip over it
    private void skipAbstract ( BufferedReader br) throws IOException {
        String line = br.readLine();
        while( ( (line != null) && !(line.trim().isEmpty()) ) ){
            //  System.out.println("COPYING LINE");
            line  = br.readLine();
        }

    }

    // =============================================================================
    //Ask user if the abstract title is relevant
    //returns true if user answers 'y
    //==============================================================================
    private boolean isRelevant (String title) {
        Scanner in = new Scanner(System.in);

        System.out.println ("Is this abstract relevant (Y/N) =====)"+ title + "====");
        String response = in.nextLine();
        response.toLowerCase();
        while(  (response.charAt(0) != 'y')  && ( response.charAt(0) != 'n') ) {
            System.out.println ("Incorrect input: Is this abstract relevant (Y/N) =====)"+ title + "====");
            response = in.nextLine();
            response.toLowerCase();
        }

        if ( response.charAt(0) == 'y') {
            return true;
        }
        else {
            return false;
        }

    }

    private boolean getAbstract ( BufferedReader br, BufferedWriter bw) throws IOException {
        ArrayList<String> abstractText = new ArrayList<String>();  // stores text for abstract

        String line = br.readLine();
        String title = line;
        while( ( (line != null) && (line.trim().isEmpty()) ) ){
            System.out.println("EMPTY LINE DUDE");
            line  = br.readLine();
            title = line;
        }

        if (title != null) {
            // process it
            System.out.println("TITLE FOUND");
            total++;
            int i =0;
            while (title.charAt(i) != ' ') {
                // skip over number at start of abstract by finding first space
                i++;
            }
            System.out.println();
            //store title stripped of number so we can check for duplicates
            if ( checkDuplicateAbstract (title.substring(i+1)) ) {
                skipAbstract (br);
            } else {
                if (isRelevant(title.substring(i+1)) ) {
                    writeCount++;
                    bw.write(title);
                    bw.newLine();
                    line  = br.readLine();
                    while( ( (line != null) && !(line.trim().isEmpty()) ) ){
                        //  System.out.println("COPYING LINE");
                        bw.write(line) ;
                        bw.newLine();
                        line  = br.readLine();
                    }
                    bw.newLine();
                }else {
                    skippedCount++;
                    skipAbstract (br);
                }
            }


        }else {
            // reached end of file
            return false ;
        }

        return true;

    }
}
