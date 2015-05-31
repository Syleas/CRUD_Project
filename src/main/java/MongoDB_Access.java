import com.mongodb.*;

import java.sql.Date;
import java.util.ArrayList;

public class MongoDB_Access<T extends Song>{
    private DBCollection collection;

    public MongoDB_Access() {
        try {
            MongoClient mongoClient = new MongoClient("localhost");
            DB db = mongoClient.getDB("sparkledb");
            collection = db.getCollection("Songs");
            System.out.println("Connecting to MongoDB@" + mongoClient.getAllAddress());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Boolean create(T entity) {
        BasicDBObject doc = new BasicDBObject("Name", entity.getName()).
                append("id", entity.getId()).
                append("Author", entity.getAuthor()).
                append("Length", entity.getLength()).
                append("deleted", false).
                append("createdAt", new Date(new java.util.Date().getTime()));
        collection.insert(doc);
        return true;
    }

    @SuppressWarnings("unchecked")
    public T readOne(int id) {        
        BasicDBObject query = new BasicDBObject("id", id);
        DBCursor cursor = collection.find(query);
        try {
            if(cursor.hasNext()) {
                BasicDBObject doc = (BasicDBObject) cursor.next();
                Song entity = new Song(
                      doc.getString("Name"),
                      doc.getString("Author"),
                      doc.getString("Length"),
                      doc.getInt("id"),
                      doc.getDate("createdAt"),
                      doc.getBoolean("deleted")
                );
                return (T) entity;
            } else {
                return null;
            }
        } finally {
            cursor.close();
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<T> readAll() {
        DBCursor cursor = collection.find();
        ArrayList<Song> results = (ArrayList<Song>) new ArrayList<T>();
        try {
            while(cursor.hasNext()) {
                BasicDBObject doc = (BasicDBObject) cursor.next();
                Song entity = new Song(
                        doc.getString("Name"),
                        doc.getString("Author"),
                        doc.getString("Length"),
                        doc.getInt("id"),
                        doc.getDate("createdAt"),
                        doc.getBoolean("deleted")
                );
                results.add(entity);
            }
            return (ArrayList<T>) results;
        } finally {
            cursor.close();
        }
    }

    public Boolean update(int id, String Name, String Author, String Length) {
        BasicDBObject query = new BasicDBObject("id", id);
        DBCursor cursor = collection.find(query);
        try {
            if(cursor.hasNext()) {
                BasicDBObject doc = (BasicDBObject) cursor.next();
                doc.put("Name", Name);
                doc.put("Author", Author);
                doc.getString("Length");
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
