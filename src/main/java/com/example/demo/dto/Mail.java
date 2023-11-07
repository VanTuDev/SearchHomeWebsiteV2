package com.example.demo.dto;

import javax.mail.Message;

public class Mail {
    private String subject;
    private String monney;
    private String time;
    private String currentCash;
    private String description;

    public Mail(String subject, String monney, String time, String currentCash, String description) {
        this.subject = subject;
        this.monney = monney;
        this.time = time;
        this.currentCash = currentCash;
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMonney() {
        return monney;
    }

    public void setMonney(String monney) {
        this.monney = monney;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCurrentCash() {
        return currentCash;
    }

    public void setCurrentCash(String currentCash) {
        this.currentCash = currentCash;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "subject='" + subject + '\'' +
                ", monney='" + monney + '\'' +
                ", time='" + time + '\'' +
                ", currentCash='" + currentCash + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
