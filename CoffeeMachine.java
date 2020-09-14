package machine;
import java.util.Scanner;

public class CoffeeMachine {
    enum State{
        CHOOSING_ACT,
        BUY,
        FILL,
        TAKE,
        REMAINING,
        EXIT,
        ESPRESSO,
        LATTE,
        CAPPUCHINO;
    }
    public static State remaining(int waterHas, int milkHas, int beansHas, int dispCupsHas, int moneyHas){
        State currentState;// = new State();
        System.out.println("The coffee machine has:");
        System.out.println(waterHas + " of water");
        System.out.println(milkHas + " of milk");
        System.out.println(beansHas + " of coffee beans");
        System.out.println(dispCupsHas + " of disposable cups");
        System.out.println("$" + moneyHas + " of money");
        return currentState = State.CHOOSING_ACT;
    }
    public static State buyAct (){
        State currentState;// = new State();
        System.out.print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        Scanner scanner = new Scanner(System.in);
        String wantToBuy = scanner.next();
        switch (wantToBuy){
            case "1":
                currentState = State.ESPRESSO;
                break;
            case "2":
                currentState = State.LATTE;
                break;
            case "3":
                currentState = State.CAPPUCHINO;
                break;
            case "back":
                currentState = State.CHOOSING_ACT;
                break;
            default:
                System.out.print("Wrong input");
                currentState = State.CHOOSING_ACT;
                break;
        }
        return currentState;
    }
    public static int fillAct (String message){
        System.out.print(message);
        return new Scanner(System.in).nextInt();
    }
    public static int takeAct (int moneyHas) {
        System.out.println("I gave you $" + moneyHas);
        return  0;
    }
    public static boolean checkRes (int waterHas, int milkHas, int beansHas, int dispCupsHas, int waterNeed, int milkNeed, int beansNeed){
        boolean bool = true;
        if (waterHas < waterNeed){
            bool = false;
            System.out.println("Sorry, not enough water!");
        }
        if(milkHas < milkNeed){
            bool = false;
            System.out.println("Sorry, not enough milk!");
        }
        if(beansHas < beansNeed){
            bool = false;
            System.out.println("Sorry, not enough coffee beans!");
        }
        if(dispCupsHas < 1){
            bool = false;
            System.out.println("Sorry, not enough disposable cups!");
        }
        return bool;
    }
    public static State choosingAct(){
        State currentState;// = new State();
        System.out.print("Write action (buy, fill, take, remaining, exit): ");
        Scanner scanner = new Scanner(System.in);
        String action = scanner.next();
        switch (action){
            case "buy":
                currentState = State.BUY;
                break;
            case "fill":
                currentState = State.FILL;
                break;
            case "take":
                currentState = State.TAKE;
                break;
            case "remaining":
                currentState = State.REMAINING;
                break;
            case "exit":
                currentState = State.EXIT;
                break;
            default:
                System.out.print("You write wrong action");
                currentState = State.CHOOSING_ACT;
                break;
        }
        return currentState;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //State currentState = new State();
        State currentState = State.CHOOSING_ACT;

        int waterHas = 400;
        int milkHas = 540;
        int beansHas = 120;
        int dispCupsHas = 9;
        int moneyHas = 550;

        while (currentState != State.EXIT){
            if (currentState == State.CHOOSING_ACT){
                currentState = choosingAct();
            }
            if (currentState == State.BUY){
                currentState = buyAct();
            }
            if (currentState == State.FILL){
                waterHas += fillAct("Write how many ml of water do you want to add: ");
                milkHas += fillAct("Write how many ml of milk do you want to add: ");
                beansHas += fillAct("Write how many grams of coffee beans do you want to add: ");
                dispCupsHas += fillAct("Write how many disposable cups of coffee do you want to add: ");
                currentState = State.CHOOSING_ACT;
            }
            if (currentState == State.TAKE){
                moneyHas = takeAct(moneyHas);
                currentState = State.CHOOSING_ACT;
            }
            if (currentState == State.REMAINING){
                currentState = remaining(waterHas, milkHas, beansHas, dispCupsHas, moneyHas);
            }
            if (currentState == State.ESPRESSO){
                if(checkRes(waterHas, milkHas, beansHas, dispCupsHas, 250, 0, 16)){
                    waterHas -= 250;
                    beansHas -= 16;
                    dispCupsHas--;
                    moneyHas += 4;
                    System.out.println("I have enough resources, making you a coffee!");
                }
                currentState = State.CHOOSING_ACT;
            }
            if (currentState == State.LATTE){
                if(checkRes(waterHas, milkHas, beansHas, dispCupsHas, 350, 75, 20)){
                    waterHas -= 350;
                    milkHas -= 75;
                    beansHas -= 20;
                    dispCupsHas--;
                    moneyHas += 7;
                    System.out.println("I have enough resources, making you a coffee!");
                }
                currentState = State.CHOOSING_ACT;
            }
            if (currentState == State.CAPPUCHINO){
                if(checkRes(waterHas, milkHas, beansHas, dispCupsHas, 200, 100, 12)){
                    waterHas -= 200;
                    milkHas -= 100;
                    beansHas -= 12;
                    dispCupsHas--;
                    moneyHas += 6;
                    System.out.println("I have enough resources, making you a coffee!");
                }
                currentState = State.CHOOSING_ACT;
            }
        }
    }
}
