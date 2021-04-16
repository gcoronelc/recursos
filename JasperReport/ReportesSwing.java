// =======================================
// Mostrando el reporte en un JasperViewer
// Manejando una lista de objetos Map
// =======================================
try {
	// Datos
	String cuenta = txtCuenta.getText();
	// Proceso
	CuentaController control = new CuentaController();
	lista = control.getMovimientos(cuenta);
	// Preparar reporte
	JRMapCollectionDataSource data = new JRMapCollectionDataSource(lista);
	Map pars = new HashMap();
	String reporte = "/pe/uni/eurekaapp/reportes/repoMovimientos.jrxml";
	InputStream is = Class.class.getResourceAsStream(reporte);
	JasperReport rep = JasperCompileManager.compileReport(is);
	JasperPrint print = JasperFillManager.fillReport(rep, pars, data);
	// Mostrar Reporte
	JasperViewer viewer = new JasperViewer(print, false);
	viewer.setTitle("Mi Reporte");
	viewer.setVisible(true);
} catch (Exception e) {
	Mensaje.error(this, e.getMessage());
}


// =======================================
// Mostrando el reporte en un JasperViewer
// Acceso directo a la base de datos
// =======================================
try {

	// Usando el archivo JRXML y el objeto Connection
	Connection cn = DSMySQL.getConnection();
	Map pars = new HashMap();
	InputStream is = Class.class.getResourceAsStream("reports/ListaClientes.jrxml")
	JasperReport rep = JasperCompileManager.compileReport(is);
	JasperPrint print = JasperFillManager.fillReport(rep, pars, cn);

	// Visualizando el Reporte
	JasperViewer.viewReport(print);
	
} catch (Exception ex) {
	JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRORE", JOptionPane.ERROR_MESSAGE);
}


// =======================================
// Mostrando el reporte en un Panel
// Manejando una lista de objetos Map
// =======================================
try {
	// Datos
	String cuenta = txtCuenta.getText();
	// Proceso
	CuentaController control = new CuentaController();
	lista = control.getMovimientos(cuenta);
	// Preparar reporte
	JRMapCollectionDataSource data = new JRMapCollectionDataSource(lista);
	Map pars = new HashMap();
	String reporte = "/pe/uni/eurekaapp/reportes/repoMovimientos.jrxml";
	InputStream is = Class.class.getResourceAsStream(reporte);
	JasperReport rep = JasperCompileManager.compileReport(is);
	JasperPrint print = JasperFillManager.fillReport(rep, pars, data);
	// Se crea el visor del reporte
	JRViewer jRViewer = new JRViewer(print);
	// Se elimina elementos del contenedor JPanel
	panelReporte.removeAll();
	// Para el tamaño del reporte se agrega un BorderLayout
	panelReporte.setLayout(new BorderLayout());
	panelReporte.add(jRViewer,BorderLayout.CENTER);
	jRViewer.setVisible(true);
	panelReporte.repaint();
	panelReporte.revalidate();

} catch (Exception e) {
	Mensaje.error(this, e.getMessage());
}
		
		

// =======================================
// Mostrando el reporte en un JasperViewer
// Manejando una lista de Beans
// =======================================		
try {

	// Usando una colección de datos
	IClienteDAO dao = DAOFactory.getDAOFactory().getClienteDAO();
	List lista = dao.consultarTodos();
	JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(lista);
	Map pars = new HashMap();
	JasperReport rep = JasperCompileManager.compileReport(MainView.class.getResourceAsStream("reports/report1.jrxml"));
	JasperPrint print = JasperFillManager.fillReport(rep, pars, data);

	// Visualizando el Reporte
	JasperViewer.viewReport(print);
	
} catch (Exception ex) {
	JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
}


