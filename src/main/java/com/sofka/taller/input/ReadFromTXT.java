package com.sofka.taller.input;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

public class ReadFromTXT implements ReadData{
    private static final Logger logger = LogManager.getLogger(ReadFromTXT.class);
    private static final String DEFAULT_ARCHIVE = "words.txt";
    private  int level;

    public void setLevel(int level) {
        this.level = level;
    }

    public LinkedList<String> readWords() {
        LinkedList<String> words = new LinkedList<String>();
        String initialBreakPoint = "LEVEL " + level;
        String endBreakPoin = "END LEVEL " + level;

        try {
            FileReader fileReader = new FileReader(DEFAULT_ARCHIVE);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String texto = bufferedReader.readLine();
            while(texto  != null && !texto.equals(initialBreakPoint)) {
                texto = bufferedReader.readLine();
            }

            // Tengo el inicio de las pabras del nivel
            texto = bufferedReader.readLine();

            do {
                words.add(texto);
                texto = bufferedReader.readLine();
            }while(texto != null && !texto.equals(endBreakPoin));




        } catch (Exception e) {
            logger.error("An error occurred while trying to read the archive " + e.getMessage());
        }
        return words;
    }

    public String writeWord(String word) {
        return null;
    }
}
