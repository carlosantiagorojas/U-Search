package com.example.usearch.Memento;

import java.util.ArrayList;

public class Caretaker {

    private ArrayList<Memento> mementos = new ArrayList<>();

    public Memento getMementoIndice(int index) {
        return mementos.get(index);
    }

    public ArrayList<Memento> getMementos() {
        return mementos;
    }

    public ArrayList<String> getMementosString() {
        ArrayList<String> mementosString = new ArrayList<>();
        for (Memento memento : this.mementos) {
            // Añadir la informacion de la consulta en formato string
            mementosString.add(memento.getConsulta().obtenerConsulta());
        }
        return mementosString;
    }

    public void setMementos(ArrayList<Memento> mementos) {
        this.mementos = mementos;
    }

    public void addMemento(Memento memento){
        mementos.add(memento);
    }
}
