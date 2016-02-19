package paper1;

public class User {
    private String ScreeName;
    private int StatusesCount;
    private int RetweetsCount;
    private int  TweetsIsRetweetedCount;
    private int  IndividualUsersRetweetCount;
    private int  MentionsCount;
    private int  IndividualUsersMentinCount;
    private int  FollowersCount;
    private int  FollowingCount;
    private String tweets;

    public void setScreeName(String ScreeName) {
        this.ScreeName = ScreeName;
    }

    public void setStatusesCount(int StatusesCount) {
        this.StatusesCount = StatusesCount;
    }

    public void setRetweetsCount(int RetweetsCount) {
        this.RetweetsCount = RetweetsCount;
    }

    public void setTweetsIsRetweetedCount(int TweetsIsRetweetedCount) {
        this.TweetsIsRetweetedCount = TweetsIsRetweetedCount;
    }

    public void setIndividualUsersRetweetCount(int IndividualUsersRetweetCount) {
        this.IndividualUsersRetweetCount = IndividualUsersRetweetCount;
    }

    public void setMentionsCount(int MentionsCount) {
        this.MentionsCount = MentionsCount;
    }

    public void setIndividualUsersMentinCount(int IndividualUsersMentinCount) {
        this.IndividualUsersMentinCount = IndividualUsersMentinCount;
    }

    public void setFollowersCount(int FollowersCount) {
        this.FollowersCount = FollowersCount;
    }

    public void setFollowingCount(int FollowingCount) {
        this.FollowingCount = FollowingCount;
    }

    public void setTweets(String tweets) {
        this.tweets = tweets;
    } 
    

    public String getScreeName() {
        return ScreeName;
    }

    public int getStatusesCount() {
        return StatusesCount;
    }

    public int getRetweetsCount() {
        return RetweetsCount;
    }

    public int getTweetsIsRetweetedCount() {
        return TweetsIsRetweetedCount;
    }

    public int getIndividualUsersRetweetCount() {
        return IndividualUsersRetweetCount;
    }

    public int getMentionsCount() {
        return MentionsCount;
    }

    public int getIndividualUsersMentinCount() {
        return IndividualUsersMentinCount;
    }

    public int getFollowersCount() {
        return FollowersCount;
    }

    public int getFollowingCount() {
        return FollowingCount;
    }

    public String getTweets() {
        return tweets;
    }

    @Override
    public String toString() {
        return "User{" + "ScreeName=" + ScreeName + ", StatusesCount=" + StatusesCount + ", RetweetsCount=" + RetweetsCount + ", TweetsIsRetweetedCount=" + TweetsIsRetweetedCount + ", IndividualUsersRetweetCount=" + IndividualUsersRetweetCount + ", MentionsCount=" + MentionsCount + ", IndividualUsersMentinCount=" + IndividualUsersMentinCount + ", FollowersCount=" + FollowersCount + ", FollowingCount=" + FollowingCount + ", tweets=" + tweets + '}';
    }

    
    
    
    
    
    

    
    
            
    
}
