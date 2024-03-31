package bullscows;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Input the length of the secret code:");
        try {
            int lenNumber = Integer.parseInt(scanner.nextLine());
            if (lenNumber < 1 || lenNumber > 36)
                throw new NumberFormatException("number out of range");

            System.out.println("Input the number of possible symbols in the code:");
            try {
                int symbolsNum = Integer.parseInt(scanner.nextLine());

                if (symbolsNum < 1 || symbolsNum > 36 || symbolsNum < lenNumber)
                    throw new NumberFormatException("number out of range");

                Game game = new Game(lenNumber, symbolsNum);
                game.printStart(lenNumber, symbolsNum);

                while (game.getBulls() != lenNumber) {
                    game.setBulls(0);
                    game.setCows(0);
                    try {
                        String guess = scanner.nextLine();
                        if (!game.checkGuess(guess))
                            throw new NumberFormatException("guessed number too long or wrong chars");
                    game.checkNumber(guess);
                    game.printScore();
                    } catch (NumberFormatException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                System.out.println("Congratulations! You guessed the secret code.");
            } catch (NumberFormatException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
