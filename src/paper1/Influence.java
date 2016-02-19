package paper1;


public class Influence {
    private String screeName;
    private int indegree;
    private int retweets;
    private int mentions;

    public void setScreeName(String screeName) {
        this.screeName = screeName;
    }
    
    public void setIndegree(int indegree) {
        this.indegree = indegree;
    }

    public void setRetweets(int retweets) {
        this.retweets = retweets;
    }

    public void setMentions(int mentions) {
        this.mentions = mentions;
    }

    public String getScreeName() {
        return screeName;
    }
    
    public int getIndegree() {
        return indegree;
    }

    public int getRetweets() {
        return retweets;
    }

    public int getMentions() {
        return mentions;
    }

    @Override
    public String toString() {
        return "Influence{" + "indegree=" + indegree + ", retweets=" + retweets + ", mentions=" + mentions + '}';
    }
    
    
    
}
