import java.io.FileNotFoundException;
import java.util.Scanner;

public class SpellChecker{

    public static void main(String[] args) throws FileNotFoundException{
        if(args.length != 0){
        /* if args exist, run this */
            for(int i = 0; i < args.length; i++){
                SpellDictionary instance = new SpellDictionary();
                if(!SpellDictionary.dictionary.contains(args[i])){
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
            SpellDictionary instance = new SpellDictionary();
            
            while(scanner.hasNextLine()){
                String currentLine = scanner.nextLine();
                String lowercase_line = currentLine.toLowerCase();
                String[] words = lowercase_line.split(" ");  
                for(String a : words){
                    if(!SpellDictionary.dictionary.contains(a));{
                        System.out.println("Suggestions: " + instance.nearMisses(a));
                    } if(SpellDictionary.dictionary.contains(a)){
                        continue;
                    }
                }
            } scanner.close();
        }
    }
}

 
    
        


