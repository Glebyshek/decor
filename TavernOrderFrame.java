package tavern.model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class TavernOrderFrame extends JFrame {

    private static final int MAX_ADDONS = 3;
    private final List<AddonEntry> addonEntries = new ArrayList<>();
    private final DefaultTableModel historyModel;

    public TavernOrderFrame() {
        super("Таверна Мари Ищейки Кобылы - Заказ Нордского Рагу");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel header = new JLabel(
                "<html><center><b> Нордское Рагу - 50 септимов </b></center></html>",
                SwingConstants.CENTER
        );
        header.setFont(new Font("Serif", Font.BOLD, 20));
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        add(header, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createTitledBorder("Добавки (не более трёх):"));

        addModifier(centerPanel, "Огненный Соус (+10 септимов)", FireSauceDecorator::new);
        addModifier(centerPanel, "Двойная Порция Оленины (+20 септимов)", DoubleVenisonDecorator::new);
        addModifier(centerPanel, "Снежные Ягоды (+5 септимов)", SnowBerriesDecorator::new);
        addModifier(centerPanel, "Нордская Лепёшка (+7 септимов)", NordFlatbreadDecorator::new);

        JButton orderButton = new JButton("Оформить заказ!");
        orderButton.setFont(new Font("Serif", Font.BOLD, 16));
        orderButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        orderButton.addActionListener(e -> placeOrder());

        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(orderButton);
        centerPanel.add(Box.createVerticalStrut(5));

        add(centerPanel, BorderLayout.CENTER);

        historyModel = new DefaultTableModel(
                new String[]{"Время", "Заказ", "Цена (септимы)"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable historyTable = new JTable(historyModel);
        historyTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        historyTable.getColumnModel().getColumn(1).setPreferredWidth(350);
        historyTable.getColumnModel().getColumn(2).setPreferredWidth(100);

        JScrollPane scrollPane = new JScrollPane(historyTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Свиток заказов:"));
        scrollPane.setPreferredSize(new Dimension(0, 200));
        add(scrollPane, BorderLayout.SOUTH);

        setSize(620, 520);
        setLocationRelativeTo(null);
    }

    private void addModifier(JPanel panel, String label, UnaryOperator<Dish> decoratorFactory) {
        JCheckBox checkBox = new JCheckBox(label);
        checkBox.setFont(new Font("Serif", Font.PLAIN, 14));
        checkBox.addItemListener(e -> updateCheckboxAvailability());
        panel.add(checkBox);

        addonEntries.add(new AddonEntry(checkBox, decoratorFactory));
    }

    private void updateCheckboxAvailability() {
        long selectedCount = addonEntries.stream()
                .filter(entry -> entry.checkBox.isSelected())
                .count();

        for (AddonEntry entry : addonEntries) {
            entry.checkBox.setEnabled(entry.checkBox.isSelected() || selectedCount < MAX_ADDONS);
        }
    }

    private void placeOrder() {
        Dish dish = new NordStew();

        for (AddonEntry entry : addonEntries) {
            dish = entry.applyIfSelected(dish);
        }

        OrderRecord record = new OrderRecord(dish.getName(), dish.getPrice());

        historyModel.addRow(new Object[]{
                record.getTime(),
                record.getName(),
                record.getPrice()
        });

        for (AddonEntry entry : addonEntries) {
            entry.checkBox.setSelected(false);
        }
        updateCheckboxAvailability();

        JOptionPane.showMessageDialog(this,
                record.getName() + "\nИтого: " + record.getPrice() + " септимов",
                "Заказ принят!",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private static class AddonEntry {

        final JCheckBox checkBox;
        private final UnaryOperator<Dish> decoratorFactory;

        @SuppressWarnings("unchecked")
        private final UnaryOperator<Dish>[] strategies = new UnaryOperator[]{
                (UnaryOperator<Dish>) dish -> dish,
                null
        };

        AddonEntry(JCheckBox checkBox, UnaryOperator<Dish> decoratorFactory) {
            this.checkBox = checkBox;
            this.decoratorFactory = decoratorFactory;
            this.strategies[1] = decoratorFactory;
        }

        Dish applyIfSelected(Dish dish) {
            int index = checkBox.isSelected() ? 1 : 0;
            return strategies[index].apply(dish);
        }
    }
}
