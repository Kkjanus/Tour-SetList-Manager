package org.csu.cpsc;

import java.util.Scanner;

/**
 * Manages role selection for the StageVote application.
 * <p>
 * Users can select either the fan or artist role to access their respective menus.
 * No authentication is required — role selection routes the user directly to
 * the appropriate menu based on their input.
 *
 * @author Kalaesia Janus
 * @version 1.0
 * @since 2026-04-20
 */
public class RoleSelection {

    /** Stores the role entered by the user ("fan" or "artist"). */
    private String roleInput;

    /**
     * Constructs a new RoleSelection instance with an empty role input.
     */
    public RoleSelection() {
        this.roleInput = "";
    }

    /**
     * Prompts the user to select a role of either "fan" or "artist".
     * <p>
     * If the user enters "artist", they are routed to {@link #showArtistMenu(Scanner)}.
     * If the user enters "fan", they are routed to {@link #showFanMenu(Scanner)}.
     * If the input is invalid, the user is prompted again until a valid role is entered.
     *
     * @param scanner the Scanner instance used to read user input from the console
     */
    public void selectRole(Scanner scanner) {
        System.out.println("=== Welcome to StageVote ===");
        System.out.println("Please select a role: fan or artist");

        roleInput = scanner.nextLine().trim().toLowerCase();

        if (roleInput.equals("artist")) {
            showArtistMenu(scanner);
        } else if (roleInput.equals("fan")) {
            showFanMenu(scanner);
        } else {
            System.out.println("Invalid role. Please enter 'fan' or 'artist'.");
            selectRole(scanner);
        }
    }

    /**
     * Displays the artist menu and handles the artist's menu selection.
     * <p>
     * The artist can choose to manage tour stops, manage the song pool,
     * manage the setlist, generate a setlist, or exit the application.
     * If an invalid choice is entered, the menu is displayed again.
     *
     * @param scanner the Scanner instance used to read user input from the console
     */
    public void showArtistMenu(Scanner scanner) {
        System.out.println("\n=== Artist Menu ===");
        System.out.println("1. Manage Tour Stops");
        System.out.println("2. Manage Song Pool");
        System.out.println("3. Manage Setlist");
        System.out.println("4. Generate Setlist");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");

        String choice = scanner.nextLine().trim();

        if (choice.equals("1")) {
            System.out.println("Opening Tour Stop Management...");
        } else if (choice.equals("2")) {
            System.out.println("Opening Song Pool Management...");
        } else if (choice.equals("3")) {
            System.out.println("Opening Setlist Management...");
        } else if (choice.equals("4")) {
            System.out.println("Generating Setlist...");
        } else if (choice.equals("5")) {
            System.out.println("Goodbye!");
        } else {
            System.out.println("Invalid choice. Please try again.");
            showArtistMenu(scanner);
        }
    }

    /**
     * Displays the fan menu and handles the fan's menu selection.
     * <p>
     * The fan can choose to view tour stops, vote for songs,
     * view the current setlist, or exit the application.
     * If an invalid choice is entered, the menu is displayed again.
     *
     * @param scanner the Scanner instance used to read user input from the console
     */
    public void showFanMenu(Scanner scanner) {
        System.out.println("\n=== Fan Menu ===");
        System.out.println("1. View Tour Stops");
        System.out.println("2. Vote for Songs");
        System.out.println("3. View Current Setlist");
        System.out.println("4. Exit");
        System.out.print("Enter choice: ");

        String choice = scanner.nextLine().trim();

        if (choice.equals("1")) {
            System.out.println("Opening Tour Stops...");
        } else if (choice.equals("2")) {
            System.out.println("Opening Voting...");
        } else if (choice.equals("3")) {
            System.out.println("Opening Setlist...");
        } else if (choice.equals("4")) {
            System.out.println("Goodbye!");
        } else {
            System.out.println("Invalid choice. Please try again.");
            showFanMenu(scanner);
        }
    }

    /**
     * Returns the role the user selected.
     *
     * @return the role input as a lowercase string ("fan" or "artist")
     */
    public String getRoleInput() {
        return roleInput;
    }
}