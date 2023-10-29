import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SpellDictionary implements SpellingOperations{

    public HashSet<String> dictionary;
    
    public SpellDictionary() throws FileNotFoundException{
        dictionary = new HashSet<String>();
        File file = new File("words.txt");
        Scanner scanner = new Scanner(file);
        String word;

        while(scanner.hasNextLine()){
            word = scanner.nextLine();
            dictionary.add(word);
        }

        scanner.close();
        System.out.println(dictionary);
    }

    public void ReadFile(File file) throws FileNotFoundException{
        Scanner scanner = new Scanner(file);
        String word;

        while(scanner.hasNextLine()){
            word = scanner.next();
            dictionary.add(word);
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
        StringBuilder alphabet = new StringBuilder("abcdefghijklmnopqrstuvwxyz");
        ArrayList<StringBuilder> possible = new ArrayList<StringBuilder>();
        /* to delete letter from original word */
        for(int i = 0; i < sb.length(); i++){
            /* slice or delete substring */
            StringBuilder s = sb.delete(i, i+1);
            if(dictionary.contains(s)){
                /* THIS IS SUCH A DUB I ACTUALLY UNDERSTAND IT THANKS PROF RANDO */
                possible.add(s);
            }
        }
        /* to add a letter to original word ADD ALPHABET LOOP!!! >:( */
        for(int i = 0; i < sb.length(); i++){
            StringBuilder s = sb.insert(i, i+1);
            if(dictionary.contains(s)){
                possible.add(s);
            }
        }
        /*loop through input string */
        for(int i = 0; i < sb.length(); i++){
            /* loop through alphabet string */
            for(int j = 0; j < alphabet.length(); j++){
                /* replace value of i with letter from alphabet at j */
                StringBuilder s = sb.replace(i, i+1, String.valueOf(j));
                if(dictionary.contains(s)){
                    possible.add(s);
                }
            }
        }
        /* loop through input string */
        for(int i = 0; i < sb.length(); i++){
            /* character at i */
            char a = sb.charAt(i);
            /* character before i */
            char b = sb.charAt(i-1);

            /* set character at i equal to value of character b */
            sb.setCharAt(i, b);
            /* set character before i equal to value of character a */
            sb.setCharAt(i-1, a);

            /* if dictionary contains the new string */
            if(dictionary.contains(sb)){
                /* add new string to possible array list */
                possible.add(sb);
            }
        }
        /* loop through input string */
        for(int i = 0; i < sb.length(); i++){
            /* create new StringBuilder adding a space to the word */
            StringBuilder s = sb.replace(i, i+1, " ");
            /* if dictionary contains new StringBuilder */
            if(dictionary.contains(s)){
                /* add new string to possible array list */
                possible.add(s);
            }
        }
        System.out.println(possible);
        return possible;
    }
    

}
