package org.csu.cpsc;

import java.util.Calendar;
import java.util.Scanner;

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

        Scanner scanner = new Scanner(System.in);
 
        // -------------------------------------------------------
        // Setup: Song Pool
        // -------------------------------------------------------
        SongPoolManagement songPool = new SongPoolManagement();
 
        // -------------------------------------------------------
        // Setup: Tour Stops
        // -------------------------------------------------------
        TourStopManagement tourManager = new TourStopManagement();
 
        // -------------------------------------------------------
        // Setup: Setlist Generation
        // -------------------------------------------------------
        SetlistGeneration generator = new SetlistGeneration(3);
 
        // -------------------------------------------------------
        // Setup: Setlist Management (Artist Override)
        // -------------------------------------------------------
        SetlistManagement setlistMgmt = new SetlistManagement();
 
        // -------------------------------------------------------
        // Role Selection — runs the app
        // -------------------------------------------------------
        boolean running = true;
 
        while (running) {
            System.out.println("\n=== Welcome to StageVote ===");
            System.out.println("Please select a role: fan or artist (or 'exit' to quit)");
            String roleInput = scanner.nextLine().trim().toLowerCase();
 
            if (roleInput.equals("exit")) {
                System.out.println("Goodbye!");
                running = false;
 
            } else if (roleInput.equals("artist")) {
                boolean artistRunning = true;
                while (artistRunning) {
                    System.out.println("\n=== Artist Menu ===");
                    System.out.println("1. View Tour Stops");
                    System.out.println("2. Add Tour Stop");
                    System.out.println("3. Remove Tour Stop");
                    System.out.println("4. Cancel Tour Stop");
                    System.out.println("5. View Song Pool");
                    System.out.println("6. Add Song to Pool");
                    System.out.println("7. Remove Song from Pool");
                    System.out.println("8. View Setlist");
                    System.out.println("9. Add Song to Setlist");
                    System.out.println("10. Remove Song from Setlist");
                    System.out.println("11. Clear Setlist");
                    System.out.println("12. Generate Setlist");
                    System.out.println("13. Display Generated Setlist");
                    System.out.println("14. Set Setlist Size");
                    System.out.println("15. Back");
                    System.out.print("Enter choice: ");
 
                    String choice = scanner.nextLine().trim();
 
                    if (choice.equals("1")) {
                        tourManager.displayTourStops();
 
                    } else if (choice.equals("2")) {
                        System.out.print("Enter city: ");
                        String city = scanner.nextLine().trim();
                        System.out.print("Enter venue: ");
                        String venue = scanner.nextLine().trim();
                        System.out.print("Enter year: ");
                        int year = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Enter month (1-12): ");
                        int month = Integer.parseInt(scanner.nextLine().trim()) - 1;
                        System.out.print("Enter day: ");
                        int day = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Enter event hour (0-23): ");
                        int hour = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Enter event minute (0-59): ");
                        int minute = Integer.parseInt(scanner.nextLine().trim());
                        Calendar date = Calendar.getInstance();
                        date.set(year, month, day);
                        tourManager.addStop(new TourStopManagement.TourStop(city, date, venue, hour, minute));
 
                    } else if (choice.equals("3")) {
                        System.out.print("Enter city: ");
                        String city = scanner.nextLine().trim();
                        System.out.print("Enter year: ");
                        int year = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Enter month (1-12): ");
                        int month = Integer.parseInt(scanner.nextLine().trim()) - 1;
                        System.out.print("Enter day: ");
                        int day = Integer.parseInt(scanner.nextLine().trim());
                        Calendar date = Calendar.getInstance();
                        date.set(year, month, day);
                        tourManager.removeDate(city, date);
 
                    } else if (choice.equals("4")) {
                        System.out.print("Enter city: ");
                        String city = scanner.nextLine().trim();
                        System.out.print("Enter year: ");
                        int year = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Enter month (1-12): ");
                        int month = Integer.parseInt(scanner.nextLine().trim()) - 1;
                        System.out.print("Enter day: ");
                        int day = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Enter cancellation message: ");
                        String message = scanner.nextLine().trim();
                        Calendar date = Calendar.getInstance();
                        date.set(year, month, day);
                        tourManager.cancelDate(city, date, message);
 
                    } else if (choice.equals("5")) {
                        songPool.displaySongs();
 
                    } else if (choice.equals("6")) {
                        System.out.print("Enter song title: ");
                        String title = scanner.nextLine().trim();
                        System.out.print("Enter artist: ");
                        String artist = scanner.nextLine().trim();
                        songPool.addSong(new SongPoolManagement.Song(title, artist));
 
                    } else if (choice.equals("7")) {
                        System.out.print("Enter song title to remove: ");
                        String title = scanner.nextLine().trim();
                        System.out.print("Enter artist: ");
                        String artist = scanner.nextLine().trim();
                        songPool.removeSong(title, artist);
 
                    } else if (choice.equals("8")) {
                        System.out.print("Enter city: ");
                        String city = scanner.nextLine().trim();
                        System.out.print("Enter year: ");
                        int year = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Enter month (1-12): ");
                        int month = Integer.parseInt(scanner.nextLine().trim()) - 1;
                        System.out.print("Enter day: ");
                        int day = Integer.parseInt(scanner.nextLine().trim());
                        Calendar date = Calendar.getInstance();
                        date.set(year, month, day);
                        setlistMgmt.viewSetlist(city, date);
 
                    } else if (choice.equals("9")) {
                        System.out.print("Enter city: ");
                        String city = scanner.nextLine().trim();
                        System.out.print("Enter year: ");
                        int year = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Enter month (1-12): ");
                        int month = Integer.parseInt(scanner.nextLine().trim()) - 1;
                        System.out.print("Enter day: ");
                        int day = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Enter song title: ");
                        String title = scanner.nextLine().trim();
                        System.out.print("Enter artist: ");
                        String artist = scanner.nextLine().trim();
                        SongPoolManagement.Song song = songPool.getSong(title, artist);
                        if (song != null) {
                            Calendar date = Calendar.getInstance();
                            date.set(year, month, day);
                            setlistMgmt.addSongToSetlist(city, date, song);
                        } else {
                            System.out.println("Song not found in pool.");
                        }
 
                    } else if (choice.equals("10")) {
                        System.out.print("Enter city: ");
                        String city = scanner.nextLine().trim();
                        System.out.print("Enter year: ");
                        int year = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Enter month (1-12): ");
                        int month = Integer.parseInt(scanner.nextLine().trim()) - 1;
                        System.out.print("Enter day: ");
                        int day = Integer.parseInt(scanner.nextLine().trim());
                        Calendar date = Calendar.getInstance();
                        date.set(year, month, day);
                        setlistMgmt.removeSongFromSetlist(city, date);
 
                    } else if (choice.equals("11")) {
                        System.out.print("Enter city: ");
                        String city = scanner.nextLine().trim();
                        System.out.print("Enter year: ");
                        int year = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Enter month (1-12): ");
                        int month = Integer.parseInt(scanner.nextLine().trim()) - 1;
                        System.out.print("Enter day: ");
                        int day = Integer.parseInt(scanner.nextLine().trim());
                        Calendar date = Calendar.getInstance();
                        date.set(year, month, day);
                        setlistMgmt.clearSetlist(city, date);
 
                    } else if (choice.equals("12")) {
                        System.out.print("Enter city: ");
                        String city = scanner.nextLine().trim();
                        System.out.print("Enter year: ");
                        int year = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Enter month (1-12): ");
                        int month = Integer.parseInt(scanner.nextLine().trim()) - 1;
                        System.out.print("Enter day: ");
                        int day = Integer.parseInt(scanner.nextLine().trim());
                        Calendar date = Calendar.getInstance();
                        date.set(year, month, day);

                        if (tourManager.getTourStop(city, date) == null) {
                            System.out.println("No tour stop found for " + city + " on that date.");
                        } else {
                            generator.loadVotes(songPool);
                            generator.generateSetlist();
                            for (SetlistGeneration.VotedSong vs : generator.getSetlist()) {
                                setlistMgmt.addSongToSetlist(city, date, vs.getSong());
                            }
                            System.out.println("Setlist generated and saved for " + city + ".");
                        }
                    } else if (choice.equals("13")) {
                        generator.displaySetlist();
 
                    } else if (choice.equals("14")) {
                        System.out.print("Enter new setlist size: ");
                        int size = Integer.parseInt(scanner.nextLine().trim());
                        generator.setSetlistSize(size);
                        System.out.println("Setlist size set to " + size + ".");
 
                    } else if (choice.equals("15")) {
                        artistRunning = false;
 
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                }
 
            } else if (roleInput.equals("fan")) {
                System.out.print("Enter your name: ");
                String fanName = scanner.nextLine().trim();
 
                // Create the fan's FanVoting instance once so votes persist across menu visits
                FanVoting fan = new FanVoting(fanName, 5, songPool);
 
                boolean fanRunning = true;
                while (fanRunning) {
                    System.out.println("\n=== Fan Menu ===");
                    System.out.println("1. View Tour Stops");
                    System.out.println("2. View Song Pool");
                    System.out.println("3. Vote for a Song");
                    System.out.println("4. Submit Votes");
                    System.out.println("5. View Current Setlist");
                    System.out.println("6. View Vote Counts");
                    System.out.println("7. Back");
                    System.out.print("Enter choice: ");
 
                    String choice = scanner.nextLine().trim();
 
                    if (choice.equals("1")) {
                        tourManager.displayTourStops();
 
                    } else if (choice.equals("2")) {
                        songPool.displaySongs();
 
                    } else if (choice.equals("3")) {
                        if (FanVoting.hasVoted(fanName)) {
                            System.out.println(fanName + " has already submitted votes and cannot vote again.");
                        } else {
                            System.out.print("Enter song title: ");
                            String title = scanner.nextLine().trim();
                            System.out.print("Enter artist: ");
                            String artist = scanner.nextLine().trim();
                            fan.castVote(title, artist);
                        }
 
                    } else if (choice.equals("4")) {
                        fan.submitVotes();
 
                    } else if (choice.equals("5")) {
                        System.out.print("Enter city: ");
                        String city = scanner.nextLine().trim();
                        System.out.print("Enter year: ");
                        int year = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Enter month (1-12): ");
                        int month = Integer.parseInt(scanner.nextLine().trim()) - 1;
                        System.out.print("Enter day: ");
                        int day = Integer.parseInt(scanner.nextLine().trim());
                        Calendar date = Calendar.getInstance();
                        date.set(year, month, day);
                        setlistMgmt.viewSetlist(city, date);
 
                    } else if (choice.equals("6")) {
                        fan.displayVoteCounts();
 
                    } else if (choice.equals("7")) {
                        fanRunning = false;
 
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                }
 
            } else {
                System.out.println("Invalid role. Please enter 'fan' or 'artist'.");
            }
        }
 
        scanner.close();
    }
}
