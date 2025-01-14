import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lab7GUI extends JFrame {
    private JTextField txtSize;
    private JRadioButton radioVoz;
    private JRadioButton radioUb;
    private JButton sortBtn;
    private JTextArea outputArea; // JTextArea для вывода результата
    private JScrollPane scrollPane; // JScrollPane для прокрутки
    private JPanel panelMain;

    public Lab7GUI() {
        panelMain = new JPanel();
        panelMain.setLayout(null);
        setContentPane(panelMain);
        setTitle("Сортировка массива");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // JLabel для ввода размера массива
        JLabel label = new JLabel("Введите размер массива:");
        label.setBounds(50, 20, 200, 30);
        panelMain.add(label);

        // JTextField для ввода размера массива
        txtSize = new JTextField();
        txtSize.setBounds(50, 50, 200, 30);
        panelMain.add(txtSize);

        // RadioButtons для выбора порядка сортировки
        radioVoz = new JRadioButton("По возрастанию");
        radioVoz.setBounds(50, 90, 150, 30);
        radioUb = new JRadioButton("По убыванию");
        radioUb.setBounds(50, 120, 150, 30);
        ButtonGroup group = new ButtonGroup();
        group.add(radioVoz);
        group.add(radioUb);
        panelMain.add(radioVoz);
        panelMain.add(radioUb);

        // Кнопка сортировки
        sortBtn = new JButton("Сортировать");
        sortBtn.setBounds(50, 150, 200, 30);
        panelMain.add(sortBtn);

        // JTextArea для вывода результата
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(50, 190, 300, 60); // Размер области прокрутки
        panelMain.add(scrollPane);

        // Обработчик нажатия кнопки сортировки
        sortBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size;
                try {
                    size = Integer.parseInt(txtSize.getText());
                } catch (NumberFormatException ex) {
                    outputArea.setText("Введите корректный размер массива.");
                    return;
                }

                List<Integer> array = createArray(size);
                String order = radioVoz.isSelected() ? "ascending" : "descending";

                // Запускаем сортировку в новом потоке
                new Thread(() -> {
                    List<Integer> sortedArray = sortArray(array, order);
                    outputArea.setText("Полученный массив: " + sortedArray);
                }).start();
            }
        });
    }

    private List<Integer> createArray(int size) {
        List<Integer> array = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            array.add(i);
        }
        Collections.shuffle(array);
        return array;
    }

    private List<Integer> sortArray(List<Integer> array, String order) {
        if ("ascending".equals(order)) {
            Collections.sort(array);
        } else {
            Collections.sort(array, Collections.reverseOrder());
        }
        return array;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Lab7GUI frame = new Lab7GUI();
            frame.setVisible(true);
        });
    }
}