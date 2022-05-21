import java.io.*;
import java.util.HashMap;
import java.util.TreeSet;

public class CSVReader {
    public static void main (String [] args) throws IOException {
        String path="/home/sreedhar/IdeaProjects/Project1/src/matches.csv";
        String line="";
//        Scenario 1 :- No. of matches played in a year by all teams.
        HashMap <String,Integer> matchesPlayedInYear=new HashMap<String,Integer>();
//        Scenario 2:- No. of matches won by each team over all years.
        HashMap<String,Integer> matchesWonByTeam=new HashMap<String,Integer>();
            BufferedReader br = new BufferedReader(new FileReader(path));
            line=br.readLine();
            while((line=br.readLine())!=null){
                String [] values=line.split(",");
                int count= matchesPlayedInYear.containsKey(values[1])? matchesPlayedInYear.get(values[1]):0;
                matchesPlayedInYear.put(values[1],count+1 );
                int value=matchesWonByTeam.containsKey(values[10]) ?matchesWonByTeam.get(values[10]):0;
                matchesWonByTeam.put(values[10],value+1 );
            }
//            matchesPlayedInYear.remove("season");
//            Removing duplicate value present in dataset due to type error
            int puneValue1= matchesWonByTeam.get("Rising Pune Supergiants");
            int puneValue2= matchesWonByTeam.get("Rising Pune Supergiant");
            matchesWonByTeam.remove("Rising Pune Supergiant");
            matchesWonByTeam.replace("Rising Pune Supergiants",puneValue1+puneValue2);
            System.out.println(matchesPlayedInYear);

            int noResultValue=matchesWonByTeam.get("");
            matchesWonByTeam.remove("");
            matchesWonByTeam.put("NoResultMatches",noResultValue);
            System.out.println(matchesWonByTeam);

            }




//     public static void Deliveries () throws IOException{
//            String path2="/home/sreedhar/IdeaProjects/Project1/src/matches.csv";
//            BufferedReader br1 = new BufferedReader(new FileReader(path2));
//            String matchesLine=br1.readLine();
//            String path1="/home/sreedhar/IdeaProjects/Project1/deliveries.csv";
//            BufferedReader br2 = new BufferedReader(new FileReader(path1));
//            String deliveriesLine= br2.readLine();
//            HashMap<String,Integer> extraRunsConceded=new HashMap<String,Integer>();
//            while ((matchesLine= br1.readLine())!=null){
//                String [] matchRecords= matchesLine.split(",");
//                if(matchRecords[1]=="2016"){
//                    while ((deliveriesLine = br2.readLine())!=null);
//                    String [] deliveriesRecords= deliveriesLine.split(",");
//                    System.out.println(deliveriesRecords);
//                    System.out.println(deliveriesRecords[2]);
//                    if(matchRecords[0]==deliveriesRecords[0]){
//                        int counter=extraRunsConceded.containsKey(deliveriesRecords[3])?extraRunsConceded.get(deliveriesRecords[3]):0;
//                        extraRunsConceded.put(deliveriesRecords[3], Integer.valueOf(counter+deliveriesRecords[16]));
//
//                    }
//                }
//        }
//         System.out.println(extraRunsConceded);
//        }
}


