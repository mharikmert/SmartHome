/*import some classes for the current time*/
import java.util.Calendar;
import java.util.Date;
/*abstract Smart Object class*/
public abstract class SmartObject {
    /*
    private data fields for every smart object
     */
    private String object;
    private String alias;
    private String mcId;
    private String IP;
    private String className;
    private boolean connectionStatus;
    /*
    overloaded constructor for smart object
     */
    SmartObject(String alias, String mcId){
        this.alias = alias;
        this.mcId = mcId;
    }
    /*
    connect method to set ip and connection status features as true.
     */
    public boolean connect(String IP){
            setIP(IP);
            setConnectionStatus(true);
        System.out.println(this.getAlias() + " connection established");
        return true;
    }
    /*
    disconnect method to cut the connection( connection status false) and sets the ip
     */
    public boolean disconnect(String IP) {
        this.setIP(IP);
        this.setConnectionStatus(false);
        return true;
    }
    /*
    toString method for properties of the smart object
     */
     void SmartObjectToString(){
        System.out.println("Test is starting for " + this.getClass().getSimpleName() + "\nThis is " + this.getClass().getSimpleName() + " device " + this.alias + "\n" +
                "      MacId: "+ this.mcId + "\n" + "      IP: " + this.IP);
    }
    /*
    control connection method using connection status
     */
    boolean controlConnection(){
        if(connectionStatus) return true;
        else {
            System.out.println("This device is not connected. "+ this.object + " -> " + this.alias);
            return false;
        }
    }
    /*
    abstract methods to be used other class
     */
    abstract boolean testObject();
    abstract boolean shutDownObject();

    /*
    to print current time as string
     */
    String calendarToString(){
        Date date = Calendar.getInstance().getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return "(Current time : " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + ")";
    }
    /*
    getter and setter methods
     */
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getMcId() {
        return mcId;
    }

    public void setMcId(String mcId) {
        this.mcId = mcId;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public boolean isConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(boolean connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public String className() {
        return this.getAlias().getClass().getSimpleName();
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
