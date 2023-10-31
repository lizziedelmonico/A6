import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SpellDictionary implements SpellingOperations{

    public static HashSet<String> dictionary;
    
    public SpellDictionary() throws FileNotFoundException{
        dictionary = new HashSet<String>();
        File file = new File("words.txt");
        Scanner scanner = new Scanner(file);
        String word;

        while(scanner.hasNextLine()){
            String uppercase_words = scanner.nextLine();
            word = uppercase_words.toLowerCase();
            dictionary.add(word);
        }

        scanner.close();
    }

    public void ReadFile(File file) throws FileNotFoundException{
        Scanner scanner = new Scanner(file);
        String word;

        while(scanner.hasNextLine()){
            word = scanner.next();
            String lowercase_word = word.toLowerCase();
            dictionary.add(lowercase_word);
        }

        scanner.close();
        
    }

    @Override
    public boolean isListed(String query) {
        if(dictionary.contains(query)){
            return true;
        } else{
            return false;
        }
    }

    public ArrayList<StringBuilder> nearMisses(String query) {
        String query_lowercase = query.toLowerCase();
        StringBuilder sb = new StringBuilder(query_lowercase);
        ArrayList<StringBuilder> possible = new ArrayList<StringBuilder>();
        /* to delete letter from original word */
        for(int i = 0; i <= sb.length(); i++){
            /* slice or delete substring */
            sb.delete(i, i+1);
            if(dictionary.contains(sb)){
                /* THIS IS SUCH A DUB I ACTUALLY UNDERSTAND IT THANKS PROF RANDO */
                possible.add(sb);
            }
            sb = new StringBuilder(query_lowercase);
        }
        /* to add a letter to original word ADD ALPHABET LOOP!!! :D */
        for(int i = 0; i <= sb.length(); i++){
            for(char j = 'a'; j <= 'z'; j++){
                 sb.insert(i, j);
                 String word = sb.toString();
                 if(dictionary.contains(word)){
                     possible.add(sb); 
                 }
                sb = new StringBuilder(query_lowercase);
             }
            
        }
        /*loop through input string */
        for(int i = 0; i < sb.length(); i++){
            /* loop through alphabet string */
            for(char j = 'a'; j <= 'z'; j++){
                /* replace value of i with letter from alphabet at j */
                sb.replace(i, i+1, String.valueOf(j));
                String word = sb.toString();
                if(dictionary.contains(word)){
                    possible.add(sb);
                }
                sb = new StringBuilder(query_lowercase);
            } 
        }
        /* loop through input string */
        for(int i = 1; i <= sb.length()-1; i++){
            /* character at i */
            char a = sb.charAt(i);
            /* character before i */
            char b = sb.charAt(i-1);

            /* set character at i equal to value of character b */
            sb.setCharAt(i, b);
            /* set character before i equal to value of character a */
            sb.setCharAt(i-1, a);

            String word = sb.toString();
            /* if dictionary contains the new string */
            if(dictionary.contains(word)){
                /* add new string to possible array list */
                possible.add(sb);
            }
            sb = new StringBuilder(query_lowercase);
        }
        System.out.println(possible);
        System.out.println(sb);
        /* loop through input string */
        for(int i = 0; i <= sb.length(); i++){
            /* create new StringBuilder adding a space to the word */
            StringBuilder s = sb.replace(i, i+1, " ");
            /* if dictionary contains new StringBuilder */
            if((dictionary.contains(s))){
                /* add new string to possible array list */
                possible.add(sb);
            }
            sb = new StringBuilder(query_lowercase);
        }
        return possible;
    }
    

}
