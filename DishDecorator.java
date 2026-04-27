package tavern.model;

public abstract class DishDecorator implements Dish {

    protected final Dish wrappedDish;
    protected DishDecorator(Dish wrappedDish) {
        this.wrappedDish = wrappedDish;
    }
    protected abstract String getAddonName();
    protected abstract int getAddonPrice();

    @Override
    public String getName() {
        return wrappedDish.getName() + " +" + getAddonName();
    }

    @Override
    public int getPrice() {
        return wrappedDish.getPrice() + getAddonPrice();
    }
}
