/** 
 * @author Kalaesia Janus
 * @version 1.0
 * @created 4/20/2026
 * This class manages the tour stops and their dates, whether that is adding them or removing them as well as displaying them
 * to the users. It will also have details pertaining to the tour stops taken. 
 * 
*/
package org.csu.cpsc;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

public class TourStopManagement {

    //Represents a single tour stop with its details and cancellation status.

    public static class TourStop {
        private String city;
        private Calendar date;
        private String venue;
        private int eventHour;
        private int eventMinute;
        private boolean isCancelled;
        private String cancellationMessage;

        public TourStop(String city, Calendar date, String venue, int eventHour, int eventMinute) {
            this.city = city;
            this.date = date;
            this.venue = venue;
            this.eventHour = eventHour;
            this.eventMinute = eventMinute;
            this.isCancelled = false;
            this.cancellationMessage = "";
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

        public void cancelStop(String message) {
            this.isCancelled = true;
            this.cancellationMessage = message;
        }

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

    public TourStopManagement() {
        tourStops = new HashMap<>();
        cancellationMessages = new HashMap<>();
    }

    public void addCity(String city){
        if (!tourStops.containsKey(city)) {
            tourStops.put(city, new ArrayList<>());
        } else {
            System.out.println("City already exists in the tour stops.");
        }
    }

    public void addStop(TourStop stop) {
        String city = stop.getCity();
        tourStops.putIfAbsent(city, new ArrayList<>());
 
        if (getStop(city, stop.getDate()) == null) {
            tourStops.get(city).add(stop);
        } else {
            System.out.println("Stop already exists for this city.");
        }
    }



    public void removeDate(String city, Calendar date){
        TourStop stop = getStop(city, date);
        if (stop != null) {
            tourStops.get(city).remove(stop);
            System.out.println("Stop removed successfully.");
        } else {
            System.out.println("Stop not found.");
        }
    }

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

    public boolean isCancelled(String city, Calendar date) {
        TourStop stop = getStop(city, date);
        return stop != null && stop.isCancelled();
    }

    public void displayTourStops() {
        for (String city : tourStops.keySet()) {
            for (TourStop stop : tourStops.get(city)) {
                System.out.println(stop);
            }
        }
    }

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