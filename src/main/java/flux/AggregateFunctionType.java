package flux;

public enum AggregateFunctionType {
    MIN("MIN"), MAX("MAX"), AVG("AVG");
    String name;

    AggregateFunctionType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
