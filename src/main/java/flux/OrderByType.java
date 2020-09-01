package flux;

public enum OrderByType {
    ASC("ASC"), DESC("DESC");

    String name;

    OrderByType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
