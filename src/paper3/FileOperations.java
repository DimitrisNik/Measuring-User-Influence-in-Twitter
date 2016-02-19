package paper3;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class FileOperations {
    private final String startPath ="C:\\Users\\Dimitris\\Documents\\NetBeansProjects\\Mesuring Influence\\dataset";
    private User user;

    
    
    
    private ArrayList<String> getFolders(String spath){
        ArrayList<String> followerspathlist = new ArrayList<>();
        File file = new File(spath);
        String[] followerspath = file.list();

        for(String s: followerspath){
            String path = spath+"\\"+s;
            File temp = new File(path);
          
            if(temp.isDirectory()){
                followerspathlist.add(path);
            }
        }
        return followerspathlist;
    }
    
    private ArrayList<String> readInfofile(String path) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader(path));
            ArrayList<String> list = new ArrayList<>();
            String line;
            while((line = br.readLine()) != null){
                list.add(line);
            }
            
            return list;
    }
    
    private String readfile(String path) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader(path));
            ArrayList<String> list = new ArrayList<>();
            String line;
            String temp ="";
            while((line = br.readLine()) != null){
                temp = temp +" "+ line;
            }
            
            return temp;
    }
    
    
    
    
    private String getValue(String s){
        String[] split = s.split(" ");
        return split[1];
    }
    
    public  ArrayList<User> readDataset() throws FileNotFoundException, IOException {
        ArrayList<String> pathlist = getFolders(startPath); //Dataset folders
        ArrayList<User> userList = new ArrayList<>();
        int count =  0;
        for(int i=0; i<pathlist.size(); i++){
            String path = pathlist.get(i);
            count++;
            System.out.println("done ("+count+"/"+pathlist.size()+")");
            user = new User();
           
            //read info.txt
            String pathinfo = path+"\\info.txt";
            ArrayList<String> templist = readInfofile(pathinfo);
            System.out.println("screename: "+templist.get(0));
            user.setScreeName(getValue(templist.get(0)));
            user.setFollowersCount(Integer.parseInt(getValue(templist.get(1))));
            templist= null;
            System.out.println("info.txt done");
            //****************************************************************************//
            //read Status            
            user.setTweets("\\alltweets.txt");
            System.out.println("setTweetsPath done");

            //followers
            System.out.println("Start Followers");
            String followerspath = path+"\\Followers";            
            ArrayList<String> followersfolderfolders = getFolders(followerspath);
            int temp =0;
            for(String s:followersfolderfolders){
                temp++;
                System.out.println("raad follower status("+temp +"/"+followersfolderfolders.size() +")");
                Follower follower = new Follower();

                String tweets = readfile(s+"\\alltweets.txt");
                follower.setTweets(tweets);
                user.followers.add(follower);
            }
            System.out.println("done Followers");
            
            userList.add(user);
            user = null;
        }
        
        
        
       return userList; 
    }
    
}
