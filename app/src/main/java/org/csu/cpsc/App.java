package org.csu.cpsc;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

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
    }
}
