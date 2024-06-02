package com.example.prueba.modelo;

public class Tipo {
    private int id;
    private String des;

    public Tipo(int id, String des) {
        this.id = id;
        this.des = des;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String ToString(){

        return des;
    }
}
