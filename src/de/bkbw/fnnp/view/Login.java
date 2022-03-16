package de.bkbw.fnnp.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JPanel loginPanel;
	private JTextField txtEmail;
	private JPasswordField txtPassword;

	/**
	 * Launch the application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
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
		loginPanel = new JPanel();
		loginPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(loginPanel);
		loginPanel.setLayout(null);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 495, 346);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 13));
		txtEmail.setHorizontalAlignment(SwingConstants.LEFT);
		txtEmail.setBounds(114, 59, 250, 33);
		txtEmail.setBorder(new EmptyBorder(0, 0, 0, 0));
		loginPanel.add(txtEmail);
		txtEmail.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 13));
		txtPassword.setBounds(114, 128, 250, 30);
		txtPassword.setBorder(new EmptyBorder(0, 0, 0, 0));
		loginPanel.add(txtPassword);

		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 13));
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setBounds(114, 34, 190, 23);
		loginPanel.add(lblEmail);

		JLabel lblPassword = new JLabel("Passwort");
		lblPassword.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 13));
		lblPassword.setBounds(114, 103, 190, 23);
		loginPanel.add(lblPassword);

		JButton btnLogin = new JButton("Einloggen");
		btnLogin.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));
		btnLogin.setBackground(Color.LIGHT_GRAY);
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBounds(114, 169, 120, 23);
		btnLogin.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		loginPanel.add(btnLogin);

		JButton btnRegister = new JButton("Registrieren");
		btnRegister.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));
		btnRegister.setBackground(Color.LIGHT_GRAY);
		btnRegister.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnRegister.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == btnRegister) {
					Login.this.dispose();
					Register myWindow = new Register();
					myWindow.getFrame().setVisible(true);	
				}

			}
		});
		
		btnRegister.setBounds(244, 169, 120, 23);
		loginPanel.add(btnRegister);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
