package com.example.usearch.Memento;

import com.example.usearch.Entidades.Consulta;

/**
 * Clase que origina el memento y restaura el memento
 */
public class Originator {

    private Consulta consulta;

    public Originator() {
    }

    public Originator(Consulta consulta) {
        this.consulta = consulta;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public Memento createMemento(){
        return new Memento(consulta);
    }

    /**
     * Restaura el memento
     * @param memento memento a restaurar
     * @return originator
     */
    public Originator restoreFromMemento(Memento memento){
        consulta = memento.getConsulta();
        return this;
    }
}
