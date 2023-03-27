package w1867207_classes;

import java.util.NoSuchElementException;

public class CircularQueue {
        private int front,rear;
        private Passenger[] passengerQueue = new Passenger[5]; //queue

        public CircularQueue(){
            this.front = this.rear = -1;
        } //initializing front and rear

        public boolean IsQueueEmpty(){
            /*
                Checks whether queue is empty
                Parameters - None
                return - boolean(true if empty)
            */

            return front == -1;
        }

        public boolean IsQueueFull(){
            /*
                Checks whether queue is full
                Parameters - None
                return - boolean(true if full)
            */

            return (rear + 1) % passengerQueue.length == front;
        }

        public void EnQueue(Passenger passenger){
            /*
                Add passengers to the queue
                Parameters - passenger object(String fName,lName and double expenses)
                return - None
            */

            if(IsQueueEmpty()){
                front++;
            }
            rear = (rear + 1) % passengerQueue.length;
            passengerQueue[rear] = passenger;
        }

        public Passenger Dequeue() {
            /* Deletes a passenger from the queue
                Parameters - none
                return - temp
            */
            if (IsQueueEmpty())
                throw new NoSuchElementException();

            Passenger temp = passengerQueue[front];

            if (front == rear)
                front = rear - 1;
            else
                front = (front + 1) % passengerQueue.length;

            return temp;
        }

}







