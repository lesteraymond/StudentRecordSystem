import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        UserManager userManager = new UserManager();

        while (true) {
            System.out.println("[1] ADD USER");
            System.out.println("[2] EDIT USER");
            System.out.println("[3] DELETE USER");
            System.out.println("[4] VIEW USERS");
            System.out.println("[5] EXIT");
            System.out.print("Enter choice: ");
            int choice = scan.nextInt();
            scan.nextLine();

            System.out.println();

            if (choice == 1) {
                System.out.print("Enter User ID: ");
                String userID = scan.nextLine();

                System.out.print("Enter User Name: ");
                String userName = scan.nextLine();

                System.out.print("Enter User Age: ");
                int userAge = scan.nextInt();
                scan.nextLine();

                System.out.print("Enter User Course: ");
                String userCourse = scan.nextLine().toUpperCase();

                System.out.print("Save? (Y/N): ");
                boolean save = scan.next().toUpperCase().charAt(0) == 'Y';
                userManager.add(new User(userID, userName, userAge, userCourse), save);
                System.out.println();
            } else if (choice == 3) {
                System.out.print("Enter User ID: ");
                String userID = scan.nextLine();

                User user = userManager.getCurrentUserByID(userID);
                if (user != null) {
                    userManager.showUserInfo(user);

                    System.out.print("Delete? (Y/N): ");
                    boolean save = scan.next().toUpperCase().charAt(0) == 'Y';
                    userManager.delete(userID, save);
                } else {
                    System.out.println("User not found.");
                }
                System.out.println();
            }
        }
    }
}
