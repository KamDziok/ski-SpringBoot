package pl.ski.dataBase;

import pl.ski.producer.Producer;

public class DataBaseProducer {

    public static Producer getProducer1(){
        Producer producer = new Producer("producent1");
        producer.setId((long) 1);
        return producer;
    }

    public static Producer getProducer2(){
        Producer producer = new Producer("producent2");
        producer.setId((long) 2);
        return producer;
    }

}
