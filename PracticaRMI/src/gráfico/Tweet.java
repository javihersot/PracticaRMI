package gráfico;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import javax.swing.JToggleButton;

public class Tweet {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tweet window = new Tweet();
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
	public Tweet() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JScrollPane scrollPane = new JScrollPane();

		JButton btnAtrs = new JButton("Ok");
		btnAtrs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		
		final JToggleButton tglbtnRetwittear = new JToggleButton("Retwittear");
		tglbtnRetwittear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tglbtnRetwittear.isSelected()){
					try {
						Cliente.Cliente.cliente.user
						.retwittear(Cliente.Cliente.cliente.user
								.getTweets()
								.indexOf(
										Cliente.Cliente.cliente.tweetAbierto),true);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}else{
					try {
						Cliente.Cliente.cliente.user
						.retwittear(Cliente.Cliente.cliente.user
								.getTweets()
								.indexOf(
										Cliente.Cliente.cliente.tweetAbierto),false);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		final JToggleButton tglbtnFav = new JToggleButton("Fav");
		tglbtnFav.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tglbtnFav.isSelected()){
					try {
						Cliente.Cliente.cliente.user
						.fav(Cliente.Cliente.cliente.user
								.getTweets()
								.indexOf(
										Cliente.Cliente.cliente.tweetAbierto),true);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}else{
					try {
						Cliente.Cliente.cliente.user
						.fav(Cliente.Cliente.cliente.user
								.getTweets()
								.indexOf(
										Cliente.Cliente.cliente.tweetAbierto),false);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(188)
							.addComponent(btnAtrs))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(54)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(tglbtnRetwittear)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(tglbtnFav)
									.addGap(49)))))
					.addContainerGap(63, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(tglbtnRetwittear)
						.addComponent(tglbtnFav))
					.addGap(24)
					.addComponent(btnAtrs)
					.addContainerGap(64, Short.MAX_VALUE))
		);

		JTextArea tweet = null;
		try {
			tweet = new JTextArea(
					Cliente.Cliente.cliente.tweetAbierto.getAutor() + " : "
							+ Cliente.Cliente.cliente.tweetAbierto.getContent()
							+ " Ret: [ "
							+ Cliente.Cliente.cliente.tweetAbierto.getRetweets()
							+ " ] Favs: [ "
							+ Cliente.Cliente.cliente.tweetAbierto.getFavs() + " ]");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		scrollPane.setViewportView(tweet);
		frame.getContentPane().setLayout(groupLayout);
		
	}
}
