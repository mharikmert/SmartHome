import java.util.Calendar;
/*
one of the subclasses of smartObject Class smartLight
implement two interfaces
* */
public class SmartLight extends SmartObject implements LocationControl, Programmable{
    /*
    private data fields for the smartLight object
     */
    private boolean hasLightTurned;
    private Calendar programTime = Calendar.getInstance();
    private boolean programAction;

    /*
    superclass constructor
     */
    SmartLight(String alias, String mcId) {
        super(alias, mcId);
    }
    /*
    turn on light method to control and change hasLightTurned status and printing messages
     */
     private void turnOnLight(){
         //check the connection first all the time
        if(controlConnection()){
            if(hasLightTurned){
                //already turned on, print message
                System.out.println("Smart Light - " + getAlias()+" has been already turned on.");
            } else {
                //if the lights are turned off then change the hasLightTurned status and print the message
                this.hasLightTurned = true;
                System.out.println("Smart Light - "+ getAlias()+" is turned on now" + calendarToString());
            }
        }
    }
    /*
    opposite of the turnOnLight method
     */
     private void turnOffLight(){
        if(controlConnection()){
            if(!hasLightTurned){
                System.out.println("Smart Light - "+getAlias()+" has been already turned off.");
            } else {
                this.hasLightTurned = false;
                System.out.println("Smart Light - "+getAlias()+" is turned off now" + calendarToString());
            }
        }
    }
    /*
    abstract testObject method and calling class methods to test the object
     */
    @Override
    public boolean testObject() {
        if(controlConnection()){
            SmartObjectToString();
            turnOnLight();
            turnOffLight();
            System.out.println("Test completed for SmartLight");
            return true;
        }
        else return false;
    }
    /*
    abstract method to shut down the object and print messages
     */
    @Override
    public boolean shutDownObject() {
        if(controlConnection()){
            SmartObjectToString();
            turnOffLight();
            return true;
        }else return false;
    }
    /*
    Overrides the Location control interface's methods onLeave and turnOfLight
     */
    @Override
    public void onLeave() {
        if(controlConnection()){
            //when the person left the house then turn off the lights
            System.out.println("On Leave -> Smart Light - " + getAlias());
            turnOffLight();
        }
    }
    /*
    other method of the LocationControl interface
     */
    @Override
    public void onCome() {
        if(controlConnection()) {
            System.out.println("On Come -> Smart Light - " + getAlias());
            turnOnLight();
        }
    }
    /*
    setTimer method of the interface programmable to set a timer for objects programTime
     */
    @Override
    public void setTimer(int seconds) {
        if(controlConnection()) {
            //setting the program time feature as the current time more seconds
            this.programTime.set(Calendar.SECOND,Calendar.getInstance().get(Calendar.SECOND) + seconds);
            setProgramTime(programTime);
            /*
            control the status and print messages
             */
            if (hasLightTurned) {
                System.out.println("Smart Light -> "+getAlias()+" will be turned off " +
                        +seconds + " seconds later! " + calendarToString());
            } else {
                System.out.println("Smart Light -> " + getAlias()+ "will be turned on " +
                        +seconds + " seconds later!" + (calendarToString()));

            }
        }
    }
    /*
    cancel the timer with method of programmable
     */
    @Override
    public void cancelTimer() {
        if(controlConnection()){
            //cancel the timer as assigning to programTime of the null value
            this.programTime = null;
        }
    }

    /*
    method of programmable interface to run the program
    print messages and change the feature as it is
     */
    @Override
    public void runProgram() {
        if(controlConnection()){
            if(hasLightTurned){
                    System.out.println("Run Program -> Smart Light - " + getAlias() +"\n"
                    + "Smart Light - " + getAlias()+ " is turned off now" + calendarToString());
                    hasLightTurned = false;

            } else {
                System.out.println("Run Program -> Smart Light - " + getAlias() + "\n"
                    + "Smart Light - " + getAlias() + " is turned on now" + calendarToString());
                hasLightTurned = true;
            }
        }
    }

    /*
    getter and setter methods
     */
    public boolean isHasLightTurned() {
        return hasLightTurned;
    }

    public void setHasLightTurned(boolean hasLightTurned) {
        this.hasLightTurned = hasLightTurned;
    }

    Calendar getProgramTime() {
        return this.programTime;
    }

    private void setProgramTime(Calendar programTime) {
        this.programTime = programTime;
    }

    public boolean isProgramAction() {
        return programAction;
    }

    public void setProgramAction(boolean programAction) {
        this.programAction = programAction;
    }
}
