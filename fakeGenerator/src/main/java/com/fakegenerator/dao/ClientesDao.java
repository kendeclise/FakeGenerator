/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fakegenerator.dao;

import com.fakegenerator.entities.ApellidoOrigen;
import com.fakegenerator.entities.Cliente;
import com.fakegenerator.entities.DireccionOrigen;
import com.fakegenerator.entities.NombreHombreOrigen;
import com.fakegenerator.entities.NombreMujerOrigen;
import com.fakegenerator.entities.Persona;
import com.fakegenerator.entities.TelefonoOrigen;
import com.fakegenerator.entities.Usuario;
import com.fakegenerator.util.conexionSQL2014;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author jbust
 */
public class ClientesDao {

    private Session session;

    public ClientesDao(Session session) {
        this.session = session;
    }

    public void cargarClientesFicticios(int numClientes) {
        Random random = new Random();
        //Variables
        String dni="";
        String nombres = "";
        String apellido_paterno = "";
        String apellido_materno = "";
        List<String> personasNomCompleto = new ArrayList<>();
        List<String> listaDnis = new ArrayList<>();

        for (int i = 0; i < numClientes; i++) {
            
            //Dni al azar
            while(true){
                dni = generarDniAleatorio(listaDnis);
                if(existeEnListaString(dni, listaDnis)){
                    listaDnis.add(dni);
                    break;
                }
            }
            
            
            //Si será un hombre(0) o una mujer(1)
            if (random.nextInt(2) == 0) {//Hombre
                while (true) {
                    nombres = generarPersonaHombre(listaNombresHombreOrigen());
                    apellido_paterno = generarApellidosPersona(listaApellidosOrigen())[0];
                    apellido_materno = generarApellidosPersona(listaApellidosOrigen())[1];
                    String nombreCompleto = nombres + " " + apellido_paterno + " " + apellido_materno;
                    if (existeEnListaString(nombreCompleto, personasNomCompleto)) {
                        personasNomCompleto.add(nombreCompleto);
                        break;
                    }

                }

            } else {//Mujer
                while (true) {
                    nombres = generarPersonaMujer(listaNombresMujerOrigen());
                    apellido_paterno = generarApellidosPersona(listaApellidosOrigen())[0];
                    apellido_materno = generarApellidosPersona(listaApellidosOrigen())[1];
                    String nombreCompleto = nombres + " " + apellido_paterno + " " + apellido_materno;
                    if (existeEnListaString(nombreCompleto, personasNomCompleto)) {
                        personasNomCompleto.add(nombreCompleto);
                        break;
                    }

                }
            }
            
            //Generando la entidad persona
            Persona persona = new Persona();
            persona.setDni(dni);
            persona.setNombres(nombres);
            persona.setApe_pat(apellido_paterno);
            persona.setApe_mat(apellido_materno);
            
            //Si son usuarios registrados (0 1 y 2) o no(3) ->75% de posibilidades que un usuario que compre se registre
            if (random.nextInt(4) != 3){
                //Generando su usuario, la contraseña es cliente (https://www.dailycred.com/article/bcrypt-calculator) encriptada en Bcrypt
                String pass = "$2a$04$Pe1dAbO/qdJwNN3SWaGjue1SSkOK5pBLldJqRFZ7OZeJEfbCBbIhu";
                
                Usuario usuario = new Usuario(dni, pass, true);
                //registrando al usuario en la bd
                registrarUsuario(usuario);
                //Asignando la cuenta a la persona respectiva
                persona.setUsuario(obtenerUnUsuarioPorUsername(usuario.getUsername()));
            }
            
            //registrando a la persona a la bd
            registrarPersona(persona);
            

        }
        
        
        
        //Impresión prueba     
        int c = 1;
        for(String s: personasNomCompleto ){
            System.out.println(c+": "+s);
            c++;
        }

    }

    public String generarPersonaHombre(List<NombreHombreOrigen> nombres) {
        String personaGenerada = "";
        Random randomGenerator = new Random();

        //Generamos un número aleatorio que nos dirá si la persona tendrá 1 o 2 nombres
        int numeroNombres = randomGenerator.nextInt(2);

        if (numeroNombres == 0) {
            int indice1 = randomGenerator.nextInt(nombres.size());
            int indice2 = randomGenerator.nextInt(nombres.size());
            while (true) {
                if (indice1 != indice2) {
                    personaGenerada += nombres.get(indice1).getNombre() + " " + nombres.get(indice2).getNombre();
                    break;
                } else {
                    indice2 = randomGenerator.nextInt(nombres.size());
                }
            }

        } else {
            personaGenerada += nombres.get(randomGenerator.nextInt(nombres.size())).getNombre();
        }

        return personaGenerada;
    }

    public String generarPersonaMujer(List<NombreMujerOrigen> nombres) {
        String personaGenerada = "";
        Random randomGenerator = new Random();

        //Generamos un número aleatorio que nos dirá si la persona tendrá 1 o 2 nombres
        int numeroNombres = randomGenerator.nextInt(2);

        if (numeroNombres == 0) {
            int indice1 = randomGenerator.nextInt(nombres.size());
            int indice2 = randomGenerator.nextInt(nombres.size());
            while (true) {
                if (indice1 != indice2) {
                    personaGenerada += nombres.get(indice1).getNombre() + " " + nombres.get(indice2).getNombre();
                    break;
                } else {
                    indice2 = randomGenerator.nextInt(nombres.size());
                }
            }

        } else {
            personaGenerada += nombres.get(randomGenerator.nextInt(nombres.size())).getNombre();
        }

        return personaGenerada;
    }

    public String[] generarApellidosPersona(List<ApellidoOrigen> apellidos) {
        String[] apellidosGenerados = new String[2];
        Random randomGenerator = new Random();

        int indice1 = randomGenerator.nextInt(apellidos.size());
        int indice2 = randomGenerator.nextInt(apellidos.size());

        apellidosGenerados[0] = apellidos.get(indice1).getApellido();
        apellidosGenerados[1] = apellidos.get(indice2).getApellido();

        return apellidosGenerados;
    }

    public boolean existeEnListaString(String cadena, List<String> lista) {
        boolean resultado = false;

        for (String s : lista) {
            if (s.equalsIgnoreCase(cadena)) {
                resultado = true;
                break;
            }
        }

        return true;
    }

    public List<ApellidoOrigen> listaApellidosOrigen() {
        List<ApellidoOrigen> lista = null;

        conexionSQL2014 c = conexionSQL2014.getInstance();
        c.setUrl(null);
        c.setUsuario(null);
        c.setClave(null);

        String sql = "select * from apellidos";
        try {
            Connection co = c.getConnection();
            PreparedStatement pst = co.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            lista = new ArrayList<>();

            while (rs.next()) {
                lista.add(new ApellidoOrigen(rs.getInt("id"), rs.getString("apellido")));
            }

            rs.close();
            pst.close();
            co.close();

        } catch (Exception e) {
            e.getStackTrace();
        }

        return lista;

    }

    public List<NombreHombreOrigen> listaNombresHombreOrigen() {
        List<NombreHombreOrigen> lista = null;

        conexionSQL2014 c = conexionSQL2014.getInstance();
        c.setUrl(null);
        c.setUsuario(null);
        c.setClave(null);

        String sql = "select * from nombres_hombre";
        try {
            Connection co = c.getConnection();
            PreparedStatement pst = co.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            lista = new ArrayList<>();

            while (rs.next()) {
                lista.add(new NombreHombreOrigen(rs.getInt("id"), rs.getString("nombre")));
            }

            rs.close();
            pst.close();
            co.close();

        } catch (Exception e) {
            e.getStackTrace();
        }

        return lista;

    }

    public List<NombreMujerOrigen> listaNombresMujerOrigen() {
        List<NombreMujerOrigen> lista = null;

        conexionSQL2014 c = conexionSQL2014.getInstance();
        c.setUrl(null);
        c.setUsuario(null);
        c.setClave(null);

        String sql = "select * from nombres_mujer";
        try {
            Connection co = c.getConnection();
            PreparedStatement pst = co.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            lista = new ArrayList<>();

            while (rs.next()) {
                lista.add(new NombreMujerOrigen(rs.getInt("id"), rs.getString("nombre")));
            }

            rs.close();
            pst.close();
            co.close();

        } catch (Exception e) {
            e.getStackTrace();
        }

        return lista;

    }

    public List<DireccionOrigen> listaDireccionesOrigen() {
        List<DireccionOrigen> lista = null;

        conexionSQL2014 c = conexionSQL2014.getInstance();
        c.setUrl(null);
        c.setUsuario(null);
        c.setClave(null);

        String sql = "select * from direcciones";
        try {
            Connection co = c.getConnection();
            PreparedStatement pst = co.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            lista = new ArrayList<>();

            while (rs.next()) {
                lista.add(new DireccionOrigen(rs.getInt("id"), rs.getString("descr")));
            }

            rs.close();
            pst.close();
            co.close();

        } catch (Exception e) {
            e.getStackTrace();
        }

        return lista;
    }

    public List<TelefonoOrigen> listaTelefonosOrigen() {
        List<TelefonoOrigen> lista = null;

        conexionSQL2014 c = conexionSQL2014.getInstance();
        c.setUrl(null);
        c.setUsuario(null);
        c.setClave(null);

        String sql = "select * from telefonos";
        try {
            Connection co = c.getConnection();
            PreparedStatement pst = co.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            lista = new ArrayList<>();

            while (rs.next()) {
                lista.add(new TelefonoOrigen(rs.getInt("id"), rs.getString("descr")));
            }

            rs.close();
            pst.close();
            co.close();

        } catch (Exception e) {
            e.getStackTrace();
        }

        return lista;
    }

    public boolean registrarPersona(Persona unaPersona) {
        boolean resultado = false;
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            session.save(unaPersona);

            tx.commit();
            resultado = true;

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error en el método [registrarPersona]: " + e);
        }

        return resultado;
    }

    public boolean registrarCliente(Cliente unCliente) {
        boolean resultado = false;
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            session.save(unCliente);

            tx.commit();
            resultado = true;

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error en el método [registrarCliente]: " + e);
        }

        return resultado;
    }
    
     public boolean registrarUsuario(Usuario unUsuario) {
        boolean resultado = false;
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            session.save(unUsuario);

            tx.commit();
            resultado = true;

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error en el método [registrarUsuario]: " + e);
        }

        return resultado;
    }
    
     public Usuario obtenerUnUsuarioPorUsername(String username) {
        Usuario unUsuario = null;

        try {

            unUsuario = session.load(Usuario.class, username);

        } catch (Exception e) {

            System.out.println("Error en el método [obtenerUnUsuarioPorUsername]: " + e);
        }

        return unUsuario;
    }


    public Persona obtenerUnaPersonaPorDni(String dni) {
        Persona unaPersona = null;

        try {

            unaPersona = session.load(Persona.class, dni);

        } catch (Exception e) {

            System.out.println("Error en el método [obtenerUnaPersonaPorId]: " + e);
        }

        return unaPersona;
    }

    public void eliminarDatosClientePersona(boolean b) {/* tener cuidado */ /* implementar otro eliminar para que solo elimine personas que son clientes */
        if (b) {

            Transaction tx = null;

            try {
                tx = session.beginTransaction();
                Query query = session.createQuery("DELETE  from Cliente");
                query.executeUpdate();

                tx.commit();

            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                System.out.println("Error en el método [eliminarDatosClientePersona], borrando clientes: " + e);
            }

            tx = null;

            try {
                tx = session.beginTransaction();
                Query query = session.createQuery("DELETE  from Persona");
                query.executeUpdate();

                tx.commit();

            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                System.out.println("Error en el método [eliminarDatosClientePersona], borrando personas: " + e);
            }

        }

    }

    public String generarDniAleatorio(List<String> listaDnis) {

        long dniInicial = 31529836;
        long dniFinal = 65895121;

        String dniGenerado = "";

        while (true) {
            dniGenerado = "" + randomLong(31529836, 65895121);
            //Si no existe en la lista
            if (existeEnListaString(dniGenerado, listaDnis)) {
                break;
            }
        }

        return dniGenerado;

    }

    public String generarDominioEmail() {
        String dominio = "";
        Random random = new Random();
        int azar = 1 + (int) (random.nextDouble() * (5 - 1));

        switch (azar) {
            case 1:
                dominio = "@gmail.com";
                break;
            case 2:
                dominio = "@hotmail.com";
                break;
            case 3:
                dominio = "@outlook.com";
                break;
            case 4:
                dominio = "@yahoo.es";
                break;
            default:
                dominio = "@yahoo.com";
                break;
        }
        return dominio;
    }

    public long randomLong(long min, long max) {
        try {
            Random random = new Random();
            long result = min + (long) (random.nextDouble() * (max - min));
            return result;
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return 0L;
    }

    public void cerrarSession() {
        session.close();
    }

}
