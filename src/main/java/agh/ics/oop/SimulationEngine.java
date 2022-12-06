package agh.ics.oop;

import agh.ics.oop.gui.IAppObserver;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine, Runnable {

    private final IWorldMap map;
    private MoveDirection[] movesArray;
    private final List<Animal> animals;
    private List<IAppObserver> observers = new ArrayList<>();
    private int moveDelay;

    public SimulationEngine(IWorldMap map, Vector2d[] initialAnimalsPositions) {
        this.map = map;
        this.animals = new ArrayList<>();

        for (Vector2d position: initialAnimalsPositions) {
            Animal animal = new Animal(map, position);
            if (map.place(animal)) {
                animals.add(animal);
            }
        }
    }

    public void setDirections(MoveDirection[] movesArray) {
        this.movesArray = movesArray;
    }

    public SimulationEngine(MoveDirection[] movesArray, IWorldMap map, Vector2d[] initialAnimalsPositions) {
        this.map = map;
        this.movesArray = movesArray;
        this.animals = new ArrayList<>();

        for (Vector2d position: initialAnimalsPositions) {
            Animal animal = new Animal(map, position);
            if (map.place(animal)) {
                animals.add(animal);
            }
        }
    }
    public Animal getAnimal(int i) {
        return animals.get(i);
    }

    @Override
    public void run() {
        int numOfAnimals = animals.size();
        int k = 0;
        for (MoveDirection moveDirection: movesArray) {
            k = k % numOfAnimals;
            Animal animal = getAnimal(k);
            animal.move(moveDirection);
            k += 1;
            for (IAppObserver observer: observers) {
                observer.positionChanged();
            }
            try {
                Thread.sleep(moveDelay);
            } catch (InterruptedException exception) {
                System.out.println("Simulations stopped: " + exception);
            }
        }
    }

    public void setDelay(int delay) {
        moveDelay = delay;
    }

    public void addObserver(IAppObserver observer) {
        observers.add(observer);
    }
}
