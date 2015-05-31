/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.ed.spark;

import com.mongodb.*;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Javier
 */
public class MongoDB_Access <T extends Song>{
    private DBCollection collection;
    
    public MongoDB_Access() {

        try {
            MongoClient mongoClient = new MongoClient("localhost:4567");
            DB db = mongoClient.getDB("sparkledb");
            collection = db.getCollection("Favoritos");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Boolean create(T entity) {
        BasicDBObject doc = new BasicDBObject("id",entity.getId()).
                append("Nombre", entity.getName()).
                append("Autor", entity.getAuthor()).
                append("Duracion", entity.getLength());

        collection.insert(doc);
        return true;
    }
    
    public T readOne(int id) {
        BasicDBObject query = new BasicDBObject("id", id);
        DBCursor cursor = collection.find(query);

        try {
            if(cursor.hasNext()) {
                BasicDBObject doc = (BasicDBObject) cursor.next();
                Song entity = new Song(
                      doc.getInt("id"),
                      doc.getString("Nombre"),
                      doc.getString("Autor"),
                      doc.getString("Duracion")
                );

                return (T) entity;
            } else {
                return null;
            }
        } finally {
            cursor.close();
        }
    }
    
    public ArrayList<T> readAll() {
        DBCursor cursor = collection.find();

        ArrayList<Song> results = (ArrayList<Song>) new ArrayList<T>();

        try {
            while(cursor.hasNext()) {
                BasicDBObject doc = (BasicDBObject) cursor.next();

                Song entity = new Song(
                      doc.getInt("id"),
                      doc.getString("Nombre"),
                      doc.getString("Autor"),
                      doc.getString("Duracion")
                );
                results.add(entity);
            }

            return (ArrayList<T>) results;
        } finally {
            cursor.close();
        }
    }
    
    public Boolean update(int id, String Nombre, String Autor, String Duracion) {
        BasicDBObject query = new BasicDBObject("id", id);
        DBCursor cursor = collection.find(query);

        try {
            if(cursor.hasNext()) {
                BasicDBObject doc = (BasicDBObject) cursor.next();
                doc.put("Nombre", Nombre);
                doc.put("Autor", Autor);
                doc.put("Duracion", Duracion);
                collection.save(doc);
                return true;
            } else {
                return false;
            }
        } finally {
            cursor.close();
        }
    }
    
    public Boolean delete(int id) {
        BasicDBObject query = new BasicDBObject("id", id);

        DBCursor cursor = collection.find(query);

        try {
            if(cursor.hasNext()) {
                collection.remove(cursor.next());
                return true;
            } else {
                return false;
            }
        } finally {
            cursor.close();
        }
    }
    
}
