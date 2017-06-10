/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fakegenerator.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 *
 * @author jbust
 */
//@MappedSuperclass
@Entity
@Table(name = "personas")
@Inheritance(strategy=InheritanceType.JOINED)
public class Persona {
    
    @Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY) para autoincrementables
    protected String dni;
    
    
    
    //	@Column(name="TIPO_TRAM") <- si los campos aquí no tienen el mismo nombre que las columnas en la tabla
    protected String nombres;
    protected String ape_pat;
    protected String ape_mat;
    
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "username")
    private Usuario usuario;
    
  


    public Persona() {
    }

    
    

    public Persona(String dni, String nombres, String ape_pat, String ape_mat) {
        this.dni = dni;
        this.nombres = nombres;
        this.ape_pat = ape_pat;
        this.ape_mat = ape_mat;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApe_pat() {
        return ape_pat;
    }

    public void setApe_pat(String ape_pat) {
        this.ape_pat = ape_pat;
    }

    public String getApe_mat() {
        return ape_mat;
    }

    public void setApe_mat(String ape_mat) {
        this.ape_mat = ape_mat;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Persona{" + "dni=" + dni + ", nombres=" + nombres + ", ape_pat=" + ape_pat + ", ape_mat=" + ape_mat + ", usuario=" + usuario + '}';
    }

    
    

    

}
