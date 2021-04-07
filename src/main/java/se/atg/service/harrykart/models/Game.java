package se.atg.service.harrykart.models;

import java.util.List;

/**
 *
 * @author YY
 */
public class Game {

    private final int laneDistance = 1000;
    private int numberOfLoops;
    private List<Participant> participants;
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
   
    @Override
    public String toString() {
        return "Game{" + "numberOfLoops=" + numberOfLoops + ", participants=" + participants + ", powerUps=" + powerUps + '}';
    }

}
