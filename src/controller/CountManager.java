/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Common.Library;
import java.io.BufferedReader;
import java.io.File;

import java.io.FileInputStream;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import view.Menu;

public class CountManager extends Menu<String> {

    Library lib = new Library();
    static String[] choices = {"Count Word In File", "Find File By Word"};

    public CountManager() {
        super("======================== Word Program  ========================", choices, "Exit");

    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                String path = lib.getValue("Enter Path: ");
                String cout = lib.getValue("Enter Enter Word: ");
               System.out.println("Bout:" + CountWordsInFile( path, cout));
                
                break;
            case 2:
                String path1 = lib.getValue("Enter Path: ");
                String word = lib.getValue("Enter Word: ");
                File path2 = new File(path1);
                searchFile(path2, word);
        }
    }

    public int CountWordsInFile(String path, String cout) {
        int wordCount = 0;
        try {
            FileInputStream fis = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();

            while (line != null) {
                String[] words = line.split(" ");
                for (String word : words) {
                    if (word.contains(cout)) {
                        wordCount++;
                    }
                }
                line = br.readLine();
            }

            
            br.close();
            isr.close();
            fis.close();

        } catch (Exception e) {
            System.out.println("1");
        }
        return wordCount;
    }

    public void searchFile(File path, String word) {
        File folder = path;
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isFile() && fileEntry.getName().contains(word)) {
                searchFile(fileEntry, word);
            } else {
                System.out.println(fileEntry.getName());
            }
        }
    }

    public List<String> getFileName(String source, String word) throws Exception {
        List<String> result = new ArrayList<>();
        File directory = new File(source);

        if (!directory.exists() || !directory.isDirectory()) {
            throw new Exception("Source is not a valid directory.");
        }
        File[] f = directory.listFiles();
        if (f != null) {
            for (File file : f) {
                if (file.isFile() && CountWordsInFile(file.getName(), word) > 0) {
                    result.add(file.getName());
                }
            }
        }
        return result;
    }

}
