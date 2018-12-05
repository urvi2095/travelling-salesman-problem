/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author keyur_donga
 */
public class City {
    
    private String name;
    private Double lon;           //coordinate on longitude
    private Double lat;           //coordinate on latitude    
    
    private static final int earth_radius = 6371;         // Approx Earth radius
    private static final double dtr = 0.0174533;          // Degree to Radian

    public City(String name, Double x, Double y){
        this.name = name;
        this.lat = x * dtr;     
        this.lon = y * dtr;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public double CalculateDistance(City city){
        double deltaLon = this.lon - city.lon;
        double deltaLat = this.lat - city.lat;
        
        double a = haversin(deltaLat) + Math.cos(this.lat) * Math.cos(city.lat) * haversin(deltaLon);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return c * earth_radius;
    }
    
    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }
    
    public String toString(){
        return name;
    }
}
