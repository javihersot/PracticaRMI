package gráfico;

import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Cliente.Cliente;
import Servidor.MessageInt;
import Servidor.Tweet;
import Servidor.User;
import Servidor.UserImpl;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;

public class Principal {

	public static JFrame frame;
	private static JLabel Imagen;
	private static JTextField usuario;
	private static JTextField tweet;
	private static JScrollPane timeLine;
	public static DefaultListModel listModel;
	JList list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void notify(String msg){
		JOptionPane.showMessageDialog(frame, msg);
	}


	public void refresh() {
		try {
			MessageInt tweet = Cliente.cliente.user.getTweets().get(Cliente.cliente.user.getTweets().size()-1);
			this.listModel.addElement(tweet.getAutor() + ": " + tweet.getContent() + " Retweets: [ " + tweet.getRetweets() + " ] Favs: [ " + tweet.getFavs()+ " ]");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list.setModel(listModel);
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		listModel = new DefaultListModel();
		try {
			for (int i = 0; i < Cliente.cliente.user.getTweets().size(); i++) {
				listModel
						.addElement(Cliente.cliente.user.getTweets().get(i));
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list = new JList(listModel);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int tweet = (Integer) list.getSelectedIndex();
				try {
					Cliente.cliente.tweetAbierto = Cliente.cliente.user.getTweets().get(tweet);
					
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				gráfico.Tweet.main(null);
			}
		});
		timeLine = new JScrollPane();
		timeLine.setViewportView(list);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btnDesconectar = new JButton("Desc");
		btnDesconectar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				JOptionPane.showConfirmDialog(null,
						"¿Está seguro de que desea salir?", "Desconexión",
						dialogButton);

				if (dialogButton == JOptionPane.YES_OPTION) {
					try {
						Cliente.cliente.funciones
								.disconnect(Cliente.cliente.user.getUserName());
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Logging.main(null);
					frame.dispose();
				} else if (dialogButton == JOptionPane.YES_OPTION) {
					return;
				}

			}
		});

		JButton btnPerfil = new JButton("Perfil");
		btnPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Perfil.main(null);
				frame.dispose();
			}
		});

		JButton btnTweetear = new JButton("Tweetear");
		btnTweetear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Cliente.cliente.user.tweet(tweet.getText(),0,0);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnTweetear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		JButton btnMensajes = new JButton("Mensajes");
		btnMensajes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MailBox.main(null);
			}
		});

		BufferedImage myPicture;

		JLabel lblNewLabel = null;
		try {
			myPicture = ImageIO.read(new File(Cliente.cliente.user.getProfile()
					.getPhoto()));
			Imagen = new JLabel(new ImageIcon(myPicture.getScaledInstance(160,
					160, BufferedImage.SCALE_SMOOTH)));
			frame.getContentPane().add(Imagen);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JButton btnBuscarUsuario = new JButton("Buscar usuario");
		btnBuscarUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					try {
						Cliente.cliente.verUser(usuario.getText());
						Cliente.cliente.usuarioAbierto = (User) Cliente.cliente.registroServ
								.lookup(usuario.getText());
					} catch (NotBoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (Cliente.cliente.usuarioAbierto == null) {
					JOptionPane.showMessageDialog(frame,
							"El usuario indicado no existe.");
				} else {
					PerfilOtros.main(null);
				}

			}
		});

		usuario = new JTextField();
		usuario.setColumns(10);

		tweet = new JTextField();
		tweet.setColumns(10);

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(143)
										.addComponent(usuario,
												GroupLayout.PREFERRED_SIZE,
												215, GroupLayout.PREFERRED_SIZE)
										.addGap(27)
										.addComponent(btnBuscarUsuario)
										.addGap(93)
										.addComponent(btnDesconectar)
										.addContainerGap())
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				tweet,
																				GroupLayout.PREFERRED_SIZE,
																				425,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				btnTweetear,
																				GroupLayout.PREFERRED_SIZE,
																				150,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																Alignment.LEADING,
																groupLayout
																		.createSequentialGroup()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addGap(44)
																										.addGroup(
																												groupLayout
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addComponent(
																																btnPerfil)
																														.addComponent(
																																btnMensajes)))
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addGap(28)
																										.addComponent(
																												Imagen,
																												GroupLayout.PREFERRED_SIZE,
																												209,
																												GroupLayout.PREFERRED_SIZE)))
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				59,
																				Short.MAX_VALUE)
																		.addComponent(
																				timeLine,
																				GroupLayout.PREFERRED_SIZE,
																				363,
																				GroupLayout.PREFERRED_SIZE)))
										.addGap(39)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																btnDesconectar)
														.addComponent(
																btnBuscarUsuario)
														.addComponent(
																usuario,
																GroupLayout.PREFERRED_SIZE,
																22,
																GroupLayout.PREFERRED_SIZE))
										.addGap(56)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				Imagen,
																				GroupLayout.PREFERRED_SIZE,
																				181,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				btnPerfil)
																		.addGap(34)
																		.addComponent(
																				btnMensajes)
																		.addGap(160))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				timeLine,
																				GroupLayout.PREFERRED_SIZE,
																				469,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)))
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING,
																false)
														.addComponent(tweet)
														.addComponent(
																btnTweetear,
																GroupLayout.DEFAULT_SIZE,
																85,
																Short.MAX_VALUE))
										.addGap(32)));
		try {
			listModel.clear();
			for (int i = 0; i < Cliente.cliente.user.getTweets().size(); i++) {
				MessageInt tweet = Cliente.cliente.user.getTweets().get(i);
				listModel.addElement(tweet.getAutor() + ": " + tweet.getContent() + " Retweets: [ " + tweet.getRetweets() + " ] Favs: [ " + tweet.getFavs()+ " ]");
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame.getContentPane().setLayout(groupLayout);
	}
}
