import com.connection.*;
import com.entities.*;
import com.ElectriccityBillManager.*;
import java.util.Scanner;


public class electricity_billing_system {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            main_menu();
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // add customer logic
                    billManager.addCustomer();
                    break;

                case (2):
                    // see all customer logic
                    billManager.seeAllCustomers();
                    break;

                case 3:
                    // add electricity bill usage
                    billManager.recordElectricityUsage();
                    break;

                case (4):
                    // see electric usage
                    billManager.seeElectricUsage();
                    break;

                case (5):
                    // generate bill logic
                    billManager.generateBill();
                    break;
                case 6:
                    System.out.println("Exiting Electricity Billing System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }
    public static void main_menu(){
        System.out.println("\n1. Add Customer");
        System.out.println("2. See All Customer");
        System.out.println("3. Record Electricity Usage");
        System.out.println("4. See Electricity Usage");
        System.out.println("5. Generate Bill");
        System.out.println("6. Exit");
    };
}
