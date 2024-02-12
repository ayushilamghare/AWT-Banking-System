package gui;

import static utils.KycValidation.*;
import static utils.BasicValidations.*;
import static utils.LoginInUtils.*;
import static utils.SignUpUtils.*;
import static main.MainCaller.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.log.Log;

import exception.BankSystemException;
import pojo.Account;
import pojo.KYC;
import services.AccountServiceImpl;
import services.AccountServices;
import services.KycService;
import services.kycServiceImpl;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.CardLayout;
import javax.swing.JSplitPane;
import javax.swing.JDesktopPane;

public class UserDashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JPanel upperPanel = new JPanel();
	private JTextField balanceTextField;
	private JTextField amountWithdrawTextField;
	private JTextField depositeAmountTextField;
	private JTextField accNoTextField;
	private JTextField amountSendTextField;
	private JTextField cityTextField;
	private JTextField pincodeTextField;
	private JTextField adhaarNoTextField;
	private JTextField balanceProfileTextField;
	private JTextField nameTextField;
	private JTextField kycDoneTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserDashboard frame = new UserDashboard();
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
	public UserDashboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1180, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(234, 234, 234));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		upperPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		upperPanel.setBackground(new Color(128, 128, 255));
		upperPanel.setBounds(-39, -23, 1229, 115);
		contentPane.add(upperPanel);
		upperPanel.setLayout(null);

		JLabel dashboardLabel = new JLabel("Dashboard");
		dashboardLabel.setFont(new Font("Verdana", Font.BOLD, 48));
		dashboardLabel.setBounds(492, 39, 408, 65);
		upperPanel.add(dashboardLabel);

		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		leftPanel.setBounds(10, 102, 252, 571);
		contentPane.add(leftPanel);
		leftPanel.setLayout(null);

		JButton btnWithdraw = new JButton("Withdraw");

		btnWithdraw.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnWithdraw.setBounds(40, 144, 156, 58);
		leftPanel.add(btnWithdraw);

		JButton btnDeposit = new JButton("Deposit");

		btnDeposit.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnDeposit.setBounds(40, 267, 156, 58);
		leftPanel.add(btnDeposit);

		JButton btnFundTransfer = new JButton("Fund Transfer");

		btnFundTransfer.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnFundTransfer.setBounds(40, 398, 156, 58);
		leftPanel.add(btnFundTransfer);

		balanceTextField = new JTextField();
		balanceTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		balanceTextField.setBounds(40, 59, 156, 31);
		leftPanel.add(balanceTextField);
		balanceTextField.setColumns(10);
		balanceTextField.setEditable(false);
		balanceTextField.setText(LoggedInUserAccount.getBalance().toString());

		JLabel balanceLabel = new JLabel("Balance");
		balanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		balanceLabel.setBounds(92, 23, 67, 31);
		leftPanel.add(balanceLabel);

		JPanel rightPanel = new JPanel();
		rightPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		rightPanel.setBounds(904, 102, 252, 571);
		contentPane.add(rightPanel);
		rightPanel.setLayout(null);

		JLabel userNameLabel = new JLabel(LoggedInUserAccount.getFirstName() + " " + LoggedInUserAccount.getLastName());
		userNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		userNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		userNameLabel.setAlignmentY(CENTER_ALIGNMENT);
		userNameLabel.setBounds(41, 22, 201, 37);
		rightPanel.add(userNameLabel);

		JTextField accNOtextField = new JTextField();
		accNOtextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		accNOtextField.setText((String) null);
		accNOtextField.setEditable(false);
		accNOtextField.setColumns(10);
		accNOtextField.setBounds(64, 68, 123, 31);
		rightPanel.add(accNOtextField);
		accNOtextField.setText(String.valueOf(LoggedInUserAccount.getAccNo()));

		JButton btnKyc = new JButton("KYC");

		btnKyc.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnKyc.setBounds(45, 150, 156, 58);
		rightPanel.add(btnKyc);

		JButton btnShowProfile = new JButton("Show Profile");

		btnShowProfile.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnShowProfile.setBounds(45, 264, 156, 58);
		rightPanel.add(btnShowProfile);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(null, "Are you sure to Logout?");
				if (response == 0)// yes 1-> no 2-> cancel {
					LoggedInUserAccount = null;
				dispose();
				Login obj = new Login();
				obj.setVisible(true);
			}

		});
		btnLogout.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnLogout.setBounds(45, 382, 156, 58);
		rightPanel.add(btnLogout);

		JPanel MainCard = new JPanel();
		MainCard.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		MainCard.setBounds(272, 102, 624, 571);
		contentPane.add(MainCard);
		MainCard.setLayout(new CardLayout(0, 0));

		JPanel MainPageCard = new JPanel();
		MainCard.add(MainPageCard, "mainPageCard");

		JPanel WithdrawCard = new JPanel();
		MainCard.add(WithdrawCard, "withdrawCard");
		WithdrawCard.setLayout(null);

		JLabel withdrawMoneyLabel = new JLabel("Withdraw Money");
		withdrawMoneyLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		withdrawMoneyLabel.setBounds(229, 10, 181, 47);
		WithdrawCard.add(withdrawMoneyLabel);

		JLabel amountWithdrawLabel = new JLabel("Amount");
		amountWithdrawLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		amountWithdrawLabel.setBounds(139, 123, 88, 53);
		WithdrawCard.add(amountWithdrawLabel);

		amountWithdrawTextField = new JTextField();
		amountWithdrawTextField.setFont(new Font("Tahoma", Font.PLAIN, 22));
		amountWithdrawTextField.setBounds(295, 123, 213, 53);
		WithdrawCard.add(amountWithdrawTextField);
		amountWithdrawTextField.setColumns(10);

		JButton btnWithdrawAmount = new JButton("Withdraw");
		btnWithdrawAmount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					double amountWithdraw = Double.parseDouble(amountWithdrawTextField.getText());
					AccountServices obj = new AccountServiceImpl();
					obj.withdrawMoney(amountWithdraw);
					balanceTextField.setText(LoggedInUserAccount.getBalance().toString());
					balanceProfileTextField.setText(LoggedInUserAccount.getBalance().toString());
					amountSendTextField.setText(null);
					JOptionPane.showMessageDialog(null, LoggedInUserAccount.getBalance());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
		btnWithdrawAmount.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnWithdrawAmount.setBounds(229, 247, 152, 47);
		WithdrawCard.add(btnWithdrawAmount);

		JPanel DepositCard = new JPanel();
		MainCard.add(DepositCard, "depositCard");
		DepositCard.setLayout(null);

		JLabel depositMoneyLabel = new JLabel("Deposit Money");
		depositMoneyLabel.setBounds(229, 10, 181, 47);
		depositMoneyLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		DepositCard.add(depositMoneyLabel);

		JLabel amountDepositLabel = new JLabel("Amount");
		amountDepositLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		amountDepositLabel.setBounds(142, 108, 88, 53);
		DepositCard.add(amountDepositLabel);

		depositeAmountTextField = new JTextField();
		depositeAmountTextField.setFont(new Font("Tahoma", Font.PLAIN, 22));
		depositeAmountTextField.setColumns(10);
		depositeAmountTextField.setBounds(298, 108, 213, 53);
		DepositCard.add(depositeAmountTextField);

		JButton btnDepositAmount = new JButton("Deposit");
		btnDepositAmount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double amountDeposit = Double.parseDouble(depositeAmountTextField.getText());
					AccountServices obj = new AccountServiceImpl();
					obj.depositMoney(amountDeposit);
					balanceTextField.setText(LoggedInUserAccount.getBalance().toString());
					balanceProfileTextField.setText(LoggedInUserAccount.getBalance().toString());
					depositeAmountTextField.setText(null);
					JOptionPane.showMessageDialog(null,
							"Debited Rs: " + amountDeposit + " \n Total Bal:" + LoggedInUserAccount.getBalance());

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
		btnDepositAmount.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnDepositAmount.setBounds(232, 232, 152, 47);
		DepositCard.add(btnDepositAmount);

		JPanel TransferCard = new JPanel();
		MainCard.add(TransferCard, "transferCard");
		TransferCard.setLayout(null);

		JLabel FundTransferLabel = new JLabel("Fund Transfer");
		FundTransferLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		FundTransferLabel.setBounds(228, 42, 152, 47);
		TransferCard.add(FundTransferLabel);

		JLabel accNoDesLabel = new JLabel("Acc No");
		accNoDesLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		accNoDesLabel.setBounds(119, 135, 88, 53);
		TransferCard.add(accNoDesLabel);

		accNoTextField = new JTextField();
		accNoTextField.setFont(new Font("Tahoma", Font.PLAIN, 22));
		accNoTextField.setColumns(10);
		accNoTextField.setBounds(275, 135, 213, 53);
		TransferCard.add(accNoTextField);

		JButton btnSend = new JButton("SEND");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					int accNo = validateAccNo(accNoTextField.getText());

					double amount = validateAmount(amountSendTextField.getText());

					AccountServices obj = new AccountServiceImpl();
					obj.fundTransfer(accNo, amount);
					balanceTextField.setText(LoggedInUserAccount.getBalance().toString());
					balanceProfileTextField.setText(LoggedInUserAccount.getBalance().toString());
					accNoTextField.setText(null);
					amountSendTextField.setText(null);
					JOptionPane.showMessageDialog(null,
							"Sent Rs: " + amount + " \n Total Bal:" + LoggedInUserAccount.getBalance());

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}

			}
		});
		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnSend.setBounds(208, 317, 152, 47);
		TransferCard.add(btnSend);

		JLabel amountSendLabel = new JLabel("Amount");
		amountSendLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		amountSendLabel.setBounds(119, 220, 88, 53);
		TransferCard.add(amountSendLabel);

		amountSendTextField = new JTextField();
		amountSendTextField.setFont(new Font("Tahoma", Font.PLAIN, 22));
		amountSendTextField.setColumns(10);
		amountSendTextField.setBounds(275, 221, 213, 53);
		TransferCard.add(amountSendTextField);

		JPanel KYCCard = new JPanel();
		MainCard.add(KYCCard, "kycCard");
		KYCCard.setLayout(null);

		JLabel kycLabel = new JLabel("KYC");
		kycLabel.setBounds(272, 45, 57, 47);
		kycLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		KYCCard.add(kycLabel);

		JLabel cityLabel = new JLabel("City");
		cityLabel.setBounds(125, 141, 88, 53);
		cityLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		KYCCard.add(cityLabel);

		cityTextField = new JTextField();
		cityTextField.setBounds(281, 141, 213, 53);
		cityTextField.setFont(new Font("Tahoma", Font.PLAIN, 22));
		cityTextField.setColumns(10);
		KYCCard.add(cityTextField);

		JLabel pincodeLabel = new JLabel("Pincode");
		pincodeLabel.setBounds(125, 221, 88, 53);
		pincodeLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		KYCCard.add(pincodeLabel);

		pincodeTextField = new JTextField();
		pincodeTextField.setBounds(281, 221, 213, 53);
		pincodeTextField.setFont(new Font("Tahoma", Font.PLAIN, 22));
		pincodeTextField.setColumns(10);
		KYCCard.add(pincodeTextField);

		JButton btnkyc = new JButton("Done");
		btnkyc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					KycService kycServiceobj = new kycServiceImpl();

					KYC obj = validateKyc(cityTextField.getText(), pincodeTextField.getText(),
							adhaarNoTextField.getText());
					kycServiceobj.addKyc(obj, LoggedInUserAccount);
					obj.setKycId(LoggedInUserAccount.getAccNo());
					LoggedInUserAccount.setKyc(obj);
					JOptionPane.showMessageDialog(null, "KYC DONE");
				} catch (BankSystemException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());

				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}

			}
		});
		btnkyc.setBounds(219, 373, 152, 47);
		btnkyc.setFont(new Font("Tahoma", Font.PLAIN, 22));
		KYCCard.add(btnkyc);

		adhaarNoTextField = new JTextField();
		adhaarNoTextField.setBounds(281, 295, 213, 53);
		adhaarNoTextField.setFont(new Font("Tahoma", Font.PLAIN, 22));
		adhaarNoTextField.setColumns(10);
		KYCCard.add(adhaarNoTextField);

		JLabel adhaarNoLabel = new JLabel("Adhaar No");
		adhaarNoLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		adhaarNoLabel.setBounds(125, 294, 137, 53);
		KYCCard.add(adhaarNoLabel);

		JPanel ProfileCard = new JPanel();
		MainCard.add(ProfileCard, "profileCard");
		ProfileCard.setLayout(null);

		JLabel profileLabel = new JLabel("Profile");
		profileLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		profileLabel.setBounds(246, 31, 75, 47);
		ProfileCard.add(profileLabel);

		JLabel lblBalance = new JLabel("Balance");
		lblBalance.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblBalance.setBounds(125, 109, 88, 53);
		ProfileCard.add(lblBalance);

		balanceProfileTextField = new JTextField();
		balanceProfileTextField.setFont(new Font("Tahoma", Font.PLAIN, 22));
		balanceProfileTextField.setColumns(10);
		balanceProfileTextField.setBounds(281, 109, 213, 53);
		balanceProfileTextField.setText(LoggedInUserAccount.getBalance().toString());
		balanceProfileTextField.setEditable(false);
		ProfileCard.add(balanceProfileTextField);

		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		nameLabel.setBounds(125, 190, 88, 53);
		ProfileCard.add(nameLabel);

		nameTextField = new JTextField();
		nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 22));
		nameTextField.setColumns(10);
		nameTextField.setBounds(281, 190, 213, 53);
		nameTextField.setText(
				LoggedInUserAccount.getFirstName().toString() + " " + LoggedInUserAccount.getLastName().toString());
		nameTextField.setEditable(false);
		ProfileCard.add(nameTextField);

		JLabel kycDoneLabel = new JLabel("KYC");
		kycDoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		kycDoneLabel.setBounds(125, 263, 137, 53);
		ProfileCard.add(kycDoneLabel);

		kycDoneTextField = new JTextField();
		kycDoneTextField.setFont(new Font("Tahoma", Font.PLAIN, 22));
		kycDoneTextField.setColumns(10);
		kycDoneTextField.setBounds(281, 264, 213, 53);
		kycDoneTextField.setEditable(false);
		if(LoggedInUserAccount.getKyc()!=null) {
			kycDoneTextField.setText("DONE");
		}
		else 
			{
			kycDoneTextField.setText("NOT DONE");
			}
			
			
		ProfileCard.add(kycDoneTextField);

		CardLayout cardLayout = (CardLayout) MainCard.getLayout();

		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(MainCard, "withdrawCard");
			}
		});

		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(MainCard, "depositCard");
			}
		});

		btnShowProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(MainCard, "profileCard");
			}
		});

		btnFundTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (LoggedInUserAccount.getKyc() == null)
						throw new BankSystemException("KYC is not done!");

					cardLayout.show(MainCard, "transferCard");

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});

		btnKyc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (LoggedInUserAccount.getKyc() != null)
						throw new BankSystemException("KYC is already done!");
					cardLayout.show(MainCard, "kycCard");

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}

			}
		});
	}
}
