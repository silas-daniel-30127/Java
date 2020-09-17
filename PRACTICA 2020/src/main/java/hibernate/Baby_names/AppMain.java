package hibernate.Baby_names;

import java.io.IOException;

public class AppMain {
    public static void main(String[] args) {
        try {
            DataReader registry = new DataReader("https://liveexample.pearsoncmg.com/data/babynamesranking2010.txt");
            registry.transferData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
