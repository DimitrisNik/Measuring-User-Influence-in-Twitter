package paper3;

import java.util.ArrayList;

public class User {

    private String ScreeName;

    private int FollowersCount;
    private int IF;
    private String tweets;
    private double ratio;

    public ArrayList<Follower> followers = new ArrayList<Follower>();

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public void setScreeName(String ScreeName) {
        this.ScreeName = ScreeName;
    }

    public void setFollowersCount(int FollowersCount) {
        this.FollowersCount = FollowersCount;
    }

    public void setIF(int IF) {
        this.IF = IF;
    }

    public void setTweets(String tweetsPath) {
        this.tweets = tweetsPath;
    }

    public void setFollowers(ArrayList<Follower> followers) {
        this.followers = followers;
    }

    public String getScreeName() {
        return ScreeName;
    }

    public int getFollowersCount() {
        return FollowersCount;
    }

    public int getIF() {
        return IF;
    }

    public String getTweets() {
        return tweets;
    }

    public ArrayList<Follower> getFollowers() {
        return followers;
    }

    public double getRatio() {
        return ratio;
    }

}
