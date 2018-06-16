package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import delegate.BusinessDelegate;
import dto.ArticuloDTO;
import dto.ClienteDTO;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.naming.CommunicationException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClienteEdit extends JFrame {

	private JPanel contentPane;
	private JTextField textDocumento;
	private JTextField textLimiteCredito;
	private JTextField textCondicionFinanciera;
	private JTextField textRazonSocial;
	private JTextField textTelefono;
	private JTextField textPais;
	private JTextField textProvincia;
	private JTextField textPartido;
	private JTextField textCodigoPostal;
	private JTextField textCalle;
	private JTextField textPassword;
	private JTextField textApellido;
	private JTextField textNombre;
	private JTextField textNumero;
	private JTextField textPiso;
	private JTextField textAltura;
	private BusinessDelegate bd;
	private int idCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteEdit frame = new ClienteEdit();
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
	public ClienteEdit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 651, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Razon Social");
		label.setBounds(15, 171, 120, 20);
		contentPane.add(label);
		
		textDocumento = new JTextField();
		textDocumento.setColumns(10);
		textDocumento.setBounds(150, 60, 146, 26);
		contentPane.add(textDocumento);
		
		textLimiteCredito = new JTextField();
		textLimiteCredito.setColumns(10);
		textLimiteCredito.setBounds(150, 96, 146, 26);
		contentPane.add(textLimiteCredito);
		
		JLabel label_1 = new JLabel("Limite Credito");
		label_1.setBounds(15, 96, 120, 20);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Condicion Financiera");
		label_2.setBounds(15, 132, 120, 20);
		contentPane.add(label_2);
		
		textCondicionFinanciera = new JTextField();
		textCondicionFinanciera.setColumns(10);
		textCondicionFinanciera.setBounds(150, 132, 146, 26);
		contentPane.add(textCondicionFinanciera);
		
		JLabel label_3 = new JLabel("Documento");
		label_3.setBounds(15, 63, 120, 20);
		contentPane.add(label_3);
		
		textRazonSocial = new JTextField();
		textRazonSocial.setColumns(10);
		textRazonSocial.setBounds(150, 168, 146, 26);
		contentPane.add(textRazonSocial);
		
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(150, 204, 146, 26);
		contentPane.add(textTelefono);
		
		JLabel label_4 = new JLabel("Telefono");
		label_4.setBounds(15, 204, 69, 20);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("Pais");
		label_5.setBounds(15, 240, 69, 20);
		contentPane.add(label_5);
		
		textPais = new JTextField();
		textPais.setColumns(10);
		textPais.setBounds(150, 240, 146, 26);
		contentPane.add(textPais);
		
		textProvincia = new JTextField();
		textProvincia.setColumns(10);
		textProvincia.setBounds(150, 276, 146, 26);
		contentPane.add(textProvincia);
		
		JLabel label_6 = new JLabel("Provincia");
		label_6.setBounds(15, 276, 69, 20);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("Partido");
		label_7.setBounds(15, 312, 69, 20);
		contentPane.add(label_7);
		
		textPartido = new JTextField();
		textPartido.setColumns(10);
		textPartido.setBounds(150, 312, 146, 26);
		contentPane.add(textPartido);
		
		textCodigoPostal = new JTextField();
		textCodigoPostal.setColumns(10);
		textCodigoPostal.setBounds(150, 348, 146, 26);
		contentPane.add(textCodigoPostal);
		
		JLabel label_8 = new JLabel("Codigo Postal");
		label_8.setBounds(15, 348, 120, 20);
		contentPane.add(label_8);
		
		JLabel label_9 = new JLabel("Calle");
		label_9.setBounds(15, 384, 69, 20);
		contentPane.add(label_9);
		
		textCalle = new JTextField();
		textCalle.setColumns(10);
		textCalle.setBounds(150, 384, 146, 26);
		contentPane.add(textCalle);
		
		textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(427, 306, 146, 26);
		contentPane.add(textPassword);
		
		JLabel label_10 = new JLabel("Password");
		label_10.setBounds(343, 309, 69, 20);
		contentPane.add(label_10);
		
		JLabel label_11 = new JLabel("Apellido");
		label_11.setBounds(343, 273, 69, 20);
		contentPane.add(label_11);
		
		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(427, 270, 146, 26);
		contentPane.add(textApellido);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(427, 234, 146, 26);
		contentPane.add(textNombre);
		
		JLabel label_12 = new JLabel("Nombre");
		label_12.setBounds(343, 237, 69, 20);
		contentPane.add(label_12);
		
		JLabel label_13 = new JLabel("Numero");
		label_13.setBounds(343, 198, 69, 20);
		contentPane.add(label_13);
		
		textNumero = new JTextField();
		textNumero.setColumns(10);
		textNumero.setBounds(427, 195, 146, 26);
		contentPane.add(textNumero);
		
		textPiso = new JTextField();
		textPiso.setColumns(10);
		textPiso.setBounds(427, 159, 146, 26);
		contentPane.add(textPiso);
		
		JLabel label_14 = new JLabel("Piso");
		label_14.setBounds(343, 162, 69, 20);
		contentPane.add(label_14);
		
		JLabel label_15 = new JLabel("Altura");
		label_15.setBounds(343, 126, 69, 20);
		contentPane.add(label_15);
		
		textAltura = new JTextField();
		textAltura.setColumns(10);
		textAltura.setBounds(427, 123, 146, 26);
		contentPane.add(textAltura);
		
		JLabel lblModificacioncliente = new JLabel("MODIFICACION CLIENTE");
		lblModificacioncliente.setBounds(15, 16, 210, 20);
		contentPane.add(lblModificacioncliente);
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					bd = new BusinessDelegate();
					bd.registrarCliente(idCliente, textRazonSocial.getText(), Integer.parseInt(textDocumento.getText()), textDocumento.getText(), 
							Integer.parseInt(textTelefono.getText()), textCondicionFinanciera.getText(), textPais.getText(), 
							textProvincia.getText(), textPartido.getText(), textCodigoPostal.getText(), textCalle.getText(), 
							textAltura.getText(), textPiso.getText(), Integer.parseInt(textNumero.getText()), 
							Float.parseFloat(textLimiteCredito.getText()), textNombre.getText(), textApellido.getText(), textPassword.getText());
				} catch (CommunicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//bd.modificacionCliente(idCliente, razonSocial, documentoId, CUIT, tel, condicion, pais, provicia, Partido, codigoPostal, calle, altura, piso, numero);
			}
		});
		btnModificar.setBounds(343, 366, 137, 29);
		contentPane.add(btnModificar);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					bd = new BusinessDelegate();
					for (ClienteDTO item : bd.getClientes()) {
						if(item.getDocumento().equals(textDocumento.getText())) {
							textRazonSocial.setText(item.getRazonSocial()); 
							textDocumento.setText(item.getDocumento()); 
							textTelefono.setText(String.valueOf(item.getTelefono())); 
							textCondicionFinanciera.setText(item.getCondicionFinanciera()); 
							textPais.setText(item.getDomicilio().getPais()); 
							textProvincia.setText(item.getDomicilio().getProvincia()); 
							textPartido.setText(item.getDomicilio().getPartido()); 
							textCodigoPostal.setText(item.getDomicilio().getCodPotal()); 
							textCalle.setText(item.getDomicilio().getCalle()); 
							textAltura.setText(item.getDomicilio().getAltura()); 
							textPiso.setText(String.valueOf(item.getDomicilio().getPiso())); 
							textNumero.setText(String.valueOf(item.getDomicilio().getNumero())); 
							textLimiteCredito.setText(String.valueOf(item.getLimiteCredito())); 
							textNombre.setText(item.getUsuario().getNombre()); 
							textApellido.setText(item.getUsuario().getApellido()); 
							textPassword.setText(item.getUsuario().getContrasena());	
							idCliente = item.getId();
						}
					}					
				} catch (CommunicationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setBounds(343, 59, 115, 29);
		contentPane.add(btnNewButton);
		
		JButton btnDarDeBaja = new JButton("Dar de Baja");
		btnDarDeBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					bd = new BusinessDelegate();
					bd.bajaCliente(idCliente);
				} catch (CommunicationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnDarDeBaja.setBounds(495, 366, 115, 29);
		contentPane.add(btnDarDeBaja);
	}

}
