package agenteviajero01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public final class Modelo {
  StringBuilder sb = new StringBuilder();
   int counter=0;
 
  //This method reads the file .txt, then is saved in a String Builder
  public void readDocument(){
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
 
      try {
         archivo = new File ("/home/juan/Escritorio/ejemplo.txt");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
         String linea;
      
         while((linea=br.readLine())!=null){
            sb.append(linea+" ");
            counter++;
         }
      }
      catch(Exception e){
      }finally{
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){}
      }
  }
  //This method saves the file's information in a matrix
  public void generateInformation(){
      int j=0,k=0;
      int[][] array = new int[counter][counter];
      String[] lines = sb.toString().split("\\s");
        for(int i=0; i<lines.length; i++){
            if((i+1)%6==0){
               array[j][k]=Integer.parseInt(lines[i]);
               j++;  
               k=0;
            }else{
              array[j][k]=Integer.parseInt(lines[i]);
              k++;
            }
        }
    }
  
  //This method chooses the first road which is going to start the heuristic
  public int generateFirstRoad(){
      return (0 + (int)(Math.random()*counter)); 
  }


//This Method is the brain of the program which is able to select the next road
  public void generateNextRoad(int lastRoad){
      
  }
}
