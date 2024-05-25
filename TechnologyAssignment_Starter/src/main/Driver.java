/**
 * The Driver class runs the program and interacts with the user.
 * It handles all UI methods and validates the ID to ensure that it is unique.
 * It controls the menu seen by user.
 *
 * @author Noemi Lovei
 * @Version 1.0 12th May 2024
 *
 * */


package main;

import controllers.WearableDeviceAPI;


import models.SmartBand;
import models.SmartWatch;
import models.WearableDevice;
import utils.ScannerInput;
import utils.Utilities;


public class Driver {

    private WearableDeviceAPI wearableAPI;

    /**
     * Entry point for the program
     * @return void
     * */

    public static void main(String[] args) throws Exception {

        new Driver().start();
    }

    /**
     * Instantiates a new WDAPI.
     * Calls for the main menu for the user.
     *
     * @return void
     *
     * */
    public void start() {

        wearableAPI = new WearableDeviceAPI();

        runMainMenu();
    }
 /**
  * Shows the user a list of options to select from.
  * @return int - that will determine what action is taken
  *
  * */

    private int mainMenu() {

        return ScannerInput.readNextInt("""
                                ------------------------------------------------------------------
                                |                      WearableDevice Store                      |
                                ------------------------------------------------------------------
                                |   1) WearableDevice CRUD MENU                                  |
                                |   2) Reports MENU                                              |
                                ------------------------------------------------------------------
                                |   3) Search WearableDevice Devices                             |
                                |   4) Sort WearableDevice Devices                               |                              
                                ------------------------------------------------------------------
                                |   5) Save all                                                  | 
                                |   6) Load all                                                  | 
                                ------------------------------------------------------------------
                                |   0)  Exit                                                     | 
                                ------------------------------------------------------------------

    
    ==>> """);
    }

    /**
     * Runs the menu in the background and having called the menu for the user, it will action user input.
     * It will keep asking for a valid option if the user enters invalid details.
     * It will exit the app once the user chooses 0.
     * @return void
     *
     * */
    private void runMainMenu() {
        int option = mainMenu();

        while(option != 0 ){
            switch(option){
                case 1 -> WearableDeviceCrudMenu();
                case 2 -> ReportsMenu();
                case 3 -> Search();
                case 4 -> SortWearableDevices();
                case 5 -> save();
                case 6 -> load();
                default -> System.out.println("Invalid option entered: " + option);
            }

            ScannerInput.readNextLine("\n Press enter key to continue...");
            option = mainMenu();
        }

        exitApp();
    }

    /**
     * Exits the app and prints exiting to the user.
     * @return void
     * */
    private void exitApp() {

        System.out.println("Exiting....");
        System.exit(0);
    }


    //---------------------
    //  General Menu Items
    //---------------------

    /**
     * WD Store Menu shows to user and reads in an option and calls for relevant methods based on user input.
     * case 1 validates the id to ensure it is unique.
     * Case 4 has a further sub-menu to allow user to choose from SB and SW.
     * */
private void WearableDeviceCrudMenu(){
        int option = ScannerInput.readNextInt("""
                                           
          ------------------------------------------------------------------
          |                     WearableDevice Store Menu                  |
          ------------------------------------------------------------------
          |   1) Add a Wearable Device                                     |
          |   2) Delete a Wearable Device                                  |
          |   3) List all Wearable Devices                                 |
          |   4) Update Wearable Devices                                   |
          |   0) Return to main menu                                       |
          ------------------------------------------------------------------                                                                    
                ==>> """);
        switch(option){

            case 0 -> {
                runMainMenu();
            }
            case 1 -> {
                String size = ScannerInput.readNextLine("Enter Device size: ");
                double price = ScannerInput.readNextDouble("Enter price: ");
                String manufacturerName = ScannerInput.readNextLine("Enter manufacturer's name: ");
                String material = ScannerInput.readNextLine("Enter material: ");
                String modelName = ScannerInput.readNextLine("Enter model name: ");
                String id = ScannerInput.readNextLine("Enter id: ");
                String displayType = ScannerInput.readNextLine("DisplayType (if any): ");

                char heartratemonitor = ScannerInput.readNextChar("Does this product have a heart rate monitor? (y/n)");
                boolean heartratemonitorB = Utilities.YNtoBoolean(heartratemonitor);

                if (wearableAPI.isValidId(id)) {
                    String uniqueId = id;

                    if (!heartratemonitorB) {
                        if (wearableAPI.addWearableDeviceDevice(size, price, manufacturerName, material, modelName, uniqueId, displayType)) {
                            System.out.println("SmartWatch successfully added");
                        } else {
                            System.out.println("An error occurred while adding SmartWatch. Try again.");
                        }

                    } else {
                        SmartBand SB = new SmartBand(size, price, manufacturerName, material, modelName, uniqueId, heartratemonitorB);
                        if (wearableAPI.addWearableDeviceDevice(size, price, manufacturerName, material, modelName, uniqueId, heartratemonitorB)) {
                            System.out.println("SmartBand successfully added");
                        } else {
                            System.out.println("An error occured while adding SmartBand. Try again.");
                        }
                    }

                }
                else {
                    System.out.println("The id must be unique. Try again.");
                }
            }
            case 2 -> {
                boolean flag;
               char delete = ScannerInput.readNextChar("Do you want to delete a wearable device using it's id?");
                if (Utilities.YNtoBoolean(delete)) flag = true;
                else flag = false;

                if(flag) {
                    String id = ScannerInput.readNextLine("Enter id of wearable device you want to delete: ");
                    System.out.println(wearableAPI.deleteWearableDeviceById(id) + "\n Delete by id Successful");
                }
                else {
                    int index = ScannerInput.readNextInt("Enter index of wearable device you want to delete: ");
                    System.out.println(wearableAPI.deleteWearableDeviceByIndex(index) + "\n Delete by index Successful");
                }

            }
            case 3 -> {
               System.out.println(wearableAPI.listAllWearableDeviceDevices());
            }
            case 4 -> {
                if(wearableAPI.numberOfWearableDeviceDevices() > 0){
                    boolean isUpdated = false;

                    int option_2 = ScannerInput.readNextInt("""
                            ------------------------------------------
                            |         1) Update a SmartWatch         |
                            |         2) Update a SmartBand          |
                            ------------------------------------------
                           ==>> """);

                    switch (option_2) {
                        case 1 -> {
                            System.out.println(wearableAPI.listAllSmartWatches());
                            if(wearableAPI.numberOfSmartWatch() > 0 ){
                                String idString = ScannerInput.readNextLine("Enter the id of the SmartWatch you wish to update: ");
                                if(wearableAPI.getDeviceB(idString)){
                                    String size = ScannerInput.readNextLine("Enter Device size: ");
                                    double price = ScannerInput.readNextDouble("Enter price: ");
                                    String manufacturerName = ScannerInput.readNextLine("Enter manufacturer's name: ");
                                    String material = ScannerInput.readNextLine("Enter material: ");
                                    String modelName = ScannerInput.readNextLine("Enter model name: ");
                                    String displayType = ScannerInput.readNextLine("DisplayType (if any): ");
                                    isUpdated = wearableAPI.updateSmartWatch(idString, size, price, manufacturerName, material, modelName, displayType);
                                }
                            }
                        }
                        case 2 -> {
                            System.out.println(wearableAPI.listAllSmartBands());
                            if(wearableAPI.numberOfSmartBands() > 0 ){
                                String idString = ScannerInput.readNextLine("Enter the id of the SmartBand you wish to update: ");
                                if(wearableAPI.getDeviceB(idString)){
                                    String size = ScannerInput.readNextLine("Enter Device size: ");
                                    double price = ScannerInput.readNextDouble("Enter price: ");
                                    String manufacturerName = ScannerInput.readNextLine("Enter manufacturer's name: ");
                                    String material = ScannerInput.readNextLine("Enter material: ");
                                    String modelName = ScannerInput.readNextLine("Enter model name: ");

                                    char heartratemonitor = ScannerInput.readNextChar("Does this product have a heart rate monitor? (y/n)");
                                    boolean heartratemonitorB = Utilities.YNtoBoolean(heartratemonitor);

                                   /* SmartBand updatedDetails = new SmartBand(size, price, manufacturerName, material, modelName, idString, heartratemonitorB); */
                                    isUpdated = wearableAPI.updateSmartBand(idString, size, price, manufacturerName, material, modelName, heartratemonitorB );
                                   }
                               }
                            } default -> System.out.println("Invalid option entered: " + option);
                        }
                    if(isUpdated){
                        System.out.println("Wearable Device successfully updated");
                    } else {
                        System.out.println("An error occurred and no devices were updated. Try again.");
                    }

                }
                else {
                    System.out.println("No devices available to update.");
                }

            }

        }
    ScannerInput.readNextLine("\n Press enter key to continue...");
        WearableDeviceCrudMenu();

}

/**
 * Short Reports Menu that allows user to go to Overview menu
 * @return void
 * */

private void ReportsMenu(){
    int option = ScannerInput.readNextInt("""
                                           
            ------------------------------------------------------------------
            |                      Reports Menu                              |
            ------------------------------------------------------------------
            |   1) WearableDevice Overview                                   |
            |   0) Return to main menu                                       |
            ------------------------------------------------------------------                                                                 
               ==>> """);
    switch(option){
        case 0 -> {
            runMainMenu();
        }
        case 1 -> {
            wearableDeviceReportsMenu();
        }
        default -> System.out.println("Invalid option entered: " + option);
    }
    ScannerInput.readNextLine("\n Press enter key to continue...");
    ReportsMenu();
}

/**
 * Overview menu with a number of options for the user to choose from.
 * Case 8 and 9 are extra options not in the original assignment menu
 * @returns void
 * */
private void wearableDeviceReportsMenu(){
    int option = ScannerInput.readNextInt("""
                                           
            ------------------------------------------------------------------
             |                      WD Reports Menu                          |
            ------------------------------------------------------------------
             |   1) List all technology                                      |
             |   2) List all SmartBands                                      |
             |   3) List all SmartWatch                                      |
             |   4) List all devices above a price                           |
             |   5) List all devices below a price                           |
             |   6) List the top five most expensive smart watches           |
             |   7) List the top five most expensive WD                      |
             |   8) Top five least expensive WD                              |
             |   9) List all devices for a chosen Manufacturer               |
            ------------------------------------------------------------------
             |   10) Find device by index                                    |
             |   11) Find device by id                                       |
             -----------------------------------------------------------------
             |                          TOTALs                               |
             -----------------------------------------------------------------
             |   12) List total number of ...                                |
             |   13) List total number of devices by chosen Manufacturer     |
             -----------------------------------------------------------------
             |   0) Return to main menu                                      |
             ------------------------------------------------------------------                                                                
               ==>> """);

    switch(option){
        case 0 -> {
            runMainMenu();
        }
        case 1 -> {
            System.out.println(wearableAPI.listAllWearableDeviceDevices());
        }
        case 2 -> {
            System.out.println(wearableAPI.listAllSmartBands());
        }
        case 3 -> {
            System.out.println(wearableAPI.listAllSmartWatches());
        }
        case 4 -> {
            double price = ScannerInput.readNextDouble("Above price: ");
           System.out.println(wearableAPI.listAllWearableDeviceAbovePrice(price));
        }
        case 5 -> {
            double price = ScannerInput.readNextDouble("Below price: ");
           System.out.println(wearableAPI.listAllWearableDeviceBelowPrice(price));
        }
        case 6 -> {
            System.out.println(wearableAPI.topFiveMostExpensiveSmartWatch());

        }
        case 7 -> {
            System.out.println(wearableAPI.topFiveMostExpensiveWearableDevices());
        }
        case 8 -> {
            System.out.println(wearableAPI.topFiveLeastExpensiveWearableDevices());
        }

        case 9 -> {
            String ManufacturerName = ScannerInput.readNextLine("Manufacturer: ");
            System.out.println(wearableAPI.listWearableDevicesByChosenManufacturer(ManufacturerName));
        }

        case 10 -> {
            int index = ScannerInput.readNextInt("Do you know the index of your device? Tell me the index!");
            System.out.println("Index: " +  index + wearableAPI.getWearableDeviceByIndex(index));
        }

        case 11 -> {
            String id = ScannerInput.readNextLine("Do you know the id of your device? Tell me the index!");
            System.out.println("Device: " + wearableAPI.getWearableDevicesById(id));
        }

        case 12 -> {
            char Q = ScannerInput.readNextChar("Would you like to know the total number of devices? (y/n) ");
            boolean A = Utilities.YNtoBoolean(Q);
            if(A) {
                System.out.println("The total number of wearable devices are: " + wearableAPI.numberOfWearableDeviceDevices());
            }
            else {
                char Q2 = ScannerInput.readNextChar("Would you like to know the total number of SmartWatches? (y/n)");
                boolean A2 = Utilities.YNtoBoolean(Q2);
                if(A2){
                    System.out.println("The total number of SmartWatches are: " + wearableAPI.numberOfSmartWatch());
                }
                else {
                    char Q3 = ScannerInput.readNextChar("Would you like to know the total number of SmartBands? (y/n)");
                    boolean A3 = Utilities.YNtoBoolean(Q3);
                    if(A3){
                        System.out.println("The total number of SmartBands are: " + wearableAPI.numberOfSmartBands());
                    }
                }
            }

        }

        case 13 -> {
            String chosenManu = ScannerInput.readNextLine("What manufacturer are you looking for?");
            System.out.println("The number of devices from " + chosenManu + " are: " + wearableAPI.numberOfWearableDevicesByChosenManufacturer(chosenManu));
        }


        default -> System.out.println("Invalid option entered: " + option);
    }
    ScannerInput.readNextLine("\n Press enter key to continue...");
    wearableDeviceReportsMenu();
}
    //---------------------
    //  Search/Sort
    //---------------------

/**
 * Searches  based on different criteria, does not use a menu but a QA for the user to choose from.
 * Has option to return to Main Menu if user does not wish to search.
 * @return void
 * */
    private void Search(){
        char alldevices = ScannerInput.readNextChar("Would you like to search among all devices (y/n) ");
        boolean allDevicesFlag = Utilities.YNtoBoolean(alldevices);
        if(allDevicesFlag){
            String modelName = ScannerInput.readNextLine("What is the  model name of the device?");
            System.out.println(wearableAPI.searchAllDevices(modelName));

        }

        char allSmartBands = ScannerInput.readNextChar("Would you like to search among all Smart Bands? (y/n)");
        boolean allSmartBandsFlag = Utilities.YNtoBoolean(allSmartBands);
        if(allSmartBandsFlag) {
            String modelNameSB = ScannerInput.readNextLine("What is the model name of the Smart Band device?");
            System.out.println(wearableAPI.searchAllSmartBands(modelNameSB));

        }

        char allSmartWatches = ScannerInput.readNextChar("Would you like to search among all SmartWatches? (y/n)");
        boolean allSmartWatchesB = Utilities.YNtoBoolean(allSmartWatches);
        if(allSmartWatchesB){
            String modelNameSW = ScannerInput.readNextLine("What is the model name of the Smart Watch device?");
            System.out.println(wearableAPI.searchAllSmartWatches(modelNameSW));
        }

        System.out.println("No further search options available.");
        char mainmenu = ScannerInput.readNextChar("Would you like to return to main menu? (y/n)");
        boolean backToMainMenu = Utilities.YNtoBoolean(mainmenu);
        if(backToMainMenu){
            runMainMenu();
        }
        else{

            Search();
        }

    }

/**
 * Sorts the devices according to user input. Has a list of QA for user to choose from.
 * @return void
 *
 * */

private void SortWearableDevices(){
      char sort = ScannerInput.readNextChar("Would you like to sort by prices ascending? (y/n) ");
      boolean sortByAscending = Utilities.YNtoBoolean(sort);

      if(sortByAscending){
          wearableAPI.sortByPriceAscending();
          System.out.println(wearableAPI.listAllWearableDeviceDevices());
          System.out.println("Successfully sorted by prices ascending");
      }
      else {
          char sortTwo = ScannerInput.readNextChar("Would you like to sort by prices descending? (y/n)");
          boolean sortByDescending = Utilities.YNtoBoolean(sortTwo);

          if(sortByDescending){
              wearableAPI.sortByPriceDescending();
              System.out.println(wearableAPI.listAllWearableDeviceDevices());
              System.out.println("Successfully sorted by prices descending");
          }
          else{
              System.out.println("No other sorts available");
          }

      }

}

/**
 * Save method has try catch block.
 * Has user interface for saving file and can display the error message from the catch block.
 * @return void
 * */
private void save() {
        try {
            System.out.println("Saving file:" + wearableAPI.fileName());
            wearableAPI.save();
        }
        catch (Exception e) {
            System.err.println("Error reading from file: " + e );
        }
}

/**
 * Loads saved data from external xml file. Method controls the UI of the loading. Has try catch block.
 * Can print an error message to the user
 * @return void
 * */

private void load(){
        try{
            System.out.println("Loading from file: " + wearableAPI.fileName());
            wearableAPI.load();

        } catch (Exception e ){
            System.err.println("Error reading from file: "+ e );
        }
}



}

