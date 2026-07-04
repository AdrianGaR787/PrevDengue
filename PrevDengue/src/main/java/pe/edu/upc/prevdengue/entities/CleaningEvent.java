package pe.edu.upc.prevdengue.entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "CleaningEvent")
public class CleaningEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEvent;

    @Column(name = "title", length = 150,nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "id_creatorUser", nullable = false)
    private User creatorUser;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude",nullable = false)
    private double longitude;

    @Column(name = "eventDate", nullable = false)
    private LocalDateTime eventDate;

    @Column(name ="status",length = 50,nullable = false)
    private String status;

    @Column(name = "maxCapacity", nullable = false)
    private int maxCapacity = 20;

    @ManyToMany
    @JoinTable(
            name = "event_participants",
            joinColumns = @JoinColumn(name = "id_event"),
            inverseJoinColumns = @JoinColumn(name = "id_user")
    )
    private Set<User> participants = new java.util.HashSet<>();

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }

    public CleaningEvent() {
    }

    public CleaningEvent(int idEvent, String title, User creatorUser, double latitude, double longitude, LocalDateTime eventDate, String status) {
        this.idEvent = idEvent;
        this.title = title;
        this.creatorUser = creatorUser;
        this.latitude = latitude;
        this.longitude = longitude;
        this.eventDate = eventDate;
        this.status = status;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(User creatorUser) {
        this.creatorUser = creatorUser;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
