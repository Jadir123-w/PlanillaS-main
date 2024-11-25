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

import model.Empleado;

public class Planillas extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	
	Empleado empleado = new Empleado();
	JLabel lblMensaje, lblEmpleado, lblPC, lblIP, lblFecha, lblHora;
	JLabel imgFondo, imgMenu;

	JLabel lblIcon01, lblIcon02, lblIcon03, lblIcon04, lblIcon05, lblIcon06;
	JLabel lblMenu01, lblMenu02, lblMenu03, lblMenu04, lblMenu05, lblMenu06;
	JLabel lblSubMenu01, lblSubMenu02, lblSubMenu03, lblSubMenu04, lblSubMenu05;
	JLabel lblSubMenu06, lblSubMenu07, lblSubMenu08, lblSubMenu09, lblSubMenu10;
	JLabel lblSubMenu11, lblSubMenu12, lblSubMenu13;  
	
	JLabel[] aLblIcon, aLblMenu, aLblSubMenu;

	JButton btnTrabajadores, btnPlanillas, btnInformes;
	JButton[] aBtnPlanilla;

	JPanel pnlMenu, pnlSubMenu;
	Thread thHora;

	int menu = -1, subMenu = -1;
			
	String[][] aMenu = {
			{"PERSONAL", "ASIGNACIÓN CONCEPTOS", "TABLAS"},
			{"PROCESAR PLANILLAS", "CALCULAR 5TA. CATEGORIA", "GRATIFICACIONES", "VACACIONES", "C.T.S.", "LIQUIDACIÓN"}
	};
	
	String[][][] aSubMenu = {
			{ 
				{"Ficha del personal", "Contratos","Derecho habiente", "Control de permisos", "Control de asistencia", "Adelantos y/o Asistencia", "Prestamos", "Pre-Post Natal / Bonificaciones"},
				{"Conceptos por trabajador", "Conceptos por sede", "Aplicar a todos"},
				{"Iniciar período", "Declarantes", "Sede", "Centros de costos", "Cargo", "Departamento", "Moneda", "Conceptos", "Comisión AFP's", "Regimen Pensionario", "Turno", "Montos por hora", "Bancos"}
			},
			{
				{"Calcular planilla", "Reporte planilla","Generación boletas"},
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
		
		btnTrabajadores = new JButton("TRABAJADORES");
		btnTrabajadores.setBounds(250, 0, 150, 59);
		btnTrabajadores.setBackground( new Color(94, 17, 90) );
		btnTrabajadores.setBorderPainted( false );
		btnTrabajadores.setFocusPainted( false );
		btnTrabajadores.setForeground( Color.WHITE );
		pnlPlanilla.add( btnTrabajadores );
		
		btnPlanillas = new JButton("PLANILLAS");
		btnPlanillas.setBounds(400, 0, 150, 59);
		btnPlanillas.setBackground( new Color(94, 17, 90) );
		btnPlanillas.setBorderPainted( false );
		btnPlanillas.setFocusPainted( false );
		btnPlanillas.setForeground( Color.WHITE );
		pnlPlanilla.add( btnPlanillas );

		btnInformes = new JButton("INFORMES");
		btnInformes.setBounds(550, 0, 150, 59);
		btnInformes.setBackground( new Color(94, 17, 90) );
		btnInformes.setBorderPainted( false );
		btnInformes.setFocusPainted( false );
		btnInformes.setForeground( Color.WHITE );
		pnlPlanilla.add( btnInformes );

		JLabel imgSalir = new JLabel();
		imgSalir.setBounds(980, 18, 24, 24);
		imgSalir.setIcon( new ImageIcon( Login.class.getResource("/ui/img/salir.png") ) );
		pnlPlanilla.add( imgSalir );
		
		imgFondo = new JLabel();
		imgFondo.setIcon( new ImageIcon( Login.class.getResource("/ui/img/fondoApp.png") ) );
		imgFondo.setBounds(0, 100, 1024, 528);
		getContentPane().add( imgFondo );
		
		imgMenu = new JLabel();
		imgMenu.setIcon( new ImageIcon( Login.class.getResource("/ui/img/logoMenu_2.png") ) );
		imgMenu.setBounds(0, 60, 250, 55);
		imgMenu.setVisible( false );
		getContentPane().add( imgMenu );

		lblMensaje = new JLabel();
		lblMensaje.setBounds(20, 60, 1024, 39);
		getContentPane().add( lblMensaje );
		
		lblEmpleado = new JLabel();
		lblEmpleado.setBounds(20, 728, 250, 30);
		getContentPane().add( lblEmpleado );

		lblPC = new JLabel("PC :");
		lblPC.setBounds(400, 728, 150, 30);
		getContentPane().add( lblPC );

		lblIP = new JLabel("IP :");
		lblIP.setBounds(600, 728, 150, 30);
		getContentPane().add( lblIP );

		lblFecha = new JLabel();
		lblFecha.setBounds(750, 728, 150, 30);
		getContentPane().add( lblFecha );

		lblHora = new JLabel();
		lblHora.setBounds(900, 728, 150, 30);
		getContentPane().add( lblHora );

		pnlMenu = new JPanel();
		pnlMenu.setBounds(0, 120, 250, 600);
		pnlMenu.setBackground( Color.BLACK );
		pnlMenu.setLayout( null );
		pnlMenu.setVisible( false );
		getContentPane().add( pnlMenu );

		pnlSubMenu = new JPanel();
		pnlSubMenu.setBackground( Color.BLACK );
		pnlSubMenu.setLayout( null );
		pnlSubMenu.setVisible( false );
		pnlMenu.add( pnlSubMenu );

		lblMenu01 = new JLabel();
		lblMenu01.setForeground( Color.WHITE );
		lblMenu01.setIconTextGap(10);
		pnlMenu.add( lblMenu01 );

		lblMenu02 = new JLabel();
		lblMenu02.setForeground( Color.WHITE );
		lblMenu02.setIconTextGap(10);
		pnlMenu.add( lblMenu02 );

		lblMenu03 = new JLabel();
		lblMenu03.setForeground( Color.WHITE );
		lblMenu03.setIconTextGap(10);
		pnlMenu.add( lblMenu03 );

		lblMenu04 = new JLabel();
		lblMenu04.setForeground( Color.WHITE );
		lblMenu04.setIconTextGap(10);
		pnlMenu.add( lblMenu04 );

		lblMenu05 = new JLabel();
		lblMenu05.setForeground( Color.WHITE );
		lblMenu05.setIconTextGap(10);
		pnlMenu.add( lblMenu05 );

		lblMenu06 = new JLabel();
		lblMenu06.setForeground( Color.WHITE );
		lblMenu06.setIconTextGap(10);
		pnlMenu.add( lblMenu06 );

		lblIcon01 = new JLabel();
		pnlMenu.add( lblIcon01 );

		lblIcon02 = new JLabel();
		pnlMenu.add( lblIcon02 );

		lblIcon03 = new JLabel();
		pnlMenu.add( lblIcon03 );

		lblIcon04 = new JLabel();
		pnlMenu.add( lblIcon04 );

		lblIcon05 = new JLabel();
		pnlMenu.add( lblIcon05 );

		lblIcon06 = new JLabel();
		pnlMenu.add( lblIcon06 );

		lblSubMenu01 = new JLabel();
		lblSubMenu01.setForeground( Color.WHITE );
		pnlSubMenu.add( lblSubMenu01 );

		lblSubMenu02 = new JLabel();
		lblSubMenu02.setForeground( Color.WHITE );
		pnlSubMenu.add( lblSubMenu02 );

		lblSubMenu03 = new JLabel();
		lblSubMenu03.setForeground( Color.WHITE );
		pnlSubMenu.add( lblSubMenu03 );

		lblSubMenu04 = new JLabel();
		lblSubMenu04.setForeground( Color.WHITE );
		pnlSubMenu.add( lblSubMenu04 );

		lblSubMenu05 = new JLabel();
		lblSubMenu05.setForeground( Color.WHITE );
		pnlSubMenu.add( lblSubMenu05 );

		lblSubMenu06 = new JLabel();
		lblSubMenu06.setForeground( Color.WHITE );
		pnlSubMenu.add( lblSubMenu06 );

		lblSubMenu07 = new JLabel();
		lblSubMenu07.setForeground( Color.WHITE );
		pnlSubMenu.add( lblSubMenu07 );

		lblSubMenu08 = new JLabel();
		lblSubMenu08.setForeground( Color.WHITE );
		pnlSubMenu.add( lblSubMenu08 );

		lblSubMenu09 = new JLabel();
		lblSubMenu09.setForeground( Color.WHITE );
		pnlSubMenu.add( lblSubMenu09 );

		lblSubMenu10 = new JLabel();
		lblSubMenu10.setForeground( Color.WHITE );
		pnlSubMenu.add( lblSubMenu10 );

		lblSubMenu11 = new JLabel();
		lblSubMenu11.setForeground( Color.WHITE );
		pnlSubMenu.add( lblSubMenu11 );

		lblSubMenu12 = new JLabel();
		lblSubMenu12.setForeground( Color.WHITE );
		pnlSubMenu.add( lblSubMenu12 );

		lblSubMenu13 = new JLabel();
		lblSubMenu13.setForeground( Color.WHITE );
		pnlSubMenu.add( lblSubMenu13 );


		addWindowListener( new WindowAdapter() {
			@Override public void windowOpened(WindowEvent e) { frame_windowOpened(); } } );
		
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
		
		btnTrabajadores.addActionListener( e -> btn_actionPerformed( btnTrabajadores, 0 ) );
		btnPlanillas.addActionListener( e -> btn_actionPerformed( btnPlanillas, 1 ) );
		btnInformes.addActionListener( e -> btn_actionPerformed( btnInformes, 2 ) );

		lblIcon01.addMouseListener(new MouseAdapter() {
			@Override public void mouseClicked(MouseEvent e) { lblIcon_mouseClicked(0); } });

		lblIcon02.addMouseListener(new MouseAdapter() {
			@Override public void mouseClicked(MouseEvent e) { lblIcon_mouseClicked(1); } });

		lblIcon03.addMouseListener(new MouseAdapter() {
			@Override public void mouseClicked(MouseEvent e) { lblIcon_mouseClicked(2); } });

		lblIcon04.addMouseListener(new MouseAdapter() {
			@Override public void mouseClicked(MouseEvent e) { lblIcon_mouseClicked(3); } });

		lblIcon05.addMouseListener(new MouseAdapter() {
			@Override public void mouseClicked(MouseEvent e) { lblIcon_mouseClicked(4); } });

		lblIcon06.addMouseListener(new MouseAdapter() {
			@Override public void mouseClicked(MouseEvent e) { lblIcon_mouseClicked(5); } });
	
	}

	protected void frame_windowOpened() {
		aBtnPlanilla = new JButton[] { btnTrabajadores, btnPlanillas, btnInformes };
		aLblIcon = new JLabel[] { lblIcon01,lblIcon02,lblIcon03,lblIcon04,lblIcon05,lblIcon06 };
		aLblMenu = new JLabel[] { lblMenu01,lblMenu02,lblMenu03,lblMenu04,lblMenu05,lblMenu06 };
		aLblSubMenu = new JLabel[] { lblSubMenu01,lblSubMenu02,lblSubMenu03,lblSubMenu04,lblSubMenu05,lblSubMenu06,lblSubMenu07,lblSubMenu08,lblSubMenu09,lblSubMenu10,lblSubMenu11,lblSubMenu12,lblSubMenu13 };
	
		lblMensaje.setText( String.format("Bienvenido %s, al sistema de planillas", empleado.getApellidoPaterno() ) );
		lblEmpleado.setText( String.format("Empleado : %s %s %s", empleado.getNombres(), empleado.getApellidoPaterno(), empleado.getApellidoMaterno() ) );
		lblFecha.setText( new SimpleDateFormat("'Fecha : 'dd/mm/yyyy").format( new Date() ) );
		lblPC.setText("PC : " + System.getenv("COMPUTERNAME") );
		thHora = new Thread(this);
		thHora.start();
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
	protected void imgSalir_mouseClicked() {
		if ( JOptionPane.showConfirmDialog(this, "¿Desea salir?", "Salir", JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION ) 
			System.exit(0);
	}
	
	protected void btn_focusGained(JButton btn) {
		if ( menu > -1 ) return;
		btn.setBackground( new Color( 124, 17, 120 ) );
	}

	protected void btn_focusLost(JButton btn) {
		if ( menu > -1 ) return;
		btn.setBackground( new Color(94, 17, 90) );
	}

	protected void btn_actionPerformed( JButton btn, int menu ) {
		this.menu = menu;
		for( JButton btnMenu : aBtnPlanilla ) {
			btnMenu.setBackground( new Color(94, 17, 90) );
			btnMenu.setForeground( Color.WHITE );	
		}

		btn.setBackground( Color.WHITE );
		btn.setForeground( Color.BLACK );
		lblMensaje.setVisible( false );
		imgFondo.setVisible( false );
		imgMenu.setVisible( true );
		pnlMenu.setVisible( true );
		lblIcon_mouseClicked(0);
	}

	protected void lblIcon_mouseClicked(int index) {
		for(JLabel lbl : aLblIcon) lbl.setVisible(false);
		for(JLabel lbl : aLblMenu) lbl.setVisible(false);
		for(JLabel lbl : aLblSubMenu) lbl.setVisible(false);

		for(int i=0, fila=10, icon=0; i < aMenu[menu].length; i++, fila += 30 ) {
			icon = (menu + 1) * 10 + (i+1);
			aLblMenu[i].setBounds(10, fila, 200, 30);
			aLblMenu[i].setIcon( new ImageIcon( Login.class.getResource("/ui/img/menu" + icon + ".png") ) );
			aLblMenu[i].setText( aMenu[menu][i] );
			aLblMenu[i].setVisible(true);
		}

	}

	@Override
	public void run() {
		SimpleDateFormat df = new SimpleDateFormat("'Hora :' hh:mm:ss");
		while (true) {
			lblHora.setText( df.format( new Date() ) );
			try { Thread.sleep(1000);
			} catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
	
}