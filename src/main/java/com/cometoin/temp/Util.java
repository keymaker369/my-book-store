package com.cometoin.temp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Util {

    public static void writeToFile(List<String> picturePageUrls, String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath);

        for (String str : picturePageUrls) {
            writer.write(str);
            writer.write("\n");
        }
    }
}
