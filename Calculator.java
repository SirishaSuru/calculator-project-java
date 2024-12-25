import java.awt.*;
import java.awt.event.*;

public class Calculator extends Frame implements ActionListener {
    TextField displayField;
    Button[] digitButtons;
    Button[] operationButtons;
    Button equalsButton;
    Button clearButton;

    public Calculator() {
        displayField = new TextField(15);
        digitButtons = new Button[10];
        operationButtons = new Button[4];
        equalsButton = new Button("=");
        clearButton = new Button("C");
        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5,5));
        setSize(500, 300);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Add digit buttons (7, 8, 9)
        for (int i = 7; i <= 9; i++) {
            digitButtons[i] = new Button(String.valueOf(i));
            digitButtons[i].addActionListener(this);
            buttonPanel.add(digitButtons[i]);
        }

        // Add division button
        operationButtons[3] = new Button("/");
        operationButtons[3].addActionListener(this);
        buttonPanel.add(operationButtons[3]);

        // Add digit buttons (4, 5, 6)
        for (int i = 4; i <= 6; i++) {
            digitButtons[i] = new Button(String.valueOf(i));
            digitButtons[i].addActionListener(this);
            buttonPanel.add(digitButtons[i]);
        }

        // Add multiplication button
        operationButtons[2] = new Button("*");
        operationButtons[2].addActionListener(this);
        buttonPanel.add(operationButtons[2]);

        // Add digit buttons (1, 2, 3)
        for (int i = 1; i <= 3; i++) {
            digitButtons[i] = new Button(String.valueOf(i));
            digitButtons[i].addActionListener(this);
            buttonPanel.add(digitButtons[i]);
        }

        // Add subtraction button
        operationButtons[1] = new Button("-");
        operationButtons[1].addActionListener(this);
        buttonPanel.add(operationButtons[1]);
	
	// Add addition button
        operationButtons[0] = new Button("+");
        operationButtons[0].addActionListener(this);
        buttonPanel.add(operationButtons[0]);

        // Add digit button 0
        digitButtons[0] = new Button("0");
        digitButtons[0].addActionListener(this);
        buttonPanel.add(digitButtons[0]);



        // Add equals button
        equalsButton.addActionListener(this);
        buttonPanel.add(equalsButton);

        // Add clear button
        clearButton.addActionListener(this);
        buttonPanel.add(clearButton);

        setLayout(new BorderLayout());
        add(displayField, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setTitle("Basic Calculator");
        pack();
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (Character.isDigit(command.charAt(0))) {
            displayField.setText(displayField.getText() + command);
        } else if ("+-*/".contains(command)) {
            displayField.setText(displayField.getText() + " " + command + " ");
        } else if (command.equals("=")) {
            String expression = displayField.getText();
            try {
                double result = evaluateExpression(expression);
                displayField.setText(String.valueOf(result));
            } catch (Exception ex) {
                displayField.setText("Error");
            }
        } else if (command.equals("C")) {
            displayField.setText("");
        }
    }

    private double evaluateExpression(String expression) {
        String[] tokens = expression.split(" ");
        double operand1 = Double.parseDouble(tokens[0]);
        String operator = tokens[1];
        double operand2 = Double.parseDouble(tokens[2]);

        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
