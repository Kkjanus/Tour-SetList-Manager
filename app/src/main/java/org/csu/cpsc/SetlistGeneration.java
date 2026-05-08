package org.csu.cpsc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Generates the set list for the tour based on the song pool and fan votes.
 */

public class SetlistGeneration {
    
    /**
     * Pairs a song with its vote count for ranking and display purposes.
     */
    public static class VotedSong implements Comparable<VotedSong> {
        private SongPoolManagement.Song song;
        private int votes;

        /** 
         * Constructs a new VotedSong with the given song and vote count.
         *
         * @param song the song object
         * @param votes the number of votes this song received
         */
        public VotedSong(SongPoolManagement.Song song, int votes) {
            this.song = song;
            this.votes = votes;
        }

        /**
         * Returns the song represented by this VotedSong.
         *
         * @return the song object
         */
        public SongPoolManagement.Song getSong() {
            return song;
        }

        /**
         * Returns the vote count for this song.
         *
         * @return the number of votes
         */
        public int getVotes() {
            return votes;
        }

        /**
         * Compares this VotedSong to another based on the number of votes, for sorting purposes.
         * <p>
         * Songs with more votes are ranked higher (sorted in descending order).
         *
         * @param other the other VotedSong to compare with
         * @return a negative integer if this song has more votes, positive if it has fewer votes, zero if equal
         */
        @Override
        public int compareTo(VotedSong other) {
            return Integer.compare(other.votes, this.votes);
        }

        @Override
        public String toString() {
            return song.toString() + " - Votes: " + votes;
        }

    }

    private List<VotedSong> votedSongs;
    private List<VotedSong> setlist;
    private int setlistSize;

    /**
     * Constructs a new SetlistGeneration instance.
     *
     * @param setlistSize the desired number of songs for the final setlist
     */
    public SetlistGeneration(int setlistSize) {
        this.setlistSize = setlistSize;
        votedSongs = new ArrayList<>();
        setlist = new ArrayList<>();
    }

    /**
     * Sets the desired number of songs for the final setlist.
     *
     * @param setlistSize the number of songs to include in the setlist
     */
    public void setSetlistSize(int setlistSize){
        this.setlistSize = setlistSize;
    }

    /**
     * Returns the current setlist size.
     *
     * @return the maximum number of songs for the setlist
     */
    public int getSetlistSize(){
        return setlistSize;
    }

    /**
     * Loads voted songs by matching song titles and artists from the fan voting map to song objects in the master pool.
     * <p>
     * This method populates the votedSongs list with all songs that received votes.
     * Songs are matched using both title and artist to ensure accuracy.
     *
     * @param songPool the SongPoolManagement containing the master pool of songs
     */
    public void loadVotes(SongPoolManagement songPool){
        votedSongs = new ArrayList<>();
        Map<String, Integer> songVotes = FanVoting.getSongVotes();

        for (SongPoolManagement.Song song : songPool.getSongPool()) {
            String title = song.getTitle();
            String artist = song.getArtist();
            String songKey = title + " | " + artist;
            
            if (songVotes.containsKey(songKey)) {
                int votes = songVotes.get(songKey);
                votedSongs.add(new VotedSong(song, votes));
            }
        }
        System.out.println("Loaded " + votedSongs.size() + " voted songs for setlist generation.");
    }

    /**
     * Sorts the votedSongs list from highest to lowest votes using selection sort algorithm.
     * <p>
     * This method implements selection sort by comparing VotedSong objects using their compareTo method.
     */
    public void selectionSort(){
        
        for (int fill = 0; fill < votedSongs.size() - 1; fill++) {
            int posMax = fill;
            for (int next = fill + 1; next < votedSongs.size(); next++) {
                if (votedSongs.get(next).compareTo(votedSongs.get(posMax)) < 0) {
                    posMax = next;
                }
            }
            swap(fill, posMax);
        }
    }

    /**
     * Swaps two VotedSong objects in the votedSongs list at the specified positions.
     *
     * @param fill the index of the first song to swap
     * @param posMax the index of the second song to swap
     */
    private void swap(int fill, int posMax) {
        VotedSong temp = votedSongs.get(fill);
        votedSongs.set(fill, votedSongs.get(posMax));
        votedSongs.set(posMax, temp);
    }

    /**
     * Generates the final setlist by sorting voted songs and selecting the top songs.
     * <p>
     * This method sorts all voted songs by vote count and then selects the top setlistSize songs for the final setlist.
     * If fewer songs have votes than the desired setlistSize, all voted songs are included.
     */
    public void generateSetlist(){
        if (votedSongs.isEmpty()) {
            System.out.println("No voted songs to generate setlist.");
            return;
        }
        selectionSort();

        setlist = new ArrayList<>();
        
        int actualSize = setlistSize;
        if (actualSize > votedSongs.size()) {
            actualSize = votedSongs.size();
        }

        for (int i = 0; i < actualSize; i++) {
            setlist.add(votedSongs.get(i));
        }
        
        System.out.println("Setlist generated with " + setlist.size() + " songs.");
    }

    /**
     * Displays the generated setlist in a formatted table.
     */
    public void displaySetlist() {
        if (setlist.isEmpty()) {
            System.out.println("The setlist is currently empty. Please generate the setlist first.");
            return;
        }
        System.out.println("=== Final Setlist ===");
        for (int i = 0; i < setlist.size(); i++) {
            System.out.println((i + 1) + ". " + setlist.get(i));
        }
    }

    /**
     * Returns a copy of the generated setlist.
     *
     * @return a list of VotedSong objects in the final setlist
     */
    public List<VotedSong> getSetlist() {
        return new ArrayList<>(setlist);
    }
    
}
