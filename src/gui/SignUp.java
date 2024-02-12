package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exception.BankSystemException;
import pojo.Account;
import services.AccountServiceImpl;
import services.AccountServices;
import services.KycService;

import static utils.SignUpUtils.*;
import static pojo.Account.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.awt.event.ActionEvent;

public class SignUp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField dobField;
	private JTextField emailField;
	private JTextField balanceField;
	private JTextField pinField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
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
	public SignUp() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 818);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel mainHeadingPanel = new JPanel();
		mainHeadingPanel.setBackground(new Color(128, 128, 255));
		mainHeadingPanel.setBounds(-10, 10, 1036, 101);
		contentPane.add(mainHeadingPanel);
		mainHeadingPanel.setLayout(null);

		JLabel mainHeading = new JLabel("Bank Management System");
		mainHeading.setBounds(296, 37, 478, 40);
		mainHeadingPanel.add(mainHeading);
		mainHeading.setFont(new Font("Verdana", Font.BOLD, 32));

		JLabel signUpLabel = new JLabel("Sign Up");
		signUpLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
		signUpLabel.setBounds(463, 121, 135, 42);
		contentPane.add(signUpLabel);

		JLabel firstNameLabel = new JLabel("First Name");
		firstNameLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		firstNameLabel.setBounds(248, 207, 185, 42);
		contentPane.add(firstNameLabel);

		firstNameField = new JTextField();
		firstNameField.setFont(new Font("Verdana", Font.PLAIN, 20));
		firstNameField.setBounds(430, 207, 341, 42);
		contentPane.add(firstNameField);
		firstNameField.setColumns(10);

		JLabel lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		lastNameLabel.setBounds(248, 276, 185, 42);
		contentPane.add(lastNameLabel);

		lastNameField = new JTextField();
		lastNameField.setFont(new Font("Verdana", Font.PLAIN, 20));
		lastNameField.setColumns(10);
		lastNameField.setBounds(430, 276, 341, 42);
		contentPane.add(lastNameField);

		JLabel dobLabel = new JLabel("Date of Birth");
		dobLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		dobLabel.setBounds(248, 419, 185, 42);
		contentPane.add(dobLabel);

		dobField = new JTextField();
		dobField.setFont(new Font("Verdana", Font.PLAIN, 20));
		dobField.setColumns(10);
		dobField.setBounds(430, 419, 341, 42);
		contentPane.add(dobField);

		emailField = new JTextField();
		emailField.setFont(new Font("Verdana", Font.PLAIN, 20));
		emailField.setColumns(10);
		emailField.setBounds(430, 350, 341, 42);
		contentPane.add(emailField);

		JLabel emailLabel = new JLabel("Email");
		emailLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		emailLabel.setBounds(248, 350, 185, 42);
		contentPane.add(emailLabel);

		balanceField = new JTextField();
		balanceField.setFont(new Font("Verdana", Font.PLAIN, 20));
		balanceField.setColumns(10);
		balanceField.setBounds(430, 498, 341, 42);
		contentPane.add(balanceField);

		JLabel balanceLabel = new JLabel("Balance");
		balanceLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		balanceLabel.setBounds(248, 498, 185, 42);
		contentPane.add(balanceLabel);

		JRadioButton savingsRadioButton = new JRadioButton("Savings");
		savingsRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		savingsRadioButton.setBounds(455, 652, 103, 42);
		contentPane.add(savingsRadioButton);

		JRadioButton currentRadioButton = new JRadioButton("Current");
		currentRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		currentRadioButton.setBounds(614, 652, 103, 42);
		contentPane.add(currentRadioButton);

		ButtonGroup btGroup = new ButtonGroup();
		btGroup.add(currentRadioButton);
		btGroup.add(savingsRadioButton);

		JLabel accountTypeLabel = new JLabel("Account Type");
		accountTypeLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		accountTypeLabel.setBounds(248, 652, 185, 42);
		contentPane.add(accountTypeLabel);

		JLabel pinLabel = new JLabel("Pin");
		pinLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		pinLabel.setBounds(248, 576, 185, 42);
		contentPane.add(pinLabel);

		pinField = new JTextField();
		pinField.setFont(new Font("Verdana", Font.PLAIN, 20));
		pinField.setColumns(10);
		pinField.setBounds(430, 576, 341, 42);
		contentPane.add(pinField);

		JButton signUpButton = new JButton("Sign Up");
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String firstName = firstNameField.getText(); // SHOULD NOT CONTAIN SPACES
				String lastName = lastNameField.getText(); // SHOULD NOT CONTAIN SPACES
				String email = emailField.getText(); //
				String dob = dobField.getText(); // Parse to Date(SQL)
				String balance = balanceField.getText(); // Should be Number
				String pin = pinField.getText(); // Number
				String accType = "";

//				if (!currentRadioButton.isSelected())
//					JOptionPane.showMessageDialog(null, "SELECT ACCOUNT TYPE, PLEASE!");
				if (currentRadioButton.isSelected()) {
					accType = "CURRENT";
				} else {
					accType = "SAVINGS";
				}
				try {
					Account accObj = validateAll(firstName, lastName, email, dob, balance, pin, accType);
					AccountServices accServiceObj = new AccountServiceImpl();
					accServiceObj.addAccount(accObj);

					JOptionPane.showMessageDialog(null, "Signed Up!");

					dispose();
					Login loginObj = new Login();
					loginObj.setVisible(true);

				} catch (BankSystemException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (DateTimeParseException e2) {
					JOptionPane.showMessageDialog(null, "Date must be in YYYY-MM-DD Format");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.getStackTrace();
				}

			}
		});
		signUpButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		signUpButton.setBounds(455, 729, 108, 42);
		contentPane.add(signUpButton);
	}
}
