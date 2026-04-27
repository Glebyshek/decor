package tavern.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class OrderRecord {

    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final String time;
    private final String name;
    private final int price;

    public OrderRecord(String name, int price) {
        this.time = LocalTime.now().format(TIME_FMT);
        this.name = name;
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "[" + time + "]  " + name + "  — " + price + " септимов";
    }
}
