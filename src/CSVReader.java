import java.io.*;

public class CSVReader {
    public static void main (String [] args){
        String path="/home/sreedhar/IdeaProjects/Project1/src/matches.csv";
        String line="";

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while((line=br.readLine())!=null){
                String [] values=line.split(",");
                System.out.println("Match Id: "+values[0]+", Season: "+values[1]+", Winner: "+values[10]);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
