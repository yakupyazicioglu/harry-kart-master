/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;

/**
 *
 * @author YY
 */
 
public class Game {
    private int numberOfLoops;
    private List<Participant> participants;
    private PowerUp powerUps;

    public int getNumberOfLoops() {
        return numberOfLoops;
    }

    public void setNumberOfLoops(int numberOfLoops) {
        this.numberOfLoops = numberOfLoops;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public PowerUp getPowerUps() {
        return powerUps;
    }

    public void setPowerUps(PowerUp powerUps) {
        this.powerUps = powerUps;
    }

    @Override
    public String toString() {
        return "Game{" + "numberOfLoops=" + numberOfLoops + ", participants=" + participants + ", powerUps=" + powerUps + '}';
    }

    
    
    

   
}
