import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        SearchManager manager = new SearchManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Movie");
            System.out.println("2. Add User");
            System.out.println("3. Search");
            System.out.println("4. Multi Search");
            System.out.println("5. View Cache Stats");
            System.out.println("6. Clear Cache");
            System.out.println("7. Exit");

            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (option) {
                case 1:
                    System.out.print("Enter movie ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over
                    System.out.print("Enter movie title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter movie genre: ");
                    String genre = scanner.nextLine();
                    System.out.print("Enter movie year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over
                    System.out.print("Enter movie rating: ");
                    double rating = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline left-over
                    manager.addMovie(id, title, genre, year, rating);
                    break;
                case 2:
                    System.out.print("Enter user ID: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over
                    System.out.print("Enter user name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter user preferred genre: ");
                    String preferredGenre = scanner.nextLine();
                    manager.addUser(userId, name, preferredGenre);
                    break;
                case 3:
                    System.out.print("Enter user ID: ");
                    userId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over
                    System.out.print("Enter search value (TITLE/GENRE/YEAR): ");
                    String searchValue = scanner.nextLine();
                    manager.search(userId, searchValue);
                    break;
                case 4:
                    System.out.print("Enter user ID: ");
                    userId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over
                    System.out.print("Enter genre: ");
                    genre = scanner.nextLine();
                    System.out.print("Enter year: ");
                    year = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over
                    System.out.print("Enter minimum rating: ");
                    double minRating = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline left-over
                    manager.searchMulti(userId, genre, year, minRating);
                    break;
                case 5:
                    manager.viewCacheStats();
                    break;
                case 6:
                    System.out.print("Enter cache level (L1/L2): ");
                    String cacheLevel = scanner.nextLine();
                    manager.clearCache(cacheLevel);
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}