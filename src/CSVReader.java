import java.io.*;
import java.util.TreeSet;

public class CSVReader {
    public static void main (String [] args){
        String path="/home/sreedhar/IdeaProjects/Project1/src/matches.csv";
        String line="";
        TreeSet<String> season=new TreeSet<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while((line=br.readLine())!=null){
                String [] values=line.split(",");
                season.add(values[1]);
            }
            season.remove("season");
            System.out.println(season);
            }catch (IOException e){
            e.printStackTrace();
        }

        }
    }

