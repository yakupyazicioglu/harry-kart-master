package se.atg.service.harrykart.models;

/**
 *
 * @author YY
 */
public class Rank implements Comparable< Rank> {

    private String name;
    private int position = 0;
    private Integer finishTime = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Integer getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Integer finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public int compareTo(Rank h) {
        return this.getFinishTime() - h.getFinishTime();
    }

    @Override
    public String toString() {
        return "{position=" + position + ", horse=" + name + '}';
    }

}
