package pe.edu.upc.prevdengue.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "Notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNotification;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(name = "title",length = 100,nullable = false)
    private String title;

    @Column(name = "message",columnDefinition ="TEXT",nullable = false)
    private String message;

    @Column(name = "is_Read")
    private boolean isRead;

    @Column(name= "send_date")
    private LocalDateTime sendDate;

    public Notification() {
    }

    public Notification(int idNotification, User user, String title, String message, boolean isRead, LocalDateTime sendDate) {
        this.idNotification = idNotification;
        this.user = user;
        this.title = title;
        this.message = message;
        this.isRead = isRead;
        this.sendDate = sendDate;
    }

    public int getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(int idNotification) {
        this.idNotification = idNotification;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead(boolean b) {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }
}

