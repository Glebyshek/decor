package tavern.model;

public class NordFlatbreadDecorator extends DishDecorator {

    public NordFlatbreadDecorator(Dish wrappedDish) {
        super(wrappedDish);
    }

    @Override
    protected String getAddonName() {
        return "Нордская Лепёшка";
    }

    @Override
    protected int getAddonPrice() {
        return 7;
    }
}
