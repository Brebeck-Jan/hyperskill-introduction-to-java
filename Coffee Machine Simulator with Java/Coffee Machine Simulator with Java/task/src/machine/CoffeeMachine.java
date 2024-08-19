package machine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CoffeeMachine {
    private final Scanner scanner = new Scanner(System.in);

    private enum Options {
        buy,
        fill,
        take,
        exit,
        remaining
    }

    private enum CoffeType {
        espresso,
        latte,
        cappuccino
    }


    private class Coffee {
        private final CoffeType TYPE;
        private final int WATER_PER_CUP;
        private final int MILK_PER_CUP;
        private final int COFFEE_PER_CUP;
        private final int PRICE;

        Coffee(CoffeType type, int waterPerCup, int milkPerCup, int coffeePerCup, int price) {
            TYPE = type;
            WATER_PER_CUP = waterPerCup;
            MILK_PER_CUP = milkPerCup;
            COFFEE_PER_CUP = coffeePerCup;
            PRICE = price;
        }
    }

    final Coffee espresso = new Coffee(CoffeType.espresso, 250, 0, 16, 4);
    final Coffee latte = new Coffee(CoffeType.latte, 350, 75, 20, 7);
    final Coffee cappuccino = new Coffee(CoffeType.cappuccino, 200, 100, 12, 6);
    Map<String, Coffee> coffeeMap = Map.of("1", espresso, "2", latte, "3", cappuccino);

    private int currentAmountOfWater = 400;
    private int currentAmountOfMilk = 540;
    private int currentAmountOfCoffee = 120;
    private int currentAmountOfCups = 9;
    private int currentAmountOfMoney = 550;

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.start();

        for(CoffeType i: CoffeType.values()) {
            if (i.name().startsWith("STAR")) counter++;run d
        }

//        coffeeMachine.setCurrentRessoures();
//        coffeeMachine.calculateIngredientRequirements();
//        coffeeMachine.brewCoffee();

//        1: espresso, latte, and cappuccino
//        2: replenished by a worker
//        3: another worker collects the money
    }

    private void start() {
        boolean coffeeMachineIsOn = true;
        while (coffeeMachineIsOn) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            Options option = Options.valueOf(scanner.next());

            switch (option) {
                case buy -> brewCoffee();
                case fill -> setCurrentRessoures();
                case take -> takeMoney();
                case remaining -> showCurrentRessoures();
                case exit -> coffeeMachineIsOn = false;
            }
        }
    }

    private void takeMoney() {
        System.out.printf("I gave you $%d%n", currentAmountOfMoney);
        currentAmountOfMoney = 0;
    }

    private void setCurrentRessoures() {
        System.out.println("Write how many ml of water you want to add:");
        currentAmountOfWater += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        currentAmountOfMilk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        currentAmountOfCoffee += scanner.nextInt();
        System.out.println("Write how many disposable cups you want to add:");
        currentAmountOfCups += scanner.nextInt();
    }

    private void showCurrentRessoures() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water.%n", currentAmountOfWater);
        System.out.printf("%d ml of milk.%n", currentAmountOfMilk);
        System.out.printf("%d g of coffee beans.%n", currentAmountOfCoffee);
        System.out.printf("%d disposable cups.%n", currentAmountOfCups);
        System.out.printf("$%d of money.%n", currentAmountOfMoney);
    }

//    private void calculateIngredientRequirements() {
//        System.out.println("Write how many cups of coffee you will need: ");
//        int neededAmoutOfCoffees = scanner.nextInt();
//        int possibleAmountOfCoffees = calculatePossibleAmountOfCoffees();
//
//        if (possibleAmountOfCoffees >= neededAmoutOfCoffees) {
//            if (possibleAmountOfCoffees > neededAmoutOfCoffees) {
//                System.out.printf("Yes, I can make that amount of coffee (and even %d more than that)%n",
//                        possibleAmountOfCoffees - neededAmoutOfCoffees);
//            } else {
//                System.out.println("Yes, I can make that amount of coffee");
//            }
//            brewCoffee();
//        } else {
//            System.out.printf("No, I can only make %d cup(s) of coffee", possibleAmountOfCoffees);
//        }

//        int water = this.calculateIngredientRequirement(neededAmoutOfCoffees, WATER_PER_CUP);
//        int milk = this.calculateIngredientRequirement(neededAmoutOfCoffees, MILK_PER_CUP);
//        int coffee = this.calculateIngredientRequirement(neededAmoutOfCoffees, COFFEE_PER_CUP);
//        System.out.printf("For %d cups of coffee you will need:%n", neededAmoutOfCoffees);
//        System.out.printf("%d ml of water%n", water);
//        System.out.printf("%d ml of milk%n", milk);
//        System.out.printf("%d g of coffee beans%n", coffee);
//    }

    private int calculatePossibleAmountOfCoffees(Coffee desiredCoffe) {
        int possibleCupsForWater = desiredCoffe.WATER_PER_CUP > 0 ? currentAmountOfWater / desiredCoffe.WATER_PER_CUP : 1000;
        int possibleCupsForMilk = desiredCoffe.MILK_PER_CUP > 0 ? currentAmountOfMilk / desiredCoffe.MILK_PER_CUP : 1000;
        int possibleCupsForCoffee = desiredCoffe.COFFEE_PER_CUP > 0 ? currentAmountOfCoffee / desiredCoffe.COFFEE_PER_CUP : 1000;
        int[] possibleAmountsPerIngrident = {possibleCupsForWater, possibleCupsForCoffee, possibleCupsForMilk};
        return Arrays.stream(possibleAmountsPerIngrident).min().getAsInt();
    }


    private int calculateIngredientRequirement(int amoutOfCoffees, int neededRequirementPerCoffee) {
        return amoutOfCoffees * neededRequirementPerCoffee;
    }

    private void brewCoffee() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino,  back - to main menu:");
        String userInput = scanner.next();
        if (!userInput.equalsIgnoreCase("back")) {
            Coffee desiredCoffee = coffeeMap.get(userInput);
            if (1 <= calculatePossibleAmountOfCoffees(desiredCoffee)) {
                System.out.println("I have enough resources, making you a coffee!");
                currentAmountOfWater -= desiredCoffee.WATER_PER_CUP;
                currentAmountOfMilk -= desiredCoffee.MILK_PER_CUP;
                currentAmountOfCoffee -= desiredCoffee.COFFEE_PER_CUP;
                currentAmountOfCups -= 1;
                currentAmountOfMoney += desiredCoffee.PRICE;
            } else {
                if (currentAmountOfWater < desiredCoffee.WATER_PER_CUP) System.out.println("Sorry, not enough water!");
                if (currentAmountOfCoffee < desiredCoffee.COFFEE_PER_CUP)
                    System.out.println("Sorry, not enough coffee beans!");
                if (currentAmountOfMilk < desiredCoffee.MILK_PER_CUP) System.out.println("Sorry, not enough milk!");

            }
        }

    }
    Complex(){
        private Double real;
        private Double imaginary;
    }
}
