package data.entities;

public class Score {
    private Participant author;
    private VALUE value;

    public Participant getAuthor() {
        return author;
    }

    public void setAuthor(Participant author) {
        this.author = author;
    }

    public VALUE getValue() {
        return value;
    }

    public void setValue(VALUE value) {
        this.value = value;
    }
}
