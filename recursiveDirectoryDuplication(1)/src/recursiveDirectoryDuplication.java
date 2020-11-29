import java.io.*;
import java.util.Scanner;

public class recursiveDirectoryDuplication {

    public static void main(String[] args) throws Exception {

        //create scanner object to take user input
        Scanner scnr = new Scanner(System.in);

        //ask the user for the source directory and store it in sourceDir
        System.out.println("Enter a source directory.");
        String sourceDir = scnr.nextLine();

        //ask the user for the destination and store it in destination
        System.out.println("Enter a destination.");
        String destinationDir = scnr.nextLine();

        recursiveMethod(sourceDir, destinationDir);

    }


    public static void copyFile(String source, String destination) throws Exception {

        //declare file
        File sourceFile = null;
        File destFile = null;

        //declare stream variables
        FileInputStream sourceStream = null;
        FileOutputStream destStream = null;

        //declare buffering variables
        BufferedInputStream bufferedSource = null;
        BufferedOutputStream bufferedDestination = null;

        try {

            //create file objects for source/destination files
            sourceFile = new File(source);
            destFile = new File(destination);

            // create file streams for the source and destination files
            sourceStream = new FileInputStream(sourceFile);
            destStream = new FileOutputStream(destFile);

            // buffer the file streams -- set the buffer sizes to 8K
            bufferedSource = new BufferedInputStream(sourceStream, 8182);
            bufferedDestination = new BufferedOutputStream(destStream, 6192);

            // use an integer to transfer data between files
            int transfer;

            // tell the user what is happening
            System.out.println("beginning file copy:");
            System.out.println("\tcopying " + source);
            System.out.println("\tto " + destination);

            // read a byte, checking for end of file (-1 is returned by read at EOF)
            while ((transfer = bufferedSource.read()) != -1) {

                // write a byte
                bufferedDestination.write(transfer);

            }

        } catch (IOException e) {

            e.printStackTrace();
            System.out.println(" An unexpected I/O error occurred.");

        } finally {

            // close file streams
            if (bufferedSource != null)
                bufferedSource.close();

            if (bufferedDestination != null)
                bufferedDestination.close();

            System.out.println("Files closed. Copy complete.");

        } // end finally
    }

    public static void recursiveMethod(String source, String destination) throws Exception {


        File sourceFile = new File(source);
        File destinationFile = new File(destination);

        if (sourceFile.exists())
        {
            if (sourceFile.isDirectory())
            {
                destinationFile.mkdirs();

                //create a string array with names of entries in the directory
                String entries[] = sourceFile.list();

                //create new directories for each entry
                for (int i = 0; i < entries.length; i++)
                {
                    //create File object for source and destination
                    File Src = new File(sourceFile, entries[i]);
                    File Dest = new File(destinationFile, entries[i]);

                    //call recursive copy method
                    recursiveMethod(Src.getAbsolutePath(), Dest.getAbsolutePath());
                }//end for loop

            } //end nested if statement

            else //this branch of the if statement will handle files
            {
                copyFile(sourceFile.getAbsolutePath(), destinationFile.getAbsolutePath());
            }
        } //end outer if statement

    }//end recursiveMethod()
}//end class





