package tavern.model;

public class FireSauceDecorator extends DishDecorator {

    public FireSauceDecorator(Dish wrappedDish) {
        super(wrappedDish);
    }

    @Override
    protected String getAddonName() {
        return "Огненный Соус";
    }

    @Override
    protected int getAddonPrice() {
        return 10;
    }
}
