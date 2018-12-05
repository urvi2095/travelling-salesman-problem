/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TSPTest;

import entities.City;
import entities.Routes;
import evolution.Breeding;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import population.Population;
/**
 *
 * @author keyur_donga
 */
public class TestCases {
    ArrayList<City> list = new ArrayList<>(Arrays.asList(new City("AL",32.361538,-86.279118), new City("AK",58.301935,-134.419740), new City("AZ",33.448457,-112.073844), new City("AR",34.736009,-92.331122)));
        
    @Test
    public void distanceTC(){
        
        City ca = list.get(0);
        City cb = list.get(1);
        City cc = list.get(2);
        
        assertEquals(4589.226495992077, ca.CalculateDistance(cb),0.1);
        assertEquals(3226.7175386042163, cb.CalculateDistance(cc),0.1);
    }
    
    @Test
    public void fitnessTC(){
        Routes route = new Routes(list);        
        assertEquals(6.20,route.getFitnessScore(),2.0);
    }
    
    @Test
    public void routesDistanceTC(){
        Routes route = new Routes(list); 
        assertEquals(10200,route.totalDistance(),2800);
    }
    
    @Test
    public void bestRouteTC(){
        Population pop = new Population(4, list); 
        pop.sortRouteList();
        assertEquals(7.8000,pop.getRouteList().get(0).getFitnessScore(),0.3);  
    }
    
    @Test
    public void crossbreedPopTC(){
        
        Population pop = new Population(4, list);
        Breeding br = new Breeding(list);
        Population crossOver = br.crossbreedPop(pop);
                
        assertEquals(crossOver.hashCode(), pop.hashCode(),0.1E10);
    }
    
    
}
