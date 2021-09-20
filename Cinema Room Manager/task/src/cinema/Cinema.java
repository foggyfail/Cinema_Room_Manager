package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    static Scanner scanner = new Scanner(System.in);
    private static int rowNumber;
    private static char[][] seats;
    private static int seatsInRow;
    private static int rows;
    private static boolean stop;
    private static int numberOfPurchasedTicket;
    private static int currentIncome;

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        seatsInRow = scanner.nextInt();

        seats = new char[rows][seatsInRow];

        for (char[] row : seats) {
            Arrays.fill(row, 'S');
        }

        stop = true;

        do {
            callMenu();
        } while(stop);
    }

    public static void callMenu() {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");

        int menu = scanner.nextInt();

        switch(menu) {
            case 1:
                System.out.println();
                printChosenSeats();
                break;
            case 2:
                System.out.println();
                buyTicket();
                break;
            case 3:
                System.out.println();
                statistics();
                break;
            case 0:
                stop = false;
                break;
        }
    }

    public static void printChosenSeats() {
        int row = 1;
        int seat = 1;

        System.out.println("Cinema:");
        System.out.print("  ");

        do {
            System.out.print(seat++ + " ");
        } while (seat != seatsInRow + 1);

        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print(row++ + " ");
            for (int j = 0; j < seatsInRow; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int ticketPrice() {
        if (rows * seatsInRow < 60) {
            return 10;
        } else {
            if (rowNumber <= rows / 2) {
                return 10;
            } else {
                return 8;
            }
        }
    }

    public static void buyTicket() {
        boolean stop1 = true;
        do {
            System.out.println("Enter a row number:");
            rowNumber = scanner.nextInt();

            System.out.println("Enter a seat number in that row:");
            int seatNumber = scanner.nextInt();
            try {
                if (seats[rowNumber - 1][seatNumber - 1] != 'B') {
                    seats[rowNumber - 1][seatNumber - 1] = 'B';
                    stop1 = false;
                } else {
                    System.out.println("That ticket has already been purchased!");
                }
            } catch (Exception e) {
                System.out.println("Wrong input!");
            }
        } while (stop1);

        System.out.println("Ticket price: $" + ticketPrice());

        numberOfPurchasedTicket++;
        currentIncome += ticketPrice();
    }

    public static void statistics() {
        double allSeats = rows * seatsInRow;
        System.out.println("Number of purchased tickets: " + numberOfPurchasedTicket);
        double numberOfPurchasedTicketInPercent = (double) numberOfPurchasedTicket / allSeats;
        System.out.printf("Percentage: %.2f", numberOfPurchasedTicketInPercent * 100);
        System.out.println("%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + getTotalIncome());
    }

    public static int getTotalIncome() {
        if (rows * seatsInRow < 60) {
            return rows * seatsInRow * 10;
        } else {
            if (rows % 2 == 0) {
                return rows / 2 * 10 * seatsInRow + rows / 2 * 8 * seatsInRow;
            } else {
                return rows / 2 * 10 * seatsInRow + (rows / 2 + 1) * 8 * seatsInRow;
            }
        }
    }
}