package com.example.tp3.model;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.math.BigDecimal;

public class Producto {
    private String codigo;
    private String descripcion;
    private double precio;

    public Producto(String codigo, String descripcion, double precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Producto() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Producto)){
            return false;
        }
        Producto p = (Producto) obj;
        if(p.getCodigo().equals(this.codigo)){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}


