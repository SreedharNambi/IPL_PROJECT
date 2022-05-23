import java.io.*;
import java.sql.SQLOutput;
import java.util.HashMap;

public class Question1and2 {
    public static void main(String[] args) throws IOException {
        String path = "/home/sreedhar/IdeaProjects/Project1/matches.csv";
        String line = "";
//        Scenario 1 :- No. of matches played in a year by all teams.
        HashMap<String, Integer> matchesPlayedInYear = new HashMap<String, Integer>();
//        Scenario 2:- No. of matches won by each team over all years.
        HashMap<String, Integer> matchesWonByTeam = new HashMap<String, Integer>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        line = br.readLine();
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            int count = matchesPlayedInYear.containsKey(values[1]) ? matchesPlayedInYear.get(values[1]) : 0;
            matchesPlayedInYear.put(values[1], count + 1);
            int value = matchesWonByTeam.containsKey(values[10]) ? matchesWonByTeam.get(values[10]) : 0;
            matchesWonByTeam.put(values[10], value + 1);
        }
//            matchesPlayedInYear.remove("season");
//            Removing duplicate value present in dataset due to type error
        int puneValue1 = matchesWonByTeam.get("Rising Pune Supergiants");
        int puneValue2 = matchesWonByTeam.get("Rising Pune Supergiant");
        matchesWonByTeam.remove("Rising Pune Supergiant");
        matchesWonByTeam.replace("Rising Pune Supergiants", puneValue1 + puneValue2);


        System.out.println("No. of matches played per year of all years");
        for (String key : matchesPlayedInYear.keySet()) {
            System.out.println(key + " - " + matchesPlayedInYear.get(key));

        }


        int noResultValue = matchesWonByTeam.get("");
        matchesWonByTeam.remove("");
        matchesWonByTeam.put("NoResultMatches", noResultValue);
        System.out.println();
        System.out.println("No. of matches won by each team over all the years:");
        for (String key : matchesWonByTeam.keySet()) {
            System.out.println(key + " - " + matchesWonByTeam.get(key));

        }

    }
}

