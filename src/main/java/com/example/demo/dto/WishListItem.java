package com.example.demo.dto;

import com.example.demo.beans.Post;

import java.time.LocalDateTime;

public class WishListItem {
    private long id;
    private long userId;
    private long postId;
    private Post post;
    private LocalDateTime createAt;

    public WishListItem(long id, long userId, long postId, LocalDateTime createAt) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.createAt = createAt;
    }

    public WishListItem() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "WishListItem{" +
                "id=" + id +
                ", userId=" + userId +
                ", postId=" + postId +
                ", createAt=" + createAt +
                '}';
    }
}
