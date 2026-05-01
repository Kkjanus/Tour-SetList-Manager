package org.csu.cpsc;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a pool of songs for the tour set list.
 */
public class SongPoolManagement {

    /**
     * Represents a single song entry in the song pool.
     */
    public static class Song {
        private String artist;
        private String title;
        private int duration;

        /**
         * Constructs a new Song.
         *
         * @param artist   the song artist or performer
         * @param title    the song title
         * @param duration the song duration in seconds
         */
        public Song(String artist, String title, int duration) {
            this.artist = artist;
            this.title = title;
            this.duration = duration;
        }

        /**
         * Returns the artist for this song.
         *
         * @return the artist name
         */
        public String getArtist() {
            return artist;
        }

        /**
         * Returns the song title.
         *
         * @return the title of the song
         */
        public String getTitle() {
            return title;
        }

        /**
         * Returns the duration of the song.
         *
         * @return the duration in seconds
         */
        public int getDuration() {
            return duration;
        }

        /**
         * Formatting the duration of songs to go from seconds to minutes and seconds.
         * @return the formatted duration in minutes and seconds
         */
        public String getFormattedDuration() {
            int minutes = duration / 60;
            int seconds = duration % 60;
            return minutes + ":" + (seconds < 10 ? "0" : "") + seconds;
        }

        @Override
        public String toString() {
            return title + " (" + getFormattedDuration() + ")";
        }

    }

    private List<Song> songPool;

    /**
     * Constructs a new SongPoolManagement instance.
     */
    public SongPoolManagement() {
        songPool = new ArrayList<>();
    }

    /**
     * Adds a song to the song pool.
     * Prevents duplicate songs based on artist and title.
     * @param song the song to add
     */
    public void addSong(Song song) {
        if (getSong(song.getTitle(), song.getArtist()) == null) {
            songPool.add(song);
            System.out.println("Added song: " + song);
        } else {
            System.out.println("Song already exists in the pool.");
        }
    }

    /**
     * Removes a song from the song pool based on artist and title.
     * @param artist the artist of the song to remove
     * @param title the title of the song to remove
     */
    public void removeSong(String title, String artist){
        Song song = getSong(title, artist);
        if (song != null) {
            songPool.remove(song);
            System.out.println("Removed song: " + song);
        } else {
            System.out.println("Song not found in the pool.");
        }

    }

    /**
     * Displays all songs currently in the master song pool.
     */
    public void displaySongs() {
        if (songPool.isEmpty()) {
            System.out.println("The song pool is currently empty.");
            return;
        }
        System.out.println("=== Master Song Pool ===");
        for( int i = 0; i < songPool.size(); i++) {
            System.out.println((i + 1) + ". " + songPool.get(i));
        }
    }

    /**
     * Retrieves a song from the song pool based on artist and title.
     * @param title the title of the song to retrieve
     * @param artist the artist of the song to retrieve
     */
    public Song getSong(String title, String artist) {
        for (Song song : songPool) {
            if (song.getTitle().equalsIgnoreCase(title) && song.getArtist().equalsIgnoreCase(artist)) {
                return song;
            }
        }
        return null;
    }

}
