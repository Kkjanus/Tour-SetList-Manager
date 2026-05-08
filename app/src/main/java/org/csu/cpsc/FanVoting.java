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
    }

    /**
     * Stores voted songs for this fan in the queue and updates the global vote counts.
     * Uses a compound key of title and artist to uniquely identify songs.
     */
    private String createSongKey(String title, String artist) {
        return title + " | " + artist;
    }

    /**
     * Allows a fan to cast a vote for a song.
     * <p>
     * Voting restrictions enforced:
     * <ul>
     *   <li>Fan cannot vote if they have already submitted votes</li>
     *   <li>Fan cannot vote for the same song twice</li>
     *   <li>Fan cannot exceed maxVotesPerFan votes</li>
     *   <li>Song must exist in the song pool</li>
     * </ul>
     *
     * @param songTitle the title of the song to vote for
     * @param artist    the artist of the song to vote for
     */
    public void castVote(String songTitle, String artist) {
        if (fanNames.contains(fanName)) {
            System.out.println(fanName + " has already submitted votes.");
            return;
        }
        if (songPool.getSong(songTitle, artist) == null) {
            System.out.println("Song not found in the pool.");
            return;
        }
        if (fanVotesQueue.contains(songTitle)) {
            System.out.println(fanName + " has already voted for this song.");
            return;
        }
        if (fanVotesQueue.size() >= maxVotesPerFan) {
            System.out.println(fanName + " has reached the maximum number of votes.");
            return;
        }

        fanVotesQueue.add(songTitle);

        String songKey = createSongKey(songTitle, artist);
        Integer currentVotes = songVotes.get(songKey);
        if (currentVotes == null) {
            songVotes.put(songKey, 1);
        } else {
            songVotes.put(songKey, currentVotes + 1);
        }

        System.out.println(fanName + " voted for: " + songTitle);
    }

    /**
     * Submits and locks in a fan's votes.
     * <p>
     * Once submitted, the fan cannot vote again. This method is called when a fan is done voting.
     */
    public void submitVotes() {
        if (fanVotesQueue.isEmpty()) {
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
     * Checks if a fan has already submitted their votes.
     *
     * @param fanName the name of the fan to check
     * @return {@code true} if the fan has submitted votes, {@code false} otherwise
     */
    public static boolean hasVoted(String fanName) {
        return fanNames.contains(fanName);
    }

    /**
     * Checks if this fan has already voted for a specific song.
     *
     * @param songTitle the title of the song to check
     * @return {@code true} if the fan has voted for this song, {@code false} otherwise
     */
    public boolean hasVotedForSong(String songTitle) {
        return fanVotesQueue.contains(songTitle);
    }

    /**
     * Displays the current vote counts for all songs in a formatted table.
     */
    public void displayVoteCounts() {
        System.out.println("=== Current Song Vote Counts ===");
        for (Map.Entry<String, Integer> entry: songVotes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " votes");
        }
    }

    /**
     * Returns the name of this fan.
     *
     * @return the fan's name
     */
    public String getFanName() {
        return fanName;
    }

    /**
     * Returns the full vote count map for all songs.
     * <p>
     * This map is shared across all fans and is used by SetlistGeneration to sort songs by vote count.
     *
     * @return a map of song titles to their respective vote counts
     */
    public static Map<String, Integer> getSongVotes() {
        return new HashMap<>(songVotes);
    }

    /**
     * Returns the total number of votes this fan has cast.
     *
     * @return the number of votes cast by this fan
     */
    public int getTotalVotes() {
        return fanVotesQueue.size();
    }

    /**
     * Returns the list of songs this fan has voted for in the order they were voted.
     *
     * @return a list of song titles voted for by this fan
     */
    public List<String> getVotedSongs() {
        return new ArrayList<>(fanVotesQueue);
    }

}
