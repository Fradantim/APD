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

public class ClienteAdd extends JFrame {

	private JPanel contentPane;
	private JTextField textRazonSocial;
	private JTextField textLimiteCredito;
	private JTextField textCondicionFinanciera;
	private JTextField textDocumento;
	private JTextField textTelefono;
	private JTextField textPais;
	private JTextField textProvincia;
	private JTextField textPartido;
	private JTextField textCodigoPostal;
	private JTextField textCalle;
	private JTextField textAltura;
	private JTextField textPiso;
	private JTextField textNumero;
	private BusinessDelegate bd;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteAdd frame = new ClienteAdd();
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
	public ClienteAdd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRazonSocial = new JLabel("Razon Social");
		lblRazonSocial.setBounds(34, 60, 120, 20);
		contentPane.add(lblRazonSocial);
		
		JLabel lblLimiteCredito = new JLabel("Limite Credito");
		lblLimiteCredito.setBounds(34, 96, 120, 20);
		contentPane.add(lblLimiteCredito);
		
		JLabel lblCondicionFinanciera = new JLabel("Condicion Financiera");
		lblCondicionFinanciera.setBounds(34, 132, 120, 20);
		contentPane.add(lblCondicionFinanciera);
		
		JLabel lblDocumento = new JLabel("Documento");
		lblDocumento.setBounds(34, 168, 120, 20);
		contentPane.add(lblDocumento);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(34, 204, 69, 20);
		contentPane.add(lblTelefono);
		
		JLabel lblPais = new JLabel("Pais");
		lblPais.setBounds(34, 240, 69, 20);
		contentPane.add(lblPais);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(34, 276, 69, 20);
		contentPane.add(lblProvincia);
		
		JLabel lblPartido = new JLabel("Partido");
		lblPartido.setBounds(34, 312, 69, 20);
		contentPane.add(lblPartido);
		
		JLabel lblCodigoPostal = new JLabel("Codigo Postal");
		lblCodigoPostal.setBounds(34, 348, 120, 20);
		contentPane.add(lblCodigoPostal);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(34, 384, 69, 20);
		contentPane.add(lblCalle);
		
		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(359, 57, 69, 20);
		contentPane.add(lblAltura);
		
		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(359, 93, 69, 20);
		contentPane.add(lblPiso);
		
		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setBounds(359, 129, 69, 20);
		contentPane.add(lblNumero);
		
		textRazonSocial = new JTextField();
		textRazonSocial.setBounds(169, 60, 146, 26);
		contentPane.add(textRazonSocial);
		textRazonSocial.setColumns(10);
		
		textLimiteCredito = new JTextField();
		textLimiteCredito.setColumns(10);
		textLimiteCredito.setBounds(169, 96, 146, 26);
		contentPane.add(textLimiteCredito);
		
		textCondicionFinanciera = new JTextField();
		textCondicionFinanciera.setColumns(10);
		textCondicionFinanciera.setBounds(169, 132, 146, 26);
		contentPane.add(textCondicionFinanciera);
		
		textDocumento = new JTextField();
		textDocumento.setColumns(10);
		textDocumento.setBounds(169, 168, 146, 26);
		contentPane.add(textDocumento);
		
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(169, 204, 146, 26);
		contentPane.add(textTelefono);
		
		textPais = new JTextField();
		textPais.setColumns(10);
		textPais.setBounds(169, 240, 146, 26);
		contentPane.add(textPais);
		
		textProvincia = new JTextField();
		textProvincia.setColumns(10);
		textProvincia.setBounds(169, 276, 146, 26);
		contentPane.add(textProvincia);
		
		textPartido = new JTextField();
		textPartido.setColumns(10);
		textPartido.setBounds(169, 312, 146, 26);
		contentPane.add(textPartido);
		
		textCodigoPostal = new JTextField();
		textCodigoPostal.setColumns(10);
		textCodigoPostal.setBounds(169, 348, 146, 26);
		contentPane.add(textCodigoPostal);
		
		textCalle = new JTextField();
		textCalle.setColumns(10);
		textCalle.setBounds(169, 384, 146, 26);
		contentPane.add(textCalle);
		
		textAltura = new JTextField();
		textAltura.setColumns(10);
		textAltura.setBounds(443, 54, 146, 26);
		contentPane.add(textAltura);
		
		textPiso = new JTextField();
		textPiso.setColumns(10);
		textPiso.setBounds(443, 90, 146, 26);
		contentPane.add(textPiso);
		
		textNumero = new JTextField();
		textNumero.setColumns(10);
		textNumero.setBounds(443, 126, 146, 26);
		contentPane.add(textNumero);
		
		JLabel lblAltaCliente = new JLabel("ALTA CLIENTE");
		lblAltaCliente.setBounds(34, 16, 164, 20);
		contentPane.add(lblAltaCliente);
		
		JButton btnAlta = new JButton("ALTA");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					bd = new BusinessDelegate();
					bd.registrarCliente(0, textRazonSocial.getText(), Integer.parseInt(textDocumento.getText()), textDocumento.getText(), 
							Integer.parseInt(textTelefono.getText()), textCondicionFinanciera.getText(), textPais.getText(), 
							textProvincia.getText(), textPartido.getText(), textCodigoPostal.getText(), textCalle.getText(), 
							textAltura.getText(), textPiso.getText(), Integer.parseInt(textNumero.getText()), 
							Float.parseFloat(textLimiteCredito.getText()), textNombre.getText(), textApellido.getText(), textPassword.getText());
				} catch (CommunicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAlta.setBounds(402, 344, 115, 29);
		contentPane.add(btnAlta);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(359, 168, 69, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(359, 204, 69, 20);
		contentPane.add(lblApellido);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(359, 240, 69, 20);
		contentPane.add(lblPassword);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(443, 165, 146, 26);
		contentPane.add(textNombre);
		
		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(443, 201, 146, 26);
		contentPane.add(textApellido);
		
		textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(443, 237, 146, 26);
		contentPane.add(textPassword);
	}
}
