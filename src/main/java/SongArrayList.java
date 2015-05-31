import java.util.ArrayList;

public class SongArrayList<T extends Song>{
    ArrayList<T> songs;

    public SongArrayList() {
        songs = new ArrayList<T>();
    }

    public Boolean create(T entity) {
        songs.add(entity);
        return null;
    }

    public T readOne(int id) {
        return songs.get(id);
    }

    public ArrayList<T> readAll() {
        return songs;
    }

    public Boolean update(int id, String Name, String Length, String Author) {
        T entity = songs.get(id);
        entity.setSummary(Length);
        entity.setTitle(Name);
        entity.setContent(Author);
        return true;
    }

    public Boolean delete(int id) {
        songs.get(id).delete();
        return true;
    }
}
