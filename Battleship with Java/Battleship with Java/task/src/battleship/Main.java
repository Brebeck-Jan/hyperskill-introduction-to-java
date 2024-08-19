package battleship;

public class Main {
// 10 x1 10 field
// 5 ships
// ships only hori. and vertical
//  field1-10 and A-J
//    ~ unknown field
//    0 our ship; X hit; M miss

//    Tasks:
//    1: print empty field
//    2: give in coordinates for your ship
//    3: output the length and coordinates of the ship
//    If he input is not valid, the programm should print "Error"
    public static void main(String[] args) {
        Game battleShip = new Game();
        battleShip.createBoard();
        battleShip.placeShips();
    }
}
