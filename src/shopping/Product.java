package shopping;

public class Product {
    private String name;
    private String type;
    private double count;

    public Product(String n, String t, double c){
        name=n;
        type=t;
        count=c;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getCount() {
        return count;
    }
}
