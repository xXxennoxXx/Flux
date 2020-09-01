package flux;

public enum JoinType {
    INNER("JOIN"), LEFT_OUTER("LEFT JOIN"), RIGHT_OUTER("RIGHT JOIN"), FULL_OUTER("FULL OUTER JOIN");
    String name;

    JoinType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
