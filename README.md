## ABOUT THIS PROJECT

This project developed and used to learn Java RMI (Remote Method Invocation)
I try to make changes according to the lab sheet 2 in Distributed Systems - SE3020 module.


## ANSWERS FOR THE LAB SHEET 2

> How can you ensure the thread safety of the increment count function, 
which updates a shared variable? 
Make sure the increment client count function is thread safe.

I used one method to increase the client count when new client comes into action,
and I used another method for the decrement. 
These two methods responsible to change a variable.

```java
private int clientCount = 0;
```

We can 'synchronized' these two methods, 
thus one thread can access the variable at a time.

```java
    /**
     *  increases client count by one
     *  */
    private synchronized void incClientCount(){
        if (clientCount>=0){
            ++clientCount;
        }
    }

    /**
     *  decreases client count by one
     *  */
    private synchronized void decClientCount(){
        if (clientCount>0){
            --clientCount;
        }
    }

```



> What does the count increment feature say about the Server object’s instantiation method?
> Is it Singleton, Per client or Per call instantiation?

Server object’s instantiation is singleton, because every time new client is added, 
client count increases by one.

> explain how can you make this a Per client or Per call instantiation? 
> (Hint: you can have multiple server objects, and the Math Server object can have associations with other objects). 

- working on the answer
