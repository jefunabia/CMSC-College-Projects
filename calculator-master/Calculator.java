import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;

public class Calculator extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	BigDecimal firstnum;
	BigDecimal secondnum;
	BigDecimal result;
	double sign;
	String operation;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator frame = new Calculator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Calculator() {
		setTitle("Simple Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 237, 371);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{53, 50, 3, 50, 0};
		gbl_contentPane.rowHeights = new int[]{61, 50, 50, 50, 50, 50, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton btnAC = new JButton("AC");
		btnAC.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btnAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
			}
		});
		
		textField = new JTextField();
		textField.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridwidth = 4;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		GridBagConstraints gbc_btnAC = new GridBagConstraints();
		gbc_btnAC.fill = GridBagConstraints.BOTH;
		gbc_btnAC.insets = new Insets(0, 0, 5, 5);
		gbc_btnAC.gridx = 0;
		gbc_btnAC.gridy = 1;
		contentPane.add(btnAC, gbc_btnAC);
		
		JButton btnSign = new JButton("+/-");
		btnSign.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btnSign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				sign = (Double.parseDouble(String.valueOf(textField.getText())));
				sign = sign * (-1);
				textField.setText(String.valueOf(sign));
			}
		});
		GridBagConstraints gbc_btnSign = new GridBagConstraints();
		gbc_btnSign.fill = GridBagConstraints.BOTH;
		gbc_btnSign.insets = new Insets(0, 0, 5, 5);
		gbc_btnSign.gridx = 1;
		gbc_btnSign.gridy = 1;
		contentPane.add(btnSign, gbc_btnSign);
		
		JButton btnPercent = new JButton("%");
		btnPercent.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btnPercent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ans;
				firstnum = new BigDecimal(textField.getText());
				textField.setText("");
				BigDecimal perc = new BigDecimal(0.0100);
				result = firstnum.multiply(perc).multiply(firstnum);
				ans = String.format("%.2f", result);
				textField.setText(ans);
	
			}
		});
		GridBagConstraints gbc_btnPercent = new GridBagConstraints();
		gbc_btnPercent.fill = GridBagConstraints.BOTH;
		gbc_btnPercent.insets = new Insets(0, 0, 5, 5);
		gbc_btnPercent.gridx = 2;
		gbc_btnPercent.gridy = 1;
		contentPane.add(btnPercent, gbc_btnPercent);
		
		JButton btnDiv = new JButton("\u00F7");
		btnDiv.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btnDiv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstnum = new BigDecimal(textField.getText());
				textField.setText("");
				operation = "/";
			}
		});
		GridBagConstraints gbc_btnDiv = new GridBagConstraints();
		gbc_btnDiv.fill = GridBagConstraints.BOTH;
		gbc_btnDiv.insets = new Insets(0, 0, 5, 0);
		gbc_btnDiv.gridx = 3;
		gbc_btnDiv.gridy = 1;
		contentPane.add(btnDiv, gbc_btnDiv);
		
		JButton btn7 = new JButton("7");
		btn7.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + btn7.getText());
			}
		});
		GridBagConstraints gbc_btn7 = new GridBagConstraints();
		gbc_btn7.fill = GridBagConstraints.BOTH;
		gbc_btn7.insets = new Insets(0, 0, 5, 5);
		gbc_btn7.gridx = 0;
		gbc_btn7.gridy = 2;
		contentPane.add(btn7, gbc_btn7);
		
		JButton btn8 = new JButton("8");
		btn8.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + btn8.getText());
			}
		});
		GridBagConstraints gbc_btn8 = new GridBagConstraints();
		gbc_btn8.fill = GridBagConstraints.BOTH;
		gbc_btn8.insets = new Insets(0, 0, 5, 5);
		gbc_btn8.gridx = 1;
		gbc_btn8.gridy = 2;
		contentPane.add(btn8, gbc_btn8);
		
		JButton btn9 = new JButton("9");
		btn9.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + btn9.getText());
			}
		});
		GridBagConstraints gbc_btn9 = new GridBagConstraints();
		gbc_btn9.fill = GridBagConstraints.BOTH;
		gbc_btn9.insets = new Insets(0, 0, 5, 5);
		gbc_btn9.gridx = 2;
		gbc_btn9.gridy = 2;
		contentPane.add(btn9, gbc_btn9);
		
		JButton btnMul = new JButton("x");
		btnMul.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btnMul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstnum = new BigDecimal(textField.getText());
				textField.setText("");
				operation = "x";
			}
		});
		GridBagConstraints gbc_btnMul = new GridBagConstraints();
		gbc_btnMul.fill = GridBagConstraints.BOTH;
		gbc_btnMul.insets = new Insets(0, 0, 5, 0);
		gbc_btnMul.gridx = 3;
		gbc_btnMul.gridy = 2;
		contentPane.add(btnMul, gbc_btnMul);
		
		JButton btn4 = new JButton("4");
		btn4.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + btn4.getText());
			}
		});
		GridBagConstraints gbc_btn4 = new GridBagConstraints();
		gbc_btn4.fill = GridBagConstraints.BOTH;
		gbc_btn4.insets = new Insets(0, 0, 5, 5);
		gbc_btn4.gridx = 0;
		gbc_btn4.gridy = 3;
		contentPane.add(btn4, gbc_btn4);
		
		JButton btn5 = new JButton("5");
		btn5.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + btn5.getText());
			}
		});
		GridBagConstraints gbc_btn5 = new GridBagConstraints();
		gbc_btn5.fill = GridBagConstraints.BOTH;
		gbc_btn5.insets = new Insets(0, 0, 5, 5);
		gbc_btn5.gridx = 1;
		gbc_btn5.gridy = 3;
		contentPane.add(btn5, gbc_btn5);
		
		JButton btn6 = new JButton("6");
		btn6.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + btn6.getText());
			}
		});
		GridBagConstraints gbc_btn6 = new GridBagConstraints();
		gbc_btn6.fill = GridBagConstraints.BOTH;
		gbc_btn6.insets = new Insets(0, 0, 5, 5);
		gbc_btn6.gridx = 2;
		gbc_btn6.gridy = 3;
		contentPane.add(btn6, gbc_btn6);
		
		JButton btnMin = new JButton("-");
		btnMin.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btnMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstnum = new BigDecimal(textField.getText());
				textField.setText("");
				operation = "-";
			}
		});
		GridBagConstraints gbc_btnMin = new GridBagConstraints();
		gbc_btnMin.fill = GridBagConstraints.BOTH;
		gbc_btnMin.insets = new Insets(0, 0, 5, 0);
		gbc_btnMin.gridx = 3;
		gbc_btnMin.gridy = 3;
		contentPane.add(btnMin, gbc_btnMin);
		
		JButton btn1 = new JButton("1");
		btn1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			textField.setText(textField.getText() + btn1.getText());
			}
		});
		GridBagConstraints gbc_btn1 = new GridBagConstraints();
		gbc_btn1.fill = GridBagConstraints.BOTH;
		gbc_btn1.insets = new Insets(0, 0, 5, 5);
		gbc_btn1.gridx = 0;
		gbc_btn1.gridy = 4;
		contentPane.add(btn1, gbc_btn1);
		
		JButton btn2 = new JButton("2");
		btn2.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + btn2.getText());
			}
		});
		GridBagConstraints gbc_btn2 = new GridBagConstraints();
		gbc_btn2.fill = GridBagConstraints.BOTH;
		gbc_btn2.insets = new Insets(0, 0, 5, 5);
		gbc_btn2.gridx = 1;
		gbc_btn2.gridy = 4;
		contentPane.add(btn2, gbc_btn2);
		
		JButton btn3 = new JButton("3");
		btn3.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + btn3.getText());
			}
		});
		GridBagConstraints gbc_btn3 = new GridBagConstraints();
		gbc_btn3.fill = GridBagConstraints.BOTH;
		gbc_btn3.insets = new Insets(0, 0, 5, 5);
		gbc_btn3.gridx = 2;
		gbc_btn3.gridy = 4;
		contentPane.add(btn3, gbc_btn3);
		
		JButton btnAdd = new JButton("+");
		btnAdd.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstnum = new BigDecimal(textField.getText());
				textField.setText("");
				operation = "+";
			}
		});
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.BOTH;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.gridx = 3;
		gbc_btnAdd.gridy = 4;
		contentPane.add(btnAdd, gbc_btnAdd);
		
		JButton btn0 = new JButton("0");
		btn0.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + btn0.getText());
			}
		});
		GridBagConstraints gbc_btn0 = new GridBagConstraints();
		gbc_btn0.fill = GridBagConstraints.BOTH;
		gbc_btn0.insets = new Insets(0, 0, 0, 5);
		gbc_btn0.gridwidth = 2;
		gbc_btn0.gridx = 0;
		gbc_btn0.gridy = 5;
		contentPane.add(btn0, gbc_btn0);
		
		JButton btnDot = new JButton(".");
		btnDot.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btnDot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + btnDot.getText());
			}
		});
		GridBagConstraints gbc_btnDot = new GridBagConstraints();
		gbc_btnDot.insets = new Insets(0, 0, 0, 5);
		gbc_btnDot.fill = GridBagConstraints.BOTH;
		gbc_btnDot.gridx = 2;
		gbc_btnDot.gridy = 5;
		contentPane.add(btnDot, gbc_btnDot);
		
		JButton btnEquals = new JButton("=");
		btnEquals.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btnEquals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ans;
				secondnum = new BigDecimal(textField.getText());
				
				switch (operation){
				case "+":
					result = firstnum.add(secondnum);
					ans = String.format("%.2f", result);
					textField.setText(ans);
					break;
					
				case "-":
					result = firstnum.subtract(secondnum);
					ans = String.format("%.2f", result);
					textField.setText(ans);
					break;
					
				case "x":
					result = firstnum.multiply(secondnum);
					ans = String.format("%.2f", result);
					textField.setText(ans);
					break;
					
				case "/":
					result = firstnum.divide(secondnum);
					ans = String.format("%.2f", result);
					textField.setText(ans);
					break;
					
				}
			}
		});
		GridBagConstraints gbc_btnEquals = new GridBagConstraints();
		gbc_btnEquals.fill = GridBagConstraints.BOTH;
		gbc_btnEquals.gridx = 3;
		gbc_btnEquals.gridy = 5;
		contentPane.add(btnEquals, gbc_btnEquals);
	}
}
