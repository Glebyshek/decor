import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Main {

    static ArrayList<String> istoriyaZakazov = new ArrayList<>();
    static JTextArea textAreaIstoriya;
    static JLabel labelCena;
    static JCheckBox checkSous;
    static JCheckBox checkOlenina;
    static JCheckBox checkYagody;
    static JCheckBox checkLepeshka;

    public static void main(String[] args) {

        JFrame okno = new JFrame("Таверна Нордское рагу");
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setSize(600, 550);
        okno.setLocationRelativeTo(null);
        okno.setLayout(new BorderLayout(10, 10));

        JLabel zagolovok = new JLabel("Нордское рагу", SwingConstants.CENTER);
        zagolovok.setFont(new Font("Serif", Font.BOLD, 22));
        zagolovok.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        okno.add(zagolovok, BorderLayout.NORTH);

        JPanel panelZakaz = new JPanel();
        panelZakaz.setLayout(new BoxLayout(panelZakaz, BoxLayout.Y_AXIS));
        panelZakaz.setBorder(BorderFactory.createTitledBorder("Оформить заказ"));

        JLabel labelBazovoe = new JLabel("Нордское рагу - 50 септимов (базовое блюдо)");
        labelBazovoe.setFont(new Font("Serif", Font.BOLD, 14));
        labelBazovoe.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panelZakaz.add(labelBazovoe);

        JLabel labelDobavki = new JLabel("Выберите добавки (не более 3-х):");
        labelDobavki.setBorder(BorderFactory.createEmptyBorder(5, 10, 2, 10));
        panelZakaz.add(labelDobavki);

        checkSous     = new JCheckBox("Огненный соус - +10 септимов");
        checkOlenina  = new JCheckBox("Двойная порция оленины - +20 септимов");
        checkYagody   = new JCheckBox("Снежные ягоды - +5 септимов");
        checkLepeshka = new JCheckBox("Нордская лепешка - +7 септимов");

        Font fontChekbox = new Font("Serif", Font.PLAIN, 14);
        checkSous.setFont(fontChekbox);
        checkOlenina.setFont(fontChekbox);
        checkYagody.setFont(fontChekbox);
        checkLepeshka.setFont(fontChekbox);

        checkSous.setBorder(BorderFactory.createEmptyBorder(3, 20, 3, 10));
        checkOlenina.setBorder(BorderFactory.createEmptyBorder(3, 20, 3, 10));
        checkYagody.setBorder(BorderFactory.createEmptyBorder(3, 20, 3, 10));
        checkLepeshka.setBorder(BorderFactory.createEmptyBorder(3, 20, 3, 10));

        panelZakaz.add(checkSous);
        panelZakaz.add(checkOlenina);
        panelZakaz.add(checkYagody);
        panelZakaz.add(checkLepeshka);

        labelCena = new JLabel("Итого: 50 септимов");
        labelCena.setFont(new Font("Serif", Font.BOLD, 16));
        labelCena.setForeground(new Color(150, 100, 0, 243));
        labelCena.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        panelZakaz.add(labelCena);

        JButton knopkaZakaz = new JButton("Оформить заказ");
        knopkaZakaz.setFont(new Font("Serif", Font.BOLD, 14));
        knopkaZakaz.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelZakaz.add(Box.createVerticalStrut(5));
        panelZakaz.add(knopkaZakaz);
        panelZakaz.add(Box.createVerticalStrut(8));

        okno.add(panelZakaz, BorderLayout.CENTER);

        JPanel panelIstoriya = new JPanel(new BorderLayout());
        panelIstoriya.setBorder(BorderFactory.createTitledBorder("Свиток заказов (история)"));
        panelIstoriya.setPreferredSize(new Dimension(600, 180));

        textAreaIstoriya = new JTextArea();
        textAreaIstoriya.setEditable(false);
        textAreaIstoriya.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textAreaIstoriya.setText("(заказов пока нет)");

        JScrollPane scrollPane = new JScrollPane(textAreaIstoriya);
        panelIstoriya.add(scrollPane, BorderLayout.CENTER);
        okno.add(panelIstoriya, BorderLayout.SOUTH);

        ActionListener slushatel = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = 0;
                if (checkSous.isSelected())     count++;
                if (checkOlenina.isSelected())  count++;
                if (checkYagody.isSelected())   count++;
                if (checkLepeshka.isSelected()) count++;

                if (count >= 3) {
                    if (!checkSous.isSelected())     checkSous.setEnabled(false);
                    if (!checkOlenina.isSelected())  checkOlenina.setEnabled(false);
                    if (!checkYagody.isSelected())   checkYagody.setEnabled(false);
                    if (!checkLepeshka.isSelected()) checkLepeshka.setEnabled(false);
                } else {
                    checkSous.setEnabled(true);
                    checkOlenina.setEnabled(true);
                    checkYagody.setEnabled(true);
                    checkLepeshka.setEnabled(true);
                }

                obnovitCenu();
            }
        };

        checkSous.addActionListener(slushatel);
        checkOlenina.addActionListener(slushatel);
        checkYagody.addActionListener(slushatel);
        checkLepeshka.addActionListener(slushatel);

        knopkaZakaz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Blyudo zakaz = new NordskoeRagu();
                if (checkSous.isSelected())     zakaz = new OgnennyySous(zakaz);
                if (checkOlenina.isSelected())  zakaz = new DvoynayaPorciyaOleninyi(zakaz);
                if (checkYagody.isSelected())   zakaz = new SnezhnyeYagody(zakaz);
                if (checkLepeshka.isSelected()) zakaz = new NordskayaLepeshka(zakaz);

                String vremya = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                String zapis = "[" + vremya + "]  " + zakaz.getNazvaniye() + "  |  " + zakaz.getCena() + " септимов";
                istoriyaZakazov.add(zapis);
                obnovitIstoriyu();

                checkSous.setSelected(false);
                checkOlenina.setSelected(false);
                checkYagody.setSelected(false);
                checkLepeshka.setSelected(false);

                checkSous.setEnabled(true);
                checkOlenina.setEnabled(true);
                checkYagody.setEnabled(true);
                checkLepeshka.setEnabled(true);

                obnovitCenu();

                JOptionPane.showMessageDialog(okno,
                        "Заказ принят!\n" + zakaz.getNazvaniye() + "\nЦена: " + zakaz.getCena() + " септимов",
                        "Заказ оформлен",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        okno.setVisible(true);
    }

    static void obnovitCenu() {
        Blyudo zakaz = new NordskoeRagu();
        if (checkSous.isSelected())     zakaz = new OgnennyySous(zakaz);
        if (checkOlenina.isSelected())  zakaz = new DvoynayaPorciyaOleninyi(zakaz);
        if (checkYagody.isSelected())   zakaz = new SnezhnyeYagody(zakaz);
        if (checkLepeshka.isSelected()) zakaz = new NordskayaLepeshka(zakaz);
        labelCena.setText("Итого: " + zakaz.getCena() + " септимов");
    }

    static void obnovitIstoriyu() {
        if (istoriyaZakazov.isEmpty()) {
            textAreaIstoriya.setText("(заказов пока нет)");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < istoriyaZakazov.size(); i++) {
            sb.append((i + 1)).append(". ").append(istoriyaZakazov.get(i)).append("\n");
        }
        textAreaIstoriya.setText(sb.toString());
    }
}