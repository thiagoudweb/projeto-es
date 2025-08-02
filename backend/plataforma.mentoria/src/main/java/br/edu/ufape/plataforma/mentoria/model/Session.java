package br.edu.ufape.plataforma.mentoria.model;

import br.edu.ufape.plataforma.mentoria.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Session {

    @Id
    @SequenceGenerator(name="session_id_seq", sequenceName="session_id_seq", allocationSize=1)
    @GeneratedValue(generator="session_id_seq", strategy=GenerationType.SEQUENCE)
    @Column(name="id", updatable=false)
    private Long id;

    @ManyToOne
    @MapsId
    @PrimaryKeyJoinColumn
    private User user;

    @ManyToOne
    @MapsId
    @PrimaryKeyJoinColumn
    private User guest;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime time;

    @Column(nullable = false)
    private String meetingTopic;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private String location;

    public Session() {}

    public Session(User user, User guest, LocalDate date, LocalTime time, String meetingTopic, String location) {
        this.user = user;
        this.guest = guest;
        this.date = date;
        this.time = time;
        this.meetingTopic = meetingTopic;
        this.status = Status.PENDING;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getGuest() {
        return guest;
    }

    public void setGuest(User guest) {
        this.guest = guest;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getMeeting_topic() {
        return meetingTopic;
    }

    public void setMeeting_topic(String meetingTopic) {
        this.meetingTopic = meetingTopic;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
