import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Run {
    Scanner scanner = new Scanner(System.in);
    float totalChars;

    Run(){

        System.out.print("Type today's Wordle number: ");
        int todaysNumber = scanner.nextInt();
        String[] words = sortedWords(todaysNumber);
        totalChars = words.length*5;
        HashMap<Character, Integer> sortedChars = sortChars(words);

        String word = getMostProbableWord(sortedChars, words);

        System.out.println(word);
    }

    public HashMap<Character, Integer> sortChars(String[] words){

        HashMap<Character, Integer> probChars = new HashMap<>();

        for (String s : words){
            for (char c : s.toCharArray()){

                if (!probChars.containsKey(c)){
                    probChars.put(c, 1);
                }
                else{
                    int num = probChars.get(c) + 1;
                    probChars.replace(c, num);
                }
            }
        }
        return probChars;
    }


    public String[] sortedWords(int startingIndex){
        String[] words = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("words.txt"));
            String str = reader.readLine();
            words = str.split(",");

        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] newArr = new String[words.length - startingIndex];
        for (int i = 0; i < words.length; i++){
            if (i < startingIndex){
                continue;
            }
            newArr[i - startingIndex] = words[i];
        }
        return newArr;
    }

    public String getMostProbableWord(HashMap<Character, Integer> map, String[] words){
        HashMap<String, Float> wordsWithProb = new HashMap<>();

        for (String s : words){
            float totalProb = 0;
            for (char c : s.toCharArray()){
                totalProb += (map.get(c) / totalChars) * 100;
            }
            wordsWithProb.put(s, totalProb);
        }

        List<Map.Entry<String, Float>> list = new LinkedList<>(wordsWithProb.entrySet());
        list.sort(Map.Entry.comparingByValue());

        System.out.println( list.get(list.size()-1).getKey());

        for (int i = list.size()-1; i > 0; i--){
            String st = list.get(i).getKey();
            if (checkDoubleLetter(st)){
                return st;
            }
        }

        return "";
    }

    public boolean checkDoubleLetter(String s){
        char[] chars = s.toCharArray();
        for (int j = 0; j < chars.length; j++){
            for (int k = j+1; k < chars.length; k++){
                if (chars[j] == chars[k]){
                    return false;
                }
            }
        }
        return true;
    }


}
