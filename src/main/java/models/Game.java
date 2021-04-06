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

    private int laneDistance = 1000;
    private int numberOfLoops;
    private List<Participant> participants;
    private List<Participant> ranking;
    private List<Integer> totalTimes;
    private PowerUp powerUps;

    public int getLaneDistance() {
        return laneDistance;
    }

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

    public List<Participant> getRanking() {
        return ranking;
    }

    public void setRanking(List<Participant> ranking) {
        this.ranking = ranking;
    }

    public List<Integer> getTotalTimes() {
        return totalTimes;
    }

    public void setTotalTimes(List<Integer> totalTimes) {
        this.totalTimes = totalTimes;
    }
   
    @Override
    public String toString() {
        return "Game{" + "numberOfLoops=" + numberOfLoops + ", participants=" + participants + ", powerUps=" + powerUps + '}';
    }

}
