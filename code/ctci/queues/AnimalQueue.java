package code.ctci.queues;

import java.util.LinkedList;

public class AnimalQueue {

    private LinkedList<Animal> dogs = new LinkedList<>();
    private LinkedList<Animal> cats = new LinkedList<>();
    private int order = 0;

    /**
     * Enqueue animal
     */
    public void enqueue(Animal animal) {
        animal.setOrder(order);
        order++;
        if (animal instanceof Dog) {
            dogs.addLast((Dog) animal);
        } else if (animal instanceof Cat) {
            cats.addLast((Cat) animal);
        }
    }

    /**
     * Dequeue any item
     */
    public Animal dequeueAny() {
        if (dogs.size() == 0) {
            return dequeueCats();
        } else if (cats.size() == 0) {
            return dequeueDogs();
        }
        Dog dog = (Dog) dogs.peek();
        Cat cat = (Cat) cats.peek();
        if (dog.isOlderThan(cat)) {
            return dequeueDogs();
        } else {
            return dequeueCats();
        }
    }

    /**
     * Dequeue dogs
     */
    public Dog dequeueDogs() {
        return (Dog) dogs.poll();
    }

    /**
     * Dequeue cats
     */
    public Cat dequeueCats() {
        return (Cat) cats.poll();
    }

    public static void main(String[] args) {
        AnimalQueue animalQueue = new AnimalQueue();
        Animal cat1 = new Cat("Senpai");
        animalQueue.enqueue(cat1);
        Animal dog1 = new Dog("Kuzo");
        animalQueue.enqueue(dog1);
        Animal dog2 = new Dog("Nani");
        animalQueue.enqueue(dog2);
        //
        Animal dequeueAnyAnimal = animalQueue.dequeueAny();
        System.out.println("Dequeued animal - " + dequeueAnyAnimal.getName());
        //
        Animal dequeueDog = animalQueue.dequeueDogs();
        System.out.println("Dequeued animal - " + dequeueDog.getName());
    }

}

abstract class Animal {
    private int order;
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Order is used as a timestamp, so that we can compare insertion order of a Dog to a Cat.
     */
    public boolean isOlderThan(Animal other) {
        return this.order < other.order;
    }

}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }
}