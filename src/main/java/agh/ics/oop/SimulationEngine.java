package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

class SimulationEngine implements IEngine {

    private final MoveDirection[] movesArray;

    private final List<Animal> animals;

    public SimulationEngine(MoveDirection[] movesArray, IWorldMap map, Vector2d[] initialAnimalsPositions) {
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
            getAnimal(k).move(moveDirection);
            k += 1;
        }
    }
}
