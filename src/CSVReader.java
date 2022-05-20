import java.io.*;
import java.util.HashMap;
import java.util.TreeSet;

public class CSVReader {
    public static void main (String [] args){
        String path="/home/sreedhar/IdeaProjects/Project1/src/matches.csv";
        String line="";
        HashMap <String,Integer> requiredMap=new HashMap<String,Integer>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while((line=br.readLine())!=null){
                String [] values=line.split(",");
                int count= requiredMap.containsKey(values[1])? requiredMap.get(values[1]):0;
                requiredMap.put(values[1],count+1 );
            }
            requiredMap.remove("season");
            System.out.println(requiredMap);

            }catch (IOException e){
            e.printStackTrace();
        }

        }
    }

