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
            System.out.println("Enter the city " + (c + 1) + "'s cost:");
            operatingCost[c] = Arrays.stream(scanner.nextLine().replaceFirst("^\uFEFF", "").split(" "))
                    .map(String::trim).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println("Enter the relocation costs:");
        for (int r = 0; r < cities; r++) {
            for (int c = 0; c < cities; c++) {
//                if (c < r) {
//                    relocationCost[r][c] = relocationCost[c][r];
//                } else if (c == r) {
                if (c == r) {
                    relocationCost[r][c] = 0;
                } else {
                    System.out.print("cost of c( " + (r + 1) + " ) to c( " + (c + 1) + " )");
                    relocationCost[r][c] = scanner.nextInt();
                }
            }
        }

        int[][] dpTable = new int[cities][months];
        int[][] path = new int[cities][months];
        // set first month
        for (int c = 0; c < cities; c++) {
            dpTable[c][0] = operatingCost[c][0];
            path[c][0] = c;
        }

        for (int month = 1; month < months; month++) {
            for (int city = 0; city < cities; city++) {
                dpTable[city][month] = Integer.MAX_VALUE;
                for (int perCity = 0; perCity < cities; perCity++) {
                    if (dpTable[perCity][month - 1] + relocationCost[perCity][city] < dpTable[city][month]) {
                        dpTable[city][month] = dpTable[perCity][month - 1] + relocationCost[perCity][city];
                        path[city][month] = perCity;
                    }
                }
                dpTable[city][month] += operatingCost[city][month];
            }
        }

        int city = 0;
        int min = Integer.MAX_VALUE;
        for (int c = 0; c < cities; c++) {
            if (dpTable[c][months - 1] < min) {
                min = dpTable[c][months - 1];
                city = c;
            }
        }
        System.out.println(min);
        int[] finalPath = new int[months];
        finalPath[months - 1] = city;
        for (int i = months - 2; i >= 0; i--) {
            finalPath[i] = path[city][i + 1];
            city = path[city][i + 1];
        }
        System.out.println("cities:");
        for (Integer c : finalPath) {
            System.out.print((c + 1) + " ");
        }
//        System.out.println("end");
    }
}
