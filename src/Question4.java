import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Question4 {
    public static void main(String[] args) throws IOException {
        String reqBowler = "";
        String reqYear = "2015";
        String path2 = "/home/sreedhar/IdeaProjects/Project1/src/matches.csv";
        BufferedReader br1 = new BufferedReader(new FileReader(path2));
        String matchesLine = br1.readLine();
        String path1 = "/home/sreedhar/IdeaProjects/Project1/deliveries.csv";
        BufferedReader br2 = new BufferedReader(new FileReader(path1));
        String deliveriesLine = br2.readLine();
        HashMap<String, Integer> extraRunsConceded = new HashMap<String, Integer>();
        HashSet<String> reqYearRecords = new HashSet<>();
        while ((matchesLine = br1.readLine()) != null) {
            String[] matchesRecord = matchesLine.split(",");
            if (reqYear.equals(matchesRecord[1])) {
//
                reqYearRecords.add(matchesRecord[0]);

            }
        }
        System.out.println(reqYearRecords);
        HashMap<String, ArrayList<Double>> bowlerRecords = new HashMap<>();
        while ((deliveriesLine = br2.readLine()) != null) {
            String[] deliveryRecords = deliveriesLine.split(",");
            if (reqYearRecords.contains(deliveryRecords[0])) {
                if (!(bowlerRecords.containsKey(deliveryRecords[8]))) {
                    ArrayList<Double> record = new ArrayList<>();
                    record.add(1.0);
                    record.add(Double.valueOf(deliveryRecords[17]));
                    bowlerRecords.put(deliveryRecords[8], record);

                } else {
                    ArrayList<Double> prevRecord = bowlerRecords.get(deliveryRecords[8]);
                    prevRecord.set(0, prevRecord.get(0) + 1);
                    prevRecord.set(1, prevRecord.get(1) + Double.valueOf(deliveryRecords[17]));
                    bowlerRecords.put(deliveryRecords[8], prevRecord);
                }
//                System.out.println(bowlerRecords);

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
        System.out.println(reqBowler);

    }

}
