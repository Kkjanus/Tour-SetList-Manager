/**
 * Manages tour stops, cancellations, and the display of scheduled events.
 * <p>
 * This class allows adding stops to tour cities, cancelling stops with a message,
 * removing stops by date, and printing the current tour itinerary.
 *
 * @author Kalaesia Janus
 * @version 1.0
 * @since 2026-04-20
 */
package org.csu.cpsc;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

public class TourStopManagement {

    /**
     * Represents a single tour stop with its scheduled date, venue, and cancellation state.
     */
    public static class TourStop {
        private String city;
        private Calendar date;
        private String venue;
        private int eventHour;
        private int eventMinute;
        private boolean isCancelled;
        private String cancellationMessage;

        /**
         * Constructs a new tour stop.
         *
         * @param city        the city where the stop will take place
         * @param date        the scheduled date and time of the stop
         * @param venue       the venue name for the stop
         * @param eventHour   the hour of the event in 24-hour format
         * @param eventMinute the minute of the event
         */
        public TourStop(String city, Calendar date, String venue, int eventHour, int eventMinute) {
            this.city = city;
            this.date = date;
            this.venue = venue;
            this.eventHour = eventHour;
            this.eventMinute = eventMinute;
            restoreStop();
        }

        public String getCity() {
            return city;
        }

        public Calendar getDate() {
            return date;
        }

        public String getVenue() {
            return venue;
        }

        public int getEventHour() {
            return eventHour;
        }

        public int getEventMinute() {
            return eventMinute;
        }

        public boolean isCancelled() {
            return isCancelled;
        }

        public String getCancellationMessage() {
            return cancellationMessage;
        }

        /**
         * Marks this stop as cancelled and records the provided message.
         *
         * @param message the cancellation explanation
         */
        public void cancelStop(String message) {
            this.isCancelled = true;
            this.cancellationMessage = message;
        }

        /**
         * Restores this stop to an active scheduled state.
         */
        public void restoreStop() {
            this.isCancelled = false;
            this.cancellationMessage = "";
        }

        private String getTime(){
            int hour = date.get(Calendar.HOUR);
            int minute = date.get(Calendar.MINUTE);
            String amPm = date.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
            String min = minute < 10 ? "0" + minute : String.valueOf(minute);
            return hour + ":" + min + " " + amPm;
        }

        @Override
        public String toString() {
            String status = isCancelled ? "Cancelled: " + cancellationMessage : "Scheduled";
            int month = date.get(Calendar.MONTH) + 1;
            int day = date.get(Calendar.DAY_OF_MONTH);
            int year = date.get(Calendar.YEAR);
            String getDate = month + "/" + day + "/" + year;

            return city + " -> " + getDate + " at " + getTime() + " (" + venue + ") = " + status;
        }
    
    }


    // Maps to store tour stops by city and cancellation messages by city.
    private Map<String, List<TourStop>> tourStops;
    private Map<String, String> cancellationMessages;

    /**
     * Constructs a new TourStopManagement instance.
     */
    public TourStopManagement() {
        tourStops = new HashMap<>();
        cancellationMessages = new HashMap<>();
    }

    /**
     * Adds a city to the tour stop lookup map.
     *
     * @param city the city to add
     */
    public void addCity(String city){
        if (!tourStops.containsKey(city)) {
            tourStops.put(city, new ArrayList<>());
        } else {
            System.out.println("City already exists in the tour stops.");
        }
    }

    /**
     * Adds a tour stop for the specified city.
     * <p>
     * If a stop with the same city and date already exists, the stop is not added.
     *
     * @param stop the tour stop to add
     */
    public void addStop(TourStop stop) {
        String city = stop.getCity();
        tourStops.putIfAbsent(city, new ArrayList<>());
 
        if (getStop(city, stop.getDate()) == null) {
            tourStops.get(city).add(stop);
        } else {
            System.out.println("Stop already exists for this city.");
        }
    }



    /**
     * Removes a tour stop for the given city and date.
     *
     * @param city the city for the stop
     * @param date the date of the stop to remove
     */
    public void removeDate(String city, Calendar date){
        TourStop stop = getStop(city, date);
        if (stop != null) {
            tourStops.get(city).remove(stop);
            System.out.println("Stop removed successfully.");
        } else {
            System.out.println("Stop not found.");
        }
    }

    /**
     * Cancels a tour stop and records a cancellation message.
     *
     * @param city    the city for the stop
     * @param date    the date of the stop to cancel
     * @param message the cancellation message to associate with the stop
     */
    public void cancelDate(String city, Calendar date, String message){
        TourStop stop = getStop(city, date);
        if (stop != null) {
            stop.cancelStop(message);
            cancellationMessages.put(city, message);
            System.out.println("Stop cancelled successfully.");
        } else {
            System.out.println("Stop not found.");
        }
    }

    /**
     * Returns whether the specified stop is cancelled.
     *
     * @param city the city for the stop
     * @param date the date of the stop
     */
    public boolean isCancelled(String city, Calendar date) {
        TourStop stop = getStop(city, date);
        return stop != null && stop.isCancelled();
    }

    /**
     * Prints all managed tour stops to standard output.
     */
    public void displayTourStops() {
        for (String city: tourStops.keySet()) {
            for (TourStop stop: tourStops.get(city)) {
                System.out.println(stop);
            }
        }
    }

        private TourStop getStop(String city, Calendar date) {
        if (tourStops.containsKey(city)) {
            for (TourStop stop: tourStops.get(city)) {
                if (stop.getDate().get(Calendar.YEAR)         == date.get(Calendar.YEAR)
                 && stop.getDate().get(Calendar.MONTH)        == date.get(Calendar.MONTH)
                 && stop.getDate().get(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH)) {
                    return stop;
                }
            }
        }
        return null;
    }

}