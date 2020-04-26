import java.util.Calendar;
/*
Subclass of the SmartObject and implements the interface Programmable
 */
public class SmartPlug extends SmartObject implements Programmable {
    /*
    private data fields for Smart Plug objects
     */
    private boolean status;
    private Calendar programTime = Calendar.getInstance();
    private boolean programAction;

    /*
    superclass constructor
     */
    SmartPlug(String alias, String mcId) {
        super(alias, mcId);
    }
    //turn on the smart plug object
    void turnOn(){
        if(controlConnection()){
            //check the connection first and print the messages according to status
            if(this.status){
                System.out.println("Smart Plug - " + getAlias() + " has been already turned on.");
            }else {
                System.out.println("Smart Plug - " + getAlias() + " is turned on now." + calendarToString());
                //update the status as true
                this.status = true;
            }
        }
    }
    //turnoff the lights and print appropriate messages
    void turnOff(){
        if(controlConnection()){
            if(this.status){
                System.out.println("Smart Plug - " + getAlias() + " is turned off now." + calendarToString());
                //update the status as false
                this.status = false;
            }else System.out.println("Smart Plug - "  + getAlias() +" has been already turned off.");
        }
    }
    //override abstract testObject method
    @Override
    boolean testObject() {
        //control connection and call the class's methods
        if(controlConnection()){
            SmartObjectToString();
            turnOn();
            turnOff();
            System.out.println("Test completed for SmartPlug");
            return true;
        }
        else return false;
    }
//override abstract superclass method and shut down the object as call the off methods
    @Override
    boolean shutDownObject() {
        if(controlConnection()){
            if(this.status){
                SmartObjectToString();
                setStatus(false);
                return true;
            }
        }
        return false;
    }

    /*
    override programmable interface methods
     */
    @Override
    public void setTimer(int seconds) {
            if(controlConnection()){
                this.programTime.set(Calendar.SECOND,Calendar.getInstance().get(Calendar.SECOND) + seconds);
                setProgramTime(programTime);
                if(this.status){
                    System.out.println("Smart Plug - "+getAlias()+" will be turned off "+seconds+" later!"+calendarToString());
                } else {
                    System.out.println("Smart Plug - "+getAlias()+" will be turned on "+seconds+" later!"+calendarToString());
                }
            }
    }
    /*
    Override the cancel timer method of the programmable interface
     */
    @Override
    public void cancelTimer() {
        if(controlConnection()){
            this.programTime = Calendar.getInstance();
        }

    }
    /*
    Override the run program method of the programmable interface
     */
    @Override
    public void runProgram() {
        //control connection first
        if(controlConnection()){
            //print the message according to status
            if(this.status){
                System.out.println("Run program -> Smart Plug "+  getAlias() +  "\n" +
                        "Smart Plug " + getAlias() +" is turned off now"+calendarToString());
                //when the process is done, update the status
                this.status = false;

            }else {
                System.out.println("Run program -> Smart Plug "+  getAlias() +  "\n" +
                        "Smart Plug " + getAlias() +" is turned on now"+calendarToString());
                this.status = true;
            }
        }
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

    public Calendar getProgramTime() {
        return this.programTime;
    }

    public void setProgramTime(Calendar programTime) {
        this.programTime = programTime;
    }

    public boolean isProgramAction() {
        return programAction;
    }

    public void setProgramAction(boolean programAction) {
        this.programAction = programAction;
    }
}
