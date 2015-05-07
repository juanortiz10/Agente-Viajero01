package agenteviajero01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public final class Model {
  StringBuilder sb = new StringBuilder();
  int counter=0;
  int idxtext = -1;
  int[][] array;
  ArrayList<ArrayList> matrix;
  ArrayList road = new ArrayList();
  
  //This method reads the file .txt
  public void readDocument(){
    BufferedReader br = null;
    
    try {
     
     String linea;
     
     br = new BufferedReader(new FileReader("gr17.tsp"));
     
     while ((linea= br.readLine()) != null) {
      if (linea.compareTo("EDGE_WEIGHT_SECTION")==0){
        idxtext = counter+1;
      }
      if (linea.compareTo("EOF")==0){
        break;
      }
      sb.append(linea+"\n");
      counter++;
    }
    
  } catch (Exception e) {
   e.printStackTrace();
 } finally {
   try {
    if (br != null)br.close();
  } catch (Exception ex) {
    ex.printStackTrace();
  }
  String[] values = sb.toString().split("\n");
  
  ArrayList<String> list = new ArrayList<>();
  
  for (int i = idxtext; i < values.length; i++) {
    list.add(values[i]);
  }
  
  matrix = new ArrayList<>();
  
  ArrayList<Integer> toinsert= new ArrayList<>();
  
  for (String string : list) {
    String[] temp=string.split("\\s+");
    
    for (String inner : temp) {
      if(inner.isEmpty())
        continue;
      Integer number = Integer.parseInt(inner);
      if(number==0){
        
        toinsert.add(number);
        matrix.add(toinsert);
        toinsert = new ArrayList<>();
      }else{
        toinsert.add(number);
      }
    }
  }
  
  for (int i = 0; i < matrix.size(); i++) {
    for (int j = 0; j < matrix.size(); j++) {
      if (j>=i){
        Integer number = (Integer)matrix.get(j).get(i);
        if(number!=0)
          matrix.get(i).add(number);
      }
    }
  }
  
  
}
}
  //This method saves the file's information in a matrix
public void generateArray(){
  array= new int[matrix.size()][matrix.size()];
  for(int i =0; i < matrix.size(); i++){
    for(int j =0; j <matrix.size(); j++){
      array[i][j]= (Integer)matrix.get(i).get(j);
    }
  }
      /*for(int i = 0; i < array.length; i++)
        {
      for(int j = 0; j <array.length; j++)
      {
         System.out.printf("%5d ", array[i][j]);
        }
      System.out.println();
         }
              */
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
