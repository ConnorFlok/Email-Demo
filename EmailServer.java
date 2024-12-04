import java.time.LocalTime;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
// implements the mailbox for each employee in which order
import java.util.ArrayList;
//storing mailbox

public class EmailServer {
    private List<Queue<Message>> mailboxes;  // Employee mailbox 

    public EmailServer(int numEmployees) {
        mailboxes = new ArrayList<>();
        // Create a mailbox for each employee
        for (int i = 0; i < numEmployees; i++) {
            mailboxes.add(new LinkedList<>()); 
        }
    }

    // Send mail from one employee to another by storing it in the mailbox
    // Ensures that only one thread is accessing code at a time.
    public synchronized void sendMail(int senderID, int recipientID, String content) {
        Message message = new Message(senderID, content);
        mailboxes.get(recipientID - 1).add(message);  
        LocalTime time = message.getTimeStamp();
        System.out.println(time + " mail received from #" + senderID + " to #" + recipientID);
        printQueueStatus();
    }
    // No other thread can execute any other synchronized
    public synchronized Message getNextMail(int employeeID) {
        Queue<Message> mailbox = mailboxes.get(employeeID - 1);
        if (mailbox.isEmpty()) {
            return null;
        }
        Message message = mailbox.poll();  // Get the first message from the queue
        System.out.println(message.getTimeStamp() + " mail delivered to #" + employeeID);
        printQueueStatus();
        return message;
    }

    // Print the queue status for all employees
    // String builider is better because it doesnt create an object for each string
    public synchronized void printQueueStatus() {
        StringBuilder status = new StringBuilder("Queue status is ");
        for (int i = 0; i < mailboxes.size(); i++) {
            status.append("#").append(i + 1).append("(");
            for (Message message : mailboxes.get(i)) {
                status.append(message.getTimeStamp()).append(" from #").append(message.getSenderID()).append(", ");
            }
            if (mailboxes.get(i).isEmpty()) {
                status.append(")");
            } else {
                status.setLength(status.length() - 2);  
                status.append(")");
            }
            status.append(", ");
        }
        status.setLength(status.length() - 2); 
        // pass the string to a method instead
        System.out.println(status.toString());
    }
}

