/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fakegenerator.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author jbust
 */
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    private String username;
    private String password;
    private boolean enabled;
    
    @OneToOne(fetch=FetchType.EAGER, mappedBy="usuario")
    private Persona personas;

    //Usuarios y Roles tienen una relación de muchos a muchos , pero la tabla usuario_roles no tiene datos adicionales aparte de las primary key asi que lo relacionamos solo 
    //con Listas
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "roles_usuario",
            joinColumns = {
                @JoinColumn(name = "username")},
            inverseJoinColumns = {
                @JoinColumn(name = "id_rol")})
    private List<Rol> roles = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Usuario{" + "username=" + username + ", password=" + password + ", enabled=" + enabled + ", roles=" + roles + '}';
    }

}
