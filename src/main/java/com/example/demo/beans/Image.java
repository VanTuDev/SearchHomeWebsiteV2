package com.example.demo.beans;

public class Image {
    private long id;
    private String imageName;
    private long postId;

    public Image(long id, String imageName, long postId) {
        this.id = id;
        this.imageName = imageName;
        this.postId = postId;
    }

    public Image() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id='" + id + '\'' +
                ", imageName='" + imageName + '\'' +
                ", postId=" + postId +
                '}';
    }
}
