package mypackage.iplproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Match> matches = fetchMatchesData();
        List<Delivery> deliveries = fetchDeliveriesData();

        findNumberOfMatchesPlayedPerYear(matches);
        findNumberOfMatchesWonByTeamsOverAllYears(matches);
        findNumberOfExtraRunsConcededByTeamIn2016(matches, deliveries);
        findMostEconomicalBowlerIn2015(matches, deliveries);
        findNumberOfMatchesWonByTeamInHomeGround(matches);


    }

    private static void findNumberOfMatchesPlayedPerYear(List<Match> matches) {
        HashMap<String, Integer> matchesPlayedPerYear = new HashMap<String, Integer>();

        for (int i = 0; i < matches.size(); i++) {
            int count = matchesPlayedPerYear.getOrDefault(matches.get(i).getSeason(), 0);
            matchesPlayedPerYear.put(matches.get(i).getSeason(), count + 1);
        }
        System.out.println("No. of Matches played per year :");
        for (String key: matchesPlayedPerYear.keySet()){
            System.out.println(key+" - "+matchesPlayedPerYear.get(key));
        }
    }

    private static void findNumberOfMatchesWonByTeamsOverAllYears(List<Match> matches) {
        HashMap<String, Integer> matchesWonByTeams = new HashMap<>();

        for (int i = 0; i < matches.size(); i++) {
            int count=matchesWonByTeams.getOrDefault(matches.get(i).getWinningTeam(),0);
            matchesWonByTeams.put(matches.get(i).getWinningTeam(),count+1);

        }
        //There's a typo in teamnames so removed duplicate entry
        int duplicateVal=matchesWonByTeams.get("Rising Pune Supergiant");
        matchesWonByTeams.remove("Rising Pune Supergiant");
        matchesWonByTeams.put("Rising Pune Supergiants",matchesWonByTeams.get("Rising Pune Supergiants")+duplicateVal);
        matchesWonByTeams.remove(""); //Removed NoResult value
        System.out.println("No.of matches won by teams over all years :");
        for(String key:matchesWonByTeams.keySet()){
            System.out.println(key+" - "+matchesWonByTeams.get(key));
        }
    }

    private static void findNumberOfExtraRunsConcededByTeamIn2016(List<Match> matches, List<Delivery> deliveries) {
        String reqYear="2016";
        ArrayList<String> reqYearMatchIds=new ArrayList<>();
        HashMap<String,Integer> extraRunsConcededByTeams=new HashMap<>();

        for(int i=0;i<matches.size();i++){
            if(reqYear.equals(matches.get(i).getSeason())){
               reqYearMatchIds.add(matches.get(i).getMatchId());
            }
        }
        for (int i=0;i<deliveries.size();i++){
            if(reqYearMatchIds.contains(deliveries.get(i).getDeliveryMatchId())){
                int counter=extraRunsConcededByTeams.getOrDefault(deliveries.get(i).getBowlingTeam(),0);
                extraRunsConcededByTeams.put(deliveries.get(i).getBowlingTeam(), counter+Integer.valueOf(deliveries.get(i).getExtraRuns()));
            }
        }
        System.out.println("Extra runs conceded by each team in 2016 :");
        for (String key:extraRunsConcededByTeams.keySet()){
            System.out.println(key+" - "+extraRunsConcededByTeams.get(key));
        }

    }

    private static void findMostEconomicalBowlerIn2015(List<Match> matches, List<Delivery> deliveries) {
        String reqBowler = "";
        String reqYear = "2015";
        ArrayList<String> reqYearMatchIds = new ArrayList<>();
        HashMap<String, ArrayList<Double>> bowlerRecords = new HashMap<>();

        for (int i = 0; i < matches.size(); i++) {
            if (reqYear.equals(matches.get(i).getSeason())) {
                reqYearMatchIds.add(matches.get(i).getMatchId());
            }
        }
        for (int i = 0; i < deliveries.size(); i++) {
            if (reqYearMatchIds.contains(deliveries.get(i).getDeliveryMatchId())) {
                if (!(bowlerRecords.containsKey(deliveries.get(i).getBowler()))) {
                    ArrayList<Double> bowlerStats = new ArrayList<>();
                    bowlerStats.add(1.0);
                    bowlerStats.add(deliveries.get(i).getTotalRuns());
                    bowlerRecords.put(deliveries.get(i).getBowler(), bowlerStats);
                } else {
                    ArrayList<Double> prevRecord = bowlerRecords.get(deliveries.get(i).getBowler());
                    prevRecord.set(0, prevRecord.get(0) + 1);
                    prevRecord.set(1, prevRecord.get(1) + deliveries.get(i).getTotalRuns());
                    bowlerRecords.put(deliveries.get(i).getBowler(), prevRecord);
                }
            }
        }
        double min = 100.0;
        for (Map.Entry mapElement : bowlerRecords.entrySet()) {
            ArrayList<Double> value = (ArrayList<Double>) mapElement.getValue();
            double avg = value.get(1) * 6 / (value.get(0));
            if (min > avg) {
                min = avg;
                reqBowler = (String) mapElement.getKey();
//                System.out.println(min + "     " + reqBowler);
            }

        }
        System.out.println("Most Economical Bowler of 2015 : "+reqBowler);
    }

    private static void findNumberOfMatchesWonByTeamInHomeGround(List<Match> matches) {

    }


    private static List<Match> fetchMatchesData() throws IOException {
        List<Match> matches = new ArrayList<>();
        String matchPath = "/home/sreedhar/IdeaProjects/Project1/matches.csv";
        BufferedReader br = new BufferedReader(new FileReader(matchPath));
        String line = br.readLine();
        while ((line = br.readLine()) != null) {
            String[] records = line.split(",");
            Match match = new Match();
            match.setMatchId(records[0]);
            match.setSeason(records[1]);
            match.setCity(records[2]);
            match.setWinningTeam(records[10]);

            matches.add(match);
        }

        return matches;
    }


    private static List<Delivery> fetchDeliveriesData() throws IOException {
        List<Delivery> deliveries = new ArrayList<>();
        String deliveriesPath = "/home/sreedhar/IdeaProjects/Project1/deliveries.csv";
        BufferedReader br = new BufferedReader(new FileReader(deliveriesPath));
        String line = br.readLine();

        while ((line = br.readLine()) != null) {
            String[] records = line.split(",");
            Delivery delivery = new Delivery();
            delivery.setDeliveryMatchId(records[0]);
            delivery.setBowlingTeam(records[3]);
            delivery.setBowler(records[8]);
            delivery.setExtraRuns(records[16]);
            delivery.setTotalRuns(Double.valueOf(records[17]));

            deliveries.add(delivery);

        }
        return deliveries;
    }


}
