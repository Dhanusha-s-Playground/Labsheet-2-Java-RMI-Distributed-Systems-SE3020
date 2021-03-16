package lk.sliit.calculator;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

//import java.lang.SecurityManager;

public class MathClient {
    public static void main(String[] args) {
        //       if (System.getSecurityManager() == null)
        //      {
        //        System.setSecurityManager (new RMISecurityManager());
        //  }


        System.setProperty("java.security.policy", "file:allowall.policy");

        MathService service = null;
        int clientCount = 0;
        try {
            service = (MathService) Naming.lookup("//localhost/CalculatorService");

            clientCount = service.increaseClientCount();

            System.out.println("Client count: " + clientCount);

            System.out.println("Add : " + service.add(2, 2));
            System.out.println("Subtract : " + service.subtract(5, 2));
            System.out.println("Multiply : " + service.multiply(2, 6));

            System.out.println("Divide : " + service.divide(4, 2));

        } catch (NotBoundException ex) {
            System.err.println(ex.getMessage());
        } catch (MalformedURLException ex) {
            System.err.println(ex.getMessage());
        } catch (RemoteException ex) {
            System.err.println(ex.getMessage());
        } finally {
            try {
                if (service != null) {
                    clientCount = service.decreaseClientCount();
                    System.out.println("Client count: " + clientCount);
                }
            } catch (RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
