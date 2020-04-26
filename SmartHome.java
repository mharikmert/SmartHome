/*
import the necessary classes
 */
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
/*
smartHome class
 */
public class SmartHome {
    //smartObjectList to hold the objects in it
    private ArrayList<SmartObject> smartObjectList = new ArrayList<>();
    //smartCameras list to compare the batteryLives of the camera
    private ArrayList<SmartCamera> smartCameras = new ArrayList<>();
    //private ArrayList<Integer> batteryLives = new ArrayList<>();
    private static int var  = 100;
    //to add the objects to list
     void addSmartObject(SmartObject smartObject){
        System.out.println(
                        "----------------------------------------------------------------\n" +
                        "----------------------------------------------------------------\n" +
                        "Adding new SmartObject\n" +
                        "----------------------------------------------------------------");
        //designing the ip value of the objects
        String ip = "10.0.0." + var;
        smartObject.connect(ip); var++;
        this.smartObjectList.add(smartObject);
        smartObject.testObject();
    }
    /*
    remove the objects in the list
     */
    boolean removeSmartObject(SmartObject smartObject){
        smartObjectList.remove(smartObject);
        return true;
    }
    /*
    method for onCome or onLeave situations
     */
    void controlLocation(boolean onCome){
        System.out.println(
                        "----------------------------------------------------------------\n" +
                        "----------------------------------------------------------------\n" +
                        "LocationControl: " + returnLocation(onCome) + "\n" +
                        "----------------------------------------------------------------");
        //search the object in smartObjectList first
        for (SmartObject smartObject : smartObjectList) {
            //find the SmartLight objects --> for location control interface
            if (smartObject instanceof SmartLight) {
                /*
                check the onCome value and call the onCome or onLeave
                 */
                if (onCome) {
                    ((SmartLight) smartObject).onCome();
                }else ((SmartLight) smartObject).onLeave();}
            }
        }
        //simple method to write onCome or onLeave according to onCome's value
        private String returnLocation(boolean onCome){
        //if onCome is true return "onCome", "onLeave otherwise
         return onCome ? "onCome" : "onLeave";
        }
    /*
        to use motion control interface
     */
    void controlMotion(boolean hasMotion, boolean isDay){
            System.out.println(
                        "----------------------------------------------------------------\n" +
                        "----------------------------------------------------------------\n" +
                        "MotionControl: HasMotion, isDay\n" +
                        "----------------------------------------------------------------");
            //again search the object
            for(SmartObject smartObject: smartObjectList){
                //find the smart camera(implements the motion control interface)
                if(smartObject instanceof SmartCamera){
                            //call the controlMotion method with the values hasMotion and isDay
                            ((SmartCamera) smartObject).controlMotion(hasMotion,isDay);
                }
            }
    }
    /*
    to use Programmable interface
     */
    void controlProgrammable(){
        System.out.println(
                        "----------------------------------------------------------------\n" +
                        "----------------------------------------------------------------\n" +
                        "Programmable: runProgram\n" +
                        "----------------------------------------------------------------");
        //search the object
        for(SmartObject smartObject: smartObjectList){
            //find the smartPlug(implements the programmable interface)
                if(smartObject instanceof SmartPlug){
                    //if the program time property of the object and current time is the same , then run the program
                    if(((SmartPlug) smartObject).getProgramTime().get(Calendar.SECOND) == Calendar.getInstance().get(Calendar.SECOND))
                    ((SmartPlug) smartObject).runProgram();
                }
                else if(smartObject instanceof SmartLight){
                    //or find the SmartLight object(implements the programmable interface as well)
                    if(((SmartLight) smartObject).getProgramTime().get(Calendar.SECOND) == Calendar.getInstance().get(Calendar.SECOND))
                    ((SmartLight) smartObject).runProgram();}
            }
        }
        //give the timer to the objects using setTimer method much seconds second
    void controlTimer(int seconds){
        System.out.println(
                        "----------------------------------------------------------------\n" +
                        "----------------------------------------------------------------\n" +
                        "Programmable : Timer " + seconds + " seconds\n" +
                        "----------------------------------------------------------------");
        for(SmartObject smartObject: smartObjectList){
            if(smartObject instanceof SmartPlug) ((SmartPlug) smartObject).setTimer(seconds);
            else if(smartObject instanceof SmartLight) ((SmartLight) smartObject).setTimer(seconds);
            }
        }
        //give the randomly timer to the objects with the same logic as up there
    void controlTimerRandomly(){
            System.out.println(
                        "----------------------------------------------------------------\n" +
                        "----------------------------------------------------------------\n" +
                        "Programmable : Timer = 5 or 10 seconds randomly\n" +
                        "----------------------------------------------------------------");
        for(SmartObject smartObject : smartObjectList){
                int random = returnRandom();
                if(smartObject instanceof SmartLight) ((SmartLight) smartObject).setTimer(random);
                else if(smartObject instanceof SmartPlug)((SmartPlug) smartObject).setTimer(random);
            }
        }
        // return 5 or 10 randomly for the controlTimerRandomly method
    private int returnRandom(){
         if(Math.random() < 0.5) return 5;
         else return 10;
    }
    // sorting the cameras to the batteryLives features and printing the properties of the cameras
    void sortCameras(){
        System.out.println("" +
                "----------------------------------------------------------------\n" +
                "----------------------------------------------------------------\n" +
                "Sort Smart Cameras\n" +
                "----------------------------------------------------------------");
        //find the smartCamera objects and add to them a new list smartCameras
        for (SmartObject smartObject : smartObjectList) {
            if (smartObject instanceof SmartCamera) {
                smartCameras.add((SmartCamera)smartObject);
            }
        }
        //then sort of them and print the properties
        Collections.sort(smartCameras);
        for(SmartCamera smartCamera: smartCameras){
            System.out.println(smartCamera.toString());
        }

    }
    /*
    getter and setter methods
     */
    public ArrayList<SmartObject> getSmartObjectList() {
        return smartObjectList;
    }

    public void setSmartObjectList(ArrayList<SmartObject> smartObjectList) {
        this.smartObjectList = smartObjectList;
    }
}
