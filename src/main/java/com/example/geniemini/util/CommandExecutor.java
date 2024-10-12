package com.example.geniemini.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//Details - We'll create a utility class to execute shell commands from Java. This will
//          help in running Hadoop/Spark job commands.


public class CommandExecutor {

    public static String executeCommand(String command) {
        StringBuilder output = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
