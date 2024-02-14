package business.domain;

public enum BeverageSize {
    SMALL(1.2,"small"), MEDIUM(1.4,"medium"), LARGE(1.5,"large");

    final double factor;
    final String size;
    BeverageSize(Double factor,String size){
        this.factor = factor;
        this.size = size;
    }
}
