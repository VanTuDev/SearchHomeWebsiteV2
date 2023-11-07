package com.example.demo.dto;

public class WishlistItemRequest {
    private final long userId;
    private final long postId;

    public WishlistItemRequest(long userId, long postId) {
        this.userId = userId;
        this.postId = postId;
    }

    public long getUserId() {
        return userId;
    }

    public long getPostId() {
        return postId;
    }

    @Override
    public String toString() {
        return "WishlistItemRequest{" +
                "userId=" + userId +
                ", postId=" + postId +
                '}';
    }
}
