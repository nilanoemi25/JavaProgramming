/**
 * The SmartBand class extends WD class and is the model for SB.
 *
 * @author Noemi Lovei
 * @Version 1.0 12th May 2024
 *
 * */

package models;

import utils.Utilities;

import java.util.Objects;

public class SmartBand extends WearableDevice {

    private boolean heartRateMonitor;

    /**
     *SB Constructor, inherits from WD class and adds heartRateMonitor.
     *
     * @params
     * Inherited:
     *         size
     *         price
     *         manufacturerName
     *         material
     *         modelName
     *         id
     *
     * Additional:
     *         heartRateMonitor
     *
     * @return SmartBand - an instantiated SB
     * */

 public SmartBand(String size, double price, String manufacturerName, String material, String modelName, String id, boolean heartRateMonitor){
     super(size, price, manufacturerName, material, modelName, id);
     this.heartRateMonitor = heartRateMonitor;
 }

/**
 * Setters and Getters specific SB Class
 *
 * */
    public boolean isHeartRateMonitor() {
        return heartRateMonitor;
    }

    public void setHeartRateMonitor(boolean heartRateMonitor) {
        this.heartRateMonitor = heartRateMonitor;
    }

    /**
     * Gets the insurance premium by performing a mathematical calculation
     *
     * @param price - price given by user
     * @return the result of maths to two decimal places.
     *
     * */

    public double getInsurancePremium(double price){
        return Utilities.toTwoDecimalPlaces(price * 0.07);
    }
 /**
  * A statement that confirms the connection to internet.
  * @return String statement
  * */
    public String connectToInternet() {
         return "Connects to the internet via Companion App.";
    }

    /**
     * toString method of SB class that inherits from WD class.
     * It adds SB relevant details to the String, it chooses between hearrate monitor included/excluded.
     *
     * @return String with details of SB.
     *
     * */
    @Override
    public String toString() {
        String str = " SmartBand " + super.toString();
        double insurancePremium = getInsurancePremium(super.getPrice());

        if(heartRateMonitor){ str +=  " Includes Heart Rate Monitor. " ; }
        else { str += " No Heart Rate Monitor included. "; }

       return str += "Insurance Premium: " + insurancePremium + " " +  "Internet Connection: " +  connectToInternet();

    }

    /**
     * Equals Method checks that the object is equal passed in is equal to it.
     * @params Object o
     * @return boolean
     * */


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SmartBand smartBand = (SmartBand) o;
        return isHeartRateMonitor() == smartBand.isHeartRateMonitor();
    }

}
