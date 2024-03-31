package bullscows;

import java.util.Random;

public class Game {
    private final Random random = new Random();


    public Game(int lenNumber, int symbolsNum) {
        this.lenNumber = lenNumber;
        this.symbolsNum = symbolsNum;
        this.number = generateNumber();
        this.bulls = 0;
        this.cows = 0;
    }

    private final String number;

    public int getBulls() {
        return bulls;
    }

    public void setBulls(int bulls) {
        this.bulls = bulls;
    }

    public void setCows(int cows) {
        this.cows = cows;
    }

    private int bulls;
    private int cows;

    private final int lenNumber;
    private final int symbolsNum;

    private char generateChar(int[] generatedChars, int symbolsNum) {
        int randChar;

        do {
            randChar = random.nextInt(symbolsNum);
        } while (generatedChars[randChar] == 1);

        generatedChars[randChar] = 1;
        return (char) (randChar > 10 ? 'a' + randChar - 11 : '0' + randChar);
    }

    public String generateNumber() {
        StringBuilder strNumber = new StringBuilder();
        int[] generatedChars = new int[36];

        for (int i = 0; i < lenNumber; i++)
            strNumber.append(generateChar(generatedChars, symbolsNum));

        return strNumber.toString();
    }

    public void checkNumber(String inputNumber) {
        for (int i = 0; i < inputNumber.length(); i++) {
            if (inputNumber.charAt(i) == number.charAt(i)) bulls++;
            else if (inputNumber.indexOf(number.charAt(i)) != -1) cows++;
        }
    }

    public boolean checkGuess(String guess) {
        if (guess.length() > lenNumber) return false;

        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) < '9' && guess.charAt(i) >= '0' && guess.charAt(i) - 48 > symbolsNum) return false;
            if (guess.charAt(i) < 'z' && guess.charAt(i) >= 'a' && guess.charAt(i) - 86 > symbolsNum) return false;
        }
        return true;
    }

    public void printScore() {
        System.out.printf("Grade: %d bull(s) and %d cow(s).\n", bulls, cows);
    }

    public void printStart(int lenNumber, int symbolsNum) {
        System.out.print("The secret is prepared: ");
        System.out.print(String.valueOf('*').repeat(lenNumber));
        if (symbolsNum < 11)
            System.out.printf(" (0-%d)\n", symbolsNum - 1);
        else
            System.out.printf(" (0-9), (a, %c)\n", (char) symbolsNum + 97 - 11);
        System.out.println("Okay, let's start a game!\n");
    }
}
