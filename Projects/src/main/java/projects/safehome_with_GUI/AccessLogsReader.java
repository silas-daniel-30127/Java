package projects.safehome_with_GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccessLogsReader {
    private List<AccessLog> accessLogs = new ArrayList<>();

    public AccessLogsReader() {
    }

    /**
     * This method should select all the files with .dat extension from the logs path and deserialize them into an array list
     */
    public void init() {
        File file1 = new File("logs");
        File[] files = file1.listFiles();
        if (files != null) {
            for (File file : files) {
                try (final FileInputStream fileInputStream = new FileInputStream(file);
                     final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
                ) {
                    AccessLog accessLog = (AccessLog) objectInputStream.readObject();
                    accessLogs.add(accessLog);
                } catch (IOException e) {
                    System.out.println("IO exception thrown");
                } catch (ClassNotFoundException e) {
                    System.out.println("Class not found exception thrown");
                }
            }
        }
    }

    public void printAccessLogs() {
        System.out.println(Arrays.toString(accessLogs.toArray()));
    }
}
