package gráfico;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

public class Register {

	private JFrame frame;
	private JPasswordField password2;
	private JPasswordField password1;
	private JTextField userName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario:");

		JLabel lblContrasea = new JLabel("Contraseña:");

		JLabel lblCompruebaContrasea = new JLabel("Comprueba contraseña:");

		password2 = new JPasswordField();

		password1 = new JPasswordField();

		userName = new JTextField();
		userName.setColumns(10);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int error = 0;
				try {
					error = Cliente.Cliente.cliente.funciones.register(userName
							.getText(), password1.getText(),
							password2.getText());
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				if (error == 1) {
					JOptionPane.showMessageDialog(frame, "Usuario ya en uso.");
				} else {
					if (error == 2) {
						JOptionPane.showMessageDialog(frame, "Las contraseñas no coinciden.");
					}
					else {
						JOptionPane.showMessageDialog(frame, "Registrado correctamente.");
						frame.dispose();
					}
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(35)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblCompruebaContrasea)
																						.addComponent(
																								lblContrasea)
																						.addComponent(
																								lblNombreDeUsuario))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING,
																								false)
																						.addComponent(
																								userName)
																						.addComponent(
																								password1,
																								GroupLayout.DEFAULT_SIZE,
																								137,
																								Short.MAX_VALUE)
																						.addComponent(
																								password2,
																								GroupLayout.DEFAULT_SIZE,
																								137,
																								Short.MAX_VALUE)))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(174)
																		.addComponent(
																				btnRegistrar)))
										.addContainerGap(93, Short.MAX_VALUE)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(42)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblNombreDeUsuario)
														.addComponent(
																userName,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblContrasea)
														.addComponent(
																password1,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblCompruebaContrasea)
														.addComponent(
																password2,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(62).addComponent(btnRegistrar)
										.addContainerGap(61, Short.MAX_VALUE)));
		frame.getContentPane().setLayout(groupLayout);
	}
}
