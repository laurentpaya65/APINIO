package org.epita.comptage;

import java.util.Comparator;
import java.util.Map;

class Comparateur implements Comparator<String> {

    private static Map<String, Integer> base;

    public Comparateur(Map<String, Integer> base) {
        this.base = base;
    }

    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        }
    }
}
