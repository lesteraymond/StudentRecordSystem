import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        UserManager userManager = new UserManager();

        while (true) {
            System.out.println("[1] ADD USER");
            System.out.println("[2] EDIT USER");
            System.out.println("[3] DELETE USER");
            System.out.println("[4] VIEW ALL USERS");
            System.out.println("[5] EXIT");
            System.out.print("Enter choice: ");
            int choice = scan.nextInt();
            scan.nextLine();

            System.out.println();

            if (choice == 1) {
                System.out.println("=== ADD USER ===");
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
            } else if (choice == 2) {
                System.out.println("=== EDIT USER ===");
                System.out.print("Enter User ID: ");
                String userID = scan.nextLine();

                int userIndex = userManager.searchUserIndexByID(userID);
                if (userIndex == -1) {
                    System.out.println("User not found.");
                } else {
                    userManager.showUserInfo(userIndex);
                    System.out.println("What do you want to edit?");
                    System.out.print("[1] ID\n[2] Name\n[3] Age\n[4] Course\n[5] Cancel\n: ");
                    choice = scan.nextInt();
                    scan.nextLine();

                    if (choice == 1) {
                        System.out.print("Enter new User ID: ");
                        String newUserID = scan.nextLine();
                        userManager.edit(userIndex, "id", newUserID);
                    } else if (choice == 2) {
                        System.out.print("Enter new User Name: ");
                        String newUserName = scan.nextLine();
                        userManager.edit(userIndex, "name", newUserName);
                    } else if (choice == 3) {
                        System.out.print("Enter new User Age: ");
                        String newUserAge = scan.nextLine();
                        userManager.edit(userIndex, "age", newUserAge);
                    } else if (choice == 4) {
                        System.out.print("Enter new User Course: ");
                        String newUserCourse = scan.nextLine();
                        userManager.edit(userIndex, "course", newUserCourse);
                    } else {

                    }
                    System.out.println("NEW USER INFO");
                    userManager.showUserInfo(userIndex);
                    System.out.print("Save? (Y/N): ");
                    boolean save = scan.next().toUpperCase().charAt(0) == 'Y';
                    userManager.save(save);
                }
            } else if (choice == 3) {
                System.out.println("=== DELETE USER ===");
                System.out.print("Enter User ID: ");
                String userID = scan.nextLine();

                User user = userManager.getCurrentUserByID(userID);
                if (user != null) {
                    userManager.showUserInfo(user);

                    System.out.print("Delete? (Y/N): ");
                    boolean save = scan.next().toUpperCase().charAt(0) == 'Y';
                    userManager.delete(user, save);
                } else {
                    System.out.println("User not found.");
                }
                System.out.println();
            } else if (choice == 4) {
                System.out.println("=== ALL USERS ===");
                userManager.showAllUsers();
            } else if (choice == 5) {
                System.out.println("Goodbyee...");
                break;
            }
        }
    }
}
