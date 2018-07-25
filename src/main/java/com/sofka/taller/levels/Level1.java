package com.sofka.taller.levels;

import com.sofka.taller.input.ReadData;

public class Level1 extends Level {

    public Level1() {
        super();

    }

    public void setReadData(ReadData readData) {
        this.readData = readData;
        this.readData.setLevel(1);
    }
}
