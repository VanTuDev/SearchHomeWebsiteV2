package com.example.demo.beans;

import com.google.api.client.util.DateTime;

import java.time.LocalDateTime;

public class Paying {
    private long id;
    private long postId;
    private long userId;
    private double numberOfMoney;

    private String description;
    private LocalDateTime dateTime;
    private String status;


    public Paying(long id,
                  long postId,
                  long userId,
                  double numberOfMoney,
                  String description,
                  LocalDateTime dateTime,
                  String status) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.numberOfMoney = numberOfMoney;
        this.description = description;
        this.dateTime = dateTime;
        this.status = status;
    }

    public Paying() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getNumberOfMoney() {
        return numberOfMoney;
    }

    public void setNumberOfMoney(double numberOfMoney) {
        this.numberOfMoney = numberOfMoney;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Paying{" +
                "id=" + id +
                ", postId=" + postId +
                ", userId=" + userId +
                ", numberOfMoney=" + numberOfMoney +
                ", description='" + description + '\'' +
                ", dateTime=" + dateTime +
                ", status='" + status + '\'' +
                '}';
    }
}
