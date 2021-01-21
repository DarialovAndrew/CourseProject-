import javax.swing.*;
import java.awt.*;

public class InstructionIntegral extends JPanel {
    public InstructionIntegral() {
        setLayout(new BorderLayout());

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawString("1)Введите функцию,запятую,нижнюю границу,верхнюю границу, нажмите Enter", 5, 20);
        g2.drawString("Пример: x^2,2,4", 5, 40);

    }

    public Dimension getPreferredSize() {
        return new Dimension(500, 200);
    }
}