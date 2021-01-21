import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Window extends JPanel {
    String curr = "";
    final double step = 0.1;
    int count = 1;
    int sidemove = 0;
    int heightmove = 0;
    String incorrect = "";
    ArrayList<Point> arr = new ArrayList<Point>();
    ArrayList<Point> arr1 = new ArrayList<Point>();
    ArrayList<Point> arr2 = new ArrayList<Point>();
    ArrayList<Point> arr3 = new ArrayList<Point>();
    String s1real = "";
    String s2real = "";
    String s3real = "";

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1200, 600);
    }

    public Window() {
        setLayout(new BorderLayout());
        TextField txt = new TextField();
        add(txt, BorderLayout.PAGE_START);
        txt.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    sidemove--;
                    try {
                        act(curr);
                        Adopt();
                    } catch (NoSuchMethodException a) {
                        System.out.println("NoSuchMethodException");
                    } catch (IllegalAccessException a) {
                        System.out.println("IllegalAccessException");
                    } catch (IllegalArgumentException a) {
                        System.out.println("IllegalArgumentException");
                    } catch (InvocationTargetException a) {
                        System.out.println("InvocationTargetException");
                    }
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    sidemove++;
                    try {
                        act(curr);
                        Adopt();
                    } catch (NoSuchMethodException a) {
                        System.out.println("NoSuchMethodException");
                    } catch (IllegalAccessException a) {
                        System.out.println("IllegalAccessException");
                    } catch (IllegalArgumentException a) {
                        System.out.println("IllegalArgumentException");

                    } catch (InvocationTargetException a) {
                        System.out.println("InvocationTargetException");
                    }
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    heightmove--;
                    try {
                        act(curr);
                        Adopt();
                    } catch (NoSuchMethodException a) {
                        System.out.println("NoSuchMethodException");
                    } catch (IllegalAccessException a) {
                        System.out.println("IllegalAccessException");
                    } catch (IllegalArgumentException a) {
                        System.out.println("IllegalArgumentException");

                    } catch (InvocationTargetException a) {
                        System.out.println("InvocationTargetException");
                    }
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    heightmove++;
                    try {
                        act(curr);
                        Adopt();
                    } catch (NoSuchMethodException a) {
                        System.out.println("NoSuchMethodException");
                    } catch (IllegalAccessException a) {
                        System.out.println("IllegalAccessException");
                    } catch (IllegalArgumentException a) {
                        System.out.println("IllegalArgumentException");

                    } catch (InvocationTargetException a) {
                        System.out.println("InvocationTargetException");
                    }
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_F1) {
                    JFrame frame = new JFrame("Derivative Calculator");
                    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    frame.setLocationByPlatform(true);
                    frame.add(new Calculatorder());
                    frame.pack();
                    frame.setVisible(true);
                } else if (e.getKeyCode() == KeyEvent.VK_F2) {
                    JFrame frame = new JFrame("Integral Calculator");
                    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    frame.setLocationByPlatform(true);
                    frame.add(new CalculatorIntegral());
                    frame.pack();
                    frame.setVisible(true);
                } else if (e.getKeyCode() == KeyEvent.VK_F3) {
                    JFrame frame = new JFrame("Instruction");
                    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    frame.setLocationByPlatform(true);
                    frame.add(new Instruction());
                    frame.pack();
                    frame.setVisible(true);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    System.out.println(incorrect.equals("Incorrect Function entered"));
                    System.out.println(curr.isEmpty());
                    if (curr.isEmpty() || incorrect.equals("Incorrect Function entered")) return;
                    switch (count) {
                        case 1:
                            arr1.addAll(arr);
                            s1real = curr;
                            count++;
                            break;
                        case 2:
                            if (s1real.equals(curr)) break;
                            arr2.addAll(arr);
                            s2real = curr;
                            count++;
                            break;
                        case 3:
                            if (s1real.equals(curr) || s2real.equals(curr)) break;
                            arr3.addAll(arr);
                            s3real = curr;
                            count++;
                            break;
                        default:
                            break;
                    }

                } else if (!txt.getText().equals(curr)) {
                    curr = txt.getText();
                    arr.clear();
                    if (curr.contains("[")) {
                        while (curr.contains("[")) {
                            try {
                                int index1 = curr.indexOf("log");
                                int index2 = 0;
                                String curr1 = curr.substring(0, index1);
                                String curr2 = curr.substring(index1);
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
                                curr = curr1 + "pog(" + smoothbrack + ")/log(" + squarebrack + curr3;
                            } catch (StringIndexOutOfBoundsException a) {
                                System.out.println(a.getMessage());
                                break;
                            }
                        }
                        curr = curr.replaceAll("p", "l");
                    }
                    while (curr.contains("cot")) {
                        try {
                            int index1 = curr.indexOf("cot");
                            int index2 = 0;
                            String curr1 = curr.substring(0, index1);
                            String curr2 = curr.substring(index1);
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
                            curr = curr1 + "1/tan(" + smoothbracket + ")" + curr3;
                        } catch (StringIndexOutOfBoundsException a) {
                            System.out.println(a.getMessage());
                            break;
                        }
                    }
                    //System.out.println(curr);
                    try {
                        act(curr);
                        incorrect = "";
                    } catch (NoSuchMethodException a) {
                        System.out.println("NoSuchMethodException");
                    } catch (IllegalAccessException a) {
                        System.out.println("IllegalAccessException");
                    } catch (IllegalArgumentException a) {
                        System.out.println("IllegalArgumentException");
                        incorrect = "Incorrect Function entered";
                    } catch (InvocationTargetException a) {
                        System.out.println("InvocationTargetException");
                    }
                }
                repaint();
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawLine(0, this.getHeight() / 2 + heightmove * this.getHeight() / 10, this.getWidth(), this.getHeight() / 2 + heightmove * this.getHeight() / 10);
        g.drawLine(this.getWidth() / 2 + sidemove * this.getWidth() / 20, 0, this.getWidth() / 2 + sidemove * this.getWidth() / 20, this.getHeight());
        Graphics2D g2 = (Graphics2D) g;
        if (count - 1 == 3) {
            g2.drawString("(MAX)count:" + Integer.toString(count - 1), 9 / 10 * this.getWidth(), this.getHeight() / 10);
        } else g2.drawString("count:" + Integer.toString(count - 1), 9 / 10 * this.getWidth(), this.getHeight() / 10);
        g2.drawString(incorrect, 9 / 10 * this.getWidth(), this.getHeight() / 12);
        int count = (-9 - sidemove);
        for (int i = this.getWidth() / 20; i < this.getWidth(); i += this.getWidth() / 20) {
            g2.draw(new Ellipse2D.Double(i, this.getHeight() / 2 + heightmove * this.getHeight() / 10, 1, 1));
            g2.drawString((Integer.toString(count++)), i, this.getHeight() / 2 - 10 + heightmove * this.getHeight() / 10);
        }
        count = 4 + heightmove;
        for (int i = this.getHeight() / 10; i < this.getHeight(); i += this.getHeight() / 10) {
            if (count == 0) {
                count--;
                continue;
            }
            g2.draw(new Ellipse2D.Double(this.getWidth() / 2 + sidemove * this.getWidth() / 20, i, 1, 1));
            g2.drawString(Integer.toString(count--), this.getWidth() / 2 - 10 + sidemove * this.getWidth() / 20, i);
        }
        for (int i = 1; i < arr.size() - 1; i++) {
            try {
                g2.draw(new Line2D.Double((arr.get(i).x * this.getWidth() / 20 + this.getWidth() / 2 + sidemove * this.getWidth() / 20), (-arr.get(i).y * this.getHeight() / 10 + this.getHeight() / 2) + heightmove * this.getHeight() / 10, (arr.get(i - 1).x * this.getWidth() / 20 + this.getWidth() / 2 + sidemove * this.getWidth() / 20), (-arr.get(i - 1).y * this.getHeight() / 10 + this.getHeight() / 2) + heightmove * this.getHeight() / 10));
            } catch (IndexOutOfBoundsException a) {
                System.out.println("All right");
            }
        }
        for (int i = 1; i < arr1.size() - 1; i++) {
            try {
                g2.draw(new Line2D.Double((arr1.get(i).x * this.getWidth() / 20 + this.getWidth() / 2 + sidemove * this.getWidth() / 20), (-arr1.get(i).y * this.getHeight() / 10 + this.getHeight() / 2) + heightmove * this.getHeight() / 10, (arr1.get(i - 1).x * this.getWidth() / 20 + this.getWidth() / 2 + sidemove * this.getWidth() / 20), (-arr1.get(i - 1).y * this.getHeight() / 10 + this.getHeight() / 2) + heightmove * this.getHeight() / 10));
            } catch (IndexOutOfBoundsException a) {
                System.out.println("All right");
            }
        }
        for (int i = 1; i < arr2.size() - 1; i++) {
            try {
                g2.draw(new Line2D.Double((arr2.get(i).x * this.getWidth() / 20 + this.getWidth() / 2 + sidemove * this.getWidth() / 20), (-arr2.get(i).y * this.getHeight() / 10 + this.getHeight() / 2) + heightmove * this.getHeight() / 10, (arr2.get(i - 1).x * this.getWidth() / 20 + this.getWidth() / 2 + sidemove * this.getWidth() / 20), (-arr2.get(i - 1).y * this.getHeight() / 10 + this.getHeight() / 2) + heightmove * this.getHeight() / 10));
            } catch (NullPointerException a) {
                System.out.println("All right");
            }
        }
        for (int i = 1; i < arr3.size() - 1; i++) {
            try {
                g2.draw(new Line2D.Double((arr3.get(i).x * this.getWidth() / 20 + this.getWidth() / 2 + sidemove * this.getWidth() / 20), (-arr3.get(i).y * this.getHeight() / 10 + this.getHeight() / 2) + heightmove * this.getHeight() / 10, (arr3.get(i - 1).x * this.getWidth() / 20 + this.getWidth() / 2 + sidemove * this.getWidth() / 20), (-arr3.get(i - 1).y * this.getHeight() / 10 + this.getHeight() / 2) + heightmove * this.getHeight() / 10));
            } catch (NullPointerException a) {
                System.out.println("All right");
            }
        }
    }


    void act(String s) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (s.isEmpty()) return;
        if (s.equals("clear")) {
            arr.clear();
            arr1.clear();
            arr2.clear();
            arr3.clear();
            s1real = s2real = s3real = "";
            sidemove = heightmove = 0;
            count = 1;
        } else {
            arr.clear();
            double x = -10 - sidemove;
            double y;
            while (x < 10 - sidemove) {
                y = FunctionSolver.solveByX(s, x);
                Point dot = new Point(x, y);
                arr.add(dot);
                x = x + step;
            }
        }
    }

    void Adopt() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        arr1.clear();
        arr2.clear();
        arr3.clear();
        String s1 = s1real;
        String s2 = s2real;
        String s3 = s3real;
        double x = -10 - sidemove;
        double y;
        switch (count - 1) {
            case 1:
                while (x < 10 - sidemove) {
                    y = FunctionSolver.solveByX(s1, x);
                    Point dot = new Point(x, y);
                    arr1.add(dot);
                    x = x + step;
                }
                break;
            case 2:
                while (x < 10 - sidemove) {
                    y = FunctionSolver.solveByX(s1, x);
                    Point dot = new Point(x, y);
                    arr1.add(dot);
                    x = x + step;
                }
                x = -10 - sidemove;
                while (x < 10 - sidemove) {
                    y = FunctionSolver.solveByX(s2, x);
                    Point dot1 = new Point(x, y);
                    arr2.add(dot1);
                    x = x + step;
                }
                break;
            case 3:
                while (x < 10 - sidemove) {
                    y = FunctionSolver.solveByX(s1, x);
                    Point dot = new Point(x, y);
                    arr1.add(dot);
                    x = x + step;
                }
                x = -10 - sidemove;
                while (x < 10 - sidemove) {
                    y = FunctionSolver.solveByX(s2, x);
                    Point dot1 = new Point(x, y);
                    arr2.add(dot1);
                    x = x + step;
                }
                x = -10 - sidemove;
                while (x < 10 - sidemove) {
                    y = FunctionSolver.solveByX(s3, x);
                    Point dot2 = new Point(x, y);
                    arr3.add(dot2);
                    x = x + step;
                }
                break;
            default:
                break;
        }
    }
}