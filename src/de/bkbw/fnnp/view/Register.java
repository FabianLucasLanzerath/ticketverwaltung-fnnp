package de.bkbw.fnnp.view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Register extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private JFrame frame;
	private JPanel registerPanel;
	private JTextField txtEmail;
	private JPasswordField txtPassword;

	/**
	 * Launch the application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the Frame
	 */
	public Register() {
		initialize();
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		registerPanel = new JPanel();
		registerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(registerPanel);
		registerPanel.setLayout(null);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 495, 346);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		registerPanel.setLayout(null);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 13));
		txtEmail.setHorizontalAlignment(SwingConstants.LEFT);
		txtEmail.setBounds(114, 59, 250, 33);
		txtEmail.setBorder(new EmptyBorder(0, 0, 0, 0));
		registerPanel.add(txtEmail);
		txtEmail.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 13));
		txtPassword.setBounds(114, 128, 250, 30);
		txtPassword.setBorder(new EmptyBorder(0, 0, 0, 0));
		registerPanel.add(txtPassword);

		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 13));
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setBounds(114, 34, 190, 23);
		registerPanel.add(lblEmail);

		JLabel lblPassword = new JLabel("Passwort");
		lblPassword.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 13));
		lblPassword.setBounds(114, 103, 190, 23);
		registerPanel.add(lblPassword);
		
		
		JButton btnRegister = new JButton("Registrieren");
		btnRegister.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));
		btnRegister.setBackground(Color.LIGHT_GRAY);
		btnRegister.setForeground(Color.BLACK);
		btnRegister.setBounds(114, 169, 120, 23);
		btnRegister.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnRegister.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		registerPanel.add(btnRegister);

		JButton btnLogin = new JButton("Einloggen");
		btnLogin.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));
		btnLogin.setBackground(Color.LIGHT_GRAY);
		btnLogin.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == btnLogin) {
					Register.this.dispose();
					Login myWindow = new Login();
					myWindow.getFrame().setVisible(true);	
				}

			}
		});
		
		btnLogin.setBounds(244, 169, 120, 23);
		registerPanel.add(btnLogin);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}