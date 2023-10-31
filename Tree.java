import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

/* An edited version of the SpellDictionary and SpellChecker classes */
public class Tree {

    /* A TreeSet representing all of the words in the dictionary */
    static TreeSet<String> dictionary;

    /**
     * Constructor for the Tree class that creates a new dictionary to be used in the classs
     * @throws FileNotFoundException    If the file cannot be found
     */
    public Tree() throws FileNotFoundException{
        /* TreeSet that contains the words in the dictionary */
        TreeSet<String> dictionary = new TreeSet<String>();
        /* The file that will be scanned (contents will be added to the TreeSet) */
        File file = new File("words.txt");
        /* New scanner to scan the file */
        Scanner scanner = new Scanner(file);
        /* The individual words in the file */
        String word;

        /* While the file has a next line, convert words to all lowercase and then add to dictionary */
        while(scanner.hasNextLine()){
            /* Takes in the words from the file */
            String uppercase_words = scanner.nextLine();
            /* Converts the words from the file to lowercase */
            word = uppercase_words.toLowerCase();
            /* Adds the word to the dictionary */
            dictionary.add(word);
        }
        scanner.close();
    }

    /**
     * Reads the text file and adds the contents to the dictionary
     * @param file      The file being scanned
     * @throws FileNotFoundException    If the file cannot be found
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

    /**
     * States if a specific string is in the dictionary
     * @param query     The string being tested
     * @return      Whether or not the string is in the dictionary
     */
    public boolean isListed(String query) {
        if(dictionary.contains(query)){
            return true;
        } else{
            return false;
        }
    }

    /**
     * Tests an incorrectly spelled string to see the potential words created by making one edit
     * @param query     The string being tested
     * @return      An ArrayList containing all of the potential correctly spelled words 
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

    public static void main(String[] args) throws FileNotFoundException{
        Timer timer = new Timer();
        timer.start();
        if(args.length != 0){
        /* if args exist, run this */
            for(int i = 0; i < args.length; i++){
                Tree instance = new Tree();
                if(!dictionary.contains(args[i])){
                    System.out.println("Not found: " + args[i]);
                    System.out.println("Suggestions: " + instance.nearMisses(args[i]));
                } 
                else{
                    System.out.println("'" + args[i] + "' is spelled correctly");
                }
            }
        } 
        else{
            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNextLine()){
                String currentLine = scanner.nextLine();
                String lowercase_line = currentLine.toLowerCase();
                String[] words = lowercase_line.split(" "); 
                Tree instance = new Tree(); 
                for(String a : words){
                    if(dictionary.contains(a));{
                        System.out.println("Suggestions: " + instance.nearMisses(a));
                    } if(dictionary.contains(a)){
                        continue;
                    }
                }
            } scanner.close();
        }
        timer.stop();
        System.out.println(timer.stop());
    }


}
