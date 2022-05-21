import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Question3 {
    public static void main(String[] args) throws IOException {
        String reqYear="2016";
        String path2="/home/sreedhar/IdeaProjects/Project1/src/matches.csv";
        BufferedReader br1 = new BufferedReader(new FileReader(path2));
        String matchesLine=br1.readLine();
        String path1="/home/sreedhar/IdeaProjects/Project1/deliveries.csv";
        BufferedReader br2 = new BufferedReader(new FileReader(path1));
        String deliveriesLine= br2.readLine();
        HashMap<String,Integer> extraRunsConceded=new HashMap<String,Integer>();
        ArrayList<String> reqYearRecords=new ArrayList<String>();
        while((matchesLine= br1.readLine())!=null){
           String [] matchesRecord=matchesLine.split(",");
           if (reqYear.equals(matchesRecord[1])){
//
               reqYearRecords.add(matchesRecord[0]);

           }
        }
        while((deliveriesLine= br2.readLine())!=null){
                   String [] deliveryRecords=deliveriesLine.split(",");

                   if (reqYearRecords.contains(deliveryRecords[0])){
                       int counter=extraRunsConceded.containsKey(deliveryRecords[3])?extraRunsConceded.get(deliveryRecords[3]):0;
                       extraRunsConceded.put(deliveryRecords[3], counter+Integer.parseInt(deliveryRecords[16]));

                   }
               }
        System.out.println("Scenario 3: The Extra runs conceded by all teams in 2016");
        for(String key : extraRunsConceded.keySet()){
            System.out.println(key +" - "+extraRunsConceded.get(key));
        }
        }


    }

