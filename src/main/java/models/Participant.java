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
public class Participant {
    private String lane;
    private String name;
    private int baseSpeed;

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

    @Override
    public String toString() {
        return "Participant{" + "lane=" + lane + ", name=" + name + ", baseSpeed=" + baseSpeed + '}';
    }
   
}
