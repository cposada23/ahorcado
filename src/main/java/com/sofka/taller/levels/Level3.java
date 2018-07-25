package com.sofka.taller.levels;

import com.sofka.taller.input.ReadData;

public class Level3 extends Level {
    public Level3() {
        super();

    }

    @Override
    public void setReadData(ReadData readData) {
        this.readData = readData;
        this.readData.setLevel(3);
    }
}
