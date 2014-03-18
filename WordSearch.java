/*
  File: WordSearch.java
  
  Description: Inputs a word search from "hidden.txt" and outputs the answers into "found.txt"

*/

import java.util.*;
import java.io.*;

public class WordSearch
{
  public static String[] find(int numWords, int height, int width, String [] words, String [][] box) //method returns answers
  {
    int loc = 0;  //will count how much the words match the characters in the search
     
    String [] key = new String [numWords]; //create array for answers
    for(int i = 0; i < numWords; i++) //stores all "None"'s in the array
    {
      key[i] = "None";
    }
    
    for(int i = 0; i < numWords; i++) //goes through each word
    {
      loc = 0;
      for(int j = 0; j < height; j++) //goes through each letter to find a word that goes forwards
      {
        loc = 0;
        for(int k = 0; k < width; k++)
        {          
          if(box[j][k].charAt(0) != words[i].charAt(loc))
          {
            loc = 0;
          }
          if(box[j][k].charAt(0) == words[i].charAt(loc))
          {
            loc++;
          }          
          if(loc == words[i].length())
          {
            key[i] = words[i] + " " + (j+1) + " " + (k-(words[i].length()-1)+1);          
            break;
          }
        }        
      }  
           
      loc = 0;
      for(int j = 0; j < height; j++) //goes through each letter to find a word that goes backwards
      {
        loc = 0;
        for(int k = width - 1; k >= 0; k--)
        {          
          if(box[j][k].charAt(0) != words[i].charAt(loc))
          {
            loc = 0;
          }
          if(box[j][k].charAt(0) == words[i].charAt(loc))
          {
            loc++;
          }          
          if(loc == words[i].length())
          {
            key[i] = words[i] + " " + (j+1) + " " + (k+(words[i].length()));        
            break;
          }
        }        
      }  
      
      for(int k = 0; k < width; k++) //goes through each letter to find a word that goes down
      {
        loc = 0;
        for(int j = 0; j < height; j++)
        {          
          if(box[j][k].charAt(0) != words[i].charAt(loc))
          {
            loc = 0;
          }
          if(box[j][k].charAt(0) == words[i].charAt(loc))
          {
            loc++;
          }          
          if(loc == words[i].length())
          {
            key[i] = words[i] + " " + ((j - (words[i].length()-1)) + 1) + " " + (k+1);        
            break;
          }
        }        
      }  

      for(int k = 0; k < width; k++) //goes through each letter to find a word that goes up
      {
        loc = 0;
        for(int j = height - 1; j >= 0; j--)
        {          
          if(box[j][k].charAt(0) != words[i].charAt(loc))
          {
            loc = 0;
          }
          if(box[j][k].charAt(0) == words[i].charAt(loc))
          {
            loc++;
          }          
          if(loc == words[i].length())
          {
            key[i] = words[i] + " " + (j +(words[i].length())) + " " + (k+1);        
            break;
          }
        }        
      }  
    }
   
    for(int i = 0; i < numWords; i++) // goes through answers and if any didn't get filled in assigns 0's to it's location
    {     
      if(key[i] == "None")
      {
        key[i] = words[i] + " " + 0 + " " + 0;      
      }      
    }    
    return key;
  }
  
  public static void main(String [] args) throws IOException
  {
    File inFile = new File ("hidden.txt"); //find file
    Scanner input = new Scanner(inFile);
        
    int height = input.nextInt(); //getting the length of word searce
    int width = input.nextInt();  //getting the width of word search
    
    String [][] box = new String [height][width]; //array for all the letters
    
    for(int i = 0; i < height; i++) //putting all letters in search in an array
    {
      for(int j = 0; j < width; j++)
      {
        box[i][j] = input.next();       
      }      
    }
    
    int numWords = input.nextInt(); //finding amount of words to search for
    
    String [] words = new String [numWords]; //array that stores word
    String [] key = new String [numWords]; //array that will have the answers
        
    String garbage = input.nextLine(); //getting rid of new line character
    
    for(int i = 0; i < numWords; i++) //storing each word in array
    {
      words[i] = input.nextLine();      
    }
    input.close();
    
    key = find(numWords, height, width, words, box); //getting the final prompt in method find
    
    File outFile = new File("found.txt"); //opening/creating output file
    FileWriter fWriter = new FileWriter (outFile);
    PrintWriter pWriter = new PrintWriter (fWriter);
    
    for(int i = 0; i < numWords; i++) //prints out each part into output file
    {     
       pWriter.println(key[i]);     
    }
    pWriter.close();
  }
}