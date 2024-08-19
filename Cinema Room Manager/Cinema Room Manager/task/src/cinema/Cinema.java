package cinema;

import java.util.*;

public class Cinema {
    public static void main(String[] args) {
        CinemaRoom cinemaCinema = new CinemaRoom();
        cinemaCinema.createCinema();
        cinemaCinema.menu();
    }

    static void getProfit(int rows, int seats) {
        Map<Integer, Map<String, Integer>> ticketClasses =
                Map.of(0, Map.of("front", 10),
                        61, Map.of("front", 10, "back", 8)
                );

        int totalSeatNumber = rows * seats;
        int profit;

        if (totalSeatNumber < 60) {
            profit = totalSeatNumber * ticketClasses.get(0).get("front");
        } else {
            int frontRows = rows / 2;
            int backRows = rows - frontRows;

            profit = (frontRows * seats * ticketClasses.get(61).get("front")) +
                    (backRows * seats * ticketClasses.get(61).get("back"));
        }


        System.out.printf("Total income: $%d%n", profit);
    }

    static class CinemaRoom {
        private final String title = "Cinema:";
        private int seats, rows, currentIncome;
        private int totalSeats;
        private String[][] cinemaStatus;

        Scanner scanner = new Scanner(System.in);

        Map<Integer, Map<String, Integer>> ticketPrices =
                Map.of(0, Map.of("front", 10),
                        61, Map.of("front", 10, "back", 8)
                );

        public void menu() {
            int userInput;
            boolean activeSession = true;
            while (activeSession) {
                printMenuHeader();
                userInput = scanner.nextInt();
                switch (userInput) {
                    case 1 -> {
                        showRoomStatus();
                    }
                    case 2 -> {
                        bookSeat();
                    }
                    case 3 -> {
                        printStatistics();
                    }
                    default -> {
                        activeSession = false;
                    }
                }
            }
        }

        private void printMenuHeader() {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
        }

        private void printStatistics() {
            int numberOfBookedSeats = 0;
            for (String[] row : cinemaStatus) {
                numberOfBookedSeats += Collections.frequency(Arrays.asList(row), "B");
            }
            System.out.printf("Number of purchased tickets: %d%n", numberOfBookedSeats);

            double capacityUtilization = ((double) numberOfBookedSeats / totalSeats) * 100;

            System.out.printf("Percentage: %.2f%%%n", capacityUtilization);

            System.out.printf("Current income: $%d%n", currentIncome);

            getProfit(rows, seats);
        }

        public void createCinema() {
            System.out.println("Enter the number of rows:");
            rows = scanner.nextInt();
            System.out.println("Enter the number of seats in each row:");
            seats = scanner.nextInt();
            totalSeats = rows * seats;

            cinemaStatus = new String[rows][seats];


            for (int row = 0; row < rows; row++) {
                String[] defaultValues = new String[seats];
                Arrays.fill(defaultValues, "S"); //
                cinemaStatus[row] = defaultValues;
            }

            showRoomStatus();
        }

        public void bookSeat() {
            int price;
            boolean hasBooked = false;

            while (!hasBooked) {
                System.out.println("Enter a row number:");
                int row = scanner.nextInt();
                System.out.println("Enter a seat number in that row:");
                int seat = scanner.nextInt();

                if (row > rows || seat > seats) {
                    System.out.println("Wrong input!");
                    continue;
                }

                if (totalSeats > 60) {
                    int frontRows = rows / 2;
                    if (row > frontRows) {
                        price = ticketPrices.get(61).get("back");
                    } else {
                        price = ticketPrices.get(61).get("front");
                    }
                } else {
                    price = ticketPrices.get(0).get("front");
                }

                if (cinemaStatus[row - 1][seat - 1].equals("B")) {
                    System.out.println("That ticket has already been purchased!");
                    continue;
                }

                System.out.printf("Ticket price: $%d%n", price);

                cinemaStatus[row - 1][seat - 1] = "B";
                currentIncome += price;
                hasBooked = true;
            }
        }

        void showRoomStatus() {
            int rowCounter = 1;
            System.out.println("Cinema:");
            System.out.print("  ");
            for (int i = 1; i <= seats; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
            for (String[] strings : cinemaStatus) {
                System.out.print(rowCounter + " ");
                for (String string : strings) {
                    System.out.print(string + " ");
                }
                rowCounter++;
                System.out.println();
            }
        }
    }
}