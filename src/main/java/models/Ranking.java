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
public class Ranking implements Comparable< Ranking> {

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
    public int compareTo(Ranking h) {
        return this.getFinishTime() - h.getFinishTime();
    }

    @Override
    public String toString() {
        return "{position=" + position + ", horse=" + name + '}';
    }

}
