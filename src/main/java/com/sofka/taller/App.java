package com.sofka.taller;

import com.sofka.taller.Utils.Util;
import java.util.LinkedList;

import com.sofka.taller.input.ReadData;
import com.sofka.taller.input.ReadFromTXT;
import com.sofka.taller.levels.Level;
import com.sofka.taller.levels.Level1;

import com.sofka.taller.levels.Level2;
import com.sofka.taller.levels.Level3;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * Hello world!
 *
 */
public class App {
    private static LinkedList<Player> sessionPlayers = new LinkedList();
    private static final Logger logger = LogManager.getLogger(App.class);
    private static final int INITIAL_SCORE = 0;
    private static final int MAX_LEVEL = 3;

    /** Initial method */
     private static ReadData readData;
     private static Level level;
     private static int nPlayers = 0;


    public static void main(String[] args) {
        PropertyConfigurator.configure("log4j.properties");
        boolean jugar = true;
        char initialOption;
        while (jugar) {
            configureDefaults();
            showInitialMenu();
            try {
                initialOption = Util.readChar("Enter an option");
                switch (initialOption) {
                    case '1':
                        String playerName = addPlayer();
                        beginGame(playerName);
                        break;
                    case '2':
                        break;
                    case '3':
                        jugar = false;
                        break;
                    default:
                        System.out.println("Choose a valid option");
                }

            } catch (Exception e) {
                logger.error("An error occurred " + e.getMessage());
            }
        }


    }

    private static void configureDefaults(){
        readData = new ReadFromTXT();
        level = new Level1();
        level.setReadData(readData);
    }

    private static void setLevel() {
        System.out.println("***** LEVEL SELECTION ******");
        try {
            int levelNumber = Util.readInt("Enter new level");
            boolean ask = true;
            do {
                switch (levelNumber) {
                    case 1:
                        ask = false;
                        level = new Level1();
                        break;
                    case 2:
                        ask = false;
                        level = new Level2();
                        break;
                    case 3:
                        ask = false;
                        level = new Level3();
                        break;
                    default:
                        System.out.println("Please enter a valid level");
                        break;
                }
            }while(ask);
            level.setReadData(readData);
        } catch (Exception e) {
            logger.error("An error occurred while changin the level" + e.getMessage());
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
                    case '1':
                        setLevel();
                        break;
                    case '2':
                        // TODO SET ORIGIN
                        break;
                    case '3':
                        playHangMan();
                        break;
                    case '4':
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

        boolean finish = false;
        while(!finish) {
            /** Dibujar el muñeco */
            level.draw();
            /** Pedir la letra y validarla en la palabra */
            try {
                char letter = Util.readChar("Enter letter to guess");
                int result = level.validate(letter);
                switch (result) {
                    case 1:
                        System.out.println("The letter is in the word, good job");
                        break;
                    case 2:
                        System.out.println("The letter is not in the word, try again");
                        break;
                    case 3:
                        System.out.println("You win!");
                        break;
                    case 4:
                        System.out.println("You loose");
                        finish = true;
                        break;
                }
            } catch (Exception e) {
                logger.error("An error occurred " + e.getMessage());
            }

        }


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
