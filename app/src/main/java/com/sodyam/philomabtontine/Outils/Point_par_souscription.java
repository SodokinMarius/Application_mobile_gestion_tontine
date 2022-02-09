package com.sodyam.philomabtontine.Outils;

public class Point_par_souscription {
    private Integer souscription, nombre_client;

    public Point_par_souscription(Integer souscription, Integer nombre_client) {
        this.souscription = souscription;
        this.nombre_client = nombre_client;
    }

    public Integer getSouscription() {
        return souscription;
    }

    public void setSouscription(Integer souscription) {
        this.souscription = souscription;
    }

    public Integer getNombre_client() {
        return nombre_client;
    }

    public void setNombre_client(Integer nombre_client) {
        this.nombre_client = nombre_client;
    }

    @Override
    public String toString() {
        return "Point_par_souscription{" +
                "souscription=" + souscription +
                ", nombre_client=" + nombre_client +
                '}';
    }
}
