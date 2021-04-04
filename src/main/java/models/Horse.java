/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
/**
 *
 * @author YY
 */
public class Horse {
    private String hName;  
    private int baseSpeed;  
    private int currentSpeed;  
    private int powerChange; 
    private int totalTime = 0;

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
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

    public int getPowerChange() {
        return powerChange;
    }

    public void setPowerChange(int powerChange) {
        this.powerChange = powerChange;
    }
    
    
}
