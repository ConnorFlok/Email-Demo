import java.util.Random;

public class Employee extends Thread {
    private int employeeID;
    private EmailServer server;
    private Random random;

    public Employee(int employeeID, EmailServer server) {
        this.employeeID = employeeID;
        this.server = server;
        this.random = new Random();
    }
    // Rather than modeling for the project model for reusable code.

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {  // Each employee performs 10 actions of send or read mail
            try {
                Thread.sleep(random.nextInt(1000));  
                int action = random.nextInt(2);  

                if (action == 0) {
                    // Send mail to a random other employee
                    int recipientID;
                    do {
                        recipientID = random.nextInt(3) + 1;  
                    } while (recipientID == employeeID);
                    server.sendMail(employeeID, recipientID, "Hello from employee #" + employeeID);
                } else {
                    
                    Message message = server.getNextMail(employeeID);
                    if (message == null) {
                        System.out.println("Employee #" + employeeID + " has no mail.");
                    }
                }
                //catch handles any interruptions that occur while employee's thread is sleeping.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
