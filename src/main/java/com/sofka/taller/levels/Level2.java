package com.sofka.taller.levels;

import com.sofka.taller.input.ReadData;

public class Level2 extends Level {
    public Level2() {
        super();

    }

    @Override
    public void setReadData(ReadData readData) {
        this.readData = readData;
        this.readData.setLevel(2);
    }
}
