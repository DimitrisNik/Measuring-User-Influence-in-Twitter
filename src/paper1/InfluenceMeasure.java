package paper1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class InfluenceMeasure {

    double IndegreeVSRetweets;
    double IndegreevsMentions;
    double RetweetsvsMentions;

    public void run() throws IOException {
        FileOperations fo = new FileOperations();
        ArrayList<User> allusersList = fo.readDataset();

        //order list
        ArrayList<Influence> measure = measure(allusersList);
        Influence[] indegreeOrderTable = bubbleSort(measure,0);
        Influence[] retweetsOrderTable = bubbleSort(measure,1);
        Influence[] mentionsOrderTable = bubbleSort(measure,2);
        
        //Spearman's Rank using relative order
        IndegreeVSRetweets = getSpearmanRank(indegreeOrderTable,retweetsOrderTable,0);
        IndegreevsMentions = getSpearmanRank(indegreeOrderTable,mentionsOrderTable,1);
        RetweetsvsMentions = getSpearmanRank(retweetsOrderTable,mentionsOrderTable,2);
         
        //print results
        int input = menu();
        while(input !=0){
            if(input == 1){
                System.out.println("\nIndegree Order\n");
                printResults(indegreeOrderTable);
                System.out.println("\n\n");
                input = menu();
            }
            else if(input == 2){
                System.out.println("\nRetweets Order\n");
                printResults(retweetsOrderTable);
                System.out.println("\n\n");
                input = input =menu();
            }
            else if( input == 3){
                System.out.println("\nMentions Order\n");
                printResults(mentionsOrderTable);         
                System.out.print("\n\n");
                input = menu();
            }
            else if(input == 4){
                System.out.println("Spearman's Rank");
                System.out.println("Indegree vs Retweets: " + IndegreeVSRetweets);
                System.out.println("Indegree vs Mentions: " + IndegreevsMentions);
                System.out.println("Retweets vs Mentions: " + RetweetsvsMentions);
                System.out.print("\n\n");
                input = menu();
            }
        }
        
         
         
    }
    
    private int menu(){
        System.out.println("1. Indegree Order");
        System.out.println("2. Retweets Order");
        System.out.println("3. Mentions Order");
        System.out.println("4. Spearman's Rank");
        System.out.println("0. Exit");
        System.out.print("\n Enter your choice: ");
        Scanner input = new Scanner(System.in);
        int in = input.nextInt();
        
        if(in>4 || in <0){
            System.out.println("Enter an incorrect choice.. Please Try again");
            menu();
            return -1;
        }
        else if(in==0){
            System.out.println("Exit...\n");
            System.exit(0);
            return -1;
        }
        else{
            return in;
        }

    }

    private void printResults(Influence[] measure) {
        System.out.println("ScreeName\t" + "Indegree\t" + "Retweets\t" + "Mentions");
        for (Influence in : measure) {
            System.out.println(in.getScreeName() + "\t" + in.getIndegree() + "\t" + in.getRetweets() + "\t" + in.getMentions());
        }

    }

    private Influence[] bubbleSort(ArrayList<Influence> list, int caseNumber) {

        /***********************/
        /*  case 0: Indegree   */
        /*  case 1: Retweets   */
        /*  case 2:  Mentions  */
        /***********************/

        Influence[] array = new Influence[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }

        int n = array.length;
        for (int i = 0; i < n; i++) {
            for (int j = (n - 1); j > i; j--) {
                if (caseNumber == 0) {
                    if (array[i].getIndegree() < array[j].getIndegree()) {
                        Influence temp = array[i];
                        array[i] = array[j];
                        array[j] = temp;
                    }
                } else if (caseNumber == 1) {
                    if (array[i].getRetweets() < array[j].getRetweets()) {
                        Influence temp = array[i];
                        array[i] = array[j];
                        array[j] = temp;
                    }
                } else if (caseNumber == 2) {
                    if (array[i].getMentions() < array[j].getMentions()) {
                        Influence temp = array[i];
                        array[i] = array[j];
                        array[j] = temp;
                    }
                }
                else{
                    System.err.println("Case error");
                    System.exit(-1);
                }
            }
        }
        return array;
    }

    private ArrayList<Influence> measure(ArrayList<User> allusersList) {
        ArrayList<Influence> userInfluenceList = new ArrayList<>();
        Influence influence;
        for (User user : allusersList) {
            influence = new Influence();
            influence.setScreeName(user.getScreeName());
            influence.setIndegree(user.getFollowersCount());
            influence.setRetweets(user.getRetweetsCount());
            influence.setMentions(user.getMentionsCount());
            userInfluenceList.add(influence);
        }
        return userInfluenceList;
    }

    private double getSpearmanRank(Influence[] table1,Influence[] table2, int caseNumber) {


        /************************************/
        /*   case 0: Indegree vs Retweets   */
        /*   case 1: Indegree vs Mentions   */
        /*   case 2: Retweets vs Mentions   */
        /************************************/
        
        double r = 0;
        int sum = 0;
        int N = table1.length;

        for (int i =0; i<table1.length; i++) {
            Influence in = table1[i];         
            int j = findPostionInTable(table2,in);
            int temp = (i+1) - (j+1);
            sum = (int) (sum + Math.pow(temp, 2));
        }
        
        int numerator = 6 * sum;
        int denominator = (int) Math.pow(N, 3) - N;

        r = 1 - ((double) numerator / (double) denominator);

        return r;
    }
    
    private int findPostionInTable(Influence[] array, Influence object){
        for(int i=0; i<array.length; i++ ){
            if(array[i].getScreeName().equals(object.getScreeName()) ){
                return i;
            }
        }
        return -1;
    }

}
