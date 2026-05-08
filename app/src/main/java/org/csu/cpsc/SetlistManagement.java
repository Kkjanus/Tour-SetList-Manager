package org.csu.cpsc;

import java.util.Map;
import java.util.HashMap;
import java.util.Stack;
import java.util.Calendar;

/**
 * Manages artist-controlled setlists for each tour stop.
 * Allows artists to create and modify their setlist for each stop.
 */

public class SetlistManagement {
    private Map<String, Stack<SongPoolManagement.Song>> setlists;

    public SetlistManagement() {
        setlists = new HashMap<String, Stack<SongPoolManagement.Song>>();
    }

    /**
     * Builds a composite key from city and date to uniquely identify a specific tour stop.
     * <p>
     * The key format is: city-MM/DD/YYYY
     *
     * @param city the city of the tour stop
     * @param date the date of the tour stop
     * @return a string key representing the tour stop location and date
     */
    private String stopKey(String city, Calendar date) {
        int month = date.get(Calendar.MONTH) + 1;
        int day = date.get(Calendar.DAY_OF_MONTH);
        int year = date.get(Calendar.YEAR);
        return city + "-" + month + "/" + day + "/" + year;
    }

    /**
     * Adds a song to the setlist for a specific tour stop.
     * <p>
     * If no setlist exists for this tour stop, a new stack is created.
     * Songs are added to the top of the stack (LIFO order).
     *
     * @param city the city of the tour stop
     * @param date the date of the tour stop
     * @param song the song to add to the setlist
     */
    public void addSongToSetlist(String city, Calendar date, SongPoolManagement.Song song) {
        String key = stopKey(city, date);
        if(!setlists.containsKey(key)) {
            setlists.put(key, new Stack<SongPoolManagement.Song>());
        }
        setlists.get(key).push(song);
        System.out.println("Added " + song.getTitle() + " to setlist for " + city + " on " + date.getTime());
    }

    /**
     * Removes the most recently added song from the setlist for a specific tour stop.
     * <p>
     * This method removes and returns the song at the top of the stack (LIFO order).
     * If the setlist is empty or does not exist, no action is taken.
     *
     * @param city the city of the tour stop
     * @param date the date of the tour stop
     */
    public void removeSongFromSetlist(String city, Calendar date) {
        String key = stopKey(city, date);
        Stack<SongPoolManagement.Song> setlist = setlists.get(key);

        if (setlist == null || setlist.isEmpty()) {
            System.out.println("Setlist is empty for " + city + " on " + date.getTime());
            return;
        }

        SongPoolManagement.Song removedSong = setlist.pop();
        System.out.println("Removed " + removedSong.getTitle() + " from setlist for " + city + " on " + date.getTime());
    }   

    /**
     * Displays all songs in the setlist for a specific tour stop in reverse order.
     * <p>
     * Songs are displayed from the most recently added to the oldest (LIFO order).
     * If the setlist is empty or does not exist, a message is printed.
     *
     * @param city the city of the tour stop
     * @param date the date of the tour stop
     */
    public void viewSetlist(String city, Calendar date) {
        String key = stopKey(city, date);
        Stack<SongPoolManagement.Song> setlist = setlists.get(key);

        if (setlist == null || setlist.isEmpty()) {
            System.out.println("Setlist is empty for " + city + " on " + date.getTime());
            return;
        }

        System.out.println("Setlist for " + city + " on " + date.getTime() + ":");
        for (int i = setlist.size() - 1; i >= 0; i--) {
            System.out.println("- " + setlist.get(i).getTitle());
        }
    }

    /**
     * Clears all songs from the setlist stack for a specific tour stop.
     * <p>
     * This removes all songs associated with the tour stop, creating an empty setlist.
     * If the setlist is already empty, a message is printed.
     *
     * @param city the city of the tour stop
     * @param date the date of the tour stop
     */
    public void clearSetlist(String city, Calendar date) {
        String key = stopKey(city, date);
        Stack<SongPoolManagement.Song> setlist = setlists.get(key);

        if (setlist == null || setlist.isEmpty()) {
            System.out.println("Setlist is already empty for " + city + " on " + date.getTime());
            return;
        }

        setlists.put(key, new Stack<SongPoolManagement.Song>());
        System.out.println("Cleared setlist for " + city + " on " + date.getTime());
    }

    /**
     * Returns a copy of the setlist stack for the given city and date.
     * <p>
     * This returns a shallow copy to prevent callers from directly modifying the internal setlist.
     * Returns {@code null} if no setlist exists for the specified tour stop.
     *
     * @param city the city of the tour stop
     * @param date the date of the tour stop
     * @return a copy of the stack of songs for this tour stop, or {@code null} if it does not exist
     */
    public Stack<SongPoolManagement.Song> getSetlist(String city, Calendar date) {
        String key = stopKey(city, date);
        Stack<SongPoolManagement.Song> setlist = setlists.get(key);
        if (setlist == null) {
            return null;
        }
        Stack<SongPoolManagement.Song> copy = new Stack<SongPoolManagement.Song>();
        for (SongPoolManagement.Song song: setlist) {
            copy.push(song);
        }
        return copy;
    }


    
}


