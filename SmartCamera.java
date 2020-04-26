/*
subclass of the smartObject class Smart Camera and implements the Motion control and comparable interfaces
 */
public class SmartCamera extends SmartObject implements MotionControl,Comparable<SmartCamera>{
    //private data fields for SmartCamera objects
    private boolean status;
    private int batteryLife;
    private boolean nightVision;
    /*
    overloaded superclass constructor
     */
     SmartCamera(String alias,String macId, boolean nightVision, int batteryLife){
        super(alias,macId);
        this.nightVision = nightVision;
        this.batteryLife = batteryLife;
}
    /*
    to record the camera with some properties with the parameter isDay boolean value
     */
    private void recordOn(boolean isDay){
         //control connection first
         if(controlConnection()){
             //if day time or nightVision feature is true
             if(isDay || nightVision){
                 //check the status, print appropriate messages and update the status
                 if(this.status){
                     System.out.println("Smart Camera " + getAlias() + " has been already turned on");
                 }else {
                     System.out.println("Smart Camera " + getAlias() + " is turned on now");
                     this.status = true;
                 }
             }else { // if time is night time and camera has no night vision then print the warning message
                 System.out.println("Sorry - " + getAlias() +" does not have night vision feature.");
             }
         }
    }
    //to record off the camera and update the properties
    private void recordOff(){
        if(controlConnection()){
            //update the status and print appropriate messages
            if(this.status){
                System.out.println("Smart Camera - " + getAlias() + " is turned off now.");
                this.status = false;
            }else System.out.println("Smart Camera - " + getAlias() + " has been already turned off.");
        }
    }

    /*
    override abstract superclass method testObject
     */
    @Override
    boolean testObject() {
         if(controlConnection()){
             //check the connection and print the messages
             SmartObjectToString();
             System.out.println("Test is starting for "+ this.getClass().getSimpleName() + " day time");
             //test for the day time
             recordOn(true);
             recordOff();
             System.out.println("Test is starting for "+ this.getClass().getSimpleName() + " night time");
             //test for the night time
             recordOn(false);
             recordOff();
             System.out.println("Test completed for "+ this.getClass().getSimpleName());
             return true;
         }else return false;
    }

    /*
    shut down the object with off methods and print the information
     */
    @Override
    boolean shutDownObject() {
         if(controlConnection()){
             SmartObjectToString();
             recordOff();
             return true;
         }else
        return false;
    }

    /*
    override the method of the motionControl interface
     */
    @Override
    public boolean controlMotion(boolean hasMotion, boolean isDay) {
        //check the motion status and print the messages
        if(hasMotion){
            System.out.println("Motion detected!");
        }else System.out.println("Motion not detected!");
        //check the day time status again
        if(isDay){
            recordOn(true);
        }else {
            // if the camera has nightVision, then  record on, record off otherwise
            if(this.nightVision) recordOn(true);
            else recordOn(false);
        }
        return false;
    }
    /*
    comparing the batteryLives to override the compareTo method of the Comparable interface
     */
    @Override
    public int compareTo(SmartCamera smartCamera) {
        return Integer.compare(this.batteryLife, smartCamera.getBatteryLife());
    }
    /*
    toString method for the smart cameras feature and record situation
     */
    @Override
    public String toString(){
        return "Smart Camera -> " + getAlias() + "'s battery life is "+ this.batteryLife + " status is " + info(this.status);
    }
    //recording message according to status value
     private String info(boolean status){
        return status ? "recording" : "is not recording";
    }
    /*
    getter and setter methods
     */
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getBatteryLife() {
        return batteryLife;
    }

    public void setBatteryLife(int batteryLife) {
        this.batteryLife = batteryLife;
    }

    public boolean isNightVision() {
        return nightVision;
    }

    public void setNightVision(boolean nightVision) {
        this.nightVision = nightVision;
    }
}
