package mypackage.iplproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
    List<Match> matches = fetchMatchesData();
    List<Delivery> deliveries=fetchDeliveriesData();
    
    findNumberOfMatchesPlayedPerYear(matches);
    findNumberOfMatchesWonByTeamsOverAllYears(matches);
    findNumberOfExtraRunsConcededByTeamIn2016(matches,deliveries);
    findMostEconomicalBowlerIn2015(matches,deliveries);
    findNumberOfMatchesWonByTeamInHomeGround(matches);



    }

    private static void findNumberOfMatchesPlayedPerYear(List<Match> matches) {
        HashMap<String, Integer> matchesPlayedInYear = new HashMap<String, Integer>();

        for (int i=0;i<matches.size();i++){
            int count = matchesPlayedInYear.containsKey([1]) ? matchesPlayedInYear.get(matches.get(i)[0]) : 0;
            matchesPlayedInYear.put(values[1], count + 1);
        }

    }

    private static void findNumberOfMatchesWonByTeamsOverAllYears(List<Match> matches) {
    }

    private static void findNumberOfExtraRunsConcededByTeamIn2016(List<Match> matches, List<Delivery> deliveries) {
    }

    private static void findMostEconomicalBowlerIn2015(List<Match> matches, List<Delivery> deliveries) {
    }

    private static void findNumberOfMatchesWonByTeamInHomeGround(List<Match> matches) {
    }


    private static List<Match> fetchMatchesData() throws IOException {
        List<Match> matches=new ArrayList<>();
        String matchPath = "/home/sreedhar/IdeaProjects/Project1/matches.csv";
        BufferedReader br = new BufferedReader(new FileReader(matchPath));
        String line = br.readLine();
        while ((line = br.readLine()) != null){
            String[] records = line.split(",");
            Match match=new Match();
            match.setMatchId(records[0]);
            match.setSeason(records[1]);
            match.setCity(records[2]);
            match.setWinningTeam(records[10]);

            matches.add(match);
        }

        return matches;
    }


    private static List<Delivery> fetchDeliveriesData() throws IOException {
        List<Delivery> deliveries=new ArrayList<>();
        String deliveriesPath="/home/sreedhar/IdeaProjects/Project1/deliveries.csv";
        BufferedReader br= new BufferedReader(new FileReader(deliveriesPath));
        String line= br.readLine();

        while ((line = br.readLine()) != null){
            String [] records=line.split(",");
            Delivery delivery=new Delivery();
            delivery.setDeliveryMatchId(records[0]);
            delivery.setBowlingTeam(records[3]);
            delivery.setBowler(records[8]);
            delivery.setExtraRuns(records[16]);
            delivery.setTotalRuns(records[17]);

            deliveries.add(delivery);

        }
        return deliveries;
    }



}
