import javax.swing.*;
import java.awt.*;

public class InstructionDer extends JPanel {
    public InstructionDer() {
        setLayout(new BorderLayout());

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawString("1)Введите функцию, после нее запятую и нужную точку, нажмите Enter", 5, 20);
        g2.drawString("Пример: x^2,2", 5, 40);

    }

    public Dimension getPreferredSize() {
        return new Dimension(400, 200);
    }
}