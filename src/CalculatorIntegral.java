import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;

public class CalculatorIntegral extends JPanel {
    String integralres = "";
    DecimalFormat dec = new DecimalFormat("#0.000");

    public CalculatorIntegral() {

        setLayout(new BorderLayout());
        TextField integral = new TextField();
        add(integral, BorderLayout.PAGE_START);
        integral.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String s = integral.getText();
                    if (s.contains("[")) {
                        while (s.contains("[")) {
                            try {
                                int index1 = s.indexOf("log");
                                int index2 = 0;
                                String curr1 = s.substring(0, index1);
                                String curr2 = s.substring(index1);
                                int count = 1;
                                for (int i = curr2.indexOf("(") + 1; i < curr2.length(); i++) {
                                    index2 = i;
                                    if (curr2.charAt(i) == ')') {
                                        count--;
                                    } else if (curr2.charAt(i) == '(') count++;
                                    if (count == 0) break;
                                }
                                String curr3 = curr2.substring(index2);
                                curr2 = curr2.substring(0, index2);
                                index1 = curr2.indexOf("[");
                                int index3 = curr2.indexOf("]");
                                String squarebrack = curr2.substring(index1 + 1, index3);
                                String smoothbrack = curr2.substring(index3 + 2, index2);
                                s = curr1 + "pog(" + smoothbrack + ")/log(" + squarebrack + curr3;
                            } catch (StringIndexOutOfBoundsException a) {
                                System.out.println(a.getMessage());
                                break;
                            }
                        }
                        s = s.replaceAll("p", "l");
                    }
                    while (s.contains("cot")) {
                        try {
                            int index1 = s.indexOf("cot");
                            int index2 = 0;
                            String curr1 = s.substring(0, index1);
                            String curr2 = s.substring(index1);
                            int count = 1;
                            for (int i = curr2.indexOf("(") + 1; i < curr2.length(); i++) {
                                index2 = i;
                                if (curr2.charAt(i) == ')') count--;
                                else if (curr2.charAt(i) == '(') count++;
                                if (count == 0) break;
                            }
                            String curr3 = curr2.substring(index2);
                            curr2 = curr2.substring(0, index2);
                            String smoothbracket = curr2.substring(curr2.indexOf("("));
                            s = curr1 + "1/tan(" + smoothbracket + ")" + curr3;
                        } catch (StringIndexOutOfBoundsException a) {
                            System.out.println(a.getMessage());
                            break;
                        }
                    }
                    double point1, point2;
                    if (!s.contains(",")) {
                        integralres = "No point entered";
                    } else {
                        int index = s.lastIndexOf(",");
                        point2 = Double.parseDouble(s.substring(index + 1));
                        s = s.substring(0, index);
                        index = s.indexOf(",");
                        point1 = Double.parseDouble(s.substring(index + 1));
                        s = s.substring(0, index);
                        try {
                            act(s, point1, point2);
                        } catch (NoSuchMethodException a) {
                            System.out.println("NoSuchMethodException");
                        } catch (IllegalAccessException a) {
                            System.out.println("IllegalAccessException");
                        } catch (IllegalArgumentException a) {
                            System.out.println("IllegalArgumentException");
                            integralres = "IllegalArgumentException";
                        } catch (InvocationTargetException a) {
                            System.out.println("InvocationTargetException");
                        }
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_F3) {
                    JFrame frame = new JFrame("Instruction");
                    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    frame.setLocationByPlatform(true);
                    frame.add(new InstructionIntegral());
                    frame.pack();
                    frame.setVisible(true);
                }
                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    void act(String s, double point1, double point2) throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        if (point1 > point2) integralres = "Incorrect points";
        else {
            double sum = 0.0;
            double curr;
            for (double x = point1; x <= point2; x += 0.0001) {
                curr = 0.0001 * FunctionSolver.solveByX(s, x);
                sum += curr;
            }
            integralres = dec.format(sum);
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawString("Result= ", 50, 150);
        g2.drawString(integralres, 100, 150);
    }

    public Dimension getPreferredSize() {
        return new Dimension(400, 200);
    }
}


