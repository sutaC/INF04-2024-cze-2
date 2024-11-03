package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static class Album {
        public String artist;
        public String album;
        public int songsNumber;
        public int year;
        public int downloadNumber;
    }

//    Elements
    private JPanel Root;
    private JButton btnLeft;
    private JButton btnRight;
    private JButton btnDownload;
    private JLabel labImg;
    private JLabel labDownloadNumber;
    private JLabel labSongsNumber;
    private JLabel labYear;
    private JLabel labAlbum;
    private JLabel labArtist;

//    Data
    private final List<Album> albums = new ArrayList<Album>();
    private int currentIndex = 0;

    public Main() {
        readAlbums();
        if(!albums.isEmpty())
            displayAlbum(currentIndex);
//        Listeners
        btnRight.addActionListener(e -> {
            currentIndex++;
            if(currentIndex >= albums.size()){
                currentIndex = 0;
            }
            displayAlbum(currentIndex);
        });
        btnLeft.addActionListener(e -> {
            currentIndex--;
            if(currentIndex < 0){
                currentIndex = albums.size() - 1;
            }
            displayAlbum(currentIndex);
        });
        btnDownload.addActionListener(e -> {
            Album album = albums.get(currentIndex);
            album.downloadNumber++;
            labDownloadNumber.setText(String.valueOf(album.downloadNumber));
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MojeDźwięki, Wykonał: XYZ");
        frame.setContentPane(new Main().Root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void readAlbums() {
        albums.clear();
        URL fileURL = getClass().getResource("/data/Data.txt");
        assert fileURL != null;
        File file = new File(fileURL.getPath());
        Scanner scn;
        try {
            scn = new Scanner(file, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        while (scn.hasNextLine()){
            Album album = new Album();
            album.artist = scn.nextLine().strip();
            album.album = scn.nextLine().strip();
            album.songsNumber = Integer.parseInt(scn.nextLine().strip());
            album.year = Integer.parseInt(scn.nextLine().strip());
            album.downloadNumber = Integer.parseInt(scn.nextLine().strip());
            albums.add(album);
            if(scn.hasNextLine())
                scn.nextLine(); // Skips whitespace
        }
        scn.close();
    }

    private void displayAlbum(int index) {
        if(index < 0 || index >= albums.size()) {
            throw new IndexOutOfBoundsException();
        }
        Album album = albums.get(index);
        labArtist.setText(album.artist);
        labAlbum.setText(album.album);
        labSongsNumber.setText(String.valueOf(album.songsNumber) + " utworów");
        labYear.setText(String.valueOf(album.year));
        labDownloadNumber.setText(String.valueOf(album.downloadNumber));
    }
}