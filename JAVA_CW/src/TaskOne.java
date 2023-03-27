import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskOne {

    static void ViewCabins(String[] pCabin){
        /*
            Views all the 12 cabins(Available/Passenger's name)
            Parameters - Cabin array of object
            return - none
         */
        for (int i = 0; i < pCabin.length; i++) {
            System.out.println("Cabin " + (i + 1) + ": " + pCabin[i]);
        }
    }

    static void AddName(String[] pCabin){
        /*
            Reserves a cabin under the entered passenger's name.
            Will exit the add passenger submenu when zero is entered.
            Checks whether the: Cabin number is between 1-12
                                Cabin is already reserved by someone else.
                                Input is an integer
            Parameters - Cabin array of object
            return - none
         */

        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Enter a cabin number between 1-12 or enter \"0\" to quit: ");
                int userInputNo = input.nextInt();
                if (userInputNo > 0 && userInputNo < 13) {
                    if (pCabin[userInputNo - 1].equals("Available")) {
                        System.out.print("Please enter the passenger's name: ");
                        String userInput = input.next();
                        pCabin[userInputNo - 1] = userInput;
                        System.out.println("Cabin reservation successful!");
                        System.out.println("Customer's Name: " + userInput + "\nCabin No: " + userInputNo);
                    }
                    else {
                        System.out.println("Sorry! The cabin no. " + userInputNo + " is already reserved.");
                    }
                }
                else if(userInputNo == 0){
                    break;
                }
                else {
                    System.out.println("Invalid cabin number!!! Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be an integer!!! Please try again.");
                input.nextLine();
            }
        }
    }

    static void ViewEmptyCabins(String[] pCabin){
        /*
            Views all the available cabins.
            Parameters - Cabin array of object
            return - none
         */

        for (int i = 0; i < pCabin.length; i++) {
            if(pCabin[i].equals("Available")){
                System.out.println("Cabin " + (i + 1) + ": " + pCabin[i]);
            }
        }
    }

    static void DeleteCustomer(String[] pCabin){
        /*
            Deletes the specific passenger entered by the user.
            Displays a message if passenger not found.
            Parameters - Cabin array of object
            return - none
         */

        Scanner input = new Scanner(System.in);
        String s = "";
        System.out.print("Please enter the passenger's name: ");
        String userInput = input.next();
        for (int i = 0; i < pCabin.length; i++) {
            if(pCabin[i].equalsIgnoreCase(userInput)){
                pCabin[i] = "Available";
                s = "Deletion successful!\nDeleted passenger " + userInput + " from cabin number " + (i + 1);
                System.out.println(s);
            }
        }
        if(s.equals("")){
            System.out.println("The passenger you are searching for is not in the list.");
        }
    }

    static void FindCustomer(String[] pCabin){
        /*
         Finds the specific passenger entered by the user.
         Displays a message if passenger not found.
         Parameters - Cabin array of object
         return - none
         */

        Scanner input = new Scanner(System.in);
        String s = "";

        System.out.print("Please enter the customer's name: ");
        String userInput = input.next();
        for (int i = 0; i < pCabin.length; i++) {
            if(pCabin[i].equalsIgnoreCase(userInput)){
                s = userInput + " is in  cabin number " + (i + 1);
                System.out.print(s);
            }
        }
        if(s.equals("")){
            System.out.println("The passenger you are searching for is not in the list.");
        }

    }

    static void WriteToFile(String[] pCabin){
        /*
            Creates a file called "CabinData.txt"
            Writes data of the cabin array into the file.
            Displays a message if data writing is unsuccessful
            Parameters - Cabin array of object
            return - none
         */

        try {
            FileWriter dataWriter = new FileWriter("CabinData.txt");
            for (String cabin : pCabin) {
                    dataWriter.write("Cabin No: " + cabin + "\n");
            }
            System.out.print("Data has been stored in the CabinData file successfully");
            dataWriter.close();
        } catch (IOException e) {
            System.out.println("Unable to Write to file");
        }
    }

    static void ReadFromFile() {
        /*
            Reads and displays data from the "CabinData.txt" file.
            Displays a message if data reading is unsuccessful
            Parameters - Cabin array of object
            return - none
         */

        try {
            File dataFile = new File("CabinData.txt");
            Scanner file = new Scanner(dataFile);
            while (file.hasNext()) {
                String line = file.nextLine();
                System.out.println(line);
            }
        }
        catch (IOException e){
            System.out.println("Unable to read from file");
        }

    }

    static void OrderedList(String[] pCabin) {
        /*
            Orders the passengers names alphabetically in the cabin array
            Parameters - Cabin array of object
            return - none
         */

        String[] tempArray = pCabin.clone();

        for (int i = 0; i < tempArray.length; i++) {
            for (int j = i + 1; j < tempArray.length; j++) {
                if (tempArray[i].compareTo(tempArray[j]) > 0) {
                    String temp = tempArray[i];
                    tempArray[i] = tempArray[j];
                    tempArray[j] = temp;
                }
            }
        }
        for (String name : tempArray) {
            if (!(name.equals("Available"))) {
                System.out.println("   Customer Name: " + name);
            }
        }
    }

    public static void main(String [] args){

        Scanner input = new Scanner(System.in);
        String[] cabin = new String[12]; //Cabin array

        //Initializing the cabin array
        for (int i = 0; i < cabin.length; i++){
            cabin[i] = "Available";
        }

        System.out.print("--------------------Welcome to our cruise boarding program.----------------------------\n");
        System.out.print("\nEnter \"A\" to add a customer's name.\nEnter \"V\" to view all cabins.\nEnter \"E\" to display Empty cabins.\nEnter \"D\" to delete a customer from a cabin.\nEnter \"F\" to find the cabin number from the customer's name.\nEnter \"S\" to store program data into the file.\nEnter \"L\" to load program data from the file.\nEnter \"O\" to view passengers ordered alphabetically by name.\nEnter \"Q\" to exit the program.");
        while (true){
            System.out.print("\nPlease enter a valid menu option: ");
            char userInput = input.next().toLowerCase().charAt(0);
            switch (userInput) {
                case 'a' -> {
                    ViewCabins(cabin);
                    AddName(cabin);
                }
                case 'v' -> ViewCabins(cabin);
                case 'e' -> ViewEmptyCabins(cabin);
                case 'd' -> DeleteCustomer(cabin);
                case 'f' -> FindCustomer(cabin);
                case 's' -> WriteToFile(cabin);
                case 'l' -> ReadFromFile();
                case 'o' -> OrderedList(cabin);
                case 'q' -> {
                    System.out.print("Exiting the program..............");
                    System.exit(0); //exiting the program
                }
                default -> System.out.print("Invalid menu option!!! Please try again.");
            }
        }
    }
}
