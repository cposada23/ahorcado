package com.sofka.taller.factory;

import com.sofka.taller.levels.Level;
import com.sofka.taller.levels.Level1;
import com.sofka.taller.levels.Level2;
import com.sofka.taller.levels.Level3;

public class LevelFactory {
    public static Level getLevel(int level) throws ClassNotFoundException{
        switch (level) {
            case 1:
                return new Level1();
            case 2:
                return new Level2();
            case 3:
                return new Level3();
            default:
                throw new ClassNotFoundException();
        }
    }
}
