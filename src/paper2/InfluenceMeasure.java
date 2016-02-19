package paper2;

import com.klout4java.Klout4Java;
import com.klout4java.Klout4JavaException;
import com.klout4java.KloutConfig;
import com.klout4java.vo.ScoreResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class InfluenceMeasure {
    public void run() throws IOException{
        ArrayList<Influence> alluserInfluenceList = new ArrayList<>();
        Influence influence;
        FileOperations fo = new FileOperations();
        ArrayList<User> userlist = fo.readDataset();
        for(User user:userlist){
            influence = new Influence();
            influence.setUser(user);
            influence.setRf(getRf(user));
            double rrt = getRrt(user);
            influence.setRrt(rrt);
            double ri = getRi(user);
            influence.setRi(ri);
            influence.setKlout(getKlout(user));
            influence.setSNP(getSNP(ri, rrt));
            alluserInfluenceList.add(influence);
        }
        ArrayList<Influence> kloutOrderList = new ArrayList<>();
        Influence[] temp = _kloutOdrder(alluserInfluenceList);
        kloutOrderList.addAll(Arrays.asList(temp));
        System.out.println("kloudOrder");
        printResults(kloutOrderList);
        System.out.println("\n\n");
        
        
        ArrayList<Influence> SNPOrderList = new ArrayList<>();
        Influence[] temp2 = _SNPOdrder(alluserInfluenceList);
        temp2 = _SNPOdrder(alluserInfluenceList);
        SNPOrderList.addAll(Arrays.asList(temp2));
        System.out.println("SNPOrderList");
        printResults(SNPOrderList);
    }
    
    public Influence[] _kloutOdrder(ArrayList<Influence> list){
        Influence[] table = new Influence[list.size()];
        list.toArray(table);

        for(int i=0; i<table.length; i++){
            for(int j= table.length-1; j>i; j--){
                if(table[i].getKlout()<table[j].getKlout()){
                    Influence temp = table[i];
                    table[i] = table[j];
                    table[j] = temp;
                }
            }
        }
        return table;
    }
    
    public Influence[] _SNPOdrder(ArrayList<Influence> list){
        Influence[] table = new Influence[list.size()];
        list.toArray(table);

        
        for(int i=0; i<table.length; i++){
            for(int j= table.length-1; j>i; j--){
                if(table[i].getSNP()<table[j].getSNP()){
                    Influence temp = table[i];
                    table[i] = table[j];
                    table[j] = temp;
                }
            }
        }
        return table;
    }
    
    public void printResults( ArrayList<Influence> list){
        System.err.println("#\t"+"ScreeName\t"+"Rf\t"+"Rrt\t"+"Ri\t"+"Klout Score\t"+"SNP" );
        int line =0;
        for(Influence in:list){
            line++;
                System.out.print(line);
                System.out.print("\t");
                System.out.print(in.getUser().getScreeName());
                System.out.print("\t");
                System.out.printf("%.2f",in.getRf());
                System.out.print("\t");
                System.out.printf("%.2f",in.getRrt());
                System.out.print("\t");
                System.out.printf("%.2f",in.getRi()) ;
                System.out.print("\t");
                System.out.printf("%.2f",in.getKlout());

                System.out.print("\t");
                System.out.printf("%.2f",in.getSNP());
                System.out.print("\n");
            
                //System.out.println(line+"\t"+in.getUser().getScreeName() +"\t"+in.getRf()+"\t" +in.getRrt() +"\t" +in.getRi() +"\t" +in.getKlout() +"\t"+in.getSNP());
        }
    }
    
    
    private double getRf(User user){
        if(user.getFollowingCount() == 0){
            return -1.0;
        }
        else{
            double rf= (double)user.getFollowersCount()/(double) user.getFollowingCount();
            return rf;
        }
    }
    
    private double getRrt(User user){
        int tweets = user.getTweetsIsRetweetedCount() +user.getMentionsCount();
        if(user.getStatusesCount() == 0){
            return -1.0;
        }
        else{
            double rrt = (double) tweets/(double)user.getStatusesCount();
            return rrt;
        }       
    }
    
    private double getRi(User user){
        int individualUsers = user.getIndividualUsersRetweetCount() + user.getIndividualUsersMentinCount();
        if(user.getFollowersCount() == 0){
            return -1.0;
        }
        else{
            double ri = (double)individualUsers/(double)user.getFollowersCount();
            return ri;
        }
    }
    
    private double getKlout(User user){
        double kloutScore = 0.0;
        KloutConfig config = new KloutConfig();
        config.setApiKey(""); //set the API key
        Klout4Java klout = new Klout4Java();
        klout.setConfig(config);

        try {
            ScoreResponse scoreForTwScreenName = klout.scoreForTwScreenName(user.getScreeName());
            kloutScore = Double.parseDouble(scoreForTwScreenName.getScore());

        } catch (Klout4JavaException e) {
            kloutScore = -1.0;
        }
        return kloutScore;
    }
    
    public double getSNP(double ri, double rrt){
        if(ri == -1.0 || rrt == -1.0){
            return -1.0;
        }
        else{
            double SNP = (ri +rrt)/2;
            return SNP;
        }
    }
}
