import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.StringBuilder;
import java.nio.file.Files;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


class LoaderWords{
    Map <String, Integer> entriesWords;
    ArrayList<String> strings;
    int nWords;

    LoaderWords (String inputFileName) throws IOException {
        strings = new ArrayList(); 
        entriesWords = new LinkedHashMap<>();
        nWords = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String str;            
            while ((str = reader.readLine()) != null){
                strings.add(str);
                StringTokenizer tokenizer = new StringTokenizer(str); 
                while (tokenizer.hasMoreTokens()){
                   String currentWord = tokenizer.nextToken();
                   if (entriesWords.containsKey(currentWord))
                       entriesWords.put(currentWord, entriesWords.get(currentWord) + 1);
                   else{
                        entriesWords.put(currentWord,  1);
                        nWords += 1;
                   }
                } 
            }   
        } 
    }
    int getNWords(){
        return nWords;
    }
    String getStringByLineNumber(int lineNumber){
        return strings.get(lineNumber);
    }
    
    void printStringsByNumber(int... lineNumbers){
        for (int j : lineNumbers){
            if (j >= 0 && j < strings.size())
            System.out.println("String " + j + ": "+getStringByLineNumber(j));
        }
    }

    void printWordsByOrderLength(){
        System.out.println("WORDS BY LENGTHS");  
        PriorityQueue <String> words = new PriorityQueue<String>(entriesWords.keySet().size(), Comparator.comparing(s -> -s.length()));
        words.addAll(entriesWords.keySet());
        while (!words.isEmpty()){
            System.out.println(words.poll());
        }
    }

    void printWordsByFrequencies(){
        System.out.println("WORDS BY FREQUENCIES");  
        PriorityQueue <Map.Entry<String,Integer>> words = 
            new PriorityQueue<>(entriesWords.size(), Comparator.comparing(e -> -e.getValue()));
        words.addAll(entriesWords.entrySet());       
        words.forEach(System.out::println);
    }



    void printWordsByOrderTime(){
        System.out.println("WORDS BY TIME"); 
        entriesWords.keySet().forEach(System.out::println);        
    }
     /*Iterator<Map.Entry<String, Integer>> iter = entriesWords.entrySet().iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }*/



}



public class Lecture2Collections {    
    public static void main(String[] args) throws IOException {
        final String inputFileName = "D:\\input.txt";
        LoaderWords lw = new LoaderWords(inputFileName);
        System.out.println("Count of words: "+lw.getNWords());
        lw.printWordsByOrderLength();
        lw.printWordsByFrequencies();        
        lw.printWordsByOrderTime();
        lw.printStringsByNumber(3, 2, 0);
          
       
    }
}