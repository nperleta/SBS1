package banking;

import java.util.Scanner;

public class Main {

    static DatabaseManager dbm = new DatabaseManager();
    static boolean out = true;
    static final Scanner s = new Scanner(System.in);

    public static void main(String[] args) {


        dbm.makeDBConnection(args[1]);



        runMenu();
    }

    public static void runMenu() {

        do {
            System.out.println("Please chose your option\n" + "1 - Create an account\n" +
                    "2 - Log into account\n" + "0 - Exit\n");

            int option = s.nextInt();

            switch (option) {
                case 1:
                    Card c = new Card();
                    c.createNewCard();
                    System.out.println("Your card has been created");
                    System.out.println("Your card number:\n" + c.getCardNumber());
                    System.out.println("Your card PIN:\n" + c.getPin());

                    String num = c.getCardNumber();
                    DatabaseManager.cards.put(num, c);

                    dbm.insertToDB(c);

                    /*for (var entry : DatabaseManager.cards.entrySet()) {
                        System.out.println(entry.getKey() + " " + entry.getValue());
                    break;*/
                case 2:
                    System.out.println("Enter your card number:");
                    String a = s.next();
                    System.out.println("Enter your PIN:");
                    String d = s.next();

                    for (Card card: DatabaseManager.cards.values()){
                        if(card.getCardNumber().equals(a)) {
                            if (card.getPin().equals(d)) {
                                System.out.println("You have successfully logged in!");
                                logInToAccount();
                            }
                        } else
                            System.out.println("Wrong card number or PIN!");
                    }
                    break;
                case 0:
                    out = !out;
                    System.out.println("Thank you for using Simple Banking System!\n" +
                            "GoodBye!");
                    break;
                default:
                    System.out.println("There is no such option!\n");
                    break;
            }
        } while (out = out);
    }

    public static void logInToAccount() {

        do {
            System.out.println("Please chose your option\n" + "1 - Balance\n" +
                            "2 - Add income\n" + "3 - Do transfer\n" + "4 - Close account\n" +
                            "5 - Log out\n" + "0 - Exit\n");

            int option = s.nextInt();

            switch (option) {
                case 1:
                   // dbm.selectBalance();
                    break;
                case 2:
                   // dbm.addIncome();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("You have successfully logged out!");
                    runMenu();
                    break;
                case 0:
                    out = !out;
                    System.out.println("Thank you for using Simple Banking System!\n" +
                            "GoodBye!");
                    break;
                default:
                    System.out.println("There is no such option!\n");
                    break;
            }
        } while (out = out);
    }
}