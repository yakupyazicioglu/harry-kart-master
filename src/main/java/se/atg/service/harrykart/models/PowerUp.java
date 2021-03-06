package se.atg.service.harrykart.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author YY
 */
public class PowerUp {

    private HashMap<Integer, List<Integer>> loopsMap = new HashMap<Integer, List<Integer>>();

    public PowerUp() {
        List<Integer> firstLoop = new ArrayList<>(Arrays.asList(0, 0, 0, 0));
        loopsMap.put(0, firstLoop);
    }

    public HashMap<Integer, List<Integer>> getLoopsMap() {
        return loopsMap;
    }

    public void setLoopsMap(HashMap<Integer, List<Integer>> loopsMap) {
        this.loopsMap = loopsMap;
    }

    @Override
    public String toString() {
        return "PowerUp{" + "loopsMap=" + loopsMap + '}';
    }

}
