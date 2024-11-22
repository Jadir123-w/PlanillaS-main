package ui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Empleado;

public class Planillas extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	
	Empleado empleado = new Empleado();
	JLabel lblMensaje, lblEmpleado, lblPC, lblIP, lblFecha, lblHora, imgmenu, imgFondo;
	JLabel lblMenu01, lblMenu02, lblMenu03, lblMenu04, lblMenu05, lblMenu06;
	JLabel lblSubMenu01, lblSubMenu02, lblSubMenu03, lblSubMenu04, lblSubMenu05, lblSubMenu06, lblSubMenu07, lblSubMenu08, lblSubMenu09, lblSubMenu10;
	JLabel lblSubMenu11, lblSubMenu12, lblSubMenu013; 
	JPanel pnlMenu, pnlSubMenu;
	Thread thHora;
	
	int menu = -1, subMenu = -1;
	
	String[][] aMenu = {
			{"PERSONAL","ASIGNACION CONCEPTOS", "TABLAS"},
			{"Procesar planillas","Calcular 5ta categoria", "Gratificaciones", "Vacaciones", "C.T.S.", "Liquidacion"}	
	};
	
	String[][][] aSubMenu = {
			{
				{"Ficha del Personal", "Contratos", "Derecho habiente", "Control de permisos","Control de asistencia", "Adelantos y/o Asistencia", "Prestamos", "Pre-post Ntal / Bonificaciones"},
				{"Conceptos por trabajador","Conceptos por Sede","Aplicar a todos"},
				{"Iniciar periodo","Declarantes","Sede","Centros de costos","Cargo","Departamento","Moneda","Conceptos","Comision AFP's","Regimen Pensionario","Turno","Montos por Hora","Bancos"}
			},
			{
				{"Calcular planilla", "Reporte planilla","Generacion de Boletas"},
				{},
				{},
				{},
				{},
				{}
				
			}	
	};

	public Planillas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1024, 768);
		setLayout( null );
		setLocationRelativeTo( null );
		setUndecorated( true );

		JPanel pnlPlanilla = new JPanel();
		pnlPlanilla.setBounds(0, 0, 1024, 59);
		pnlPlanilla.setBackground( new Color(94, 17, 90) );
		pnlPlanilla.setLayout( null );
		getContentPane().add( pnlPlanilla );
		
		JLabel imgLogo = new JLabel();
		imgLogo.setIcon( new ImageIcon( Login.class.getResource("/ui/img/logo.png") ) );
		imgLogo.setBounds(20, 8, 208, 43);
		pnlPlanilla.add( imgLogo );
		
		JButton btnTrabajadores = new JButton("TRABAJADORES");
		btnTrabajadores.setBounds(250, 0, 150, 59);
		btnTrabajadores.setBorderPainted(false);
		btnTrabajadores.setFocusPainted(false);
		btnTrabajadores.setBackground(new Color(94,17,90));
		btnTrabajadores.setForeground(Color.white);
		pnlPlanilla.add(btnTrabajadores);
		
		JButton btnPlanillas = new JButton("PLANILLAS");
		btnPlanillas.setBounds(400, 0, 150, 59);
		btnPlanillas.setBorderPainted(false);
		btnPlanillas.setFocusPainted(false);
		btnPlanillas.setBackground(new Color(94,17,90));
		btnPlanillas.setForeground(Color.white);
		pnlPlanilla.add(btnPlanillas);
		
		JButton btnInformes = new JButton("INFORMES");
		btnInformes.setBounds(550, 0, 150, 59);
		btnInformes.setBorderPainted(false);
		btnInformes.setFocusPainted(false);
		btnInformes.setBackground(new Color(94,17,90));
		btnInformes.setForeground(Color.white);
		pnlPlanilla.add(btnInformes);
		
		
		JLabel imgSalir = new JLabel();
		imgSalir.setBounds(980, 18, 24, 24);
		imgSalir.setIcon( new ImageIcon( Login.class.getResource("/ui/img/salir.png") ) );
		pnlPlanilla.add( imgSalir );
		
		imgFondo = new JLabel();
		imgFondo.setIcon( new ImageIcon( Login.class.getResource("/ui/img/fondoApp.png") ) );
		imgFondo.setBounds(0, 100, 1024, 528);
		getContentPane().add( imgFondo );
		
		imgmenu = new JLabel();
		imgmenu.setBounds(0, 60, 250, 55);
		imgmenu.setIcon( new ImageIcon( Login.class.getResource("/ui/img/logoMenu_2.png") ) );
		imgmenu.setVisible(false);
		getContentPane().add( imgmenu );
		
		lblMensaje = new JLabel();
		lblMensaje.setBounds(20, 60, 1024, 39);
		getContentPane().add( lblMensaje );
		
		lblEmpleado = new JLabel();
		lblEmpleado.setBounds(20, 728, 200, 30);
		getContentPane().add( lblEmpleado );

		lblPC = new JLabel("PC :");
		lblPC.setBounds(400, 728, 150, 30);
		getContentPane().add( lblPC );

		lblIP = new JLabel("IP :");
		lblIP.setBounds(550, 728, 150, 30);
		getContentPane().add( lblIP );

		lblFecha = new JLabel();
		lblFecha.setBounds(650, 728, 150, 30);
		getContentPane().add( lblFecha );

		lblHora = new JLabel();
		lblHora.setBounds(900, 728, 150, 30);
		getContentPane().add( lblHora );
		
		pnlMenu = new JPanel();
		pnlMenu.setVisible(false);
		pnlMenu.setBackground(Color.black);
		pnlMenu.setBounds(0, 117, 250, 605);
		getContentPane().add(pnlMenu);
		
		addWindowListener( new WindowAdapter() {
			@Override public void windowOpened(WindowEvent e) {
				frame_windowOpened(); } } );
		
		imgSalir.addMouseListener(new MouseAdapter() { 	
			@Override public void mouseClicked(MouseEvent e) { imgSalir_mouseClicked(); } });
		
		
		btnTrabajadores.addMouseListener(new MouseAdapter() {
			@Override public void mouseEntered(MouseEvent e) { btn_focusGained( btnTrabajadores ); }
			@Override public void mouseExited(MouseEvent e) { btn_focusLost( btnTrabajadores ); } });
		
		btnPlanillas.addMouseListener(new MouseAdapter() {
			@Override public void mouseEntered(MouseEvent e) { btn_focusGained( btnPlanillas ); }
			@Override public void mouseExited(MouseEvent e) { btn_focusLost( btnPlanillas ); } });
		
		btnInformes.addMouseListener(new MouseAdapter() {
			@Override public void mouseEntered(MouseEvent e) { btn_focusGained( btnInformes ); }
			@Override public void mouseExited(MouseEvent e) { btn_focusLost( btnInformes ); } });
		
		btnTrabajadores.addActionListener( e -> btn_actionPerformed(btnTrabajadores, 0) );
		btnPlanillas.addActionListener( e -> btn_actionPerformed(btnPlanillas, 1) );
		btnInformes.addActionListener( e -> btn_actionPerformed(btnInformes, 2) );
	}

	protected void frame_windowOpened() {
		lblMensaje.setText( String.format("Bienvenido %s, al sistema de planillas", empleado.getApellidoPaterno() ) );
		lblEmpleado.setText( String.format("Empleado : %s %s %s", empleado.getNombres(), empleado.getApellidoPaterno(), empleado.getApellidoMaterno() ) );
		lblFecha.setText( new SimpleDateFormat("'Fecha : 'dd/mm/yyyy").format( new Date() ) );
		lblPC.setText("PC : " + System.getenv("COMPUTERNAME"));
		thHora = new Thread(this);
		thHora.start();
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
	protected void imgSalir_mouseClicked() {
		if ( JOptionPane.showConfirmDialog(this, "�Desea salir?", "Salir", JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION ) 
			System.exit(0);
	}
	
	protected void btn_focusGained(JButton btn) {
		if (menu > -1) return;
		btn.setBackground( new Color(124, 17, 120) );
	}

	protected void btn_focusLost(JButton btn) {
		if(menu > -1) return;
		btn.setBackground( new Color(94,17,90) );
	}
	
	private void btn_actionPerformed(JButton btn, int menu) {
		this.menu = menu;
		btn.setBackground(Color.white);
		btn.setForeground(Color.black);
		lblMensaje.setVisible(false);
		imgFondo.setVisible(false);
		imgmenu.setVisible(true);
		pnlMenu.setVisible(true);
	}
	
	@Override
	public void run() {
		SimpleDateFormat df = new SimpleDateFormat("'Hora:' hh:mm:ss");
		while (true) {
			lblHora.setText(df.format(new Date()));
			try { Thread.sleep(1000);
				} catch(InterruptedException e) {e.printStackTrace();}
		}
	}
	
}