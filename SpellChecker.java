import java.util.Scanner;
import java.io.FileNotFoundException;



public class SpellChecker{

    public static void main(String[] args) throws FileNotFoundException{
        String word = args.toString();
        Scanner scanner = new Scanner(word);

        while(scanner.hasNext()){
            for(int i = 0; i < args.length; i++){
                SpellDictionary instance = new SpellDictionary();
                if(!SpellDictionary.dictionary.contains(word)){
                    System.out.println("Not found: " + word);
                    System.out.println("Suggestions: " + instance.nearMisses(word));
                } 
                else{
                    System.out.println("'" + word + "' is spelled correctly");
                }
                
            }
        } scanner.close();


    }

}