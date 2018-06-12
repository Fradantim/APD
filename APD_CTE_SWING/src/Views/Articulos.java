package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import delegate.BusinessDelegate;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.naming.CommunicationException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Articulos extends JFrame {

	private JPanel contentPane;
	private JTextField textCodBarras;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private BusinessDelegate bd;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Articulos frame = new Articulos();
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
	public Articulos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Codigo de Barras");
		lblNewLabel.setBounds(38, 83, 126, 20);
		contentPane.add(lblNewLabel);
		
		textCodBarras = new JTextField();
		textCodBarras.setBounds(179, 80, 146, 26);
		contentPane.add(textCodBarras);
		textCodBarras.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(38, 119, 126, 20);
		contentPane.add(lblDescripcion);
		
		JLabel lblTamao = new JLabel("Tama\u00F1o");
		lblTamao.setBounds(38, 158, 126, 20);
		contentPane.add(lblTamao);
		
		JLabel lblPresentacion = new JLabel("Presentacion");
		lblPresentacion.setBounds(38, 202, 126, 20);
		contentPane.add(lblPresentacion);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(179, 116, 146, 26);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(179, 155, 146, 26);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(179, 199, 146, 26);
		contentPane.add(textField_2);
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					bd = new BusinessDelegate();
					bd.altaArticulo(0, "test", "desTest", 10, "Caja", "Kgs", 20, 100, 20);
				} catch (CommunicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnInsertar.setBounds(236, 269, 115, 29);
		contentPane.add(btnInsertar);
		
		JLabel lblAltaDeArticulo = new JLabel("ALTA DE ARTICULO");
		lblAltaDeArticulo.setBounds(38, 28, 240, 20);
		contentPane.add(lblAltaDeArticulo);
	}
}
