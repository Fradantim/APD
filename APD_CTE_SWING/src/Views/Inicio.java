package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Inicio extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
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
	public Inicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 394);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Clientes");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmAgregar = new JMenuItem("Agregar");
		mnNewMenu.add(mntmAgregar);
		mntmAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) 
			{
				ClienteAdd a = new ClienteAdd();
				a.setVisible(true);				
			}
		});
		
		
		JMenuItem mntmModificarbaja = new JMenuItem("Modificar/Baja");
		mnNewMenu.add(mntmModificarbaja);
		mntmModificarbaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) 
			{
				ClienteEdit a = new ClienteEdit();
				a.setVisible(true);				
			}
		});
		
		JMenu mnArticulos = new JMenu("Articulos");
		menuBar.add(mnArticulos);
		
		JMenuItem mntmAlta = new JMenuItem("Alta");
		mntmAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) 
			{
				ArticulosAdd a = new ArticulosAdd();
				a.setVisible(true);				
			}
		});
		mnArticulos.add(mntmAlta);
		
		JMenuItem mntmModificacion = new JMenuItem("Modificacion");
		mntmModificacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) 
			{
				ArticulosEdit a = new ArticulosEdit();
				a.setVisible(true);				
			}
		});
		mnArticulos.add(mntmModificacion);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
}
