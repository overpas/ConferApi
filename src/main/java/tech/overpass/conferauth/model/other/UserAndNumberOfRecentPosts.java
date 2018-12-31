package tech.overpass.conferauth.model.other;

import tech.overpass.conferauth.model.db.User;

public class UserAndNumberOfRecentPosts {

    private User user;
    private int numberOfRecentPosts;

    public UserAndNumberOfRecentPosts(User user, int numberOfRecentPosts) {
        this.user = user;
        this.numberOfRecentPosts = numberOfRecentPosts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNumberOfRecentPosts() {
        return numberOfRecentPosts;
    }

    public void setNumberOfRecentPosts(int numberOfRecentPosts) {
        this.numberOfRecentPosts = numberOfRecentPosts;
    }
}
