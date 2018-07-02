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

public class UsuarioEdit extends JFrame {

	private JPanel contentPane;
	private JTextField textDocumento;
	private JTextField textPassword;
	private JTextField textApellido;
	private JTextField textNombre;
	private BusinessDelegate bd;
	private int idCliente;
	private JTextField textRol;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuarioEdit frame = new UsuarioEdit();
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
	public UsuarioEdit() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 651, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textDocumento = new JTextField();
		textDocumento.setColumns(10);
		textDocumento.setBounds(150, 60, 146, 26);
		contentPane.add(textDocumento);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(15, 63, 120, 20);
		contentPane.add(lblId);
		
		textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(99, 195, 146, 26);
		contentPane.add(textPassword);
		
		JLabel label_10 = new JLabel("Password");
		label_10.setBounds(15, 198, 69, 20);
		contentPane.add(label_10);
		
		JLabel label_11 = new JLabel("Apellido");
		label_11.setBounds(15, 162, 69, 20);
		contentPane.add(label_11);
		
		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(99, 159, 146, 26);
		contentPane.add(textApellido);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(99, 123, 146, 26);
		contentPane.add(textNombre);
		
		JLabel label_12 = new JLabel("Nombre");
		label_12.setBounds(15, 126, 69, 20);
		contentPane.add(label_12);
		
		JLabel lblModificacioncliente = new JLabel("MODIFICACION CLIENTE");
		lblModificacioncliente.setBounds(15, 16, 210, 20);
		contentPane.add(lblModificacioncliente);
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					bd = new BusinessDelegate();
					//(textLimiteCredito.getText()), textNombre.getText(), textApellido.getText(), textPassword.getText());
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
		
		JLabel label = new JLabel("Password");
		label.setBounds(15, 234, 69, 20);
		contentPane.add(label);
		
		textRol = new JTextField();
		textRol.setColumns(10);
		textRol.setBounds(99, 231, 146, 26);
		contentPane.add(textRol);
	}

}
