package edu.upc.essi.gps.utils;

public interface Matcher<T> {
    public boolean matches(T entity);
}

class AllMatcher implements Matcher<Object> {

    @Override
    public boolean matches(Object entity) {
        return true;
    }
}

