/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evolution;

import entities.City;
import entities.Routes;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import population.Population;

/**
 *
 * @author keyur_donga
 */
public class Breeding {
    
    public static final double mutationRatio = 0.15;
    public static final int tournament_size = 50;
    public static final int top_route = 1;
    
    public ArrayList<City> firstRoute = null;    
    
    public Breeding(ArrayList<City> route){
        this.firstRoute = route;
    }

    public ArrayList<City> getFirstRoute() {
        return firstRoute;
    }

    public void setFirstRoute(ArrayList<City> firstRoute) {
        this.firstRoute = firstRoute;
    }
    
    public Population crossbreedPop(Population pop){
        Population cbp = new Population(pop.getRouteList().size(),this);
        for(int i=0;i<top_route;i++){
            cbp.getRouteList().set(i, pop.getRouteList().get(i));
        }
        for(int i = top_route;i<cbp.getRouteList().size();i++){
            Routes r1 = tournamentPop(pop).getRouteList().get(0);
            Routes r2 = tournamentPop(pop).getRouteList().get(0);
            if (Math.random() < 0.5) {                
                cbp.getRouteList().set(i, crossbreedRoute(r1, r2));
            }else{
                cbp.getRouteList().set(i, crossbreedRoute(r2, r1));                
            }
        }
        return cbp;
    }
    
    public Population mutatePop(Population pop){
        for(Routes r:pop.getRouteList()){
            if(pop.getRouteList().indexOf(r) >= top_route){
                mutateRoute(r);
            }
        }
        return pop;
    }
    
    public Population evolveIndividual(Population pop){
        
        Population cpy1 = pop; 
        Population cpy2 = pop;         
        
        CompletableFuture<Population> p1 = parallelProcess(cpy1);
        CompletableFuture<Population> p2 = parallelProcess(cpy2);
        
        CompletableFuture<Population> parallelProcess = p1.
                    thenCombine(p2, (pop1, pop2) -> {
                        pop1.sortRouteList();
                        pop2.sortRouteList();
                        
                        Population combinedPop = new Population(pop1.getRouteList().size(),this);
                        int num = 0;
                        for(int i=0;i<Math.floor(pop1.getRouteList().size()/2);i++){
                            combinedPop.getRouteList().set(num++, pop1.getRouteList().get(i));
                            combinedPop.getRouteList().set(num++, pop2.getRouteList().get(i));
                        }                        
                        return combinedPop;
                    });
        return parallelProcess.join();       
    }
    
    private CompletableFuture<Population> parallelProcess(Population pop) {
        return CompletableFuture.supplyAsync(
            () -> {
                return mutatePop(crossbreedPop(pop));
            }
        );
    }
    
    public Population tournamentPop(Population pop){
        Population tPop = new Population(tournament_size, this);
        for(int i=0;i<tournament_size;i++){
            int rNum = (int) (pop.getRouteList().size() * Math.random());
            tPop.getRouteList().set(i, pop.getRouteList().get(rNum));
        }
        tPop.sortRouteList();
        return tPop;
    }
    
    public Routes crossbreedRoute(Routes r1, Routes r2){
        Routes cbr = new Routes(this);
        int len = cbr.getCityList().size()/2;
        for(int i=1;i<=len;i++){
            cbr.getCityList().set(i, r1.getCityList().get(i));
        }        
        for(City c: r2.getCityList()){
            if(!cbr.getCityList().contains(c)){
                for (int i = 0; i < r2.getCityList().size(); i++) {
                    if (cbr.getCityList().get(i) == null) {
                        cbr.getCityList().set(i, c);
                        break;
                    }
                }
            }
        }
        return cbr;
    }
    
    public Routes mutateRoute(Routes route){
        for(City c: route.getCityList()){
            if(Math.random() < mutationRatio){
                int rNum = (int) (Math.random() * route.getCityList().size());
                City rCity = route.getCityList().get(rNum);
                route.getCityList().set(route.getCityList().indexOf(c), rCity);
                route.getCityList().set(rNum, c);                
            }
        }
        return route;
    }
    
    
}
