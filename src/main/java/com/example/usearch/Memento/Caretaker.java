package com.example.usearch.Memento;

import java.util.ArrayList;

/**
 * Clase que se encarga de guardar los mementos
 */
public class Caretaker {

    private ArrayList<Memento> mementos = new ArrayList<>();

    public Memento getMementoIndice(int index) {
        return mementos.get(index);
    }

    public ArrayList<Memento> getMementos() {
        return mementos;
    }

    /**
     * Metodo que obtiene los mementos en formato string
     * @return lista de mementos en formato string
     */
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

    /**
     * Metodo que añade un memento a la lista de mementos
     * @param memento memento a añadir
     */
    public void addMemento(Memento memento){
        mementos.add(memento);
    }
}
