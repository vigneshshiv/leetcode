package code.java.design;

/**
 * https://leetcode.com/problems/design-parking-system/
 */
public class ParkingSystem {

    private int[] slots;

    public ParkingSystem(int big, int medium, int small) {
        this.slots = new int[] {big, medium, small};
    }

    public boolean addCar(int carType) {
        return this.slots[carType - 1]-- > 0;
    }

    public static void main(String[] args) {
        ParkingSystem parkingSystem = new ParkingSystem(1, 1, 0);
        System.out.println("Space available for CarType 1 - " + parkingSystem.addCar(1));
        System.out.println("Space available for CarType 2 - " + parkingSystem.addCar(2));
        System.out.println("Space available for CarType 3 - " + parkingSystem.addCar(3));
        System.out.println("Space available for CarType 1 - " + parkingSystem.addCar(1));
    }

}
