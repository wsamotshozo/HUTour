/*
 * Note.java 1.0 03/08/2011
 * 
 * Copyright (c) Joel and Dave
 */
package tour.howard.extra;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Model a text message along with the date, time and location where
 * it was created.
 * 
 * @author dpowell2
 */
public class Note implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private double latitude;

    private double longitude;

    private Date date;
    private String message;

    /**
     * Default constructor of a Note with a 0 length message for a
     * longitude and latitude of 0, 0 at the current date and time.
     */
    public Note() {
        this("");
    }

    /**
     * Initializes <code>Note</code> with supplied location, date and
     * message.
     * 
     * @param latitude double representing the north south location
     * @param longitude double representing the east west location
     * @param date a date representing the date and time of message
     *        location
     * @param message String representing the text message received.
     */
    public Note(double latitude, double longitude, Date date, String message) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.message = message;
    }

    /**
     * Copy constructor to make a duplicate of <code>note</code>
     * 
     * @param note a note to make a full copy of latitude, longitude,
     *        date and message.
     */
    public Note(Note note) {
        this.latitude = note.latitude;
        this.longitude = note.longitude;
        this.date = (Date) note.date.clone();
        this.message = new String(note.message);
    }

    /**
     * Initializes <code>Note</code> with supplied
     * <code>message</code> for location at latitude 0 and longitude 0
     * for the current date and time.
     * 
     * @param message String representing the text message received.
     */
    public Note(String message) {
        this.message = message;
        latitude = 0.0;
        longitude = 0.0;
        date = new Date();
    }

    /**
     * Objects are considered equal if they have the same location,
     * time of creation and message.
     * 
     * @param obj a note to test against this
     * @return true if same location, date and message otherwise
     *         false.
     */
    
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Note other = (Note) obj;
        if (date == null) {
            if (other.date != null) {
                return false;
            }
        } else if (!date.equals(other.date)) {
            return false;
        }
        if (Double.doubleToLongBits(latitude) != Double
                .doubleToLongBits(other.latitude)) {
            return false;
        }
        if (Double.doubleToLongBits(longitude) != Double
                .doubleToLongBits(other.longitude)) {
            return false;
        }
        if (message == null) {
            if (other.message != null) {
                return false;
            }
        } else if (!message.equals(other.message)) {
            return false;
        }
        return true;
    }

    /**
     * Gets the date that the <code>Note</code> was created.
     * 
     * @return the date and time when the <code>Note</code> was
     *         created.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Gets the latitude where the message was created.
     * 
     * @return double representing latitude where message was created.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Gets the longitude where the message was created.
     * 
     * @return double representing longitude where message was
     *         created.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Gets the text message on a <code>Note</code>
     * 
     * @return string representing the text message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the date and time for the cretion of the <code>Note</code>
     * 
     * @param date date and time of <code>Note</code> creation
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Sets the latitude location of where the <code>Note</code> was
     * created.
     * 
     * @param latitude double representing latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Sets the longitude location of where the <code>Note</code> was
     * created.
     * 
     * @param longitude double representing longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Sets the current message to <code>message</code>.
     * 
     * @param message string of the message to store in the
     *        <code>Note</code>
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets a string representation of the Note represented as name
     * values on separate lines with latitude, longitude, message and
     * date
     * 
     * @return string name value representation of fields making up a
     *         Note
     */
    
    public String toString() {
        String newLine = "\n";
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy/MM/dd HH:mm:ss z");
        String s = "Lat: " + getLatitude() + newLine;
        s += "Long: " + getLongitude() + newLine;
        s += "Date: " + dateFormat.format(this.getDate()) + newLine;
        s += "Message: " + getMessage();
        return s;
    }
}
