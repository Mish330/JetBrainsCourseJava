package machine;
import java.util.Scanner;

public class CoffeeMachine {
    
    public enum EnumCoffeeMachine {
        BUY,
        FILL,
        TAKE,
        REMAINING,
        EXIT;}
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int[] ESPRESSO = {250, 0, 16, 1, 4};
        final int[] LATTE = {350, 75, 20, 1, 7};
        final int[] CAPPUCCINO = {200, 100, 12, 1, 6};
        final int[][] DRINKS = {ESPRESSO, LATTE, CAPPUCCINO};
        int[] coffeeMachine = {400, 540, 120, 9, 550};
        boolean exit = false;
        while (!exit) {
            System.out.printf("%n%s%n", "Write action (buy, fill, take, remaining, exit):");
            EnumCoffeeMachine action = EnumCoffeeMachine.valueOf(sc.next().toUpperCase());
            switch (action) {
                case BUY:
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, " +
                            "back - to main menu: ");
                    String drinkChoiceStr = sc.next();
                    if (drinkChoiceStr.equals("back")) break;
                    int drinkChoice = Integer.parseInt(drinkChoiceStr);
                    MachineBuy(coffeeMachine, drinkChoice, DRINKS);
                    break;
                case FILL:
                    FillMachine (coffeeMachine, sc);
                    System.out.printf("%n");
                    break;
                case REMAINING:
                    System.out.printf("%n");
                    MachinePrintsStatus(coffeeMachine);
                    break;
                case TAKE:
                    System.out.printf("%s %d%n%n", "I gave you $",coffeeMachine[4]);
                    TakeMoney(coffeeMachine);
                    break;
                case EXIT:
                    exit = true;
                    break;
                default:
            }
        }
        sc.close();
    }
    // Machine status printer
    public static void MachinePrintsStatus(int[] coffeeMachine) {
        System.out.printf("%s%n%d%s%n%d%s%n%d%s%n%d%s%n%s%d%s%n",
                "The coffee machine has:",
                coffeeMachine[0], " ml of water",
                coffeeMachine[1], " ml of milk",
                coffeeMachine[2], " g of coffee beans",
                coffeeMachine[3], " disposable cups",
                "$", coffeeMachine[4], " of money");
    }
    // Buy action for CoffeeMachine
    public static void MachineBuy(int[] coffeeMachine, int drinkChoice, int[][] DRINKS) {
        int[] coffeeMacTemp = new int[5];
        System.arraycopy(coffeeMachine, 0,coffeeMacTemp, 0, 5 );
        boolean suppliesQnt = true;
        String[] optionsFill = {"water", "milk",
                "coffee beans", "disposable cups of coffee"};
        for (int i = 0; i < 4; i++) {
            coffeeMacTemp[i] -= DRINKS[drinkChoice - 1][i];
            if (coffeeMacTemp[i] < 0) {
                System.out.printf("%s %s %s", "Sorry, not enough", optionsFill[i], "!");
                suppliesQnt = false;
                break;
            }
        }
        if (suppliesQnt) {
            System.out.println("I have enough resources, making you a coffee!");
            System.arraycopy(coffeeMacTemp, 0,coffeeMachine, 0, 5 );
            coffeeMachine[4] += DRINKS[drinkChoice - 1][4];
            System.out.printf("%n");
        }
    }
    // Fill action for CoffeeMachine
    public static void FillMachine (int[] coffeeMachine, Scanner sc) {
        String[] optionsFill = {"ml of water", "ml of milk",
                "grams of coffee beans", "disposable cups of coffee"};
        for (int i = 0; i < 4; i++) {
            System.out.printf("%s %s %s%n", "Write how many",
                    optionsFill[i], "you want to add:");
            coffeeMachine[i] += sc.nextInt();
        }
    }
    // Take money
    public static void TakeMoney(int[] coffeeMachine) {
        coffeeMachine[4] = 0;
    }
}
