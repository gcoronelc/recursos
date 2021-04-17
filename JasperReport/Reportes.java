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


// =======================================
// Reportes Web
// =======================================		

// De WebContent\reportes\...

try {
	IArticuloDAO articuloDAO = DAOFactory.getDAOFactory().getArticuloDAO();
	ArrayList<ArticuloTO> lista = articuloDAO.consultarPorNombre("%");
	JRBeanCollectionDataSource data;
	data = new JRBeanCollectionDataSource(lista);
	ServletConfig config = getServletConfig();
	ServletContext sc = config.getServletContext();
	InputStream inputStream = sc.getResourceAsStream("/reportes/repoArticulos.jasper");
	if (inputStream == null) { throw new Exception("Reporte no existe.<br>"); }
	JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
	byte[] bytes = JasperRunManager.runReportToPdf(jasperReport, null, data);
	response.setContentType("application/pdf");
	response.setContentLength(bytes.length);
	ServletOutputStream out = response.getOutputStream();
	out.write(bytes, 0, bytes.length);
	out.flush();
	out.close();
} catch (Exception e) {
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
	out.println("Error: " + e.getMessage());
	out.close();
}


// De /pe/eureka/reportes/

try {
	// Datos
	String cuenta = request.getParameter("cuenta");
	// Proceso
	EurekaService service = new EurekaService();
	List<Map<String, ?>> lista = service.getMovimientos(cuenta);
	// Preparar reporte

	JRMapCollectionDataSource data = new JRMapCollectionDataSource(lista);
	Map pars = new HashMap<>();
	
	String reporte = "/pe/eureka/reportes/repoMovimientos.jasper";
	//InputStream is = EurekaController.class.getResourceAsStream(reporte);
	InputStream is = getClass().getResourceAsStream(reporte);
	
	/*
	ServletConfig config = getServletConfig();
	ServletContext sc = config.getServletContext();
	String reporte = "/reportes/repoMovimientos.jasper";
	InputStream is = sc.getResourceAsStream(reporte);
	*/
	if(is == null){
		throw new Exception("Reporte no existe.");
	}
	
	JasperReport rep = (JasperReport) JRLoader.loadObject(is);
	byte[] bytes = JasperRunManager.runReportToPdf(rep, null, data);
	// Preparar el reporte
	response.setContentType("application/pdf");
	response.setContentLengthLong(bytes.length);
	ServletOutputStream out = response.getOutputStream();
	out.write(bytes, 0, bytes.length);
	out.flush();
	out.close();
} catch (Exception e) {
	e.printStackTrace();
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
	out.println("Error: " + e.getMessage());
	out.close();
}