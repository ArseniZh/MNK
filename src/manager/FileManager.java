package manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    public ArrayList<Double> coppingFromFile(String path, ArrayList<Double> list) throws FileNotFoundException {
        File file = new File(path);
        Scanner scan = new Scanner(file);

        String line;
        line = scan.nextLine();
        String[] numbers = line.split(" ");
        for (String num : numbers) {
            list.add(Double.parseDouble(num));
        }

        return list;
    }
}
