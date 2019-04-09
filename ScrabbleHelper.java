/**
 * @author ikramgabiyev
 * *************
 * ANAGRAM GAME
 * *************
 */
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;
import java.util.Scanner;
import java.io.File;

public class ScrabbleHelper
{
    //my hashmap data structure
    private static HashMap<String, Vector<String>> hMap = new HashMap<String, Vector<String>>();
    public static void main(String[] args)
    {
        //the file with list of words
        File wordList = new File(args[0]);
        try
        {
            //scanner of the wordList file
            Scanner wordListScanner = new Scanner(wordList);
            
            //while there is a next line in the file
            while(wordListScanner.hasNextLine())
            {
                //take that next line string
                String readWord = wordListScanner.nextLine(); 
                // the key of hashmap - word sorted alphabetically
                String keyWord = sortWord(readWord);
                //modifying the hashmap
                modifyMap(keyWord, readWord);
            }
            wordListScanner.close();
            //prompting my user
            promptUser();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Could not read the file!!!");
        }
        


    }

    /**
     * return word sorting it
     * in alphabetical order
     * @param word
     * @return
     */
    private static String sortWord(String word)
    {
        
        //convert it to a char array
        char[] wordArr = word.toCharArray();
        //sort it alphabetically
        Arrays.sort(wordArr);
        //convert it back to string
        String sortedWord = new String(wordArr);

        return sortedWord;
    }


    /**
     * take the key and word
     * and modify the hashmap accordingly
     * @param keyWord
     * @param readWord
     */
    private static void modifyMap(String keyWord, String readWord)
    {
        //if such a key exists in hashmap
        if(hMap.containsKey(keyWord))
        {
            //then add the read word to its value - Vector
            hMap.get(keyWord).add(readWord);
        }
        else 
        {
            //create a new vector
            Vector<String> anagramList = new Vector<String>();
            //the the read word to it
            anagramList.add(readWord);
            //put the key and anagram list to hashmap
            hMap.put(keyWord, anagramList);
        }
    }
    
    /**
     * creates an interaction with user
     * through terminal
     */
    private static void promptUser() 
    {
        boolean stopGame = false;

        //terminal scanner
        Scanner terminalScan = new Scanner(System.in);

        while(stopGame == false)
        {
            System.out.println("Enter your letters:");
            
            //the word user wants to find anagrams for
            String inputWord = terminalScan.next();
            //sort the input in aplhabetical order
            String inputKey = sortWord(inputWord);

            //if such a key does not exist => continue
            if(!hMap.containsKey(inputKey))
            {
                System.out.println("No anagrams exist for these letters");
                continue;
            }

            //get the accosiated value in hashmap as a Vector
            Vector<String> anagramsList = hMap.get(inputKey);
            
            //print all anagrams
            for(String anagram : anagramsList)
            {
                System.out.print(anagram + " ");
            }

            //ask for another service
            System.out.println();
            System.out.println("Another?");
            
            String response = terminalScan.next();
            if(response.equals("no")){
                stopGame = true;
            }
            else if( !( response.equals("yes") ) ) 
            {
                System.out.println("Sorry did not get it?");
            }
        }

        terminalScan.close();
    }
}