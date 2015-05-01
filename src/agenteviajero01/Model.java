package agenteviajero01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public final class Model {
  StringBuilder sb = new StringBuilder();
  int counter=0;
  int[][] array;
  ArrayList road= new ArrayList();
  
  //This method reads the file .txt, then is saved in a String Builder
  public void readDocument(){
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
 
      try {
         archivo = new File ("../ejemplo.txt");
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
      array= new int[counter][counter];
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

  //This method is going to search the best next route 
  public void searchRoad(int number){
   int min=1000,value,stop=0;
   for(int i=0; i<array.length;i++){
       value=array[number][i];
       if(value<min && value!=0){
          min=value;
          stop=i;
       }
   }
      road.add(stop);
      for(int j=0; j<array.length; j++){
          for(int k=0;k<array.length; k++){
               if(k==stop)
                   array[j][k]=0;
          }
      }
  }
//This Method calls searchRoad method to asks about next roads until it has found all of them
  public void generateNextRoad(int firstRoad){
      road.add(firstRoad);
      for(int i=0; i<counter;i++){
      searchRoad(firstRoad);
      }
      road.remove(counter);
      road.add(firstRoad);
  }
}
