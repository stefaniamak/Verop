package com.example.simpleeshop;

import java.util.ArrayList;

// Observer Pattern
public class UiRefresher {
    public interface RefreshListener {
        void refreshUi();
    }

    private static UiRefresher instance;

    public static UiRefresher Instance() {
        if(instance == null)
            instance = new UiRefresher();
        return instance;
    }

    ArrayList<RefreshListener> listeners;

    public UiRefresher(){
        listeners = new ArrayList<>();
    }

    public void refreshUis() {
        for(RefreshListener listener : listeners)
            listener.refreshUi();
    }

    public void addListener(RefreshListener listener) {
        listeners.add(listener);
    }
}
