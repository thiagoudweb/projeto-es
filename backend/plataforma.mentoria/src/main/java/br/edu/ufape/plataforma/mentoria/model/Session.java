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
    private Mentor mentor;

    @ManyToOne
    private Mentored mentored;

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

    public Session(Mentor mentor, Mentored mentored, LocalDate date, LocalTime time, String meetingTopic, String location) {
        this.mentor = mentor;
        this.mentored = mentored;
        this.date = date;
        this.time = time;
        this.meetingTopic = meetingTopic;
        this.status = Status.PENDING;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    public Mentored getMentored() {
        return mentored;
    }

    public void setMentored(Mentored mentorado) {
        this.mentored = mentorado;
    }

    public String getMeetingTopic() {
        return meetingTopic;
    }

    public void setMeetingTopic(String meetingTopic) {
        this.meetingTopic = meetingTopic;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
