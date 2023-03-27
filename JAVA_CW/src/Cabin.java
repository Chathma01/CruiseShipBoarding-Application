package w1867207_classes;

public class Cabin {
    private String cabinIs;
    private Passenger[] passengers = new Passenger[3];

    public Cabin (String cabinIs){
        this.cabinIs = cabinIs;
    }

    public void PassengerArray(Passenger passenger){
        /*
          initializes the passenger array
          Parameters - passenger object(String fName,lName and double expenses)
          return - none
         */

        for (int i = 0; i < passengers.length; i++){
            if (passengers[i] == null){
                passengers[i] = passenger;
                break;
            }
        }
    }

    public boolean isCabinEmpty() {
        /*
            checks whether at least one passenger slot is empty
            Parameters - none
            return - boolean(true if at least one passenger slot is empty)
        */

        return passengers [0] == null || passengers [1] == null || passengers [2] == null;
    }

    public boolean isPassengersEmpty(){
        /*
            checks whether all the 3 passengers are null
            Parameters - none
            return - boolean(true if all are null)
        */

        return passengers [0] == null && passengers [1] == null && passengers [2] == null;
    }

    public String getCabinIs() {
        /*
            Parameters - none
            return - String cabinIs
        */
        return cabinIs;
    }

    public void setCabinIs(String cabinIs) {
        /*
            Parameters - String cabinIs
            return - none
        */

        this.cabinIs = cabinIs;
    }

    public Passenger[] getPassengers() {
        /*
            Parameters - none
            return - passengers array
        */
        return passengers;
    }

    public void setPassengers(Passenger[] passengers) {
        this.passengers = passengers;
    }
}
