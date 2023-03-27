package w1867207_classes;

public class Passenger {
    private String fName;
    private String lName;
    private double expenses;


    public Passenger ( String fName, String lName, double expenses ){
        this.fName = fName;
        this.lName = lName;
        this.expenses = expenses;
    }


    public String display(){
        /*
            Parameters - none
            return - String fName, lName, expenses
        */
        return "      First Name: " + fName + "\n" + "      Last Name: " + lName + "\n" + "      Expenses: " + expenses;
    }

    public String getFName() {
        /*
            Parameters - none
            return - String fName
        */

        return fName;
    }


    public double getExpenses() {
        /*
            Parameters - none
            return - double expenses
        */

        return expenses;
    }

}
