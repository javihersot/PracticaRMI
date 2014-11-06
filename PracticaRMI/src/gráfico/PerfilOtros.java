package gráfico;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import Servidor.InexistentUserException;

import Cliente.Cliente;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PerfilOtros {

	private JFrame frame;
	private JLabel imagen_1;
	private JLabel userName_1;
	private JLabel nombre_1;
	private JTextArea info_1;
	private JLabel web_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PerfilOtros window = new PerfilOtros();
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
	public PerfilOtros() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel imagen = new JLabel();
		BufferedImage myPicture;
		try {
			myPicture = ImageIO
					.read(new File (Cliente.cliente.usuarioAbierto.getProfile().getPhoto()));
			imagen_1 = new JLabel(new ImageIcon(myPicture.getScaledInstance(
					160, 160, BufferedImage.SCALE_SMOOTH)));
		}catch(RemoteException e ){
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel userName=null;
		try {
			userName_1 = new JLabel(Cliente.cliente.usuarioAbierto.getUserName());
			userName_1.setHorizontalAlignment(SwingConstants.CENTER);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel nombre = null;
		try {
			nombre_1 = new JLabel(Cliente.cliente.usuarioAbierto.getProfile().getName());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		nombre_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JTextArea info=null;
		try {
			info_1 = new JTextArea(Cliente.cliente.usuarioAbierto.getProfile().getInfo());
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		info_1.setEditable(false);
		
		final JToggleButton tglbtnSeguir = new JToggleButton("Siguiendo");
		tglbtnSeguir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!tglbtnSeguir.isSelected()){
					System.out.println("Mal");
					try {
						Cliente.cliente.user.unfollow(Cliente.cliente.usuarioAbierto.getUserName());
					} catch (RemoteException e1) {
						e1.printStackTrace();
					} catch (InexistentUserException e1) {
						e1.printStackTrace();
					}
				}else{
					System.out.println("OK");
					try {
						Cliente.cliente.user.follow(Cliente.cliente.usuarioAbierto.getUserName());
					} catch (RemoteException e1) {
						e1.printStackTrace();
					} catch (InexistentUserException e1) {
						e1.printStackTrace();
					}
				}
					
			}
		});

		
		JLabel web=null;
		try {
			web_1 = new JLabel(Cliente.cliente.usuarioAbierto.getProfile().getWeb());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		web_1.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(tglbtnSeguir)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(imagen_1, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
								.addComponent(userName_1, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
							.addGap(51)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(nombre_1, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
								.addComponent(info_1, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(41, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(284, Short.MAX_VALUE)
					.addComponent(web_1)
					.addGap(86))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(nombre_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(info_1, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(imagen_1, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(userName_1)))
					.addGap(18)
					.addComponent(web_1)
					.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
					.addComponent(tglbtnSeguir)
					.addContainerGap())
		);
		try {
			for (int i = 0; i< Cliente.cliente.user.following().size(); i++){
				if(Cliente.cliente.user.following().get(i).equals(Cliente.cliente.usuarioAbierto.getUserName())){
					tglbtnSeguir.setSelected(true);
					break;
				}
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame.getContentPane().setLayout(groupLayout);
	}
}
