package agenteviajero01;

public class Main{    
    public static void main(String[] args) {
        Modelo md= new Modelo();
        md.readDocument();
        md.generateInformation();
        int firstRoad=md.generateFirstRoad();
    }
}
