package code.java.graphs.social_network;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private int personID;
    private String info;
    private List<Integer> friends;

    public Person(int id) {
        this.personID = id;
        this.friends = new ArrayList<>();
    }

    public int getID() {
        return this.personID;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void addFriend(int id) {
        this.friends.add(id);
    }

    public List<Integer> getFriends() {
        return this.friends;
    }

}
