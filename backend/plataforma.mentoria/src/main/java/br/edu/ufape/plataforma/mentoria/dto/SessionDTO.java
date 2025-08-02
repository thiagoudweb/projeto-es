package br.edu.ufape.plataforma.mentoria.dto;

import br.edu.ufape.plataforma.mentoria.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class SessionDTO {

    @NotNull(message = "O ID do mentor é obrigatório")
    private Long userId;

    @NotNull(message = "O ID do mentorado é obrigatório")
    private Long guestId;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime time;

    @NotNull
    private String meetingTopic;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private String location;

    public SessionDTO() {
    }

    public SessionDTO(Long userId, Long guestId, LocalDate date, LocalTime time, String meetingTopic, String location) {
        this.userId = userId;
        this.guestId = guestId;
        this.date = date;
        this.time = time;
        this.meetingTopic = meetingTopic;
        this.status = Status.PENDING;
        this.location = location;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
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

    public String getMeetingTopic() {
        return meetingTopic;
    }

    public void setMeetingTopic(String meetingTopic) {
        this.meetingTopic = meetingTopic;
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
