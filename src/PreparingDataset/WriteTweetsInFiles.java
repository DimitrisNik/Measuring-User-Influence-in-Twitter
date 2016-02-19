package PreparingDataset;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WriteTweetsInFiles {

    String StartPath = "C:\\Users\\Dimitris\\Desktop\\teliko_dataset";

    public static void main(String[] args) throws IOException {
        WriteTweetsInFiles wf = new WriteTweetsInFiles();
        wf.run();
    }

    public void run() throws IOException {
        ArrayList<String> allusers = getFolders(StartPath);
        int count = 0;
        for (String userpath : allusers) {
            count++;
            System.out.println("done (" + count + "/" + allusers.size() + ")");
            ArrayList<String> textfilespathList = getTextfiles(userpath);
            ArrayList<String> textfileContent = new ArrayList<>();
            for (String textfilepath : textfilespathList) {
                String tweet = readfile(textfilepath);
                textfileContent.add(tweet);
            }
            userpath = userpath + "\\";
            System.out.println("userPath: " + userpath);
            writeAlltweets(userpath, textfileContent);
            textfileContent.clear();
            //followers
            ArrayList<String> textfileContentFollower = new ArrayList<>();
            String followerPath = userpath + "followers";
            ArrayList<String> followersPathList = new ArrayList<>();
            followersPathList.addAll(getFolders(followerPath));

            for (String s : followersPathList) {
                ArrayList<String> textfiles = getTextfiles(s);
                for (String k : textfiles) {
                    String readfile = readfile(k);
                    textfileContentFollower.add(readfile);
                }
                s = s + "\\";
                writeAlltweets(s, textfileContentFollower);
                textfileContentFollower.clear();
            }
        }
    }

    public String readfile(String path) throws IOException {
        String content = "";

        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while ((line = br.readLine()) != null) {
            content = content + line;
        }
        return content;
    }

    private ArrayList<String> getFolders(String spath) throws IOException {
        ArrayList<String> directoriesList = new ArrayList<>();
        File file = new File(spath);
        File[] allfiles = file.listFiles();

        for (File temp : allfiles) {

            if (temp.isDirectory()) {
                directoriesList.add(temp.getAbsolutePath());
            }
        }
        return directoriesList;
    }

    private ArrayList<String> getTextfiles(String spath) throws IOException {
        ArrayList<String> TextFileList = new ArrayList<>();
        File file = new File(spath);

        File[] allfiles = file.listFiles();

        for (File temp : allfiles) {
            if (temp.isFile() && !temp.getName().contains("info")) {
                TextFileList.add(temp.getAbsolutePath());
            }

        }
        return TextFileList;
    }

    public void writeAlltweets(String path, ArrayList<String> content) throws IOException {
        File file = new File(path);
        File temp = new File(path + "\\alltweets.txt");
        if (temp.exists()) {
            temp.setWritable(true);
            temp.setReadable(true);
            temp.delete();
        }

        PrintWriter writer = new PrintWriter(path + "alltweets.txt", "UTF-8");
        for (String s : content) {
            s = removelinksAndMentions(s);
            writer.println(s);
        }
        writer.close();

    }

    public String removelinksAndMentions(String s) {
        String[] split = s.split(" ");
        s = "";
        for (String a : split) {
            if (a.contains("http") || a.contains("@")) {
            } else {
                s = s + " " + a;
            }
        }
        return s;
    }
}
