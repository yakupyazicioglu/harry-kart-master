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
public class Horse implements Comparable< Horse> {

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

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public String toString() {
        return "Ranking{" + "position=" + position + ", horse=" + name + '}';
    }


    @Override
    public int compareTo(Horse h) {
        return this.getFinishTime() - h.getFinishTime();
    }
}
