/*  Luqhasal
*   Distributed Systems
*/

//package calculator;

//import library Java RMI
import java.rmi.Remote;
import java.rmi.RemoteException;

//The interface for specifying remote behaviours
public interface Calculator extends Remote {

    //This method will take the value of operand and push it on to the top of the calculator stack.
    public void pushValue(int operand) throws RemoteException;

    //This method will push a String containing an operator ("+","-","*","/") to the stack, 
    //which will cause the server to pop the two operands already on the stack, 
    //apply the operation and then push the result back on to the stack.
    public void pushOperator(String operator) throws RemoteException;

    //This method will pop the top of the calculator stack and return it to the client.
    public int pop() throws RemoteException;

    //This method will return true if the stack is empty, false otherwise.
    public boolean isEmpty() throws RemoteException;

    //This method will wait millis milliseconds before carrying out the pop operation as above.
    public int delayPop(int millis) throws RemoteException;

}