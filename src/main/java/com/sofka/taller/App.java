package com.sofka.taller;

import com.sofka.taller.Utils.Props;
import com.sofka.taller.Utils.Util;

import java.util.LinkedList;

import com.sofka.taller.factory.LevelFactory;
import com.sofka.taller.input.ReadData;
import com.sofka.taller.input.ReadFromTXT;
import com.sofka.taller.levels.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;




/**
 * Hello world!
 *
 */
public class App {
    private static Props props = Props.getInstance();
    private static LinkedList<Player> sessionPlayers = new LinkedList();
    private static final Logger logger = LogManager.getLogger(App.class);
    private static final int INITIAL_SCORE = 0;
    private static final int MAX_LEVEL = 3;


    /** Constantes de los menus */
    private static final char ADD_PLAYER = '1';
    private static final char VIEW_SCORES = '2';
    private static final char EXIT = '3';
    private static final char SET_LEVEL = '1';
    private static final char SET_ORIGIN = '2';
    private static final char PLAY_HANGMAN = '3';
    private static final char RETURN = '4';
    private static final int LETTER_IN_WORD= 1;
    private static final int LETTER_NOT_IN_WORD = 2;
    private static final int WIN = 3;
    private static final int LOOSE= 4;



    /** Initial method */
     private static ReadData readData;
     private static Level level;
     private static int nPlayers = 0;


    public static void main(String[] args) {
        /** Ejemplo de uso de properties */
        String result = props.getProperty("uno");
        System.out.println(result);
        /** Fin ejemplo de uso de properties */


        PropertyConfigurator.configure("log4j.properties");
        boolean play = true;
        char initialOption;
        while (play) {

            try {
                configureDefaults();
                showInitialMenu();
                initialOption = Util.readChar("Enter an option");
                switch (initialOption) {
                    case ADD_PLAYER:
                        String playerName = addPlayer();
                        beginGame(playerName);
                        break;
                    case VIEW_SCORES:
                        break;
                    case EXIT:
                        play = false;
                        break;
                    default:
                        System.out.println("Choose a valid option");
                }
            } catch (ClassNotFoundException e) {
                logger.error("An error occurred while trying to configure defaults");
            } catch (Exception  e) {
                logger.error("An error occurred " + e.getMessage());
            }
        }


    }

    private static void configureDefaults() throws ClassNotFoundException {
        readData = new ReadFromTXT();
        level = LevelFactory.getLevel(1);
        level.setReadData(readData);
    }

    private static void setLevel() {
        System.out.println("***** LEVEL SELECTION ******");
        try {
            int levelNumber = Util.readInt("Enter new level");
            boolean ask = true;
            do {
                try {
                    level = LevelFactory.getLevel(levelNumber);
                    ask = false;
                }catch (ClassNotFoundException e) {
                    logger.warn("Please enter a valid level");
                    levelNumber = Util.readInt("Enter new level");
                }

            }while(ask);
            level.setReadData(readData);
        } catch (Exception e) {
            logger.error("An error occurred while changing the level" + e.getMessage());
        }
    }

    private static void beginGame(String playerName) {
        System.out.println("****** WELCOME " + playerName + "*****");
        boolean play = true;
        char option;
        while (play) {
            showSecondaryMenu();
            try {
                option = Util.readChar("Enter an option");
                switch (option) {
                    case SET_LEVEL:
                        setLevel();
                        break;
                    case SET_ORIGIN:
                        // TODO SET ORIGIN
                        break;
                    case PLAY_HANGMAN:
                        playHangMan();
                        break;
                    case RETURN:
                        play = false;
                        break;
                    default:
                        System.out.println("Choose a valid option");
                }

            } catch (Exception e) {
                logger.error("An error occurred " + e.getMessage());
            }
        }

    }

    private static void playHangMan(){
        /** Obtener la palabra */
        String wordToGuess = level.selectWord();
        System.out.println(wordToGuess);

        boolean finish = false;
        while(!finish) {
            /** Dibujar el muñeco */
            level.draw();
            /** Pedir la letra y validarla en la palabra */
            try {
                char letter = Util.readChar("Enter letter to guess");
                int result = level.validate(letter);
                switch (result) {
                    case LETTER_IN_WORD:
                        System.out.println("The letter is in the word, good job!");
                        break;
                    case LETTER_NOT_IN_WORD:
                        System.out.println("The letter is not in the word, try again!");
                        break;
                    case WIN:
                        printWinning();
                        finish = true;
                        break;
                    case LOOSE:
                        printLoosing();
                        finish = true;
                        break;
                }
            } catch (Exception e) {
                logger.error("An error occurred " + e.getMessage());
            }

        }


    }

    private static void printWinning() {
        System.out.println("****************************************");
        System.out.println("*                                      *");
        System.out.println("*          YOU WIN!!!!!!               *");
        System.out.println("*                                      *");
        System.out.println("****************************************");
    }

    private static void printLoosing() {
        System.out.println("****************************************");
        System.out.println("*                                      *");
        System.out.println("*          YOU LOOSE!!!!!!             *");
        System.out.println("*                                      *");
        System.out.println("****************************************");
    }




    private static String addPlayer(){
        nPlayers++;
        System.out.println("******** NEW PLAYER ********");
        String playerName = "";
        try {
            playerName = Util.readString("Enter your name");
            Player player = new Player(playerName, nPlayers, INITIAL_SCORE);
            sessionPlayers.add(player);
        } catch (Exception e) {
            logger.error("An error occurred " + e.getMessage());
        }
        return playerName;
    }

    private static void showInitialMenu() {
        System.out.println("*****  Welcome ********");
        System.out.println("-- CHOOSE AN OPTION --");
        System.out.println("\t1) New player");
        System.out.println("\t2) Scores");
        System.out.println("\t3) EXIT ");
    }

    private static void showSecondaryMenu() {
        System.out.println("-- CHOOSE AN OPTION --");
        System.out.println("\t1) Set level");
        System.out.println("\t2) Choose origin of the data");
        System.out.println("\t3) Begin the game");
        System.out.println("\t4) Initial menu");
    }
}
