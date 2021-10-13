package banking;

public class Card {

    private String cardNumber;
    private String pin;
    private int balance;

    public int getBalance() {
        balance = 0;
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardnNumber) {
        this.cardNumber = cardnNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String createNewCardNumber() {

        String cardnum = "400000" + String.format("%09d", (long) (Math.random() * 999999999L));
        setCardNumber(cardnum + generateCheckDigit(cardnum));
        return cardNumber;
    }

    public String createNewPin(){

        setPin(String.format("%04d", (long) (Math.random() * 9999)) );
        return pin;
    }

    public void createNewCard(){
        createNewCardNumber();
        createNewPin();
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNumber='" + cardNumber + '\'' +
                ", pin='" + pin + '\'' +
                ", balance=" + balance +
                '}';
    }

    /**
     * Generate check digit using luhn algorithm
     * Source from: https://gist.github.com/stanzheng/5781833
     *
     * @param cardNumber
     * @return
     */
    private static int generateCheckDigit(String cardNumber) {
        int sum = 0;
        int remainder = (cardNumber.length() + 1) % 2;
        for (int i = 0; i < cardNumber.length(); i++) {

            // Get the digit at the current position.
            int digit = Integer.parseInt(cardNumber.substring(i, (i + 1)));

            if ((i % 2) == remainder) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
        }

        // The check digit is the number required to make the sum a multiple of
        // 10.
        int mod = sum % 10;
        int checkDigit = ((mod == 0) ? 0 : 10 - mod);

        return checkDigit;


    }
}
