package pl.ski.dataBase;

import pl.ski.ski.Ski;

public class DataBaseSki {

    public static Ski getSki1Producent1(){
        Ski ski = new Ski("narta1", 185, "biegowe", DataBaseProducer.getProducer1());
        ski.setId((long) 1);
        return ski;
    }

    public static Ski getSki2Producent1(){
        Ski ski = new Ski("narta2", 175, "zjazdowe", DataBaseProducer.getProducer1());
        ski.setId((long) 2);
        return ski;
    }

    public static Ski getSki3Producent2(){
        Ski ski = new Ski("narta3", 185, "biegowe", DataBaseProducer.getProducer2());
        ski.setId((long) 3);
        return ski;
    }
}
