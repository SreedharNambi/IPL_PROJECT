import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

//No. of matches won by Sunrisers Hyderabad in Hyderabad
public class OwnScenario {
    public static void main(String[] args) throws IOException {
        String path = "/home/sreedhar/IdeaProjects/Project1/src/matches.csv";
        String team = "Sunrisers Hyderabad";
        String homeGround = "Hyderabad";
        String line = "";
        Integer counter = 0;
        BufferedReader br = new BufferedReader(new FileReader(path));
        br.readLine();
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            if (homeGround.equals(values[2]) && team.equals(values[10])) {
                        counter++;
            }

        }
        System.out.print("No of matches won by Sunrisers Hyderabad in Homeground(Hyderabad) : ");
        System.out.println(counter);

    }
}
