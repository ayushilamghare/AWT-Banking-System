package gui;
import static utils.LoginInUtils.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exception.BankSystemException;
import services.AccountServiceImpl;
import services.KycService;
import services.kycServiceImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.lang.System.Logger;
import java.awt.event.ActionEvent;
import static main.MainCaller.*;
public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField accNoField;
	private JPasswordField pinField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login()
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 990, 684);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel mainHeading = new JLabel("Ayushi's Bank Management");
		mainHeading.setFont(new Font("Verdana", Font.BOLD, 36));
		mainHeading.setBounds(206, 24, 582, 83);
		contentPane.add(mainHeading);
		
		JPanel mainHeadingPanel = new JPanel();
		mainHeadingPanel.setBackground(new Color(128, 128, 255));
		mainHeadingPanel.setBounds(-125, -21, 1176, 145);
		contentPane.add(mainHeadingPanel);
		
		JLabel loginHeading = new JLabel("Login");
		loginHeading.setFont(new Font("Verdana", Font.BOLD, 25));
		loginHeading.setBounds(434, 150, 87, 67);
		contentPane.add(loginHeading);
		
		accNoField = new JTextField();
		accNoField.setFont(new Font("Verdana", Font.PLAIN, 20));
		accNoField.setBounds(466, 269, 237, 43);
		contentPane.add(accNoField);
		accNoField.setColumns(10);
		
		JLabel accNoLabel = new JLabel("Account Number");
		accNoLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		accNoLabel.setBounds(259, 269, 181, 43);
		contentPane.add(accNoLabel);
		
		JLabel pinLabel = new JLabel("Pin");
		pinLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		pinLabel.setBounds(259, 347, 181, 43);
		contentPane.add(pinLabel);
		
		pinField = new JPasswordField();
		pinField.setFont(new Font("Verdana", Font.PLAIN, 20));
		pinField.setBounds(466, 347, 237, 43);
		contentPane.add(pinField);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String stringAccNo = accNoField.getText();
				
				char[] pinArray = pinField.getPassword();
				String stringPin = String.valueOf(pinArray);
				
				try
				{
					
					validateLoginCreds(stringAccNo,stringPin);	//Check if empty or not in number format
					AccountServiceImpl service = new AccountServiceImpl();
					LoggedInUserAccount= service.getAccount(Integer.parseInt(stringAccNo) ,Integer.parseInt(stringPin));	//get user by accno and pin... if wrong, throw exception
					
					KycService kycService = new kycServiceImpl();
					LoggedInUserAccount.setKyc(kycService.getAccount(LoggedInUserAccount.getAccNo()));
					
					dispose();
					UserDashboard dashboardObj= new UserDashboard();
					dashboardObj.setVisible(true);
					
					
				} catch (BankSystemException e1)
				{
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				catch (Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(342, 474, 124, 43);
		contentPane.add(btnNewButton);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				SignUp signUpObj = new SignUp();
				signUpObj.setVisible(true);
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSignUp.setBounds(521, 474, 124, 43);
		contentPane.add(btnSignUp);
	}
}
