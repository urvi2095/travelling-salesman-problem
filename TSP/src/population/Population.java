/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package population;

import entities.City;
import entities.Routes;
import evolution.Breeding;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author keyur_donga
 */
public class Population {
    
    public static final int pop_size = 150;    
    private ArrayList<Routes> routeList = new ArrayList<>(pop_size);
    
    public Population(ArrayList<City> cl){
        for(int i=0 ;i<pop_size;i++){
            routeList.add(new Routes(cl));
        }
    }
    
    public Population(int size, ArrayList<City> cl){
        for(int i=0 ;i<size;i++){
            routeList.add(new Routes(cl));
        }
        System.out.println(routeList.toString());
    }
    
    public Population(int size, Breeding br) {        
        for(int i=0 ;i<size;i++){
            routeList.add(new Routes(br.getFirstRoute()));
        }
    }
    
    public ArrayList<Routes> getRouteList() {
        return routeList;
    }

    public void setRouteList(ArrayList<Routes> routeList) {
        this.routeList = routeList;
    }
    
    public void sortRouteList(){
        routeList.sort((r1, r2) -> {           
            int flag = 0;
            if(r1.getFitnessScore() > r2.getFitnessScore())
                flag = -1;
            else if(r1.getFitnessScore() < r2.getFitnessScore())
                flag = 1;
            return flag;
        });
    }
}
