package com.example.final_project;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;



public class HelloController{
    int count;

    // components
    @FXML
    private Button SearchVideo;

    @FXML
    private Button SplitVideo;

    @FXML
    private TextField AddSearchKeyWord;

    @FXML
    private ListView<SearchVideoList> VideoListView;


    // A list that enables listeners to track changes when they occur.
    ObservableList<SearchVideoList> videoList= FXCollections.observableArrayList();

    private void ListView() {
        VideoListView.setItems(videoList);

    }

    //Click the search button
    @FXML
    private void searchVideo(Event e) {
        String currentSearchID = getRandomnumber();
        System.out.println(AddSearchKeyWord.getText());
        String currentStatus = "In the process";
        videoList.add(new SearchVideoList(AddSearchKeyWord.getText(), currentSearchID, currentStatus));
        try {
            String[] command = new String[] {"/bin/sh", "-c", "mkdir downloadVideo; cd downloadVideo; mkdir " + AddSearchKeyWord.getText() +"; cd "+ AddSearchKeyWord.getText() + "; youtube-dl 'ytsearch10:"+ AddSearchKeyWord.getText()+ "'" };
            Process newProcess;
            newProcess = Runtime.getRuntime().exec(command);
            newProcess.waitFor();
            BufferedReader buffeReader = new BufferedReader(new InputStreamReader(newProcess.getInputStream(), "UTF-8"));
            String line;
            while ((line = buffeReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        count++;
        updateVideoList(currentSearchID);
    }

    //Click the split button
    @FXML
    private void splitVideo(Event e) throws Exception {
        File folder = new File("./downloadVideo/"+ AddSearchKeyWord.getText());
        File[] listOfFiles = folder.listFiles();
        int numbersFile = 0;
        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getName());
                String[] command = new String[] {"/bin/sh", "-c", "cd downloadVideo/"+ AddSearchKeyWord.getText() + "; mkdir 'splittedVideo"+ numbersFile + "'; scenedetect -i '" + file.getName() + "' -o splittedVideo"+ numbersFile + " detect-content split-video" };
                numbersFile++;
                Process newProcess;
                newProcess = Runtime.getRuntime().exec(command);
                newProcess.waitFor();
                BufferedReader buffeReader = new BufferedReader(new InputStreamReader(newProcess.getInputStream(), "UTF-8"));
               String line;
                while ((line = buffeReader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        }
    }



    public static String getRandomnumber() {
        ArrayList number= new ArrayList();
        Random random= new Random();
        while(true) {
            int rannumber = random.nextInt((99999-10000)+1)+1000;
            if(!number.contains(rannumber)) {
                return ""+rannumber;
            }
        }
    }
    private void updateVideoList(String SearchID ) {
        for(int i = 0; i<count;i++) {
            if (SearchID.equals( videoList.get(i).getCurrentSearchID())){
                videoList.get(i).setStatus("Download Completed");
            }
        }
        //Refresh the Listview
        VideoListView.setItems(null);
        ListView();
    }


}

// source code:https://www.baeldung.com/run-shell-command-in-java