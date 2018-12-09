package data.entities;

public enum VALUE {
    POSITIVE(1),
    NEGATIVE(-1),
    ZERO(0);

    private int value;

    VALUE(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    ;
}
