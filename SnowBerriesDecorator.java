package tavern.model;

public class SnowBerriesDecorator extends DishDecorator {

    public SnowBerriesDecorator(Dish wrappedDish) {
        super(wrappedDish);
    }

    @Override
    protected String getAddonName() {
        return "Снежные Ягоды";
    }

    @Override
    protected int getAddonPrice() {
        return 5;
    }
}
