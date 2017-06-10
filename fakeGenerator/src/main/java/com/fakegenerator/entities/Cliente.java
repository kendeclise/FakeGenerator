/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fakegenerator.entities;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
/**
 *
 * @author jbust
 */
@Entity
@Table(name = "clientes")
//@PrimaryKeyJoinColumn(name="dni")
public class Cliente extends Persona {

   
    private String direccion;
    @ManyToOne
    @JoinColumn(name = "id_distri")
    private Distrito distrito;
    private String telefono;
    private String email;
    private String notas;

    public Cliente() {
    }
 

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "Cliente{" +"dni="+dni+" nombres="+nombres+" "+ape_pat+" "+ape_mat+ ", direccion=" + direccion + ", distrito=" + distrito + ", telefono=" + telefono + ", email=" + email + ", notas=" + notas + '}';
    }

    


    
    
    
}
