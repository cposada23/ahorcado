package com.sofka.taller.levels;
import com.sofka.taller.input.ReadData;
import org.apache.log4j.Logger;
import java.util.LinkedList;
import java.util.Random;

public abstract class Level {
    static final Logger logger = Logger.getLogger(Level.class);
    protected ReadData readData;
    static final int numbersOfAttempts = 6;
    static int attempts;
    public String wordToGuess;

    public String [] lettersToGuess;
    public String [] guessedLetters;

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
        guessedLetters = new String[lettersToGuess.length];
        return wordToGuess;

    }

    public int validate(char letter) {
        boolean trueGuess = false;
        for (int i = 0; i < lettersToGuess.length; i++) {
            if(wordToGuess.charAt(i) == letter) {
                // La letra existia en la palabra
                guessedLetters[i] = String.valueOf(letter);
                trueGuess = true;
            }
        }
        if(trueGuess) {
            //Validar si ya adivino todas la palabras
            if(validateWord()) {
                // Ya adivino la palabra
                return 3;
            }
            return 1;
        }
        attempts--;
        if(validateNumberOfAttempts()) {
            // No se han superado el numero de intentos
            return 2;
        }
        // Se superaron el numero de intentos
        return  4;
    }

    private boolean validateNumberOfAttempts() {
        if(attempts < 1) {
            return false;
        }
        return true;
    }

    private boolean validateWord() {
        for (int i = 0; i < lettersToGuess.length; i++) {
            if(!lettersToGuess[i].equals(guessedLetters[i])) {
                return false;
            }
        }
        return true;
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
    }


}
