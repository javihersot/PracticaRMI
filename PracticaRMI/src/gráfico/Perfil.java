package gráfico;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import Servidor.MessageInt;

import Cliente.Cliente;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Perfil {

	private JFrame frame;
	private JLabel Imagen_1;
	private JLabel nombre_1;
	private JLabel info_1;
	private JLabel web_1;
	public static DefaultListModel listModel;
	private static JScrollPane misTweets;
	JList list_1;
	private JList list;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Perfil window = new Perfil();
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
	public Perfil() {
		listModel = new DefaultListModel();
		try {
			for (int i = 0; i < Cliente.cliente.user.getMisTweets().size(); i++) {
				listModel
						.addElement(Cliente.cliente.user.getMisTweets().get(i));
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list_1 = new JList(listModel);
		list_1.setModel(listModel);
		misTweets = new JScrollPane();
		misTweets.setViewportView(list_1);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel Imagen = null;
		BufferedImage myPicture;
		try {
			myPicture = ImageIO
					.read(new File (Cliente.cliente.user.getProfile().getPhoto()));
			Imagen_1 = new JLabel(new ImageIcon(myPicture.getScaledInstance(
					160, 160, BufferedImage.SCALE_SMOOTH)));
			Imagen_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					CambiarFoto.main(null);
				}
			});
			frame.getContentPane().add(Imagen_1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JLabel nombre = null;
		try {
			nombre_1 = new JLabel(Cliente.cliente.user.getProfile()
					.getName());
			nombre_1.setHorizontalAlignment(SwingConstants.CENTER);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JLabel info = null;
		JLabel web = null;
		try {
			info_1 = new JLabel(Cliente.cliente.user.getProfile()
					.getInfo());

			info_1.setHorizontalAlignment(SwingConstants.CENTER);

			web_1 = new JLabel(Cliente.cliente.user.getProfile().getWeb());
			web_1.setHorizontalAlignment(SwingConstants.CENTER);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JButton btnEditarPerfil = new JButton("Editar perfil");
		btnEditarPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EditarPerfil.main(null);
				frame.dispose();
			}
		});

		misTweets = new JScrollPane();

		JButton btnEditarCuenta = new JButton("Editar Cuenta");
		btnEditarCuenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EditarUsuario.main(null);
			}
		});

		JButton btnInicio = new JButton("Inicio");
		btnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Principal.main(null);
				frame.dispose();
			}
		});
		
		JButton btnSeguidores = new JButton("Seguidores");
		btnSeguidores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Usuarios.main(null);
			}
		});
		
		JButton btnSiguiendo = new JButton("Siguiendo");
		btnSiguiendo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Siguiendo.main(null);
			}
		});

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(56, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(Imagen_1, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnInicio, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnEditarCuenta)
								.addComponent(btnEditarPerfil)
								.addComponent(btnSeguidores)
								.addComponent(btnSiguiendo))
							.addGap(62)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(133)
							.addComponent(nombre_1, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(80)
							.addComponent(info_1, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(70)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(misTweets, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)
								.addComponent(web_1, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(40, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addComponent(Imagen_1, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnEditarPerfil)
					.addGap(27)
					.addComponent(btnEditarCuenta)
					.addGap(31)
					.addComponent(btnSeguidores)
					.addGap(29)
					.addComponent(btnSiguiendo)
					.addPreferredGap(ComponentPlacement.RELATED, 155, Short.MAX_VALUE)
					.addComponent(btnInicio, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addGap(49))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addComponent(nombre_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(info_1, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(web_1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
					.addComponent(misTweets, GroupLayout.PREFERRED_SIZE, 489, GroupLayout.PREFERRED_SIZE)
					.addGap(28))
		);
		
		list = new JList(listModel);
		misTweets.setViewportView(list);
		
		try {
			for (int i = 0; i < Cliente.cliente.user.getMisTweets().size(); i++) {
				listModel
						.addElement(Cliente.cliente.user.getMisTweets().get(i));
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			listModel.clear();
			for (int i = 0; i < Cliente.cliente.user.getMisTweets().size(); i++) {
				MessageInt tweet = Cliente.cliente.user.getMisTweets().get(i);
				listModel.addElement(tweet.getAutor() + ": " + tweet.getContent() + " Retweets: [ " + tweet.getRetweets() + " ] Favs: [ " + tweet.getFavs()+ " ]");
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame.getContentPane().setLayout(groupLayout);
	}
}
