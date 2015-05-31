import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Song {
    private String Name;
    private String Author;
    private Date createdAt;
    private String Length;
    private Integer id;
    private Boolean deleted;

    public Song(String Name,  String Author,String Length, Integer size) {
        this.Name = Name;
        this.Author = Author;
        this.Length = Length;
        this.createdAt = new Date();
        this.id = size;
        this.deleted = false;
    }

    public Song(Integer id, String Name, String Author, String Length) {
        this.Name = Name;
        this.Author = Author;
        this.Length = Length;
        this.deleted = false;
        this.id = id;
    }

    public Song(String Name, String Author,String Length, Integer id, Date createdAt, Boolean deleted) {
        this.Name = Name;
        this.Author = Author;
        this.Length = Length;
        this.createdAt = createdAt;
        this.id = id;
        this.deleted = deleted;
    }

    public String getName() {
        return Name;
    }

    public String getAuthor() {
        return Author;
    }

    public String getLength() {
        return Length;
    }

    public void setTitle(String Name) {
        this.Name = Name;
    }

    public void setContent(String Author) {
        this.Author = Author;
    }

    public void setSummary(String Length) {
        this.Length = Length;
    }

    public Integer getId() {
        return id;
    }

    public void delete() {
        this.deleted = true;
    }

    public Boolean readable() {
        return !this.deleted;
    }

    public String getCreatedAt() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(this.createdAt);
    }

    public String getEditLink() {
        return "/song/update/" + this.id;
    }

    public String getDeleteLink() {
        return "/song/delete/" + this.id;
    }

    public String getLengthLink() {
        return "<a href='/song/read/" + this.id + "'>" + this.Length + "</a>";
    }
}
