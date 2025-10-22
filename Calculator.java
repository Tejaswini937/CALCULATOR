import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    JTextField display;
    String operator, num1, num2;
    double result;
    JButton[] buttons;
    String[] labels = {
        "7","8","9","/","4","5","6","*",
        "1","2","3","-","0",".","=","+","C"
    };

    public Calculator() {
        setTitle("Calculator");
        setSize(300,400);
        setLayout(new BorderLayout());
        display = new JTextField();
        display.setEditable(false);
        add(display, BorderLayout.NORTH);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,4,5,5));
        buttons = new JButton[labels.length];
        for (int i=0; i<labels.length; i++) {
            buttons[i] = new JButton(labels[i]);
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }
        JButton clear = new JButton("C");
        clear.addActionListener(this);
        panel.add(clear);
        add(panel, BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.charAt(0) >= '0' && s.charAt(0) <= '9' || s.equals(".")) {
            display.setText(display.getText() + s);
        } else if (s.equals("C")) {
            display.setText("");
            operator = num1 = num2 = "";
        } else if (s.equals("=")) {
            num2 = display.getText();
            if (num1 != null && operator != null) {
                double a = Double.parseDouble(num1);
                double b = Double.parseDouble(num2);
                switch (operator) {
                    case "+": result = a + b; break;
                    case "-": result = a - b; break;
                    case "*": result = a * b; break;
                    case "/": result = b != 0 ? a / b : 0; break;
                }
                display.setText(String.valueOf(result));
                num1 = String.valueOf(result);
                operator = null;
            }
        } else {
            if (!display.getText().isEmpty()) {
                num1 = display.getText();
                operator = s;
                display.setText("");
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
