package edu.upc.essi.gps.utils;

import edu.upc.essi.gps.domain.HasName;

public final class Matchers {

    private Matchers() {
    }

    public static Matcher<HasName> nameMatcher(final String name) {
        return new Matcher<HasName>() {
            @Override
            public boolean matches(HasName named) {
                return named.getNom().equals(name);
            }
        };
    }

    public static Matcher<Object> all = new Matcher<Object>() {
        @Override
        public boolean matches(Object entity) {
            return true;
        }
    };
}



