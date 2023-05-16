package com.example.usearch.Memento;

import com.example.usearch.Logica.Consulta;

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

    public Originator restoreFromMemento(Memento memento){
        consulta = memento.getConsulta();
        return this;
    }
}
