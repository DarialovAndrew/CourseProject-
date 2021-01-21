import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;

public class Calculatorder extends JPanel {
    double derivativeres;
    DecimalFormat dec = new DecimalFormat("#0.00000");

    public Calculatorder() {
        setLayout(new BorderLayout());
        TextField derivative = new TextField();
        add(derivative, BorderLayout.PAGE_START);
        derivative.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String s = derivative.getText();
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
                    double point;
                    if (!s.contains(",")) {
                        System.out.println("No point entered");
                    } else {
                        int index = s.indexOf(",");
                        point = Double.parseDouble(s.substring(index + 1));
                        s = s.substring(0, index);
                        try {
                            act(s, point);
                        } catch (NoSuchMethodException a) {
                            System.out.println("NoSuchMethodException");
                        } catch (IllegalAccessException a) {
                            System.out.println("IllegalAccessException");
                        } catch (IllegalArgumentException a) {
                            System.out.println("IllegalArgumentException");
                        } catch (InvocationTargetException a) {
                            System.out.println("InvocationTargetException");
                        }
                    }

                } else if (e.getKeyCode() == KeyEvent.VK_F3) {
                    JFrame frame = new JFrame("Instruction");
                    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    frame.setLocationByPlatform(true);
                    frame.add(new InstructionDer());
                    frame.pack();
                    frame.setVisible(true);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public Dimension getPreferredSize() {
        return new Dimension(400, 200);
    }

    void act(String s, double point) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        double x1 = FunctionSolver.solveByX(s, point);
        double x2 = FunctionSolver.solveByX(s, point + 0.00000001);
        derivativeres = (x2 - x1) / 0.00000001;
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawString("Result= ", 50, 150);
        g2.drawString(dec.format(derivativeres), 100, 150);
    }
}
