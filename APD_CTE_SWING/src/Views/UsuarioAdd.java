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
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class UsuarioAdd extends JFrame {

	private JPanel contentPane;
	private BusinessDelegate bd;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textPassword;
	private JTextField textRol;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuarioAdd frame = new UsuarioAdd();
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
	public UsuarioAdd() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 658, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAltaCliente = new JLabel("ALTA USUARIO");
		lblAltaCliente.setBounds(34, 16, 164, 20);
		contentPane.add(lblAltaCliente);
		
		JButton btnAlta = new JButton("ALTA");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					bd = new BusinessDelegate();
					bd.registrarUsuario(0, textNombre.getText(), textApellido.getText(), textRol.getText(), textPassword.getText());
				} catch (CommunicationException | RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAlta.setBounds(402, 344, 115, 29);
		contentPane.add(btnAlta);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(36, 90, 69, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(36, 126, 69, 20);
		contentPane.add(lblApellido);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(36, 162, 69, 20);
		contentPane.add(lblPassword);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(120, 87, 146, 26);
		contentPane.add(textNombre);
		
		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(120, 123, 146, 26);
		contentPane.add(textApellido);
		
		textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(120, 159, 146, 26);
		contentPane.add(textPassword);
		
		JLabel lblNivelRol = new JLabel("Nivel Rol");
		lblNivelRol.setBounds(34, 204, 69, 20);
		contentPane.add(lblNivelRol);
		
		textRol = new JTextField();
		textRol.setColumns(10);
		textRol.setBounds(120, 201, 146, 26);
		contentPane.add(textRol);
	}
}
