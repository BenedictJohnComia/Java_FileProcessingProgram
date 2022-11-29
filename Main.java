import java.util.Scanner;
import FileSystem.fileSystemApp;

public class Main{
    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        boolean thisCondition = true;
        fileSystemApp mainFile = new fileSystemApp();
        String userName;

        System.out.println("------------------------- FILE PROCESSING PROGRAM -------------------------");
        System.out.print("\nEnter your name: ");
        userName = input.nextLine();
        System.out.println("\n**************************************************************************");
        System.out.println("               Welcome " + userName + " to the file processing program");
        System.out.println("A program where you can create, update, delete, display, and search notes");
        System.out.println("              Use integer to indicate your choice. Enjoy!");
        System.out.println("**************************************************************************");
  
        while(thisCondition != false){
            try{
                System.out.println("\n1 - Create");
                System.out.println("2 - Update");
                System.out.println("3 - Delete");
                System.out.println("4 - Display");
                System.out.println("5 - Search");
                System.out.println("6 - Exit");
                
                System.out.print("Enter your choice: ");
                int choiceNum = input.nextInt();
                
                switch(choiceNum){
                    case 1:
                        mainFile.addNote();
                        break;
                    case 2:
                        mainFile.updateNote();
                        break;
                    case 3:
                        mainFile.deleteNote();
                        break;
                    case 4:
                        mainFile.displayNotes();
                        break;
                    case 5:
                        mainFile.searchNote();
                        break;
                    case 6:
                        System.out.println("\nAre you sure you want to exit?");
                        System.out.println("1 - Yes");
                        System.out.println("2 - No");
                        System.out.print("Enter your choice: ");
                        int choiceYN = input.nextInt();

                        if(choiceYN == 1){
                            thisCondition = false;
                        }else if(choiceYN == 2){
                            break;
                        }else{
                            System.out.println("\nInvalid Input. Please try again.");
                        }

                        break;
                    default:
                        System.out.println("\nInvalid Input. Please try again.");
                        break;
                        
                }
            } catch (Exception e){
                input.nextLine();
                System.out.print("\nYou entered a value that is not an integer. Please try again.\n");
        }
    }    
        System.out.println("\nThank you "+ userName +" for using the file processing program. Have a nice day! :)");
    }
}