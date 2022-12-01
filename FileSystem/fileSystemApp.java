package FileSystem;

import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class fileSystemApp{
    Scanner input = new Scanner(System.in);
    ArrayList<String> filesOfApp = new ArrayList<String>();
    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("MMM-dd-yyyy hh:mm:ss a", Locale.getDefault());
    
    public void addNote(){
        System.out.println("\nADD NOTE");
        File fileItems = new File("Notes.txt");
        if (fileItems.exists() == true){
            arraySizeChecker();
        } else {
            System.out.println("\nA new data file is created! \n");
        }

        String current = ZonedDateTime.now(ZoneId.systemDefault()).format(dateTimeFormat);

        System.out.print("Enter note to be added: ");
        String itemInput = input.nextLine();

        filesOfApp.add(itemInput + " / " + current);
        System.out.println("\nNote added successfully!");

        int numFiles = filesOfApp.size()-1;

        if (numFiles == 0){
            dataFileCreator();
        }else{
            arrayToFile();
        }
    }
    
    public void updateNote(){
        System.out.println("\nUPDATE NOTE");
        arraySizeChecker();

        String current = ZonedDateTime.now(ZoneId.systemDefault()).format(dateTimeFormat);
        try{
            System.out.print("Enter the item number to be modified: ");
            int itemNum = input.nextInt();
            input.nextLine();
    
            int testNum = itemNum - 1;
            if(testNum >= filesOfApp.size() || testNum < 0 ){
                System.out.println("\nInvalid Input. Please try again.");
            } else{
                System.out.print("Enter new note to be added: ");
                String userInput = input.nextLine();
                System.out.println("\nNote added successfully!");
    
                filesOfApp.set(testNum, userInput + " / " + current);
                arrayToFile();
            }
        
        }catch(Exception e){
            System.out.print("\nYou entered a value that is not an integer. Please try again.\n");
            input.nextLine();
        }
    }
        
    public void deleteNote(){
        System.out.println("\nDELETE NOTE");
        arraySizeChecker();
        
        System.out.print("Enter the item number to be deleted: ");
        int itemNum = input.nextInt();
        input.nextLine();

        int testNum = itemNum - 1;
        if(testNum >= filesOfApp.size() || testNum < 0 ){
            System.out.println("\nInvalid Input. Please try again.");
        } else{
            filesOfApp.remove(testNum);
            System.out.println("\nNote deleted successfully!");
            arrayToFile();  
        }
    }

    public void displayNotes(){
        try {
            File fileItems = new File("Notes.txt");
            Scanner fileReader = new Scanner(fileItems);

            int counter = 1;
            System.out.println("\n--------------------------------------------------------------------------");
            System.out.println("Your notes (Note / Date Created):");
            if(fileReader.hasNextLine()){
                while (fileReader.hasNextLine()) {
                    String data = fileReader.nextLine();
                    System.out.println(counter + ".) " + data);
                    counter++;
                }
            }else{
                System.out.println("\nYou currently have no Notes.\n");
            }
            System.out.println("--------------------------------------------------------------------------");

            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("\nAn error occurred. File not found.");
            e.printStackTrace();
        }
    }
    
    public void searchNote(){
        System.out.println("\nSEARCH NOTE");
        arraySizeChecker();

        try{
            System.out.print("Enter the note number to be searched: ");
            int itemNum = input.nextInt();
            int testNum = itemNum - 1;

            if(testNum >= filesOfApp.size() || testNum < 0 ){
                System.out.println("\nInvalid Input. Please try again.");
            }else{
                for(int idx = 0; idx < filesOfApp.size(); idx++){
                    if(idx == testNum){
                        System.out.println("\nThe note at number " + itemNum + " is: ");
                        System.out.println(itemNum + ".) " + filesOfApp.get(idx));
                        input.nextLine();
                        break;
                    }
                }
            }
        }catch(Exception e){
            System.out.print("\nYou entered a value that is not an integer. Please try again.\n");
            input.nextLine();
        }
    }

    public void dataFileCreator() {
        try{
            FileWriter dataFile = new FileWriter("Notes.txt", true);
            for (int ctr = filesOfApp.size()-1; ctr < filesOfApp.size(); ctr++){ 
                dataFile.write(filesOfApp.get(ctr) + '\n');
            }
            dataFile.close();
        }
        catch(Exception e){
            System.out.println("File not found.");
        }
    }

    public void fileToArray(){
        try {
            File fileItems = new File("Notes.txt");
            Scanner fileReader = new Scanner(fileItems);
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                filesOfApp.add(data);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("\nAn error occurred. File not found.");
            e.printStackTrace();
        }
    }

    public void arrayToFile(){
        try {
            File fileDelete = new File("Notes.txt");
            fileDelete.delete();
            FileWriter fileItems = new FileWriter("Notes.txt", true);
            for(int idx = 0; idx < filesOfApp.size(); idx++){
                fileItems.write(filesOfApp.get(idx) + '\n');
            }
            fileItems.close(); 
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void arraySizeChecker(){
        if(filesOfApp.size() == 0){
            fileToArray();
        }
    }
}