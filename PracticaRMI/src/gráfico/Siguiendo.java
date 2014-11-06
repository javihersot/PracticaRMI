package gráfico;

import java.awt.EventQueue;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Cliente.Cliente;
import Servidor.User;

public class Siguiendo {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Siguiendo window = new Siguiendo();
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
	public Siguiendo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lblSiguiendo = new JLabel("Siguiendo:");
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(186)
					.addComponent(lblSiguiendo)
					.addContainerGap(186, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(42, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE)
					.addGap(29))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSiguiendo)
					.addGap(37)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		
		String[] siguiendo = null;
		try {
			siguiendo = new String[Cliente.cliente.user.following().size()];
			for (int i = 0; i < siguiendo.length; i++) {
				siguiendo[i] = Cliente.cliente.user.following().get(i);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		final JList list = new JList(siguiendo);
		int [] seleccion = null;
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String seleccion = (String) list.getSelectedValue();
				try {
					try {
						Cliente.cliente.funciones.verUser(seleccion);
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
