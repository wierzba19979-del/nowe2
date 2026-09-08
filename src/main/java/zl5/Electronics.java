package zl5;

public class Electronics extends Product{
    private int warranty;

    public Electronics(String name, double price, String category, int warranty) {
        super(name, price, category);
        this.warranty = warranty;
    }

    @Override
    public String getDescription(){
        return  super.getDescription() + warranty;
    }
}
