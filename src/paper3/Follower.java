package paper3;

import java.util.ArrayList;

public class Follower {

    private ArrayList<String> Topic;
    private int ER = 0;
    private String tweets;

    public void setTopic(ArrayList<String> Topic) {
        this.Topic = Topic;
    }

    public void setER(int ER) {
        this.ER = ER;
    }

    public void setTweets(String tweetsPath) {
        this.tweets = tweetsPath;
    }

    public ArrayList<String> getTopic() {
        return Topic;
    }

    public int getER() {
        return ER;
    }

    public String getTweets() {
        return tweets;
    }

}
