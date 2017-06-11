/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fakegenerator.entities;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jbust
 */
@Entity
@Table(name = "empleados")
public class Empleado extends Persona {

    @OneToMany(mappedBy = "empleado")//hace referencia a la variable relacionada en la otra tabla
    private Set<OrdenPago> ordenesPago;

    public Empleado() {
    }

    public Set<OrdenPago> getOrdenesPago() {
        return ordenesPago;
    }

    public void setOrdenesPago(Set<OrdenPago> ordenesPago) {
        this.ordenesPago = ordenesPago;
    }

    @Override
    public String toString() {
        return "Persona{" + "dni=" + dni + ", nombres=" + nombres + ", ape_pat=" + ape_pat + ", ape_mat=" + ape_mat + ", telefono=" + telefono + ", email=" + email + '}';
    }
}
