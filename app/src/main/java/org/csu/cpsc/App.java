package org.csu.cpsc;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * Entry point for the Tour SetList Manager application.
 * <p>
 * Demonstrates creating and managing tour stops using {@link TourStopManagement}
 * and its nested {@link TourStopManagement.TourStop} type.
 */
public class App {
    /**
     * Returns a greeting string used by the application startup.
     *
     * @return the greeting text
     */
    public String getGreeting() {
        return "Hello World!";
    }

    /**
     * The main application entry point.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println(new App().getGreeting());

        TourStopManagement manager = new TourStopManagement();

        // Create dates
        Calendar date1 = Calendar.getInstance();
        date1.set(2026, Calendar.JULY, 4, 19, 30); // July 4, 2026 at 7:30 PM

        Calendar date2 = Calendar.getInstance();
        date2.set(2026, Calendar.AUGUST, 12, 20, 0); // August 12, 2026 at 8:00 PM

        // Create stops
        TourStopManagement.TourStop stop1 = new TourStopManagement.TourStop("Atlanta", date1, "State Farm Arena", 19, 30);
        TourStopManagement.TourStop stop2 = new TourStopManagement.TourStop("Miami", date2, "Kaseya Center", 20, 0);

        // Test addStop
        System.out.println("--- Adding Stops ---");
        manager.addStop(stop1);
        manager.addStop(stop2);

        // Test duplicate
        System.out.println("\n--- Adding Duplicate ---");
        manager.addStop(stop1);

        // Test displayTourStops
        System.out.println("\n--- All Tour Stops ---");
        manager.displayTourStops();

        // Test isCancelled before cancelling
        System.out.println("\n--- Is Atlanta Cancelled? ---");
        System.out.println(manager.isCancelled("Atlanta", date1));

        // Test cancelDate
        System.out.println("\n--- Cancelling Atlanta ---");
        manager.cancelDate("Atlanta", date1, "Venue issues.");

        // Test isCancelled after cancelling
        System.out.println("\n--- Is Atlanta Cancelled? ---");
        System.out.println(manager.isCancelled("Atlanta", date1));

        // Test displayTourStops after cancel
        System.out.println("\n--- All Tour Stops After Cancel ---");
        manager.displayTourStops();

        // Test removeDate
        System.out.println("\n--- Removing Miami ---");
        manager.removeDate("Miami", date2);

        // Final display
        System.out.println("\n--- Final Tour Stops ---");
        manager.displayTourStops();

        // -------------------------------------------------------
        // SongPoolManagement Tests
        // -------------------------------------------------------
        SongPoolManagement songPool = new SongPoolManagement();

        // Test addSongToPool
        System.out.println("--- Adding Songs ---");
        songPool.addSong(new SongPoolManagement.Song("Taylor Swift", "Love Story", 234));
        songPool.addSong(new SongPoolManagement.Song("Taylor Swift", "Shake It Off", 219));
        songPool.addSong(new SongPoolManagement.Song("Taylor Swift", "Blank Space", 231));

        // Test duplicate
        System.out.println("\n--- Reject Duplicate ---");
        songPool.addSong(new SongPoolManagement.Song("Taylor Swift", "Love Story", 234));

        // Test displaySongPool
        System.out.println("\n--- Song Pool ---");
        songPool.displaySongs();

        // Test removeSongFromPool
        System.out.println("\n--- Removing Shake It Off ---");
        songPool.removeSong("Shake It Off", "Taylor Swift");

        // Test remove song that doesn't exist
        System.out.println("\n--- Removing Song That Doesn't Exist ---");
        songPool.removeSong("Bad Blood", "Taylor Swift");

        // Test displaySongPool after removal
        System.out.println("\n--- Song Pool After Removal ---");
        songPool.displaySongs();

        // create the song pool and add songs
        SongPoolManagement songPool1 = new SongPoolManagement();
        songPool1.addSong(new SongPoolManagement.Song("Taylor Swift", "Love Story", 234));
        songPool1.addSong(new SongPoolManagement.Song("Taylor Swift", "Shake It Off", 219));

        // create a fan voting session
        FanVoting fanVoting = new FanVoting("Alice", 3, songPool1);

        // vote for songs
        fanVoting.castVote("Love Story", "Taylor Swift");
        fanVoting.castVote("Shake It Off", "Taylor Swift");

        // try a duplicate vote
        fanVoting.castVote("Love Story", "Taylor Swift");

        // submit votes
        fanVoting.submitVotes();

        // print results
        System.out.println("Voted songs: " + fanVoting.getVotedSongs());
        fanVoting.displayVoteCounts();

        // verify the fan is locked in
        System.out.println("Has Alice voted? " + FanVoting.hasVoted("Alice"));
    }
}
