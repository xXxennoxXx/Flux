package flux.fieldholders;

public enum ConditionsConcatenationType {
    AND("AND"), OR("OR");

    String name;

    ConditionsConcatenationType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
