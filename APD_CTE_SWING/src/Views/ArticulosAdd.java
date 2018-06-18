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

public class ArticulosAdd extends JFrame {

	private JPanel contentPane;
	private JTextField textCodBarras;
	private JTextField textDescripcion;
	private JTextField textTamano;
	private JTextField textPresentacion;
	private BusinessDelegate bd;
	private JTextField textUnidad;
	private JTextField textPrecioVenta;
	private JTextField textCantAComprar;
	private JTextField textCantUbicable;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					ArticulosAdd frame = new ArticulosAdd();
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
	public ArticulosAdd() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 487, 491);
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
		lblPresentacion.setBounds(38, 194, 126, 20);
		contentPane.add(lblPresentacion);
		
		textDescripcion = new JTextField();
		textDescripcion.setColumns(10);
		textDescripcion.setBounds(179, 116, 146, 26);
		contentPane.add(textDescripcion);
		
		textTamano = new JTextField();
		textTamano.setColumns(10);
		textTamano.setBounds(179, 155, 146, 26);
		contentPane.add(textTamano);
		
		textPresentacion = new JTextField();
		textPresentacion.setColumns(10);
		textPresentacion.setBounds(179, 191, 146, 26);
		contentPane.add(textPresentacion);
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					bd = new BusinessDelegate();
					bd.altaArticulo(0, textCodBarras.getText(), textDescripcion.getText(), Float.parseFloat(textTamano.getText()),
							textPresentacion.getText(), textUnidad.getText(), Float.parseFloat(textPrecioVenta.getText()),
							Integer.parseInt(textCantAComprar.getText()), Integer.parseInt(textCantUbicable.getText()));
				} catch (CommunicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnInsertar.setBounds(320, 390, 115, 29);
		contentPane.add(btnInsertar);
		
		JLabel lblAltaDeArticulo = new JLabel("ALTA DE ARTICULO");
		lblAltaDeArticulo.setBounds(38, 28, 240, 20);
		contentPane.add(lblAltaDeArticulo);
		
		JLabel lblUnidad = new JLabel("Unidad");
		lblUnidad.setBounds(38, 230, 126, 20);
		contentPane.add(lblUnidad);
		
		JLabel lblPrecioVenta = new JLabel("Precio venta");
		lblPrecioVenta.setBounds(38, 266, 126, 20);
		contentPane.add(lblPrecioVenta);
		
		JLabel lblCantAComprar = new JLabel("Cant a Comprar");
		lblCantAComprar.setBounds(38, 302, 126, 20);
		contentPane.add(lblCantAComprar);
		
		textUnidad = new JTextField();
		textUnidad.setColumns(10);
		textUnidad.setBounds(179, 227, 146, 26);
		contentPane.add(textUnidad);
		
		textPrecioVenta = new JTextField();
		textPrecioVenta.setColumns(10);
		textPrecioVenta.setBounds(179, 263, 146, 26);
		contentPane.add(textPrecioVenta);
		
		textCantAComprar = new JTextField();
		textCantAComprar.setColumns(10);
		textCantAComprar.setBounds(179, 299, 146, 26);
		contentPane.add(textCantAComprar);
		
		JLabel lblCantUbicable = new JLabel("Cant Ubicable");
		lblCantUbicable.setBounds(38, 338, 126, 20);
		contentPane.add(lblCantUbicable);
		
		textCantUbicable = new JTextField();
		textCantUbicable.setColumns(10);
		textCantUbicable.setBounds(179, 335, 146, 26);
		contentPane.add(textCantUbicable);
	}
}
