/* Andrea Marquez Tavera
 * Dr. Steinberg
 * COP3503 Spring 2024
 * Programming Assignment 5
 */

import java.io.*;
import java.util.*;

public class Railroad {// 1. Create a public class called Railroad

    // variables
    public int tracksTotal;
    public String name;

    private List<String[]> edges = new ArrayList<>();
    private Map<String, String> hash = new HashMap<>();

    public Railroad(int tracksTotal, String name) {// 2. The class constructor for Railroad takes two parameters
        this.tracksTotal = tracksTotal;// 2.a. he first parameter is a primitive int that represents the number of tracks you are
        //given in designing the railroad system. This is the total number of lines in each
        //respective text file.

        this.name = name;// 2.b. The second parameter is a String object that contains the name of a text file with
        //all possible tracks the company has provided.

        // read file
        try {
            Scanner scanner = new Scanner(new File(name));
            // go through each line
            for(int i = 0; i < tracksTotal; i++) {
                String tmpLine = scanner.nextLine();
                // split per white space
                String[] entries = tmpLine.split(" ");

                // add to edge list
                edges.add(entries);
            }
            scanner.close();
        } catch (Exception e) {// otherwise print error
            e.printStackTrace();
        }
    }

    public String buildRailroad() {
        // use Kruskal's algo

        // sort by cost/weight
        Collections.sort(edges, Comparator.comparingInt(edges -> Integer.parseInt(edges[2])));// found online lol
        
        // used to concot. return string
        StringBuilder returnThis = new StringBuilder();
        
        // variables
        int totalCost = 0;
        int compare = 0;

        for (String[] edges : edges) {// go through array of edges

            // sort lexicographically
            compare = edges[0].compareToIgnoreCase(edges[1]);
            if (compare > 0) {// if not in order, reorder
                String temp = edges[0];
                edges[0] = edges[1];
                edges[1] = temp;
            }
            
            // check if in hasmap
            String hash1 = exists(hash, edges[0]);
            String hash2 = exists(hash, edges[1]);
            
            if (!hash1.equals(hash2)) {
                // first part of string 
                returnThis.append(edges[0]).append("---").append(edges[1]).append("	$").append(Integer.parseInt(edges[2])).append(System.getProperty("line.separator"));
            
                // add total cost
                totalCost += Integer.parseInt(edges[2]);

                // perform union
                hash.put(hash1, hash2);
            }
        }

        return returnThis.toString() + "The cost of the railroad is $" + totalCost + ".";
    }

    // taken from class code, recommended from TA
    private String exists(Map<String, String> hash, String edge) {
        if (!hash.containsKey(edge)) {
            hash.put(edge, edge);
            return edge;
        }

        if (!edge.equalsIgnoreCase(hash.get(edge))) {
            hash.put(edge, exists(hash, hash.get(edge)));
        }

        return hash.get(edge);
    }
}
// happy last program to grade <3 have a good summer