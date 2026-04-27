package tavern.model;

public class DoubleVenisonDecorator extends DishDecorator {
    public DoubleVenisonDecorator(Dish wrappedDish) {
        super(wrappedDish);
    }

    @Override
    protected String getAddonName() {
        return "Двойная Порция Оленины";
    }

    @Override
    protected int getAddonPrice() {
        return 20;
    }
}
