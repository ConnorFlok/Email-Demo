public class EmailDemo {
    public static void main(String[] args) {
        
        EmailServer server = new EmailServer(3);

        // Create and start employee threads
        Employee employee1 = new Employee(1, server);
        Employee employee2 = new Employee(2, server);
        Employee employee3 = new Employee(3, server);

        employee1.start();
        employee2.start();
        employee3.start();

        // Wait for all employees to finish
        try {
            employee1.join();
            employee2.join();
            employee3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Email finished.");
    }
}
