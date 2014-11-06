package gráfico;

import java.awt.Component;
import java.awt.EventQueue;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JList;

import Cliente.Cliente;
import Servidor.User;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Usuarios {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuarios window = new Usuarios();
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
	public Usuarios() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel label = new JLabel("");

		JLabel lblNewLabel = new JLabel();
		String[] seguidores= null;
		try {
			seguidores = new String[Cliente.cliente.user.followers().size()];
			for (int i = 0; i < seguidores.length; i++) {
				seguidores[i] = Cliente.cliente.user.followers().get(i);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblSeguidores = new JLabel("Seguidores:");

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(181)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(label))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblSeguidores)
					.addContainerGap(203, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(62, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
					.addGap(45))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(label)
						.addComponent(lblSeguidores))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(38, Short.MAX_VALUE))
		);

		final JList list = new JList(seguidores);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String seleccion = (String) list.getSelectedValue();
				try {
						Cliente.cliente.funciones.verUser(seleccion);
						try {
							Cliente.cliente.usuarioAbierto = (User) Cliente.cliente.registroServ.lookup(seleccion);
						} catch (NotBoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				PerfilOtros.main(null);
			}
		});
		scrollPane.setViewportView(list);

		frame.getContentPane().setLayout(groupLayout);

	}
}
