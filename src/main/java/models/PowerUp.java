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
public class PowerUp {
    private int loopNo;
    private int laneNo;
    private int power;

    public int getLoopNo() {
        return loopNo;
    }

    public void setLoopNo(int loopNo) {
        this.loopNo = loopNo;
    }

    public int getLaneNo() {
        return laneNo;
    }

    public void setLaneNo(int laneNo) {
        this.laneNo = laneNo;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "PowerUp{" + "loopNo=" + loopNo + ", laneNo=" + laneNo + ", power=" + power + '}';
    }
    
    
    
}
