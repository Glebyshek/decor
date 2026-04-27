package tavern.model;

public class NordStew implements Dish {

    @Override
    public String getName() {
        return "Нордское Рагу";
    }

    @Override
    public int getPrice() {
        return 50;
    }
}
