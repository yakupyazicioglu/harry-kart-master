package se.atg.service.harrykart.models;

/**
 *
 * @author YY
 */
public class Participant {

    private String lane;
    private String name;
    private int baseSpeed;
    private int currentSpeed = 0;
    private int finishTime = 0;

    public Participant() {
    }
    
    public Participant(String lane, String name, int baseSpeed) {
        this.lane = lane;
        this.name = name;
        this.baseSpeed = baseSpeed;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }

    public void setBaseSpeed(int baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public String toString() {
        return "Participant{" + "lane=" + lane + ", name=" + name + ", baseSpeed=" + baseSpeed + '}';
    }

}
