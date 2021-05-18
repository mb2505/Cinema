import java.util.Scanner;
import java.util.Currency;
public class Cinema {

    static int rows;
    static int seats;
    static int soldTickets;
    static int currentIncome;
    static int totalIncome;
    static double percentage;
    static String[][] arr;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        rows = s.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = s.nextInt();
        totalIncome();
        arr = createCinema();
        showMenu();
    }

    public static void showMenu() {
        Scanner s = new Scanner(System.in);
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        int num = s.nextInt();
        if (num == 1) {
            printCinema();
            showMenu();
        } else if (num == 2) {
            buyTicket();
            showMenu();
        } else if (num == 3) {
            statistics();
            showMenu();
        }
    }

    public static String[][] createCinema() {
        String[][] arr = new String[rows + 1][seats + 1];
        int count = 1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (i == 0 && j == 0) {
                    arr[i][j] = " ";
                } else {
                    arr[i][j] =  String.valueOf(count);
                    count++;
                }
                if (i > 0 && j == 0) {
                    arr[i][j] = String.valueOf(i);;
                } else if (i > 0 && j > 0) {
                    arr[i][j] = "S";
                }
            }
        }
        return arr;
    }

    public static void printCinema() {
        System.out.println();
        System.out.println("Cinema:");

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void buyTicket() {
        Scanner s = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter a row number:");
        int checkRow = s.nextInt();
        System.out.println("Enter a seat number in that row:");
        int checkSeat = s.nextInt();

        if (checkRow > rows || checkSeat > seats) {
            System.out.println("Wrong input!");
            buyTicket();
        } else if (arr[checkRow][checkSeat] == "B") {
            System.out.println("That ticket has already been purchased!");
            buyTicket();
        } else {
            arr[checkRow][checkSeat] = "B";
            soldTickets++;
            percentage = (double) soldTickets / (rows * seats) * 100;

            if (rows * seats <= 60) {
                System.out.println("Ticket price: $10");
                currentIncome += 10;
            } else {
                if (rows % 2 == 0) {
                    if (checkRow <= rows / 2) {
                        System.out.println("Ticket price: $10");
                        currentIncome += 10;
                    } else {
                        System.out.println("Ticket price: $8");
                        currentIncome += 8;
                    }
                } else {
                    if (checkRow <= rows / 2) {
                        System.out.println("Ticket price: $10");
                        currentIncome += 10;
                    } else {
                        System.out.println("Ticket price: $8");
                        currentIncome += 8;
                    }
                }
            }
        }
    }

    public static void totalIncome () {
        if (rows * seats <= 60) {
            totalIncome = rows * seats * 10;
        } else {
            if (rows % 2 == 0) {
                totalIncome = rows * seats * 9;
            } else {
                totalIncome = (rows / 2) * seats * 10 + ((rows / 2) + 1) * seats * 8;
            }
        }
        soldTickets = 0;
    }

    public static void statistics() {
        Currency cur = Currency.getInstance("USD");
        String symbol = cur.getSymbol();
        System.out.println();
        System.out.println("Number of purchased tickets: " + soldTickets);
        System.out.printf("Percentage: %.2f", percentage);
        System.out.println("%");
        System.out.println("Current income: " + symbol + currentIncome);
        System.out.println("Total income: " + symbol + totalIncome);
    }
}