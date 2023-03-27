package w1867207_classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskThree {

    static int cabinCount = 0;
    static CircularQueue queue = new CircularQueue();

    public static void addPassengers(Cabin[] pCabin) {
        /*
            Reserves a cabin under the entered passenger's name.
            Will exit the add passenger submenu when zero is entered.
            Adds passengers to the waiting list once 12 cabins are occupied.
            Checks whether the: Cabin number is between 1-12
                                Cabin is already reserved by someone else.
                                Input is an integer
            Parameters - Cabin array of object
            return - none
         */

        Scanner input = new Scanner(System.in);
        while (true) {
            if (cabinCount == 12) {
                System.out.println("Since all the cabins are full you will be added to the waiting list");
                while (true) {
                    System.out.print("Enter \"Y\" to continue or \"N\" quit: ");
                    String userInput = input.next();

                    if (userInput.equals("y")) {
                        AddToQueue();
                        break;
                    } else if (userInput.equals("n")) {
                        break;
                    } else {
                        System.out.println("Invalid input please try again!!!");
                        input.nextLine();
                    }
                }
                break;
            }
            try {
                System.out.print("Enter cabin number (1-12) or \"0\" to quit: ");
                int userInputNo = input.nextInt();
                if (userInputNo > 0 && userInputNo < 13) {
                    if (pCabin[userInputNo - 1].isCabinEmpty()) {
                        Passenger passengerDetails = getDetails();
                        pCabin[userInputNo - 1].PassengerArray(passengerDetails);
                        pCabin[userInputNo - 1].setCabinIs("Unavailable");
                        if (pCabin[userInputNo - 1].getPassengers()[0] != null && pCabin[userInputNo - 1].getPassengers()[1] == null && pCabin[userInputNo - 1].getPassengers()[2] == null){
                            cabinCount++;
                        }
                    }
                    else {
                        System.out.print("Cabin number " + userInputNo + " is already occupied please choose another cabin.\n");
                        System.out.println();
                    }
                }
                else if(userInputNo == 0){
                    break;
                }
                else {
                    System.out.println("Invalid Cabin number!! Cabin number range (1- 12)");
                }
            } catch (InputMismatchException e) {
                System.out.print("Input must be an integer!!! Please try again.");
                input.nextLine();
            }
        }

    }


    public static Passenger getDetails (){
        /*
            Get details from the customer
            Validates expenses
            pass the input data as an object
            Parameters - none
            return - passDetails
        */

        Scanner input = new Scanner (System.in);
        Passenger passDetails;

        System.out.print("Enter the passenger's first name: ");
        String fName = input.next().toLowerCase();
        System.out.print("Enter the passenger's last name: ");
        String lName = input.next().toLowerCase();
        while (true) {
            try {
                System.out.print("Enter the passenger's expenses: ");
                double expenses = input.nextDouble();
                passDetails = new Passenger(fName, lName, expenses);
                break;
            }
            catch (InputMismatchException e) {
                System.out.println("Input must be an integer!!! Please try again.\n");
                input.nextLine();
            }
        }
        return passDetails;
    }

    public static void viewCabins (Cabin[] pCabin) {
        /*
            Views all the 12 cabins and its passengers
            Parameters - Cabin array of object
            return - none
         */

        for (int i = 0; i < pCabin.length; i++) {
            if (pCabin[i].getCabinIs().equals("Unavailable")) {
                System.out.println("Cabin " + (i + 1) + ": ");
                for (int j = 0; j < 3; j++) {
                    if (pCabin[i].getPassengers()[j] != null) {
                        System.out.println("    Passenger " + (j+1) + ": " + "\n" + pCabin[i].getPassengers()[j].display());
                    }
                    else {
                        System.out.println("    Passenger " + (j + 1) + ": Available");
                    }
                }
            }
            else {
                System.out.println("Cabin No " + (i + 1) + ": Available");
            }
        }
    }

    public static void viewEmptyCabins(Cabin[] pCabin) {
        /*
            Views all the available cabins.
            Parameters - Cabin array of object
            return - none
         */

        for (int i = 0; i < pCabin.length; i++) {
            if (pCabin[i].getCabinIs().equals("Available")) {
                System.out.println("Cabin " + (i + 1) + ": " + pCabin[i].getCabinIs());
            }
        }
    }

    public static void DeleteCustomer(Cabin[] pCabin) {
        /*
            Deletes the specific passenger entered by the user and add the first user in the waiting list.
            Displays a message if passenger not found.
            Parameters - Cabin array of object
            return - none
         */

        Scanner input = new Scanner(System.in);
        String output = "";

        System.out.print("Please enter the customer's first name: ");
        String userInput = input.next();
        for (int i = 0; i < pCabin.length; i++) {
            for (int j = 0; j < 3; j++) {
                if (pCabin[i].getPassengers()[j] != null && pCabin[i].getPassengers()[j].getFName().equalsIgnoreCase(userInput)) {
                    pCabin[i].getPassengers()[j] = null;
                    if (pCabin[i].getPassengers()[0] == null){
                        if (!queue.IsQueueEmpty()){ //add from waiting list
                            pCabin[i].PassengerArray(queue.Dequeue());
                            pCabin[i].setCabinIs("Unavailable");
                            System.out.println("Cabin no " + (i + 1) + " is booked through the waiting list");
                        }
                    }
                    else {
                        cabinCount = 12;
                    }
                    output = "Deletion successful";
                    System.out.println(output);

                }

            }
            if (pCabin[i].isPassengersEmpty()){ //If all the passengers cabin are null
                pCabin[i].setCabinIs("Available");
            }
        }
        if (output.equals("")) {
            System.out.println("The customer you are looking for is not in the list.");
        }
    }

    public static void FindCustomer(Cabin[] pCabin){
        /*
         Finds the specific passenger entered by the user.
         Displays a message if passenger not found.
         Parameters - Cabin array of object
         return - none
         */

        Scanner input = new Scanner(System.in);
        String output = "";

        System.out.print("Please enter the customer's first name: ");
        String userInput = input.next();
        for (int i = 0; i < pCabin.length; i++) {
            for (int j = 0; j < 3; j++) {
                if (pCabin[i].getPassengers()[j] != null && pCabin[i].getPassengers()[j].getFName().equalsIgnoreCase(userInput)) {
                    output = userInput + " is in cabin number " + (i + 1);
                    System.out.print(output);
                }
            }
        }
        if (output.equals("")) {
            System.out.println("The customer you are looking for is not in the list.");
        }

    }

    public static void WriteToFile(Cabin[] pCabin){
        /*
            Creates a file called "CruiseData.txt"
            Writes data of the cabin array into the file.
            Displays a message if data writing is unsuccessful
            Parameters - Cabin array of object
            return - none
         */

        try {
            FileWriter dataWriter = new FileWriter("CruiseData.txt");
            for (int i = 0; i < pCabin.length; i++) {
                if (pCabin[i].getCabinIs().equals("Unavailable")) {
                    dataWriter.write("Cabin " + (i + 1) + ": \n");
                    for (int j = 0; j < 3; j++) {
                        if (pCabin[i].getPassengers()[j] != null) {
                            dataWriter.write("    Passenger " + (j+1) + ": " + "\n" + pCabin[i].getPassengers()[j].display() + "\n");
                        }
                        else {
                            dataWriter.write("    Passenger " + (j + 1) + ": Available\n");
                        }
                    }
                }
                else {
                    dataWriter.write("Cabin No " + (i + 1) + ": Available\n");
                }
            }
            System.out.print("Data has been stored in the file successfully");
            dataWriter.close();
        } catch (IOException e) {
            System.out.println("Unable to write to file!!!");
        }
    }

    static void ReadFromFile() {
        /*
            Reads and displays data from the "CruiseData.txt" file.
            Displays a message if data reading is unsuccessful
            Parameters - Cabin array of object
            return - none
         */

        try {
            File dataFile = new File("CruiseData.txt");
            Scanner file = new Scanner(dataFile);
            while (file.hasNext()) {
                String line = file.nextLine();
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Unable to read the file!!!");
        }
    }

    static void OrderedList(Cabin[] pCabin){
        /*
            Orders the passengers names alphabetically in the cabin array
            Parameters - Cabin array of object
            return - none
         */

        String fNames = "";
        for (Cabin cabin : pCabin) {
            for (int j = 0; j < 3; j++) {
                if (cabin.getPassengers()[j] != null) {
                    fNames += cabin.getPassengers()[j].getFName() + ",";
                }
            }
        }
        String[] tempArray = fNames.split(",");
        for (int i = 0; i < tempArray.length; i++) {
            for (int j = i + 1; j < tempArray.length; j++) {
                if (tempArray[i].compareTo(tempArray[j]) > 0) {
                    String temp = tempArray[i];
                    tempArray[i] = tempArray[j];
                    tempArray[j] = temp;
                }
            }
        }
        for (String fName : tempArray) {
            System.out.println("   Customer Name: " + fName);
        }
    }

    static void CalculateTotal(Cabin[] pCabin){
        /*
            Display expenses of each passenger and displays the total of all passenger expenses
            Parameters - Cabin array of object
            return - none
         */

        double totalExp = 0.0;
        for (int i = 0; i < pCabin.length; i++) {
            for (int j = 0; j < 3; j++) {
                if (pCabin[i].getPassengers()[j] != null) {
                    System.out.println("Cabin No " + (i + 1) + ": \n" + "Passenger " + (j + 1) + ": \n"+ pCabin[i].getPassengers()[j].getFName() + " - " +pCabin[i].getPassengers()[j].getExpenses());
                    totalExp += pCabin[i].getPassengers()[j].getExpenses();
                }
            }
        }
        System.out.println("Total expenses: " + totalExp);
    }

    public static void AddToQueue(){
        /*
            Adds the passenger to the waiting queue if not full
            Parameters - none
            return - none
        */

        if(!queue.IsQueueFull()){
            Passenger details = getDetails();
            queue.EnQueue(details);
            System.out.println("Data has been successfully added to the waiting list");
        }
        else {
            System.out.println("Sorry! The waiting list is already full");
        }
    }

    public static void main (String[]args) {

        Scanner input = new Scanner(System.in);

        Cabin[] cabin = new Cabin[12]; //Cabin array

        //Initializing the cabin array
        for (int i = 0; i < cabin.length; i++) {
            cabin[i] = new Cabin("Available");
        }


        System.out.print("--------------------Welcome to our cruise boarding program.----------------------------");
        System.out.print("\nEnter \"A\" to add a customer's name.\nEnter \"V\" to view all cabins.\nEnter \"E\" to display Empty cabins.\nEnter \"D\" to delete a customer from a cabin.\nEnter \"F\" to find the cabin number from the customer's name.\nEnter \"S\" to store program data into the file.\nEnter \"L\" to load program data from the file.\nEnter \"O\" to view passengers ordered alphabetically by name.\nEnter \"T\" to calculate the total expenses.\nEnter \"Q\" to exit the program.");
        while (true) {
            System.out.print("\nPlease enter a valid menu option: ");
            char userInput = input.next().toLowerCase().charAt(0);
            switch (userInput) {
                case 'a' -> {
                    viewCabins(cabin);
                    addPassengers(cabin);
                }
                case 'v' -> viewCabins(cabin);
                case 'e' -> viewEmptyCabins(cabin);
                case 'd' -> DeleteCustomer(cabin);
                case 'f' -> FindCustomer(cabin);
                case 's' -> WriteToFile(cabin);
                case 'l' -> ReadFromFile();
                case 'o' -> OrderedList(cabin);
                case 't' -> CalculateTotal(cabin);
                case 'q' -> {
                    System.out.print("Exiting the program..............");
                    System.exit(0);
                }
                default -> {
                    System.out.print("Invalid menu option!!! Please try again.");
                    input.nextLine();
                }
            }
        }
    }
}

