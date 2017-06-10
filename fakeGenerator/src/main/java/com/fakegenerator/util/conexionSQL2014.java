package com.fakegenerator.util;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionSQL2014 {

    //Con Patrón Singleton
    private static conexionSQL2014 instanciaBD;
    private String url;
    private String usuario;
    private String clave;

    private conexionSQL2014() {
    }

    private synchronized static void createInstance() {
        if (instanciaBD == null) {
            instanciaBD = new conexionSQL2014();
        }
    }

    public static conexionSQL2014 getInstance() {
        if (instanciaBD == null) {
            createInstance();
        }

        return instanciaBD;

    }

    public Connection getConnection() throws SQLException {
        Connection c = null;
        DriverManager.registerDriver(new SQLServerDriver());
        c = DriverManager.getConnection(
                this.url,
                this.usuario,
                this.clave);
        return c;
    }

    public void setUrl(String url) {
        if (url == null) {
            this.url = "jdbc:sqlserver://localhost;databaseName=fakeGeneratorDB;";
        } else {
            this.url = url;
        }

    }

    public void setUsuario(String usuario) {
        if (usuario == null) {
            this.usuario = "sa";
        } else {
            this.usuario = usuario;
        }
    }

    public void setClave(String clave) {
        if (clave == null) {
            this.clave = "sql";
        } else {
            this.clave = clave;
        }
    }

}
