package com.sofka.taller.levels;
import com.sofka.taller.input.ReadData;
import org.apache.log4j.Logger;
import java.util.LinkedList;
import java.util.Random;

public abstract class Level {
    static final Logger logger = Logger.getLogger(Level.class);
    private static final int LETTER_IN_WORD= 1;
    private static final int LETTER_NOT_IN_WORD = 2;
    private static final int WIN = 3;
    private static final int LOOSE= 4;
    protected ReadData readData;
    static final int numbersOfAttempts = 6;
    static int attempts;
    public String wordToGuess;

    private String [] lettersToGuess;
    private char [] guessedLetters;

    public Level() {
        attempts = numbersOfAttempts;
    }

    public abstract void setReadData(ReadData readData);

    public String selectWord() {
        LinkedList<String> words = readData.readWords();
        Random random =  new Random();
        int countWords = words.size();
        int randomNumber = random.nextInt(countWords);
        wordToGuess = words.get(randomNumber);

        lettersToGuess = wordToGuess.split("");
        guessedLetters = new char[lettersToGuess.length];
        return wordToGuess;

    }

    public int validate(char letter) {
        boolean trueGuess = false;
        for (int i = 0; i < lettersToGuess.length; i++) {
            if(wordToGuess.charAt(i) == letter) {
                // La letra existia en la palabra
                guessedLetters[i] = letter;
                trueGuess = true;
            }
        }
        if(trueGuess) {
            //Validar si ya adivino todas la palabras
            if(validateWord()) {
                // Ya adivino la palabra
                return WIN;
            }
            return LETTER_IN_WORD;
        }
        attempts--;
        if(validateNumberOfAttempts()) {
            // No se han superado el numero de intentos
            return LETTER_NOT_IN_WORD;
        }
        // Se superaron el numero de intentos
        return  LOOSE;
    }

    private boolean validateNumberOfAttempts() {
        if(attempts < 1) {
            return false;
        }
        return true;
    }

    private boolean validateWord() {
        String guess = String.valueOf(guessedLetters);
        if(guess.equals(wordToGuess)) {
            return true;
        }
        return false;

    }

    public void draw() {
        switch (attempts) {
            case 6:
                System.out.println(" ---------------------");
                for (int j = 0; j < 15; j++) {
                System.out.println(" |");

            }
            System.out.println("__________");
            break;

            case 5:
                System.out.println(" ---------------------");
                System.out.println(" |                     |");
                System.out.println(" |                     |");
                System.out.println(" |                  -------");
                System.out.println(" |                 | -  -  |");
                System.out.println(" |                 |   o   |");
                System.out.println(" |                  -------");
                for (int j = 0; j < 10; j++) {
                System.out.println(" |");

            }
            System.out.println("__________");
            break;

            case 4:
                System.out.println(" ---------------------");
                System.out.println(" |                     |");
                System.out.println(" |                     |");
                System.out.println(" |                  -------");
                System.out.println(" |                 | -  -  |");
                System.out.println(" |                 |   o   |");
                System.out.println(" |                  -------");
                System.out.println(" |                     |   ");
                System.out.println(" |                     |   ");
                System.out.println(" |                     |   ");
                System.out.println(" |                     |   ");
                System.out.println(" |                     |   ");
                for (int j = 0; j < 5; j++) {
                    System.out.println(" |");

                }
                System.out.println("__________");
                break;

            case 3:
                System.out.println(" ---------------------");
                System.out.println(" |                     |");
                System.out.println(" |                     |");
                System.out.println(" |                  -------");
                System.out.println(" |                 | -  -  |");
                System.out.println(" |                 |   o   |");
                System.out.println(" |                  -------");
                System.out.println(" |                     |   ");
                System.out.println(" |                   / |   ");
                System.out.println(" |                 /   |   ");
                System.out.println(" |                /    |   ");
                System.out.println(" |                     |   ");
                for (int j = 0; j < 5; j++) {
                    System.out.println(" |");

                }
                System.out.println("__________");
                break;

            case 2:
                System.out.println(" ---------------------");
                System.out.println(" |                     |");
                System.out.println(" |                     |");
                System.out.println(" |                  -------");
                System.out.println(" |                 | -  -  |");
                System.out.println(" |                 |   o   |");
                System.out.println(" |                  -------");
                System.out.println(" |                     |   ");
                System.out.println(" |                   / | \\ ");
                System.out.println(" |                  /  |   \\ ");
                System.out.println(" |                 /   |     \\ ");
                System.out.println(" |                     |   ");
                for (int j = 0; j < 5; j++) {
                    System.out.println(" |");

                }
                System.out.println("__________");
                break;

            case 1:
                System.out.println(" ---------------------");
                System.out.println(" |                     |");
                System.out.println(" |                     |");
                System.out.println(" |                  -------");
                System.out.println(" |                 | -  -  |");
                System.out.println(" |                 |   o   |");
                System.out.println(" |                  -------");
                System.out.println(" |                     |   ");
                System.out.println(" |                   / | \\ ");
                System.out.println(" |                  /  |   \\ ");
                System.out.println(" |                 /   |     \\ ");
                System.out.println(" |                     |   ");
                System.out.println(" |                    /  ");
                System.out.println(" |                   /      ");
                System.out.println(" |                  /       ");
                for (int j = 0; j < 2; j++) {
                System.out.println(" |");

            }
            System.out.println("__________");
            break;

            case 0:
                System.out.println(" ---------------------");
                System.out.println(" |                     |");
                System.out.println(" |                     |");
                System.out.println(" |                  -------");
                System.out.println(" |                 | X  X  |");
                System.out.println(" |                 |   o   |");
                System.out.println(" |                  -------");
                System.out.println(" |                     |   ");
                System.out.println(" |                   / | \\ ");
                System.out.println(" |                  /  |   \\ ");
                System.out.println(" |                 /   |     \\ ");
                System.out.println(" |                     |   ");
                System.out.println(" |                    / \\");
                System.out.println(" |                   /   \\  ");
                System.out.println(" |                  /     \\ ");
                for (int j = 0; j < 2; j++) {
                System.out.println(" |");

            }
            System.out.println("__________");
            System.out.println("GAME OVER");
            break;
        }
        printGuessedCharacters();
    }

    private void printGuessedCharacters() {
        for (int i = 0; i < guessedLetters.length; i++) {
            if(!Character.isLetter(guessedLetters[i])) {
                System.out.printf(" %c%c ", '_', '_');
            }else {
                System.out.printf(" %c " , guessedLetters[i]);
            }

        }
        System.out.println("");
    }

}
