package battleship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {
    private final int rows = 10;
    private final int columns = 10;
    private String[][] boardStatus;
    private List<Ship> ships = List.of(
            new Ship(ShipType.AircraftCarrier, 5),
            new Ship(ShipType.Battleship, 4),
            new Ship(ShipType.Submarine, 3),
            new Ship(ShipType.Cruiser, 3),
            new Ship(ShipType.Destroyer, 2)
    );

    //   Start with the largest one
//   Error if length doesn't match expected length
//    Ships cannot adjacent
    private final Scanner scanner = new Scanner(System.in);

    static class Coordinate {
        char alphabetic;
        int numeric;

        Coordinate(String input) {
            alphabetic = input.charAt(0);
            numeric = Integer.parseInt(input.replace(String.valueOf(alphabetic), ""));
        }

        Coordinate(char alphabetic, int numeric) {
            this.alphabetic = alphabetic;
            this.numeric = numeric;
        }
    }

    public enum ShipType {
        AircraftCarrier("Aircraft carrier"),
        Battleship("Battleship"),
        Submarine("Submarine"),
        Cruiser("Cruiser"),
        Destroyer("Destroyer");

        public final String value;

        ShipType(String value) {
            this.value = value;
        }
    }

    private class Ship {
        private ShipType type;
        private int length;
        private Coordinate[] coordinates;

        private boolean contactWithOtherShip() {
            ArrayList<Coordinate> coordinatesToEvaluate = new ArrayList<>();
            boolean collission = false;

            for (Coordinate coordinate : this.coordinates) {
                if (
//                        potentiall out of bound
                        boardStatus[Math.max(coordinate.alphabetic - 97, 0)][coordinate.numeric - 1].equals("0") ||
                                boardStatus[coordinate.alphabetic - 96][coordinate.numeric - 1].equals("0") ||
                                boardStatus[Math.max(coordinate.alphabetic - 98, 0)][coordinate.numeric - 1].equals("0") ||
                                boardStatus[Math.max(coordinate.alphabetic - 97, 0)][Math.max(coordinate.numeric - 2,0)].equals("0") ||
                                boardStatus[Math.max(coordinate.alphabetic - 97, 0)][coordinate.numeric].equals("0")

                ) {
                    System.out.println("Error! You placed it too close to another one. Try again: ");
                    collission = true;
                    break;
                }
            }


            return false;
        }

        private void placeShip(String lat, String lon) {
            Coordinate latCoordinate = new Coordinate(lat);
            Coordinate lonCoordinate = new Coordinate(lon);

            char alphaLat = latCoordinate.alphabetic;
            int numericLat = latCoordinate.numeric;
            char alphaLon = lonCoordinate.alphabetic;
            int numericLon = lonCoordinate.numeric;

            int alphabetDistance = Math.abs(alphaLat - alphaLon) + 1;
            int numericDistance = Math.abs(numericLat - numericLon) + 1;

            if (!(alphabetDistance == this.length || numericDistance == this.length)) {
                System.out.println("Error");
            } else {
                while (this.coordinates == null) {
                    if (alphabetDistance > numericDistance) {
                        char startLetter = alphaLat > alphaLon ? alphaLon : alphaLat;
                        char endLetter = alphaLat > alphaLon ? alphaLat : alphaLon;

                        this.coordinates = new Coordinate[alphabetDistance];
                        int index = 0;

                        for (char i = startLetter; i <= endLetter; i++) {
                            this.coordinates[index] = new Coordinate(i, numericLat);
                            index++;
                        }
                    } else {
                        int endNumber = Math.max(numericLat, numericLon);
                        int startNumber = Math.min(numericLat, numericLon);

                        this.coordinates = new Coordinate[numericDistance];
                        int index = 0;

                        for (int i = startNumber; i <= endNumber; i++) {
                            this.coordinates[index] = new Coordinate(alphaLat, i);
                            index++;
                        }
                    }

                    boolean hasContactWithOtherShip = contactWithOtherShip();

                    if (hasContactWithOtherShip) {
                        this.coordinates = null;
                    } else {
                        for (Coordinate coordinate : this.coordinates) {
                            boardStatus[coordinate.alphabetic - 97][coordinate.numeric - 1] = "0";
                        }
                    }
                }
            }
        }

        Ship(ShipType type, int length) {
            this.length = length;
            this.type = type;
        }

        @Override
        public String toString() {
            return String.format("Length: %d%nParts: %s", length, Arrays.toString(coordinates));
        }
    }

    public void placeShips() {
        for (Ship ship : ships) {
            showBoard();

            System.out.printf("Enter the coordinates of the %s (%d cells):%n", ship.type.value, ship.length);
            String lat = scanner.next().toLowerCase();
            String lon = scanner.next().toLowerCase();

            boolean isInputValid = isInputValid(lat, lon);
            ship.placeShip(lat, lon);
        }

    }

    public boolean isInputValid(String lat, String lon) {
        Coordinate latCoordinate = new Coordinate(lat);
        Coordinate lonCoordinate = new Coordinate(lon);

        if (!(lat.length() > 1 && lat.length() <= 3 || lon.length() > 1 && lon.length() <= 3)) {
            System.out.println("Error");
            return false;
        } else if (!(Character.isAlphabetic(lat.charAt(0)) && Character.isAlphabetic(lon.charAt(0)))) {
            System.out.println("Error");
            return false;
        } else if (!(latCoordinate.alphabetic == lonCoordinate.alphabetic || latCoordinate.numeric == lonCoordinate.numeric)) {
            System.out.println("Error");
            return false;
        } else if (!(latCoordinate.alphabetic < 'k' && latCoordinate.alphabetic > '`' && lonCoordinate.alphabetic < 'k' && lonCoordinate.alphabetic > '`'
                && latCoordinate.numeric < 11 && latCoordinate.numeric > 0 && lonCoordinate.numeric < 11 && lonCoordinate.numeric > 0)) {
            System.out.println("Error");
            return false;
        }
//      Validation of the numeric part of the coordinate is missing.
        return true;
    }

    class Account {

        private long balance;
        private String ownerName;
        private boolean locked;

        public long getBalance() {
            return balance;
        }

        public void setBalance(long balance) {
            this.balance = balance;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public boolean isLocked() {
            return locked;
        }

        public void setLocked(boolean locked) {
            this.locked = locked;
        }
    }

    public void createBoard() {
//        System.out.println("Enter the number of rows:");
//        rows = scanner.nextInt();
//        System.out.println("Enter the number of seats in each row:");
//        columns = scanner.nextInt();

        boardStatus = new String[rows][columns];

        for (int row = 0; row < rows; row++) {
            String[] defaultValues = new String[columns];
            Arrays.fill(defaultValues, "~"); //
            boardStatus[row] = defaultValues;
        }
    }

    void showBoard() {
        int rowCounter = 1;
        System.out.printf("%n  ");
        for (int i = 1; i <= columns; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (String[] strings : boardStatus) {
            System.out.print(((char) (rowCounter + 64)) + " ");
            for (String string : strings) {
                System.out.print(string + " ");
            }
            rowCounter++;
            System.out.println();
        }
    }
}
