package paper1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileOperations {
    private final String startPath ="C:\\Users\\Dimitris\\Documents\\NetBeansProjects\\Mesuring Influence\\dataset";
    private User user;
    
    private ArrayList<String> getFolders(){
        ArrayList<String> followerspathlist = new ArrayList<>();
        File file = new File(startPath);
        String[] followerspath = file.list();

        for(String s: followerspath){
            String path = startPath+"\\"+s;
            File temp = new File(path);
          
            if(temp.isDirectory()){
                followerspathlist.add(path);
            }
        }
        return followerspathlist;
    }
    
    private ArrayList<String> readfile(String path) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader(path));
            ArrayList<String> list = new ArrayList<>();
            String line;
            while((line = br.readLine()) != null){
                list.add(line);
            }
            
            return list;
    }
    
    private String readStatus(ArrayList<String> pathlist) throws FileNotFoundException, IOException {
         BufferedReader br;
         String userStatuses = null;
         String line = null;
         
        for (String path : pathlist) {
            br = new BufferedReader(new FileReader(path));  
            while ((line = br.readLine()) != null) {
                userStatuses = userStatuses + line;
            }
            br=null;
        }
        return userStatuses;
    }
    
    private ArrayList<String> geStatusFiles(String path){
        ArrayList<String> txtFileList = new ArrayList<>();
        File file = new File(path);
        String[] list = file.list();
        for(String s:list){
            if(s.endsWith(".txt")){
                if(!s.contains("info.txt")){
                    s = path+"\\"+s;

                    txtFileList.add(s);
                }
            }
        }
        return txtFileList;
    }
    
    private String getValue(String s){
        String[] split = s.split(" ");
        return split[1];
    }
    
    public  ArrayList<User> readDataset() throws FileNotFoundException, IOException {
        ArrayList<String> pathlist = getFolders(); //Dataset folders
        ArrayList<User> userList = new ArrayList<>();
        
        for(String path:pathlist){
            
            user = new User();
           
            //read info.txt
            String pathinfo = path+"\\info.txt";
            ArrayList<String> templist = readfile(pathinfo);
            
            user.setScreeName(getValue(templist.get(0)));
            user.setStatusesCount(Integer.parseInt(getValue(templist.get(1))));
            user.setRetweetsCount(Integer.parseInt(getValue(templist.get(2))));
            user.setTweetsIsRetweetedCount(Integer.parseInt(getValue(templist.get(3))));
            user.setIndividualUsersRetweetCount(Integer.parseInt(getValue(templist.get(4))));
            user.setMentionsCount(Integer.parseInt(getValue(templist.get(5))));
            user.setIndividualUsersMentinCount(Integer.parseInt(getValue(templist.get(6))));
            user.setFollowersCount(Integer.parseInt(getValue(templist.get(7))));
            user.setFollowingCount(Integer.parseInt(getValue(templist.get(8))));

            userList.add(user);
        }
        
        
        
       return userList; 
    }
    
}
