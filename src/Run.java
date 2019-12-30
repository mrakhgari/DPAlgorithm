import java.util.Arrays;
import java.util.Scanner;

public class Run {
    private static int cities;
    private static int months;


    public static void main(String[] args) {
        System.out.println("start of project created by Ziri ft friends:");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of cities:");
        cities = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter number of months:");
        months = Integer.parseInt(scanner.nextLine());

        int[][] operatingCost = new int[cities][months];
        int[][] relocationCost = new int[cities][cities];
        System.out.println("Enter the operating Costs:");
        for (int c = 0; c < cities; c++) {
            System.out.println("Enter the city " + c + "'s cost:");
            operatingCost[c] = Arrays.stream(scanner.nextLine().replaceFirst("^\uFEFF", "").split(" "))
                    .map(String::trim).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println("Enter the relocation costs:");
        for (int r = 0; r < cities; r++) {
            for (int c = 0; c < cities; c++) {
                if (c < r) {
                    relocationCost[r][c] = relocationCost[c][r];
                } else if (c == r) {
                    relocationCost[r][c] = 0;
                } else {
                    System.out.print("cost of c( " + r + " ) to c( " + c + " )");
                    relocationCost[r][c] = scanner.nextInt();
                }
            }
        }

        System.out.println(Arrays.deepToString(operatingCost));
        System.out.println(Arrays.deepToString(relocationCost));
    }

    public void createTable() {

    }
}
