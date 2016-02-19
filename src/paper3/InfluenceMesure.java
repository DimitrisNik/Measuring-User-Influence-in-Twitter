package paper3;

import java.io.IOException;
import java.util.ArrayList;

public class InfluenceMesure {

    public void run() throws IOException {
        FileOperations fo = new FileOperations();
        ArrayList<User> dataset = fo.readDataset();

        //find Topics
        Topic topic;
        for (User user : dataset) {
            topic = new Topic();
            ArrayList<String> topicList = topic.getTopic(user.getTweets());

            ArrayList<Follower> newList = new ArrayList<>();
            ArrayList<Follower> templist = user.getFollowers();
            for (Follower foll : templist) {
                String Followertweets = foll.getTweets();
                if (machingTopics(topicList, Followertweets)) {
                    foll.setER(1);
                } else {
                    foll.setER(0);
                }
                newList.add(foll);
            }
            user.followers.clear();
            user.followers.addAll(newList);
            newList = null;
            templist = null;
            templist = user.getFollowers();
            int PotentialReaders = 0;
            for (Follower follower : templist) {
                if (follower.getER() == 0) {
                    PotentialReaders++;
                }

            }
            int ER = user.getFollowersCount() - PotentialReaders;
            user.setIF(ER);
            user.setIF(ER);

            user = null;
            topic = null;
            topicList = null;
            templist = null;

        }
        for (User user : dataset) {
            double ratio = (double) ((double) user.getIF() / (double) user.getFollowersCount()) * 100;
            user.setRatio(ratio);
        }
        User[] sort = sort(dataset);
        System.out.println("\n\n\n*******************************************************\n");
        System.out.println("Screname /t IF /t Ratio");
        for (User user : sort) {
            System.out.println(user.getScreeName() + "\t" + user.getIF() + "\t" + user.getRatio());
        }

    }

    private boolean machingTopics(ArrayList<String> userTopicList, String followersTweets) {
        followersTweets = followersTweets.toLowerCase();
        for (String userTopic : userTopicList) {
            if (followersTweets.contains(userTopic)) {
                return true;
            }
        }

        return false;
    }

    public User[] sort(ArrayList<User> list) {
        User[] table = new User[list.size()];
        for (int i = 0; i < list.size(); i++) {
            table[i] = list.get(i);
        }

        for (int i = 0; i < table.length; i++) {
            for (int j = table.length - 1; j > i; j--) {
                if (table[i].getIF() < table[j].getIF()) {
                    User temp = table[i];
                    table[i] = table[j];
                    table[j] = temp;
                }
            }
        }
        return table;
    }
}
