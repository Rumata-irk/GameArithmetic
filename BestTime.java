package GameArithmetic;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class BestTime {

    private final String directory = System.getProperty("user.home");
    private final String fileName = "scores.txt";
    private final String absolutePath = directory + File.separator + fileName;
    private HashMap<String, String> hashMapFromFile = new HashMap<>();

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mm:ss");
    public static LocalTime bestTime;
    public static String mapKey;

    private boolean checkFile() {
        File file = new File(absolutePath);
        return file.exists();
    }

    public void createMap() {
        try {
            if (!checkFile()) {
                for (int i = 1; i < 7; i++) {
                    hashMapFromFile.put("Level" + i, "00:59:59");
                }
                System.out.println(hashMapFromFile);
                Path path = Paths.get(absolutePath);
                Files.createFile(path);
                System.out.println("File create");
                saveToFile();
            } else {
                loadFromFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToFile() {
        try {
            BufferedWriter a = new BufferedWriter(new FileWriter(absolutePath));
            Set<String> keys = hashMapFromFile.keySet();
            for (String key : keys) {
                a.write(key + " " + hashMapFromFile.get(key));
                a.newLine();
            }
            a.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        try {
            Scanner scan = new Scanner(new File(absolutePath));
            while (scan.hasNextLine()) {
                String[] entry = scan.nextLine().split(" ");
                hashMapFromFile.put(entry[0], entry[1]);
            }
            System.out.println(hashMapFromFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void keySelection() {
        if (GameOperations.operation.equals("+") && Arrays.equals(GameOperations.numberOfDigits, new int[]{10, 99})) {
            mapKey = "Level1";
        } else if (GameOperations.operation.equals("+") && Arrays.equals(GameOperations.numberOfDigits, new int[]{10, 999})) {
            mapKey = "Level2";
        } else if (GameOperations.operation.equals("-") && Arrays.equals(GameOperations.numberOfDigits, new int[]{10, 99})) {
            mapKey = "Level3";
        } else if (GameOperations.operation.equals("-") && Arrays.equals(GameOperations.numberOfDigits, new int[]{10, 999})) {
            mapKey = "Level4";
        } else if (GameOperations.operation.equals("*") && Arrays.equals(GameOperations.numberOfDigits, new int[]{10, 99})) {
            mapKey = "Level5";
        } else if (GameOperations.operation.equals("*") && Arrays.equals(GameOperations.numberOfDigits, new int[]{10, 999})) {
            mapKey = "Level6";
        }
    }

    public void checkTime(LocalTime timeFromTimer) {

//        LocalTime timeTimer = LocalTime.parse(timeFromTimer);
        System.out.println("Value: " + hashMapFromFile.get(mapKey));
        LocalTime time = LocalTime.parse(hashMapFromFile.get(mapKey));

        if (time.isBefore(timeFromTimer)) {
            bestTime = time;
        } else {
            bestTime = timeFromTimer;
        }

        hashMapFromFile.put(mapKey, bestTime.toString());
        System.out.println(hashMapFromFile);
        saveToFile();
    }

    public String bestTimeFirst() {
        LocalTime time = LocalTime.parse(hashMapFromFile.get(mapKey));
        return time.format(dtf);
    }


}
