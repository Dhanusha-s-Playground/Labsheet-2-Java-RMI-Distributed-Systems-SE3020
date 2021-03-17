package lk.sliit.calculator;


import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MathServer extends UnicastRemoteObject implements MathService {

    private static int clientCount = 0;

    // TODO: Add a private variable to keep the client count

    public MathServer() throws RemoteException {
        super();
        System.out.println("MathServer() constructor called..!");
    }

    // TODO: add a method to increment the client count. Make it thread safe 

    public static void main(String[] args) {
        // set the policy file as the system securuty policy
        System.setProperty("java.security.policy", "file:allowall.policy");
        try {
            MathServer svr = new MathServer();
            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("CalculatorService", svr);


            System.out.println("Service started....");
        } catch (RemoteException re) {
            System.err.println(re.getMessage());
        } catch (AlreadyBoundException abe) {
            System.err.println(abe.getMessage());
        }
    }

    public int add(int a, int b) throws RemoteException {
        System.out.println("Adding " + a + " and " + b + " in the Server");
        return a + b;
    }

    public int subtract(int a, int b) throws RemoteException {
        System.out.println("Substracting " + a + " and " + b + " in the Server");
        return a - b;
    }

    public int multiply(int a, int b) throws RemoteException {
        System.out.println("Multiplying " + a + " and " + b + " in the Server");
        return a * b;
    }

    // this method is not remotely accessible as it's not in the remote interface
    public int test(int a) {
        System.out.println("this is a test");
        return 0;
    }

    public int divide(int a, int b) throws RemoteException {

        System.out.println("Dividing " + a + " and " + b + " in the Server");

        // TODO: Uncomment this to observe the clients get blocked
        for(double i=0; i<1000000.0; i++){
              System.out.println("I'm doing something that takes a long time.");
	   }

        return a / b; //check for division with zero here!
    }

    /**
     * this method will increase the client count by one
     * */
    public int increaseClientCount() {
        return (clientCount>=0)?++clientCount:clientCount;
    }

    /**
     * this method will decrease the client count by one
     * */
    public int decreaseClientCount() {
        return (clientCount>0)?--clientCount:clientCount;
    }

//    /**
//     *  increases client count by one
//     *  */
//    private synchronized static void incClientCount(){
//        if (clientCount>=0){
//            ++clientCount;
//        }
//    }
//
//    /**
//     *  decreases client count by one
//     *  */
//    private synchronized static void decClientCount(){
//        if (clientCount>0){
//            --clientCount;
//        }
//    }
}
