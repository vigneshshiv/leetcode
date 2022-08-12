package code.ctci.dynamic_programming;

import java.util.Stack;

public class HanoiTower {

    private String name;
    private Stack<Integer> disks;

    public HanoiTower(String name) {
        this.name = name;
        disks = new Stack<>();
    }

    public void add(int disk) {
        if (!disks.isEmpty() && disks.peek() <= disk) {
            System.out.println("Error placing disk " + disk + " in " + name + " tower.");
        } else {
            disks.push(disk);
        }
    }

    public void moveTopTo(HanoiTower tower) {
        int disk = disks.pop();
        tower.add(disk);
    }

    public void moveDisks(int quantity, HanoiTower destination, HanoiTower buffer) {
        if (quantity <= 0) return;
        moveDisks(quantity - 1, buffer, destination);
        System.out.println("Move " + disks.peek() + " from " + this.name + " to " + destination.name);
        moveTopTo(destination);
        buffer.moveDisks(quantity - 1, destination, this);
    }

    public void print() {
        System.out.println(name + " Tower disks - " + disks.toString());
    }

    public static void main(String[] args) {
        HanoiTower source = new HanoiTower("source");
        HanoiTower buffer = new HanoiTower("buffer");
        HanoiTower destination = new HanoiTower("destination");
        int noOfDisks = 3;
        for (int disk = noOfDisks; disk > 0; disk--) {
            source.add(disk);
        }
        source.print();
        source.moveDisks(noOfDisks, destination, buffer);
        destination.print();
    }

}
