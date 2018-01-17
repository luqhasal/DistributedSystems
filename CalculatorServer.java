/*  Luqhasal
*   Distributed Systems
*
*   The calculator server operates a stack and clients push values and operations on to the stack. 
*   While, usually, each client should have its own stack, you may use a single stack on the server. 
*   You may also assume that operations are always carried out in a sensible order: that is, 
*   we will only push an operator after pushing two operands and we will only pop 
*   when there is a value on the stack. You may also assume that the operator provided will only 
*   be one of the four displayed types and that you may use integer arithmetic for all calculations. 
*   Finally, you are not required to handle the case of division by zero.
*/

//package calculator;

//import library Java RMI
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

//Server class 
public abstract class CalculatorServer implements Calculator {

    //constructor
    public CalculatorServer() {
        super();
    }

    public static void main(String[] args) {

    try {
        //Java RMI Implementation
        Calculator obj = new CalculatorImplementation();
        Calculator stub = (Calculator) UnicastRemoteObject.exportObject(obj, 0);
        Registry registry = LocateRegistry.getRegistry();
        String name = "Calculator";
        registry.rebind(name, stub);
        System.out.println("Server is ready...");
        } catch (RemoteException e) {
            System.out.println("Server exception: " + e.toString());
        }
    }
}