package com.gusdecool.calculator;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JFrame {

	private static final int WINDOW_WIDTH = 300;
	private static final int WINDOW_HEIGHT = 300;
	private static final boolean ALLOW_RESIZE = false;
	private Controller controller;
	private JTextField resultText;

	public void open(Controller controller) {
		this.controller = controller;
		init();
		setVisible(true);
	}

	private void init() {
		setTitle("Calculator");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setResizable(ALLOW_RESIZE);
		setLocationRelativeTo(null);

		// Prepare UI
		JPanel panel = new JPanel();
		this.getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		// result text UI
		JPanel resultPanel = new JPanel();
		resultPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, 50));
		resultText = new JTextField();
		resultText.setEditable(false);
		resultText.setPreferredSize(new Dimension(WINDOW_WIDTH, 50));
		resultText.setBackground(Color.BLACK);
		resultText.setForeground(Color.green);
		resultText.setText("0");
		resultText.setHorizontalAlignment(SwingConstants.RIGHT);
		resultText.setFont(new Font(null, Font.PLAIN, 23));
		resultPanel.add(resultText);
		panel.add(resultPanel);

		// action Panel
		JPanel actionPanel = new JPanel();
		actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.X_AXIS));
		panel.add(actionPanel);

		// Number Panel
		JPanel numberPanel = new JPanel(new GridLayout(4, 3));
		numberPanel.setPreferredSize(new Dimension(250, 350));
		numberPanel.setSize(250, 350);
		actionPanel.add(numberPanel);

		ArrayList<String> listButton = new ArrayList<>();
		listButton.add("7");
		listButton.add("8");
		listButton.add("9");
		listButton.add("4");
		listButton.add("5");
		listButton.add("6");
		listButton.add("1");
		listButton.add("2");
		listButton.add("3");
		listButton.add("C");
		listButton.add("0");
		listButton.add("CE");

		for(String buttonName : listButton) {
			JButton button = new JButton(buttonName);
			button.addActionListener(controller.actionListener());
			numberPanel.add(button);
		}

		// Operator Panel
		JPanel operatorPanel = new JPanel();
		operatorPanel.setLayout(new BoxLayout(operatorPanel, BoxLayout.Y_AXIS));
		operatorPanel.setPreferredSize(new Dimension(150, 350));
		operatorPanel.setSize(new Dimension(150, 350));
		actionPanel.add(operatorPanel);
		ArrayList<String> listOperator = new ArrayList<>();
		listOperator.add("+");
		listOperator.add("-");
		listOperator.add("*");
		listOperator.add("/");
		listOperator.add("=");

		for(String buttonName : listOperator) {
			JButton button = new JButton(buttonName);
			button.addActionListener(controller.actionListener());
			operatorPanel.add(button);
		}


	}

	public void setDisplayedText(String text) {
		resultText.setText(text);
	}
}
