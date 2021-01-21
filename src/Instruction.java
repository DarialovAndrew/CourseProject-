import javax.swing.*;
import java.awt.*;

public class Instruction extends JPanel {
    public Instruction() {
        setLayout(new BorderLayout());

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawString("1)Нажмите F1 чтобы открыть окно для вычисления производной в точке", 5, 20);
        g2.drawString("2)Нажмите F2 чтобы открыть окно для вычисления определенного интеграла", 5, 40);
        g2.drawString("3)Введите в поле, что перед Вами, функцию которую нужно изобразить", 5, 60);
        g2.drawString("4)Чтобы функция осталась на экране нажмите Enter(максимальное количество сохраненных функций-3)", 5, 80);
        g2.drawString("5)Для перемещения графика используйте стрелки с клавиатуры", 5, 100);
        g2.drawString("6)Чтобы очистить экран и вернуть центр к началу координат введите в строке \"clear\"", 5, 120);
        g2.drawString("7)Для ввода логарифмической функции с произвольным основанием используйте квадратные скобки.", 5, 140);
        g2.drawString("Пример: log[3](x) (Логарифм по основанию \"3\" от \"х\")", 5, 160);
        g2.drawString("8)Некоторые функции: cosh(x),sinh(x),exp(x),tanh(x),tan(x),acos(x),asin(x),atan(x),cot(x)", 5, 180);
    }

    public Dimension getPreferredSize() {
        return new Dimension(600, 250);
    }
}
