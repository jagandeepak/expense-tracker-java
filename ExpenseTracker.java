import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpenseTracker {
    private static class Expense {
        private final String title;
        private final String category;
        private final double amount;

        public Expense(String title, String category, double amount) {
            this.title = title;
            this.category = category;
            this.amount = amount;
        }

        public double getAmount() {
            return amount;
        }

        @Override
        public String toString() {
            return String.format("%-20s | %-14s | %.2f", title, category, amount);
        }
    }

    private final List<Expense> expenses = new ArrayList<>();

    public void addExpense(String title, String category, double amount) {
        expenses.add(new Expense(title, category, amount));
        System.out.println("Expense added successfully.");
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded yet.");
            return;
        }

        System.out.println("\n--- Recorded Expenses ---");
        System.out.printf("%-20s | %-14s | %s%n", "Title", "Category", "Amount");
        System.out.println("-------------------------------------------------");
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
        System.out.printf("Total Spent: %.2f%n", getTotalExpense());
    }

    public double getTotalExpense() {
        double total = 0.0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        return total;
    }

    private static double readPositiveDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value > 0) {
                    return value;
                }
                System.out.println("Please enter an amount greater than 0.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }

    private static String readNonEmptyString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseTracker tracker = new ExpenseTracker();

        while (true) {
            System.out.println("\n=== Expense Tracker ===");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses & Total");
            System.out.println("3. Exit");
            System.out.print("Choose an option (1-3): ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    String title = readNonEmptyString(scanner, "Enter expense title: ");
                    String category = readNonEmptyString(scanner, "Enter category (e.g., Food, Travel, Bills): ");
                    double amount = readPositiveDouble(scanner, "Enter amount: ");
                    tracker.addExpense(title, category, amount);
                    break;
                case "2":
                    tracker.viewExpenses();
                    break;
                case "3":
                    System.out.println("Exiting Expense Tracker. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please enter 1, 2, or 3.");
            }
        }
    }
}