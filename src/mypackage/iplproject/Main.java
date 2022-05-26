package mypackage.iplproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static final int MATCH_ID=0;

    public static final int MATCH_SEASON=1;

    public static final int MATCH_CITY=2;

    public static final int MATCH_WINNING_TEAM=10;

    public static final int DELIVERY_MATCH_ID=0;

    public static final int BOWLING_TEAM=3;

    public static final int BOWLER=8;

    public static final int EXTRA_RUNS=16;

    public static final int TOTAL_RUNS=17;
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

        for (Match match : matches) {
            int count = matchesPlayedPerYear.getOrDefault(match.getSeason(), 0);
            matchesPlayedPerYear.put(match.getSeason(), count + 1);
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

        for (Match match : matches) {
            if (reqYear.equals(match.getSeason())) {
                reqYearMatchIds.add(match.getMatchId());
            }
        }
        for (Delivery delivery : deliveries) {
            if (reqYearMatchIds.contains(delivery.getDeliveryMatchId())) {
                int counter = extraRunsConcededByTeams.getOrDefault(delivery.getBowlingTeam(), 0);
                extraRunsConcededByTeams.put(delivery.getBowlingTeam(), counter + Integer.valueOf(delivery.getExtraRuns()));
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

        for (Match match : matches) {
            if (reqYear.equals(match.getSeason())) {
                reqYearMatchIds.add(match.getMatchId());
            }
        }
        for (Delivery delivery : deliveries) {
            if (reqYearMatchIds.contains(delivery.getDeliveryMatchId())) {
                if (!(bowlerRecords.containsKey(delivery.getBowler()))) {
                    ArrayList<Double> bowlerStats = new ArrayList<>();
                    bowlerStats.add(1.0);
                    bowlerStats.add(delivery.getTotalRuns());
                    bowlerRecords.put(delivery.getBowler(), bowlerStats);
                } else {
                    ArrayList<Double> prevRecord = bowlerRecords.get(delivery.getBowler());
                    prevRecord.set(0, prevRecord.get(0) + 1);
                    prevRecord.set(1, prevRecord.get(1) + delivery.getTotalRuns());
                    bowlerRecords.put(delivery.getBowler(), prevRecord);
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
        //Team I have chosen is SunRisers Hyderabad and their homeground is Hyderabad
        String team ="Sunrisers Hyderabad";
        String homeGround="Hyderabad";
        Integer numberOfMatchesWon=0;
        for (Match match : matches) {
            if (team.equals(match.getWinningTeam())) {
                if (homeGround.equals(match.getCity())) {
                    numberOfMatchesWon += 1;
                }
            }
            {

            }

        }
        System.out.println("Number of matches won by Sunrisers Hyderabad in Hyderabad is " + numberOfMatchesWon);
    }


    private static List<Match> fetchMatchesData() throws IOException {
        List<Match> matches = new ArrayList<>();
        String matchPath = "./matches.csv";
        BufferedReader br = new BufferedReader(new FileReader(matchPath));
        String line = br.readLine();
        while ((line = br.readLine()) != null) {
            String[] records = line.split(",");
            Match match = new Match();
            match.setMatchId(records[MATCH_ID]);
            match.setSeason(records[MATCH_SEASON]);
            match.setCity(records[MATCH_CITY]);
            match.setWinningTeam(records[MATCH_WINNING_TEAM]);

            matches.add(match);
        }

        return matches;
    }


    private static List<Delivery> fetchDeliveriesData() throws IOException {
        List<Delivery> deliveries = new ArrayList<>();
        String deliveriesPath = "./deliveries.csv";
        BufferedReader br = new BufferedReader(new FileReader(deliveriesPath));
        String line = br.readLine();

        while ((line = br.readLine()) != null) {
            String[] records = line.split(",");
            Delivery delivery = new Delivery();
            delivery.setDeliveryMatchId(records[DELIVERY_MATCH_ID]);
            delivery.setBowlingTeam(records[BOWLING_TEAM]);
            delivery.setBowler(records[BOWLER]);
            delivery.setExtraRuns(records[EXTRA_RUNS]);
            delivery.setTotalRuns(Double.valueOf(records[TOTAL_RUNS]));

            deliveries.add(delivery);

        }
        return deliveries;
    }


}
