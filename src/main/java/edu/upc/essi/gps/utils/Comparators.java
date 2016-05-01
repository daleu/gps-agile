package edu.upc.essi.gps.utils;

import edu.upc.essi.gps.domain.Entity;
import edu.upc.essi.gps.domain.HasName;
import java.util.Comparator;

public final class Comparators {

    private Comparators() {
    }

    public static Comparator<HasName> byName = new Comparator<HasName>() {

        @Override
        public int compare(HasName o1, HasName o2) {
            return o1.getNom().compareTo(o2.getNom());
        }

    };


    public static Comparator<Entity> byId = new Comparator<Entity>() {
        @Override
        public int compare(Entity o1, Entity o2) {
            return new Integer(o1.getId()).compareTo(o2.getId());
        }
    };
}
