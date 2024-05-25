/**
 * The SmartWatch class extends WD class and is the model for SW.
 *
 * @author Noemi Lovei
 * @Version 1.0 12th May 2024
 *
 * */


package models;

import utils.DisplayTypeUtility;
import utils.Utilities;

import java.util.Objects;

public class SmartWatch extends WearableDevice {

    private String displayType = "LCD";

    /**
     *SW Constructor, inherits from WD class and adds displayType.
     *
     * @params
     * Inherited:
     *         size
     *         price
     *         manufacturerName
     *         material
     *         modelName
     *         id
     *Additional:
     *         displayType
     *
     * @return SmartWatch - instantiated SmartWatch
     * */
  public SmartWatch(String size, double price, String manufacturerName, String material, String modelName, String id, String displayType){
        super(size, price, manufacturerName, material, modelName, id);
        if (DisplayTypeUtility.isValidDisplayType(displayType)) {
            this.displayType = displayType;
        }

    }

    /**
     * Setters and Getters specific for SW Class
     *Validates the displayType against a Utility Class.
     * */
    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        if (DisplayTypeUtility.isValidDisplayType(displayType)) {
            this.displayType = displayType;
        }
    }

    /**
     * Gets the insurance premium by performing a mathematical calculation
     *
     * @param price - price given by user
     * @return the result of maths to two decimal places.
     *
     * */
    @Override
    public double getInsurancePremium(double price) {
        return Utilities.toTwoDecimalPlaces(price * 0.06);
    }

    /**
     * A statement that confirms the connection to internet.
     * @return String statement
     * */
    public String connectToInternet(){
        return " Connects to the internet via bluetooth. ";
    }

    /**
     * toString method of SW class that inherits from WD class.
     * It adds SW relevant details to the String.
     *
     * @return String with details of SW.
     *
     * */

    @Override
    public String toString() {
        String str = " SmartWatch " + super.toString();
        return str + " Display Type: " +  displayType + getInsurancePremium(getPrice()) + connectToInternet();
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
        SmartWatch that = (SmartWatch) o;
        return Objects.equals(getDisplayType(), that.getDisplayType());
    }

}
