import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* Creates a HashSet of words to create the "dictionary" and creates methods to find near misses for user input */
public class SpellDictionary implements SpellingOperations{

    /* The HashSet full of words from the "words.txt" file */
    public static HashSet<String> dictionary;
    
    /* Creates a new dictionary HashSet and adds words to it from the text file */
    public SpellDictionary() throws FileNotFoundException{
        /* Creates the new dictionary */
        dictionary = new HashSet<String>();
        /* Creates a new file from the original one */
        File file = new File("words.txt");
        /* Creates a scanner to read the file */
        Scanner scanner = new Scanner(file);
        /* A string representing each word in the text file that will be added to the dictionary */
        String word;

        /* While the file has a next line, scan the words, convert them to lowercase, and add to the dictionary */
        while(scanner.hasNextLine()){
            /* The unedited words from the file */
            String uppercase_words = scanner.nextLine();
            /* The words from the file edited to all lowercase */
            word = uppercase_words.toLowerCase();
            /* Adds the word to the dictionary */
            dictionary.add(word);
        }

        scanner.close();
    }

    /**
     * Reads the text file full of words and adds them to the dictionary HashSet
     * @param file      The file being scanned
     * @throws FileNotFoundException    In case the file cannot be found
     */
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

  /** Checks the dictionary for a specific word and states if it is there or not
   *  @param query the word to check
   *  @return true if the query word is in the dictionary.
   */
    @Override
    public boolean isListed(String query) {
        if(dictionary.contains(query)){
            return true;
        } else{
            return false;
        }
    }

  /** Takes in the user input and checks the dictionary to see if the input would be in there after one edit
   *  @param query the word to check
   *  @return a list of all valid words that are one edit away from the query
   */
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
