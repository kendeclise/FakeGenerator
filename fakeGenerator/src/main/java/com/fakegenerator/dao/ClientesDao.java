/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fakegenerator.dao;

import com.fakegenerator.entities.ApellidoOrigen;
import com.fakegenerator.entities.Cliente;
import com.fakegenerator.entities.DireccionOrigen;
import com.fakegenerator.entities.Distrito;
import com.fakegenerator.entities.NombreHombreOrigen;
import com.fakegenerator.entities.NombreMujerOrigen;
import com.fakegenerator.entities.Persona;
import com.fakegenerator.entities.Rol;
import com.fakegenerator.entities.TelefonoOrigen;
import com.fakegenerator.entities.Usuario;
import com.fakegenerator.util.conexionSQL2014;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    public List<String> cargarClientesFicticios(int numClientes) {
        Random random = new Random();

        //Variables
        String dni = "";
        String nombres = "";
        String apellido_paterno = "";
        String apellido_materno = "";
        String direccion = "";
        String telefono = "";
        String email = "";
        List<String> personasNomCompleto = new ArrayList<>();
        List<String> listaDnis = new ArrayList<>();

        for (int i = 0; i < numClientes; i++) {

            //Dni al azar
            while (true) {
                dni = generarDniAleatorio(listaDnis);
                if (existeEnListaString(dni, listaDnis)) {
                    listaDnis.add(dni);
                    break;
                }
            }

            //Si será un hombre(0) o una mujer(1) 
            if (random.nextInt(2) == 0) {//Hombre -> 50% de posibilidades de que el cliente sea hombre
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

            } else {//Mujer -> 50% de posibilidades de que el cliente sea mujer
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
            Cliente cliente = new Cliente();
            cliente.setDni(dni);
            cliente.setNombres(nombres);
            cliente.setApe_pat(apellido_paterno);
            cliente.setApe_mat(apellido_materno);

            //Generando su usuario, la contraseña es cliente (https://www.dailycred.com/article/bcrypt-calculator) encriptada en Bcrypt
            String pass = "$2a$04$Pe1dAbO/qdJwNN3SWaGjue1SSkOK5pBLldJqRFZ7OZeJEfbCBbIhu";

            Usuario usuario = new Usuario(dni, pass, true);
            Rol unRol = obtenerUnRolByid(4);
            List<Rol> roles = new ArrayList<>();
            roles.add(unRol);
            usuario.setRoles(roles);
            //registrando al usuario en la bd
            registrarUsuario(usuario);
            //Asignando la cuenta a la persona respectiva
            cliente.setUsuario(obtenerUnUsuarioPorUsername(usuario.getUsername()));

            

            //Creando datos para la tabla cliente(los campos (direccion y distrito), telefono, email pueden ser nulos, para este generador pondremos el 33% d probabilidades que no
            //Se llenen al registrar
          

            //Dirección al azar    - se puede repetir, ya que pueden vivir en una misma casa 2 clientes distintos 
            int randomD = randomInt(0, 2);

            if (randomD != 0) {
                direccion = generarDireccion(listaDireccionesOrigen());

                //Distrito al azar(actualmente hay 1831 distritos) 1-1831
                int distritoId = randomInt(1, 1831);

                cliente.setDireccion(direccion);
                cliente.setDistrito(obtenerUnDistritoPorId(distritoId));

            }

            //Teléfono al azar - se puede repetir
            randomD = randomInt(0, 2);
            if (randomD != 0) {
                telefono = generarTelefono(listaTelefonosOrigen());
                cliente.setTelefono(telefono);
            }

            //Correo al azar
            randomD = randomInt(0, 2);
            if (randomD != 0) {
                email = nombres.substring(0,3)+"_"+apellido_paterno.substring(0, 1)+apellido_materno+"_"+dni+generarDominioEmail();
                cliente.setEmail(email);
                //System.out.println("email: "+email);
            }
            
            //registrando el registro cliente
            //System.out.println(cliente);
            registrarCliente(cliente);

        }

        
        return listaDnis;

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

    public String generarDireccion(List<DireccionOrigen> direcciones) {
        String direccionGenerada = "";

        int indexAleatorio = randomInt(0, direcciones.size() - 1);

        direccionGenerada = direcciones.get(indexAleatorio).getDesc();

        return direccionGenerada;
    }

    public String generarTelefono(List<TelefonoOrigen> telefonos) {
        String telefonoGenerado = "";

        int indexAleatorio = randomInt(0, telefonos.size() - 1);

        telefonoGenerado = telefonos.get(indexAleatorio).getDesc();

        return telefonoGenerado;
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
    
    public Rol obtenerUnRolByid(int id) {
        Rol unRol = null;

        try {

            unRol = session.load(Rol.class, id);

        } catch (Exception e) {

            System.out.println("Error en el método [obtenerUnRolByid]: " + e);
        }

        return unRol;
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

    public Distrito obtenerUnDistritoPorId(int id) {
        Distrito unDistrito = null;

        try {

            unDistrito = session.load(Distrito.class, id);

        } catch (Exception e) {

            System.out.println("Error en el método [obtenerUnDistritoPorId]: " + e);
        }

        return unDistrito;
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
    
    public List<Cliente> listaClientes(){
        List<Cliente> clientes = null;

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Cliente> criteria = builder.createQuery(Cliente.class);
        Root<Cliente> root = criteria.from(Cliente.class);
        //Añadiendo lo que queremos que contenga nuestro query de categoría
        criteria.select(root);
        clientes = session.createQuery(criteria).getResultList();

        return clientes;
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
        int azar = randomInt(1, 5);

        switch (azar) {//40% de posiblidades de que el dominio de los correos sea gmail,20% sea hotmail, 20% sea outlook y 20% sea yahoo
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
                dominio = "@gmail.com";
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

    public int randomInt(int min, int max) {
        try {
            Random random = new Random();
            int result = min + (int) (random.nextDouble() * (max - min));
            return result;
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return 0;
    }

    public static String getFechaFormateadaHoras(java.util.Date fechaDate) {

        Timestamp fecha = new Timestamp(fechaDate.getTime());

        SimpleDateFormat formateador = new SimpleDateFormat("_dd-MM-yyyy_HH-mm-ss");

        return (formateador.format(fecha.getTime()));

    }
    
    public long getDateDiff(Date dateOne, Date dateTwo){
    long timeOne = dateOne.getTime();
    long timeTwo = dateTwo.getTime();
    long oneDay = 1000 * 60 * 60 * 24;
    long delta = (timeTwo - timeOne) / oneDay;

    if (delta > 0) {
        return delta;
    }
    else {
        delta *= -1;
        return delta;
     }
}

    public void cerrarSession() {
        session.close();
    }

}
