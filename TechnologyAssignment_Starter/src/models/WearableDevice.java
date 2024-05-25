/**
 * The Wearable device is an abstract class from which SB and SW will inherit.
 *
 * @author Noemi Lovei
 * @Version 1.0 12th May 2024
 *
 * */


package models;

import utils.Utilities;
import utils.ManufacturerNameUtility;

import java.util.Objects;

public  abstract class WearableDevice {

    private String size;
    private double price = 20;
    private String manufacturerName;
    private String material;
    private String modelName;
    private String id = "unknown";

    /**
     * Constructs the WD class with validation,
     * material must be less than 20 characters, size less than 10, checks that ManuName is valid from Utilities class,
     * ensures id is max 10 in length, ensures modelName is max 30 length, and price is at least 20.
     *
     * @params size, price, manufacturerName, material, modelName, id
     * @returns WearableDevice
     *
     * */
    WearableDevice(String size, double price,String manufacturerName, String material, String modelName, String id){
        if(material.length() < 20){
            this.material = material;
        }
        else {this.material = Utilities.truncateString(material, 20);}

        if(size.length() < 10){
            this.size = size;
        }
        else{ this.size = Utilities.truncateString(size,10); }

        if(ManufacturerNameUtility.isValidManuName(manufacturerName)){
            this.manufacturerName = manufacturerName;
        }

        if(Utilities.validStringlength(id, 10)){
            this.id = id;
        }
        else { Utilities.truncateString(id, 10); }


        if(Utilities.validStringlength(modelName, 30)){
            this.modelName = modelName;
        }
         else { Utilities.truncateString(modelName, 30); }

        if(price >= 20 ){
            this.price = price;
        }

    }

    /*
    * Getters and Setters for WD class.
    * Gets size and @return size
    * Sets size @param size and validates size less than 10. @return size
    *
    * */
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        if(size.length() < 10){
            this.size = size;
        }
    }

    /**
     * Gets price and @return price
     * Sets price @param price and validates as more than or equal to 20, @return price
     *
     * */

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 20) {
            this.price = price;
        }
    }

    /**
     * Gets Manufacturer Name and @return ManufacturerName
     * Sets ManufacturerName @param manufacturerName and validates from utilities class
     * @return manufacturerName
     * */

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        if(ManufacturerNameUtility.isValidManuName(modelName)){
            this.modelName = modelName;
        }
    }
 /**
  * Gets material @return material
  * Sets material @param material and checks that length is less than 20
  * @return material
  *
  * */
    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        if(material.length() < 20){
            this.material = material;
        }
    }
/**
 * gets ModelName @return modelName
 * Sets ModelName @param modelName and validates with Utils class that it is less than 30 length
 * @return modelName
 *
 * */
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        if(Utilities.validStringlength(modelName, 30)){
            this.modelName = modelName;
        }
    }

    /**
     * Gets id @return id
     * Sets id and validates that it is less than 10 lenght
     * @return id
     * */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (Utilities.validStringlength(id, 10)) {
            this.id = id;
        }
    }
/**
 * Abstract method that is not implemented yet. Creates method signature
 * @param price
 * @return double
 * */



    public abstract double getInsurancePremium (double price);

    /**
     * Abstract method that is not implemented yet. Creates method signature
     * @return String
     *
     * */

    public abstract String connectToInternet();

  /**
   * toString method that will display the WD details to the user
   * @return String
   * */

    @Override
    public String toString() {
        return "size = " + size  + " price = " + price + " manufacturerName = " + manufacturerName  + " material = " + material +
                " modelName = " + modelName + " id = " + id;
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
        WearableDevice that = (WearableDevice) o;
        return Double.compare(getPrice(), that.getPrice()) == 0 && Objects.equals(getSize(), that.getSize()) && Objects.equals(getManufacturerName(), that.getManufacturerName()) && Objects.equals(getMaterial(), that.getMaterial()) && Objects.equals(getModelName(), that.getModelName()) && Objects.equals(getId(), that.getId());
    }


}
