/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fakegenerator.entities;

/**
 *
 * @author jbust
 */
public class TelefonoOrigen {
    private int id;
    private String desc;

    public TelefonoOrigen() {
    }

    public TelefonoOrigen(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "TelefonoOrigen{" + "id=" + id + ", desc=" + desc + '}';
    }
    
    
}
