package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import delegate.BusinessDelegate;
import dto.ArticuloDTO;

import javax.swing.JTextField;
import javax.naming.CommunicationException;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ArticulosEdit extends JFrame {

	private BusinessDelegate bd;
	private JPanel contentPane;
	private JTextField txtCodigoBarras;
	private JTextField txtIdArticulo;
	private JLabel label;
	private JTextField textDescripcion;
	private JLabel label_1;
	private JTextField textTamano;
	private JLabel label_2;
	private JTextField textPresentacion;
	private JLabel label_3;
	private JTextField textUnidad;
	private JLabel label_4;
	private JTextField textPrecioVenta;
	private JLabel label_5;
	private JTextField textCantAComprar;
	private JLabel label_6;
	private JTextField textCantUbicable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArticulosEdit frame = new ArticulosEdit();
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
	public ArticulosEdit() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 541, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtCodigoBarras = new JTextField();
		txtCodigoBarras.setText("Codigo Barras");
		txtCodigoBarras.setBounds(15, 86, 111, 26);
		contentPane.add(txtCodigoBarras);
		txtCodigoBarras.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					bd = new BusinessDelegate();
					for (ArticuloDTO item : bd.getArticulos()) {
						if(item.getCodDeBarras().equals(txtCodigoBarras.getText())) {
						    txtIdArticulo.setText(String.valueOf(item.getId()));
						    textCantAComprar.setText(String.valueOf(item.getCantidadAComprar()));
						    textCantUbicable.setText(String.valueOf(item.getCantidadUbicable()));
						    textDescripcion.setText(String.valueOf(item.getDescripcion()));
						    textPrecioVenta.setText(String.valueOf(item.getPrecioDeVenta()));
						    textPresentacion.setText(String.valueOf(item.getPresentacion()));
						    textTamano.setText(String.valueOf(item.getTamano()));
						    textUnidad.setText(String.valueOf(item.getUnidad()));
						}
					}
					
				} catch (CommunicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnBuscar.setBounds(138, 85, 115, 29);
		contentPane.add(btnBuscar);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(268, 89, 28, 20);
		contentPane.add(lblId);
		
		txtIdArticulo = new JTextField();
		txtIdArticulo.setBounds(311, 86, 54, 26);
		contentPane.add(txtIdArticulo);
		txtIdArticulo.setColumns(10);
		
		label = new JLabel("Descripcion");
		label.setBounds(15, 143, 126, 20);
		contentPane.add(label);
		
		textDescripcion = new JTextField();
		textDescripcion.setColumns(10);
		textDescripcion.setBounds(156, 140, 146, 26);
		contentPane.add(textDescripcion);
		
		label_1 = new JLabel("Tama\u00F1o");
		label_1.setBounds(15, 182, 126, 20);
		contentPane.add(label_1);
		
		textTamano = new JTextField();
		textTamano.setColumns(10);
		textTamano.setBounds(156, 179, 146, 26);
		contentPane.add(textTamano);
		
		label_2 = new JLabel("Presentacion");
		label_2.setBounds(15, 218, 126, 20);
		contentPane.add(label_2);
		
		textPresentacion = new JTextField();
		textPresentacion.setColumns(10);
		textPresentacion.setBounds(156, 215, 146, 26);
		contentPane.add(textPresentacion);
		
		label_3 = new JLabel("Unidad");
		label_3.setBounds(15, 254, 126, 20);
		contentPane.add(label_3);
		
		textUnidad = new JTextField();
		textUnidad.setColumns(10);
		textUnidad.setBounds(156, 251, 146, 26);
		contentPane.add(textUnidad);
		
		label_4 = new JLabel("Precio venta");
		label_4.setBounds(15, 290, 126, 20);
		contentPane.add(label_4);
		
		textPrecioVenta = new JTextField();
		textPrecioVenta.setColumns(10);
		textPrecioVenta.setBounds(156, 287, 146, 26);
		contentPane.add(textPrecioVenta);
		
		label_5 = new JLabel("Cant a Comprar");
		label_5.setBounds(15, 326, 126, 20);
		contentPane.add(label_5);
		
		textCantAComprar = new JTextField();
		textCantAComprar.setColumns(10);
		textCantAComprar.setBounds(156, 323, 146, 26);
		contentPane.add(textCantAComprar);
		
		label_6 = new JLabel("Cant Ubicable");
		label_6.setBounds(15, 362, 126, 20);
		contentPane.add(label_6);
		
		textCantUbicable = new JTextField();
		textCantUbicable.setColumns(10);
		textCantUbicable.setBounds(156, 359, 146, 26);
		contentPane.add(textCantUbicable);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					bd = new BusinessDelegate();
					bd.altaArticulo(Integer.parseInt(txtIdArticulo.getText()), txtCodigoBarras.getText(), textDescripcion.getText(), Float.parseFloat(textTamano.getText()),
							textPresentacion.getText(), textUnidad.getText(), Float.parseFloat(textPrecioVenta.getText()),
							Integer.parseInt(textCantAComprar.getText()), Integer.parseInt(textCantUbicable.getText()));
				} catch (CommunicationException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		btnActualizar.setBounds(330, 403, 115, 29);
		contentPane.add(btnActualizar);
		
		JLabel lblModificacionArticulos = new JLabel("MODIFICACION ARTICULOS");
		lblModificacionArticulos.setBounds(15, 33, 216, 20);
		contentPane.add(lblModificacionArticulos);
	}
}
