package hibernate.Visualize_data;

import java.io.IOException;

public class AppMain {
    public static void main(String[] args) {
        TransferDATA transferDATA = new TransferDATA();
        try {
            transferDATA.transferData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
