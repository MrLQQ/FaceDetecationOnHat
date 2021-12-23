package com.mrlqq.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class pythonFace {
    public String OnHat(String imgName) {
        String imgInHatName="";
        Process proc;
        try {
            String pythonFile = "src/main/java/com/mrlqq/Python/face.py";
            String[] command = new String[]{"python", pythonFile, imgName};
            proc = Runtime.getRuntime().exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            List outLine = new ArrayList();
            while ((line = in.readLine()) != null) {
                outLine.add(line);
                System.out.println(line);
            }
            imgInHatName = (String) outLine.get(outLine.size() - 1);
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return imgInHatName;
    }
    public static void main(String[] args) {
        pythonFace pythonFace = new pythonFace();
        System.out.println(pythonFace.OnHat("1.jpg"));
    }
}
