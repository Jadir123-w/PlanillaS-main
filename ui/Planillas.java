package ui;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Empleado;

public class Planillas extends JFrame {
	private static final long serialVersionUID = 1L;
	Empleado empleado = new Empleado();
	JLabel lblMensaje, lblEmpleado, lblPC, lblIP, lblFecha, lblHora;
	Thread thHora;
	
	public Planillas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1024, 768);
		setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(false);
		
		JPanel pnPlanilla = new JPanel();
		pnPlanilla.setBounds(0, 0, 1024, 59);
		pnPlanilla.setBackground(new Color(94, 17, 90));
		pnPlanilla.setLayout(null);
		getContentPane().add(pnPlanilla);
		
		JLabel imgLogo = new JLabel();
		imgLogo.setIcon( new ImageIcon( Login.class.getResource("/ui/img/logo.png") ) );
		imgLogo.setBounds(20, 8, 208, 43);
		pnPlanilla.add(imgLogo);
		
		JLabel imgSalir = new JLabel();
		imgSalir.setBounds(980, 18, 24, 24);
		imgSalir.setIcon( new ImageIcon( Login.class.getResource("/ui/img/salir.png") ) );
		pnPlanilla.add(imgSalir);
		
		lblMensaje = new JLabel();
		lblMensaje.setBounds(20, 60 , 1024, 40);
		getContentPane().add(lblMensaje);
		
		JLabel lblEmpleado= new JLabel();
		lblEmpleado.setBounds(20, 728 , 250, 30);
		getContentPane().add(lblEmpleado);
		
		JLabel lblPC= new JLabel();
		lblPC.setBounds(20, 728 , 250, 30);
		getContentPane().add(lblPC);
		
		JLabel lblHora= new JLabel();
		lblHora.setBounds(20, 728 , 250, 30);
		getContentPane().add(lblHora);
		
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				frame_windowOpened(); } });
	}
	
	private void frame_windowOpened() {
		lblMensaje.setText(String.format("Bienvenido %s, al sistema de planillas", empleado.getApellidoPaterno()) );
		lblEmpleado.setText(String.format("Empleado : %s %s %s", empleado.getNombres(), empleado.getApellidoPaterno(), empleado.getApellidoMaterno()) );
		lblHora.setText( new SimpleDateFormat(" 'Fecha : 'dd/m/yyyy"));
	}

	public void setEmpleado(Empleado empleado) {
		
	}
	
}
