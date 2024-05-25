/**
 * The WearableDevicesAPI class controls the storage of devices in a list and has many methods
 * to manipulate the data in the storage. It implements ISerializer and its methods.
 *
 * @author Noemi Lovei
 * @Version 1.0 12th May 2024
 *
 * */

package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


import models.SmartBand;
import models.SmartWatch;
import models.WearableDevice;
import utils.ISerializer;
import utils.Utilities;

import java.util.ArrayList;
import java.util.List;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class WearableDeviceAPI implements ISerializer {


    private List<WearableDevice> wearableList;
    private String file;
    public WearableDeviceAPI(){

        wearableList = new ArrayList<>();
        file = "WearableDeviceDevices.xml";
    }

    /**
     * Read in parameters, declare a new SmartWatch object and add it to the ArrayList.
     *
     * @params size - size of SW
     *         price - price of SW
     *         manufacturerName - manufacturerName of SW
     *         material - material of SW
     *         modelName - modelName of SW
     *         id - id of SW
     *         displayType - display Type of SW
     *
     * @return boolean true if device successfully added else false
     *
     * */

  ///     ///
 /// CRUD ///
 ///     ///
    public  boolean addWearableDeviceDevice(String size, double price, String manufacturerName, String material, String modelName, String id, String displayType){
        SmartWatch SW = new SmartWatch(size, price, manufacturerName, material, modelName,id, displayType);
        return wearableList.add(SW);
    }

   /**
    * Read in parameters, declare a new SmartBand object and add it to the ArrayList.
    *
    * @params size - size of SB
    *         price - price of SB
    *         manufacturerName - manufacturerName of SB
    *         material - material of SB
    *         modelName - modelName of SB
    *         id - id of SB
    *         heartratemonitorB - heartRateMonitor of SB
    *
    * @return boolean true if device successfully added to arraylist else false
    *
    * */

    public  boolean addWearableDeviceDevice(String size, double price, String manufacturerName, String material, String modelName, String id, boolean heartratemonitorB){
        SmartBand SB = new SmartBand(size, price, manufacturerName, material, modelName, id, heartratemonitorB);
        return wearableList.add(SB);
    }

    /**
     * Reads in integer index and deletes the device in the arraylist found at that index.
     *
     * @param IndexToBeDeleted - representing the number to be deleted
     * @return WearableDevice deleted if device successfully deleted else returns null.
     * */

    public WearableDevice deleteWearableDeviceByIndex(int IndexToBeDeleted){
       if (Utilities.isValidIndex(wearableList, IndexToBeDeleted)){
          return  wearableList.remove(IndexToBeDeleted);
        }
       else
           return null;
    }

    /**
     * Reads in string id and deletes the device in the array;ist found with that string id.
     *
     * @param id - representing the id of the device
     * @returns WearableDevice deleted if device successfully deleted else returns null.
     * */


public WearableDevice deleteWearableDeviceById(String id) {
        int index = wearableList.size() + 1 ;
        for (WearableDevice device : wearableList) {
            if (!isValidId(id)) {
               index = wearableList.indexOf(id);
            }
        }
        if(!Utilities.isValidIndex(wearableList, index)){
            return null;
        }
        else {
            return wearableList.remove(index);
        }

}

/**
 * Finds WD by index in the arrayList and returns it.
 *
 * @param IndexToGet - the integer index to be found
 * @return WD at that index else returns null
 *
 * */
    public WearableDevice getWearableDeviceByIndex(int IndexToGet){
        if(Utilities.isValidIndex(wearableList, IndexToGet)){
            return wearableList.get(IndexToGet);
        }
        else
            return null;
    }

 /**
  * Finds WD by id in the arraylist and returns it.
  *
  * @param id - the String id to be found
  * @return WD at that id else returns null
  *
  * */

    public WearableDevice getWearableDevicesById(String id){
        for (WearableDevice wearableDevice : wearableList) {
            if (wearableDevice.getId().equalsIgnoreCase(id))
                return wearableDevice;
        }
        return null;
    }

    /**
     * Finds WD in the arrayList and confirms if it exists
     *
     * @param id - the String id to be found
     * @return boolean true if device exists with the id else false
     *
     * */
    public boolean getDeviceB( String id ){
        for (WearableDevice wearableDevice : wearableList) {
            if (wearableDevice.getId().equalsIgnoreCase(id))
                return true;
        }
        return false;
    }

    /**
     * Lists all WD in the arrayList by iterating through the arrayList and adding all devices to a String
     *
     * @ return A String with all the devices else an error String.
     *
     * */

    public String listAllWearableDeviceDevices(){
        String str = "";
        for(WearableDevice device: wearableList){
           str += "Index: " + wearableList.indexOf(device) + " " + device.toString() + "\n ";
        }  if (str.isEmpty()){
            return "No WearableDevice Devices";
        } else
            return str;
    }

    /**
     * Lists all SB in the arrayList by iterating through the arrayList and adding all SB to a String
     *
     * @return A string with all the SB devices else an error String
     *
     *
     * */

    public String listAllSmartBands() {
        String str = "";
        for (WearableDevice device: wearableList) {
            if (device instanceof SmartBand) {
                str += "Index: " + wearableList.indexOf(device) + device.toString() + "\n";
            }
        }
          if (str.isEmpty()) {
             return "No Smart Bands ";
        } else {
              return str;
          }
    }
    /**
     *
     * Lists all SW in the arrayList by iterating through the arrayList and adding all SW to a String
     *
     * @return A string with all the SW devices else an error String
     *
     *
     * */


    public String listAllSmartWatches() {
        String str = "";
        for (WearableDevice device: wearableList) {
            if (device instanceof SmartWatch) {
                str += "Index: " + wearableList.indexOf(device) + device.toString() + "\n";
            }
        }
        if (str.isEmpty()) {
            return "No Smart Watches ";
        } else {
            return str;
        }
    }

    /**
     * Lists all WD in the arrayList by iterating through the arrayList and checks to see whether the devices are above given price.
     * For all prices above the price, they are added to a String.
     *
     * @param price - the price we are checking above
     * @return A string with all the WD that are above the price else an error String
     *
     *
     * */

    public String listAllWearableDeviceAbovePrice(double price){
      String str = "";
      for( WearableDevice device: wearableList ){
          if(device.getPrice() > price){
             str += "Index" + wearableList.indexOf(device) +  device.toString() +  "\n";
          }
      }
        if(str.isEmpty()){
            return "No WearableDevice more expansive than " + price +" Euros";
        }
        else
            return str;
    }

    /**
     * Lists all WD in the arrayList by iterating through the arrayList and checks to see whether the devices are below given price.
     * For all prices below the price, they are added to a String.
     *
     * @param price - the price we are checking below
     * @return A string with all the WD that are below the price else an error String
     *
     *
     * */

    public String listAllWearableDeviceBelowPrice(double price){
        String str = "";
        for( WearableDevice device: wearableList ){
            if(device.getPrice() < price){
                str += "Index" + wearableList.indexOf(device) + device.toString() +  "\n";
            }
        }
        if(str.isEmpty()){
            return "No WearableDevice cheaper than " + price + " Euros";
        }
        else
            return str;
    }

    /**
     * Counts the number of WD in the arrayList
     * @return the integer value of the number of WDs
     *
     * */
    public int numberOfWearableDeviceDevices(){
        int counter = 0;
        for(WearableDevice device : wearableList){
            counter++;
        }
        return counter;
    }

    /**
     * Counts the number of SB in the arrayList
     * @return the integer value of the number of SBs
     *
     * */

    public int numberOfSmartBands(){
        int counter = 0;
        for(WearableDevice device: wearableList){
            if(device instanceof SmartBand){
                counter++;
            }
        }
        return counter;
    }

    /**
     * Counts the number of SW in the arrayList
     * @return the integer value of the number of SWs
     *
     * */

    public int numberOfSmartWatch(){
        int counter = 0;
        for(WearableDevice device: wearableList){
            if(device instanceof SmartWatch){
                counter++;
            }
        }
        return counter;
    }

    /**
     * Counts the number of WD with a given manufacturer in the arrayList
     * @param manufacturerName - String name that we are checking against
     *
     * @return the count of WD with given manufacturerName
     * */
    public int numberOfWearableDevicesByChosenManufacturer(String manufacturerName){
       int counter = 0;
       for(WearableDevice device: wearableList){
           if(device.getManufacturerName() == null){
               continue;
           }
           if( device.getManufacturerName().equalsIgnoreCase(manufacturerName)){
               counter ++;
           }
       }
       return counter;
    }

    /**
     * Lists all WD with given manufacturer by iterating through arrayList and matching the manufacturerName.
     * If the Mname is null then it skips the iteration.
     *
     * @param manufacturerName - the String the method is matching against
     *
     * @return String with all the devices that match the manufacturerName else an error String
     * */

    public String listWearableDevicesByChosenManufacturer(String manufacturerName){
       String str = "";
        for(WearableDevice device: wearableList){
            if(device.getManufacturerName() == null){
                continue;
            }
            if(device.getManufacturerName().equalsIgnoreCase(manufacturerName)){
                str += "Index " + wearableList.indexOf(device) + device.toString() + "\n";
            }
        }
       if(!str.isEmpty()){
            return str;
        }
        else {
            return "No device with that manufacturer";
        }
    }

 /**
  * Updates existing SW in arrayList with updated details.
  *
  * @param id - id to be updated
  *        size - size to be updated
  *        price - price to be updated
  *        manufacturerName - manufacturerName to be updated
  *        material - material to be updated
  *        modelName - modelName to be updated
  *        displayType - displayTyple to be updated
  *
  * @returns boolean true if update successful else false
  * */

    public boolean updateSmartWatch(String id, String size, double price, String manufacturerName, String material, String modelName, String displayType){
      WearableDevice found = getWearableDevicesById(id);

      if((found != null)&& (found instanceof SmartWatch)){
          found.setManufacturerName(manufacturerName);
          ((SmartWatch) found).setDisplayType(displayType);
          found.setMaterial(material);
          found.setSize(size);
          found.setModelName(modelName);
          found.setPrice(price);
          return true;
      }
      else {
          return false;
      }
    }

    /**
     * Updates existing SB in arrayList with updated details.
     *
     * @param id - id to be updated
     *        size - size to be updated
     *        price - price to be updated
     *        manufacturerName - manufacturerName to be updated
     *        material - material to be updated
     *        modelName - modelName to be updated
     *        heartRateMonitorB - heartRateMonitor to be updated
     *
     * @returns boolean true if update successful else false
     * */

    public boolean updateSmartBand(String id, String size, double price, String manufacturerName, String material, String modelName, boolean heartRateMonitorB){
        WearableDevice found = getWearableDevicesById(id);

        if((found != null)&& (found instanceof SmartBand)){
            found.setManufacturerName(manufacturerName);
            ((SmartBand) found).setHeartRateMonitor(heartRateMonitorB);
            found.setMaterial(material);
            found.setSize(size);
            found.setModelName(modelName);
            found.setPrice(price);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Checks whether the id already exists in the arrayList
     * @param id - to be checked
     * @return boolean true if the id is not in the arrayList and false if it is already in use
     * */

    public boolean isValidId(String id) {
        for (WearableDevice techDev : wearableList) {
            if (techDev.getId().equals(id))
                return false;
        }
            return true;
        }

        /**
         * Sorts arrayList by price descending. It iterates through the arrayList, presumes lowest price at index 0 and compares the price at index 0
         * with index +1. It calls the swapWearableDevices() method to sort the devices.
         *
         *
         * */
public void sortByPriceDescending() {
    for (int i = wearableList.size() -1; i >= 0; i--){
        int lowestIndex = 0;
        for (int j = 0; j <= i; j++){
            {
                if (wearableList.get(j).getPrice() < wearableList.get(lowestIndex).getPrice()) {
                  lowestIndex = j;
                }
            }
        }
       swapWearableDevices(wearableList,i, lowestIndex);
    }
}


    /**
     * Sorts arrayList by price ascending. It iterates through the arrayList, presumes highest price at index 0 and compares the price at index 0
     * with index +1. It calls the swapWearableDevices() method to sort the devices.
     *
     *
     * */

    public void sortByPriceAscending() {
        for (int i = wearableList.size() -1; i >= 0; i--){
            int heighestIndex = 0;
            for (int j = 0; j <= i; j++){
                {
                    if (wearableList.get(j).getPrice() > wearableList.get(heighestIndex).getPrice()) {
                        heighestIndex = j;
                    }
                }
            }
            swapWearableDevices(wearableList,i, heighestIndex);
        }
    }

    /**
     * Swaps the WD at index i and index j. Helper method in sorting.
     * @param  wearableList, int i, int j - the arrayList and the indexes at which the swaps occur
     * @return void
     * */

private void swapWearableDevices(List<WearableDevice> wearableList, int i, int j){
        WearableDevice lower = wearableList.get(i);
        WearableDevice higher = wearableList.get(j);

        wearableList.set(i,higher);
        wearableList.set(j,lower);
}

/**
 * Searches all WD based on deviceName and returns the found WD by adding all found to String.
 *
 * @param deviceName - the search criteria
 * @return String with found WDs else an error String
 * */
public String searchAllDevices(String deviceName){
 String matchingDevice = "";
 for(WearableDevice device : wearableList){
     if(device.getModelName().toUpperCase().contains(deviceName.toUpperCase())){
         matchingDevice += "Index " + wearableList.indexOf(device) + " : " + device + "\n";
     }
   }
 if(matchingDevice.equals("")){
     return "No devices matches your search";
 }
 else{
      return matchingDevice;
   }
}
/**
 * Searches through SB and matches against a device Name, adding all found to a String
 * @param deviceName - to search against
 * @return String with all found SBs else an error String
 * */

    public String searchAllSmartBands(String deviceName) {
        String matchingSmartBand = "";
        for (WearableDevice device : wearableList) {
            if (device instanceof SmartBand) {
                if (device.getModelName().toUpperCase().contains(deviceName.toUpperCase())) {
                    matchingSmartBand += "Index" + wearableList.indexOf(device) + " : " + device + "\n";
                }
            }
        }
        if(matchingSmartBand.equals("")){
            return "No SmartBand mathces your search";
        }
        else {
            return matchingSmartBand;
        }
    }

    /**
     * Searches through SW and matches against a device Name, adding all found to a String
     * @param deviceName - to search against
     * @return String with all found SWs else an error String
     * */

    public String searchAllSmartWatches(String deviceName){
        String matchingSmartWatch = " ";
        for (WearableDevice device : wearableList) {
            if (device instanceof SmartWatch) {
                if (device.getModelName().toUpperCase().contains(deviceName.toUpperCase())) {
                    matchingSmartWatch += "Index " +  wearableList.indexOf(device) + " : " + device + "\n";
                }
            }
        }
        if(matchingSmartWatch.equals("")){
            return "No SmartBand matches your search";
        }
        else {
            return matchingSmartWatch;
        }
    }

/**
 * Sorts WD by Descending and searches for the top 5 WD in the sorted arrayList.
 * @return String with top 5 WD matches else error String
 * */

public String topFiveMostExpensiveWearableDevices(){
     String str = "";
     sortByPriceDescending();
     for(int i = 0; (i < wearableList.size() && (i <5)); i++){
         str+= "Expensive: " +  wearableList.get(i) + " , " + " \n" ;
     }
   if(str.isEmpty())
   {
       return "There are no wearable devices in the list";
   }
   else
        return str;
}

/**
 * Sorts WD by ascending and returns the top 5 least expensive deviecs from arrayList
 * @return String with top 5 least expensive WDs else error String
 *
 * */

    public String topFiveLeastExpensiveWearableDevices(){
        String str = "";
        sortByPriceAscending();
        for(int i = 0; (i < wearableList.size() && (i <5)); i++){
            str+= "Expensive: " +  wearableList.get(i) + " , " + " \n" ;
        }
        if(str.isEmpty())
        {
            return "There are no wearable devices in the list";
        }
        else
            return str;
    }
    /**
     * Sorts SW by Descending and searches for the top 5 SW in the sorted arrayList.
     * @return String with top 5 SW matches else error String
     * */

    public String topFiveMostExpensiveSmartWatch(){
        String str = "";
        sortByPriceDescending();
        for(WearableDevice device: wearableList) {
            if (device instanceof SmartWatch) {
                for (int i = 0; (i < wearableList.size() && (i <5)); i++) {
                    str += "Most expensive watches" + wearableList.get(i) + " \n";
                }
            }
        }
        if(str.isEmpty())
        {
            return "There are no wearable smartwatches in the list.";
        }
        else
            return str;
    }



    /**
     * Saves/Writes arrayList to a xml file. Closes stream.
     * @return void
     *
     * */
    @Override
    public void save() throws Exception {
        XStream xStream = new XStream(new DomDriver());
        ObjectOutputStream out = xStream.createObjectOutputStream(new FileWriter("WearableDeviceDevices.xml"));
        out.writeObject(wearableList);
        out.close();
    }

    /**
     * Loads data from xml file and throws exception if error occurs.
     * Allows only given class formats applies security, reads in Object, closes the stream.
     *
     * @returns void
     * */
    @Override
    public void load() throws Exception {
        Class<?>[] classes = new Class[]{WearableDevice.class, SmartWatch.class, SmartBand.class};
        XStream xStream = new XStream(new DomDriver());
        xStream.setupDefaultSecurity(xStream);
        xStream.allowTypes(classes);

        ObjectInputStream in = xStream.createObjectInputStream(new FileReader("WearableDeviceDevices.xml"));
        wearableList = (List<WearableDevice>) in.readObject();
        in.close();

    }

 /**
  *  Returns the otherwise private file.
  *
  * @return file
  *
  * */
    @Override
    public String fileName() {

        return file;
    }



}
