/*  Luqhasal
*   Distributed Systems
*
*   3.2 docs.oracle.com "Since remote method invocation 
*   on the same remote object may execute concurrently, 
*   a remote object implementation needs to make sure 
*   its implementation is thread-safe."
*/

//package calculator;

//import library Java RMI
//import stack class
import java.util.Stack;
import java.rmi.RemoteException;

//The implementation for implemeting behaviours logic 
public class CalculatorImplementation implements Calculator {

    //field variables
    private int operand;
    private String operator;

    //shared stack memory to store values pushed by client
    Stack<Integer> serverStackINT = new Stack<>();

    //store the operand to the sercer stack
    @Override
    public void pushValue(int operand) throws RemoteException {
        //thread-safe
        synchronized(this) {      
        serverStackINT.push(operand);
        }
    }

    //apply operation for the two operands
    @Override
    public void pushOperator(String operator) throws RemoteException {
        
        //check for empty stack before apply some operation
        if (isEmpty()) {
            System.out.println("Server stack is empty when apply operation [" + operator + "]" );
        }

        //thread-safe
        synchronized(this) {
        int stackResult;              //store calculation
        int num1 = serverStackINT.pop();  //pop the value on top 
        int num2 = serverStackINT.pop();  //pop the value on top
            
            /*switch branching logic for arithmetics and push back 
             the result to the server stack. The server output
            the pop and push values */
            switch (operator) {
                case "+":
                    System.out.println("Server pop " + "[" + num1 + "]");
                    System.out.println("Server pop " + "[" + num2 + "]");
                    System.out.println("Apply operation [+]");
                    stackResult = num1 + num2;
                    serverStackINT.push(stackResult);
                    System.out.println("Server push " + "[" + stackResult + "]" + " back to stack");

                break;
                
                case "-":
                    System.out.println("Server pop " + "[" + num1 + "]");
                    System.out.println("Server pop " + "[" + num2 + "]");
                    System.out.println("Apply operation [-]");
                    stackResult = num1 - num2;
                    serverStackINT.push(stackResult);
                    System.out.println("Server push " + "[" + stackResult + "]" + " back to stack");
                break;
                
                case "*":
                    System.out.println("Server pop " + "[" + num1 + "]");
                    System.out.println("Server pop " + "[" + num2 + "]");
                    System.out.println("Apply operation [*]");
                    stackResult = num1 * num2;
                    serverStackINT.push(stackResult);
                    System.out.println("Server push " + "[" + stackResult + "]" + " back to stack");
                break;            
                
                case "/":
                    System.out.println("Server pop " + "[" + num1 + "]");
                    System.out.println("Server pop " + "[" + num2 + "]");
                    System.out.println("Apply operation [/]");
                    stackResult = num1 / num2;
                    serverStackINT.push(stackResult);
                    System.out.println("Server push " + "[" + stackResult + "]" + " back to stack");
                break;
            }
        }    
    }

    //pop the server stack
    @Override
    public int pop() throws RemoteException {
        //thread-safe
        synchronized(this) { 
        int popSeverST = serverStackINT.pop();
        return popSeverST;
        }    
    }

    //check the server stack whether empty or not; True if it is and False if not empty
    @Override
    public boolean isEmpty() throws RemoteException {
        return serverStackINT.isEmpty();    
    }

    @Override
    public int delayPop(int millis) throws RemoteException {
        return millis;
    }
}