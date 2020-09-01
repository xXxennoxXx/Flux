package flux.fieldholders;

public enum WhereConcatOperatorEnum {
    AND("AND"), OR("OR");

    String name;

    WhereConcatOperatorEnum(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
