/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

import entities.City;
import entities.Routes;
import evolution.Breeding;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JComponent;
import javax.swing.JFrame;
import population.Population;
import static population.Population.pop_size;

/**
 *
 * @author keyur_donga
 */
public class TSP {
    
    public static final int genCount = 500;
    //public ArrayList<City> list = new ArrayList<City>(Arrays.asList(new City("AL",32.361538,-86.279118), new City("AK",58.301935,-134.419740), new City("AZ",33.448457,-112.073844), new City("AR",34.736009,-92.331122), new City("CA",38.555605,-121.468926), new City("CO",39.7391667,-104.984167), new City("CT",41.767,-72.677), new City("DE",39.161921,-75.526755), new City("FL",30.4518,-84.27277), new City("GA",33.76,-84.39), new City("HI",21.30895,-157.826182), new City("ID",43.613739,-116.237651), new City("IL",39.783250,-89.650373), new City("IN",39.790942,-86.147685), new City("IA",41.590939,-93.620866), new City("KS",39.04,-95.69), new City("KY",38.197274,-84.86311), new City("LA",30.45809,-91.140229), new City("ME",44.323535,-69.765261), new City("MD",38.972945,-76.501157), new City("MA",42.2352,-71.0275), new City("MI",42.7335,-84.5467), new City("MN",44.95,-93.094), new City("MS",32.320,-90.207), new City("MO",38.572954,-92.189283), new City("MT",46.595805,-112.027031), new City("NE",40.809868,-96.675345), new City("NV",39.160949,-119.753877), new City("NH",43.220093,-71.549127), new City("NJ",40.221741,-74.756138), new City("NM",35.667231,-105.964575), new City("NY",42.659829,-73.781339), new City("NC",35.771,-78.638), new City("ND",48.813343,-100.779004), new City("OH",39.962245,-83.000647), new City("OK",35.482309,-97.534994), new City("OR",44.931109,-123.029159), new City("PA",40.269789,-76.875613), new City("RI",41.82355,-71.422132), new City("SC",34.000,-81.035), new City("SD",44.367966,-100.336378), new City("TN",36.165,-86.784), new City("TX",30.266667,-97.75), new City("UT",40.7547,-111.892622), new City("VT",44.26639,-72.57194), new City("VA",37.54,-77.46), new City("WA",47.042418,-122.893077), new City("WV",38.349497,-81.633294), new City("WI",43.074722,-89.384444), new City("WY",41.145548,-104.802042)));
    public ArrayList<City> list = new ArrayList<City>(Arrays.asList(new City("AL",32.361538,-86.279118), new City("AK",58.301935,-134.419740), new City("AZ",33.448457,-112.073844), new City("AR",34.736009,-92.331122), new City("CA",38.555605,-121.468926), new City("CO",39.7391667,-104.984167), new City("CT",41.767,-72.677), new City("DE",39.161921,-75.526755), new City("FL",30.4518,-84.27277), new City("GA",33.76,-84.39), new City("HI",21.30895,-157.826182), new City("ID",43.613739,-116.237651), new City("IL",39.783250,-89.650373), new City("IN",39.790942,-86.147685), new City("IA",41.590939,-93.620866), new City("KS",39.04,-95.69), new City("KY",38.197274,-84.86311), new City("LA",30.45809,-91.140229), new City("ME",44.323535,-69.765261), new City("MD",38.972945,-76.501157), new City("MA",42.2352,-71.0275), new City("MI",42.7335,-84.5467), new City("MN",44.95,-93.094), new City("MS",32.320,-90.207), new City("MO",38.572954,-92.189283), new City("MT",46.595805,-112.027031), new City("NE",40.809868,-96.675345), new City("NV",39.160949,-119.753877), new City("NH",43.220093,-71.549127), new City("NJ",40.221741,-74.756138), new City("NM",35.667231,-105.964575), new City("NY",42.659829,-73.781339), new City("NC",35.771,-78.638), new City("ND",48.813343,-100.779004), new City("OH",39.962245,-83.000647), new City("OK",35.482309,-97.534994)));
    //public ArrayList<City> list = new ArrayList<City>(Arrays.asList(new City("AL",32.361538,-86.279118), new City("AK",58.301935,-134.419740), new City("AZ",33.448457,-112.073844), new City("AR",34.736009,-92.331122), new City("CA",38.555605,-121.468926), new City("CO",39.7391667,-104.984167)));
    
    public static void main(String[] args) {
        
        TSP tsp = new TSP();
        Population pop = new Population(tsp.list);
        Breeding br = new Breeding(tsp.list);
        Routes bestRoute = pop.getRouteList().get(0);
        int bestRouteGen = 1;       
        tsp.drawRoute(bestRoute,"First");
        for(int i = 1;i<=genCount;i++){
             
            pop = br.evolveIndividual(pop);
            
            if(i % 20 == 0 || i == 1){
                System.out.println("Generation "+ i);
                pop.sortRouteList();
                tsp.showPopulation(pop,false);
                System.out.println("");  
            }
            
            if((bestRoute.getFitnessScore().doubleValue() + 0.0001) < pop.getRouteList().get(0).getFitnessScore().doubleValue()){
                bestRoute = pop.getRouteList().get(0);
                bestRouteGen = genCount;
            }
        }
        tsp.result(bestRoute,bestRouteGen);        
        tsp.drawRoute(bestRoute,"Best");
        
    }
    
    public void result(Routes br, int brg){
        System.out.println("\nBest Route from "+ brg +" Generations "); 
        System.out.print(br.toString() + " - Fitness: ");
        System.out.print(br.getFitnessScore() + " - Distance: ");
        System.out.print(br.totalDistance()); 
        System.out.println("");
    }
    
    public void showPopulation(Population pop, Boolean result){
        int cnt = 0 ;
        System.out.println("Top 5 routes from this Generation");
        for(Routes r : pop.getRouteList()){
            if(cnt > 5){
                break;
            }
            System.out.print(r.toString() + " - Fitness: ");
            System.out.print(r.getFitnessScore() + " - Distance: ");
            System.out.print(r.totalDistance());
            System.out.println("");
            cnt++;
        }
        System.out.println("");
    }
    
    Graphics2D graph;
        
    private void drawRoute(Routes br,String rNum) {
        
        JComponent jComponent;
        
        jComponent = new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                graph = (Graphics2D) g.create();
                //graph.translate(getWidth() - 1500 , getHeight() + 2100 );
                graph.translate(getWidth() - 2550 , getHeight() - 580);
                graph.setStroke(new BasicStroke(5));
                graph.rotate(Math.toRadians(80));
                graph.scale(0.65, 0.65);
                ArrayList<City> list = br.getCityList();
                City firstCity = list.get(0);
                City lastCity = list.get(list.size()-1);
                
                for (int i=0;i<list.size()-2; i++) {                    
                    City curr = list.get(i);
                    City curr1 = list.get(i+1);
                    City curr2 = list.get(i+2);

                    int x1 = graphPoint(curr.getLat());
                    int y1 = graphPoint(curr.getLon());
                    int x2 = graphPoint(curr1.getLat());
                    int y2 = graphPoint(curr1.getLon());
                    int x3 = graphPoint(curr2.getLat());
                    int y3 = graphPoint(curr2.getLon());
                    
                    //System.out.println("\n" + x1 + "," + y1 + "," + x2 + "," + y2 + "," + x3 + "," + y3);
                    
                    if(i % 2 == 0){
                        graph.setColor(Color.RED);
                    }else{
                        graph.setColor(Color.BLACK);
                    }
                    graph.drawLine(x1, y1, x2, y2);
                    graph.drawLine(x2, y2, x3, y3);
                }
                int x1 = graphPoint(firstCity.getLat());
                int y1 = graphPoint(firstCity.getLon());
                int x2 = graphPoint(lastCity.getLat());
                int y2 = graphPoint(lastCity.getLon());
                graph.drawLine(x1, y1, x2, y2);
                graph.dispose();
            }
        };

        JFrame frame = new JFrame(rNum+" route");
        frame.add(jComponent);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);        
    
    }
    
    public int graphPoint(Double val){
        int mf = (int) (1400 * val);
        return mf;
    }
    
}
