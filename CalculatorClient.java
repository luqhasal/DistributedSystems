/*  Luqhasal
*   Distributed Systems
*/

//package calculator;

//import library Java RMI
//import file I/O
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

//client class
public class CalculatorClient {

    //constructor
    private CalculatorClient() {
        super();
    }

    //check for the numeric operand
    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }

    public static void main(String args[]) throws FileNotFoundException {

        //read test file from the command line "bash $ java calculator.CalculatorClient test1.txt"
        Scanner scanFile = null;
        try {
            scanFile = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();  
        }

    try {
        //Java RMI Implementation
        Registry registry = LocateRegistry.getRegistry();
        String name = "Calculator";
        Calculator stub = (Calculator) registry.lookup(name);

        while (scanFile.hasNextLine()) {
                Scanner inWord = new Scanner(scanFile.nextLine());

            //when there're still some values...
            while (inWord.hasNext()) {
                String s = inWord.next();

                //if the string is operator then push to the stub
                if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                    System.out.println("Client push " + "[" + s + "]");
                    stub.pushOperator(s);

                } else {
                    //if the string is operand then convert to int then push to the stub
                    int num = Integer.parseInt(s);
                    System.out.println("Client push " + "[" + num + "]");
                    stub.pushValue(num);
                }
            }
        }

        } catch (NotBoundException | RemoteException e) {
            System.err.println("Client exception: " + e.toString());
        }
    }
}