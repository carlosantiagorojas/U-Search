package com.example.usearch.Memento;

import java.util.ArrayList;

public class Caretaker {

    private ArrayList<Memento> mementos = new ArrayList<>();

    public ArrayList<Memento> getMementos() {
        return mementos;
    }

    public void setMementos(ArrayList<Memento> mementos) {
        this.mementos = mementos;
    }
}
