/**
 * Manages tour stops, cancellations, and the display of scheduled events.
 * <p>
 * This class stores tour stops grouped by city and supports adding new stops,
 * cancelling or removing scheduled stops, querying cancellation status, and
 * printing the current itinerary.
 *
 * <p>Each tour stop is represented by an inner {@link TourStop} object containing
 * the city, date, venue, time, and cancellation details.
 *
 * @author Kalaesia Janus
 * @version 1.0
 * @since 2026-04-20
 */
package org.csu.cpsc;

import java.util.Map;

import org.csu.cpsc.TourStopManagement.TourStop;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

public class TourStopManagement {

    /**
     * Represents a single tour stop with its scheduled date, venue, and cancellation state.
     * <p>
     * The inner class stores all required details for a tour stop and provides methods
     * to cancel or restore the stop while preserving a cancellation message.
     */
    public static class TourStop {
        /** The city where the event is scheduled. */
        private String city;

        /** The scheduled date and time of the event. */
        private Calendar date;

        /** The venue name for the stop. */
        private String venue;

        /** The hour component of the event time in 24-hour format. */
        private int eventHour;

        /** The minute component of the event time. */
        private int eventMinute;

        /** Whether the stop has been cancelled. */
        private boolean isCancelled;

        /** The optional cancellation message explaining why the stop was cancelled. */
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
            this.date.set(Calendar.HOUR_OF_DAY, eventHour);
            this.date.set(Calendar.MINUTE, eventMinute);
            restoreStop();
        }

        /**
         * Returns the city for this tour stop.
         *
         * @return the stop city
         */
        public String getCity() {
            return city;
        }

        /**
         * Returns the scheduled date for this tour stop.
         *
         * @return the stop date as a {@link Calendar}
         */
        public Calendar getDate() {
            return date;
        }

        /**
         * Returns the venue name for this tour stop.
         *
         * @return the venue name
         */
        public String getVenue() {
            return venue;
        }

        /**
         * Returns the event hour for this tour stop.
         *
         * @return the scheduled hour in 24-hour format
         */
        public int getEventHour() {
            return eventHour;
        }

        /**
         * Returns the event minute for this tour stop.
         *
         * @return the scheduled minute
         */
        public int getEventMinute() {
            return eventMinute;
        }

        /**
         * Returns whether this tour stop has been cancelled.
         *
         * @return {@code true} if the stop is cancelled; {@code false} otherwise
         */
        public boolean isCancelled() {
            return isCancelled;
        }

        /**
         * Returns the cancellation message associated with this tour stop.
         *
         * @return the cancellation message, or an empty string if the stop is active
         */
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
         * <p>
         * This clears any previous cancellation state and message.
         */
        public void restoreStop() {
            this.isCancelled = false;
            this.cancellationMessage = "";
        }

        /**
         * Formats the scheduled date into a human-readable time string.
         *
         * @return the stop time formatted as {@code H:MM AM/PM}
         */
        private String getTime(){
            int hour24 = eventHour;
            int minute = eventMinute;
            String amPm = hour24 < 12 ? "AM" : "PM";
            int hour12 = hour24 % 12;
            if (hour12 == 0) {
                hour12 = 12;
            }
            String min = minute < 10 ? "0" + minute : String.valueOf(minute);
            return hour12 + ":" + min + " " + amPm;
        }

        /**
         * Returns a string representation of the tour stop.
         * <p>
         * The output includes city, date, time, venue, and status information.
         *
         * @return a formatted summary of the tour stop
         */
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


    /**
     * Maps each city to its list of scheduled tour stops.
     */
    private Map<String, List<TourStop>> tourStops;

    /**
     * Constructs a new TourStopManagement instance.
     */
    public TourStopManagement() {
        tourStops = new HashMap<>();
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
     * @return {@code true} if the stop exists and is cancelled; {@code false} otherwise
     */
    public boolean isCancelled(String city, Calendar date) {
        TourStop stop = getStop(city, date);
        return stop != null && stop.isCancelled();
    }

    /**
     * Returns a tour stop for the given city and date, or null if not found.
     *
     * @param city the city of the tour stop
     * @param date the date of the tour stop
     * @return the matching TourStop, or null if none exists
     */
    public TourStop getTourStop(String city, Calendar date) {
        return getStop(city, date);
    }

    /**
     * Prints all managed tour stops to standard output.
     * <p>
     * Each stop is displayed using its {@link TourStop#toString()} representation.
     */
    public void displayTourStops() {
        for (String city: tourStops.keySet()) {
            for (TourStop stop: tourStops.get(city)) {
                System.out.println(stop);
            }
        }
    }

    /**
     * Finds a tour stop by city and date.
     *
     * @param city the city of the stop
     * @param date the date of the stop to locate
     * @return the matching {@link TourStop} instance, or {@code null} if none is found
     */
    private TourStop getStop(String city, Calendar date) {
        if (tourStops.containsKey(city)) {
            for (TourStop stop : tourStops.get(city)) {
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