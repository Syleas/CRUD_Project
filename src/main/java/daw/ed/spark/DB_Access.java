/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.ed.spark;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Javier
 */
public class DB_Access {
    Connection conexionBD;
    
    public DB_Access() {
        conexionBD = null;
    }
    
    public void abrirConexionBD() {
        if (conexionBD == null) {
            String tablaConexionBD = "jdbc:mysql://localhost/CRUD_favoritos"; //Conexión con nuestra BD
            try {
                Class.forName("com.mysql.jdbc.Driver"); //El driver de mysql
                //Conexión teniendo en cuenta el usuario y clave de administrador de la BD
                conexionBD = DriverManager.getConnection(tablaConexionBD, "root", "root");
            } catch (Exception e) {
                //Error en la conexión con la BD
                System.out.println("No se ha completado la peticion...");
                Logger.getLogger(DB_Access.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    
    public void createFavorito(String name, String author, String length) {
        if (conexionBD == null) //Se comprueba que la conexión con la BD esté abierta
        {
            abrirConexionBD();
        }
            try {
                ResultSet res;
                String query;
                Statement s = conexionBD.createStatement();
                query = "INSERT INTO `favoritos` (Nombre, Autor, Duracion) VALUES ('" + name + "' , '" + author + "' , '" + length + "')";
                s.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(DB_Access.class.getName()).log(Level.SEVERE, null, ex);
            }        
    }
    
    public void updateFavorito(String nameP, String authorP, String lengthP, String name, String author, String length) {
        if (conexionBD == null) //Se comprueba que la conexión con la BD esté abierta
        {
            abrirConexionBD();
        }
            try {
                ResultSet res;
                String query;
                Statement s = conexionBD.createStatement();
                query = "UPDATE `favoritos` SET favoritos.Nombre='" + name + "', favoritos.Autor='"+ author +"', favoritos.Duracion='"+ length +"' WHERE favoritos.Nombre='" + nameP + "' AND favoritos.Autor='"+authorP+"' AND favoritos.Duracion='"+lengthP+"'";
                s.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(DB_Access.class.getName()).log(Level.SEVERE, null, ex);
            }        
    }
    
    public void deleteFavorito(String nameP, String authorP, String lengthP) {
        if (conexionBD == null) //Se comprueba que la conexión con la BD esté abierta
        {
            abrirConexionBD();
        }
        try {
            String query;
            Statement s = conexionBD.createStatement();
            query = "DELETE FROM `favoritos` WHERE favoritos.Nombre='" + nameP + "' AND favoritos.Autor='"+authorP+"' AND favoritos.Duracion='"+lengthP+"'";
            s.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DB_Access.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet getFavoritos() {
        if (conexionBD == null) //Se comprueba que la conexión con la BD esté abierta
        {
            abrirConexionBD();
        }
        ResultSet resultados = null;
        try {
            String con;
            Statement s = conexionBD.createStatement();
            con = "SELECT * FROM `favoritos`";
            resultados = s.executeQuery(con);
            return resultados;
        } catch (Exception e) {
            //Error en la conexión con la BD
            System.out.println("No se ha completado la peticion...");
            return resultados;
        }
    }
}
