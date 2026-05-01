package org.csu.cpsc;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * Manages fan voting for songs to be included in the tour set list.
 * Each fan can vote for their favorite songs, and the system will keep track of the votes to determine which songs are most popular.
 * Votes are tracked per song and duplicate votes are prevented to ensure fairness in the voting process. (fanNames)
 */
public class FanVoting {
    private static Map<String, Integer> songVotes = new HashMap<>(); // Maps song titles to their vote counts (shared across fans)
    private Queue<String> fanVotesQueue; // Queue to track the order of songs this fan voted for
    private List<String> votedSongs; // List to track the songs the fan has voted for
    private static List<String> fanNames = new ArrayList<>(); // List to track the names of fans who have voted (shared)

    private String fanName;
    private int maxVotesPerFan;
    private SongPoolManagement songPool;

    /**
     * Constructs a new FanVoting instance for a specific fan with a maximum number of votes allowed.
     */
    public FanVoting(String fanName, int maxVotesPerFan, SongPoolManagement songPool) {
        this.fanName = fanName;
        this.maxVotesPerFan = maxVotesPerFan;
        this.songPool = songPool;
        this.fanVotesQueue = new LinkedList<>();
        this.votedSongs = new ArrayList<>();
    }

    /**
     * Allows a fan to vote for a song. 
     * Cannot vote if they have already submitted votes, cannot vote same song twice, and cannot exceed maxVotesPerFan.
     */
    public void castVote(String songTitle, String artist) {
        if (fanNames.contains(fanName)) {
            System.out.println(fanName + " has already voted.");
            return;
        }
        if (songPool.getSong(songTitle, artist) == null) {
            System.out.println("Song not found in the pool.");
            return;
        }
        if (votedSongs.contains(songTitle)) {
            System.out.println(fanName + " has already voted for this song.");
            return;
        }
        if (votedSongs.size() >= maxVotesPerFan) {
            System.out.println(fanName + " has reached the maximum number of votes.");
            return;
        }

        fanVotesQueue.add(songTitle);
        votedSongs.add(songTitle);

        Integer currentVotes = songVotes.get(songTitle);
        if (currentVotes == null) {
            songVotes.put(songTitle, 1);
        } else {
            songVotes.put(songTitle, currentVotes + 1);
        }

        System.out.println(fanName + " voted for: " + songTitle);
    }

    /**
     * Locks in votes so that the fan cannot vote again and is called when fan is done voting.
     */
    public void submitVotes() {
        if (votedSongs.isEmpty()) {
            System.out.println(fanName + " has not voted for any songs.");
            return;
        }
        if (!fanNames.contains(fanName)) {
            fanNames.add(fanName);
            System.out.println(fanName + " has submitted their votes.");
        } else {
            System.out.println(fanName + " has already submitted their votes.");
        }
    }

    /**
     * Checks if a fan has already voted.
     */
    public static boolean hasVoted(String fanName) {
        return fanNames.contains(fanName);
    }

    /**
     * Checks if this fan has already voted for a specific song.
     */
    public boolean hasVotedForSong(String songTitle) {
        return votedSongs.contains(songTitle);
    }

    /**
     * Display the current vote counts for all songs.
     */
    public void displayVoteCounts() {
        System.out.println("=== Current Song Vote Counts ===");
        for (Map.Entry<String, Integer> entry: songVotes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " votes");
        }
    }

    /**
     * Returns the fan's name.
     */
    public String getFanName() {
        return fanName;
    }

    /**
     * Returns the full vote count map.
     * Used by SetlistGeneration to sort songs by vote.
     */
    public static Map<String, Integer> getSongVotes() {
        return songVotes;
    }

    /**
     * Returns how many votes this fan has cast.
     */
    public int getTotalVotes() {
        return votedSongs.size();
    }

    /**
     * Returns the list of songs this fan has voted for.
     */
    public List<String> getVotedSongs() {
        return new LinkedList<>(fanVotesQueue);
    }

}
