package org.csu.cpsc;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a pool of songs for the tour set list.
 * <p>
 * This class stores songs in a master pool, supports adding and removing songs,
 * prevents duplicates by artist and title, and exposes the pool to other
 * components such as setlist generation.
 */
public class SongPoolManagement {

    /**
     * Represents a single song entry in the song pool.
     */
    public static class Song {
        private String artist;
        private String title;

        /**
         * Constructs a new Song.
         *
         * @param title  the song title
         * @param artist the song artist or performer
         */
        public Song(String title, String artist) {
            this.title = title;
            this.artist = artist;
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
         * Returns a human-readable representation of the song.
         *
         * @return the song title
         */
        @Override
        public String toString() {
            return title + " by " + artist;
        }

    }

    /**
     * The live song pool used by the application.
     */
    private List<Song> songPool;

    /**
     * Constructs a new SongPoolManagement instance.
     */
    public SongPoolManagement() {
        songPool = new ArrayList<>();
    }

    /**
     * Adds a song to the song pool.
     * <p>
     * If the same artist/title combination already exists, the song is not added.
     *
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
     * Removes a song from the song pool by title and artist.
     *
     * @param title  the title of the song to remove
     * @param artist the artist of the song to remove
     */
    public void removeSong(String title, String artist) {
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
        for (int i = 0; i < songPool.size(); i++) {
            System.out.println((i + 1) + ". " + songPool.get(i));
        }
    }

    /**
     * Returns the full song pool list.
     *
     * @return the list of songs in the pool
     */
    public List<Song> getSongPool() {
        return songPool;
    }

    /**
     * Retrieves a song from the song pool based on title and artist.
     *
     * @param title  the title of the song to retrieve
     * @param artist the artist of the song to retrieve
     * @return the matching song, or {@code null} if none is found
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
