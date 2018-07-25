package com.sofka.taller.input;

import java.util.LinkedList;

public interface ReadData {
    public void setLevel(int level);
    public LinkedList<String> readWords();
    public String writeWord(String word);
}
