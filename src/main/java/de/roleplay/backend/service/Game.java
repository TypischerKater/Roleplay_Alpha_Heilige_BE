package de.roleplay.backend.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game implements Runnable {

    protected String status;

    protected int progress;
    private UUID id = UUID.randomUUID();

    private List<GameObserver> observers = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void run() {
        for (int i = 0; i <= 1000; i++) {
            // Perform some task
            progress = i;
            status = "your current nuber is: " + i;
            notifyObservers();
            // Sleep for 100ms
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.status = "closed";
        notifyObservers();
    }

    public int getProgress() {
        return progress;
    }

    public void registerObserver(GameObserver observer) {
        observers.add(observer);
    }

    public String getStatus() {
        return status;
    }

    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (GameObserver observer : observers) {
            try {
                observer.update(this);
            }catch (IOException e){
                System.out.println("closing of wss not worked");
            }

        }
    }
}
