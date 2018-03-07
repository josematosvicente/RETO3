package org.com.dm.web.controller;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.com.dm.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNLogEntryPath;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNRevisionProperty;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import com.sun.corba.se.impl.orbutil.closure.Constant;

@Controller
public class ControllerSvn {
	

	@Value("${svn.url}")
	private String urlSVN;
	@Value("${svn.url.desarrollo}")
	private String urlSVNDesarrollo;
	@Value("${svn.url.desarrollo.user}")
	private String svnUsuarioDesarrollo;
	@Value("${svn.url.desarrollo.password}")
	private String svnPasswordDesarrollo;
	@Value("${svn.url.calidad}")
	private String urlSVNCalidad;
	@Value("${svn.url.calidad.user}")
	private String svnUsuarioCalidad;
	@Value("${svn.url.calidad.password}")
	private String svnPasswordCalidad;
	@Value("${svn.url.produccion}")
	private String urlSVNProduccion;
	@Value("${svn.url.produccion.user}")
	private String svnUsuarioProduccion;
	@Value("${svn.url.produccion.password}")
	private String svnPasswordProduccion;
	
	static SVNRepository repositoryOrigen = null;
	static SVNRepository repositoryDestino = null;
	
	
	
	private static final Logger logger = Logger.getLogger(ControllerSvn.class);
	
	@RequestMapping(value = "/SVN/SVNVIEW", method = RequestMethod.GET)
	public String svnView(ModelMap model, HttpServletRequest request) {
		try{
			
			String path = request.getParameter("path").toString();
			
			String ambiente = request.getParameter("ambiente").toString();
			
			String path1 = "";
			String path2 = "";
			if(ambiente.equals(Constants.AMBIENTE_DESARROLLO)){
				path1=path;
				path2=path.replaceAll(urlSVNDesarrollo,urlSVNCalidad);
			}else if(ambiente.equals(Constants.AMBIENTE_CALIDAD)){
				path1=path;
				path2=path.replaceAll(urlSVNCalidad,urlSVNProduccion);
			}else if(ambiente.equals(Constants.AMBIENTE_PRODUCCION)){
				path1=path;
				path2=path.replaceAll(urlSVNProduccion,urlSVNProduccion);
			}
			
			loadInitialParameters();
			logger.info("Inicio Controller POST ControllerSvn");
//			SVNRepository repositoryOrigen = repositoryDesarrollo;
//			SVNRepository repositoryDestino = repositoryCalidad;
			init(request.getParameter("ambiente").toString());
//			List<Entry> listaPaths = showLog(repositoryOrigen, "DM1521");
//			System.out.println(listaPaths);
//			String pathSvn = listaPaths.get(0).getPath();
//			Entry origenRevision = /*getLastRevision(repositoryOrigen, pathSvn)*/;
			String sourceOrigen = showFile(repositoryOrigen, path1, new Long(request.getParameter("revision").toString()).longValue());
			String sourceDestino = "";
			Entry origenDestino = new Entry("",0L,null,"");;
			long previousRevision = 0L;
			try{
				origenDestino = getLastRevision(repositoryDestino, path2);
				
				
				previousRevision= origenDestino.getRevision();
				if(ambiente.equals(Constants.AMBIENTE_PRODUCCION)){
					previousRevision = origenDestino.getRevision();
					do{
						previousRevision--;
						sourceDestino = showFile(repositoryDestino, path2, previousRevision);	
					}while(sourceDestino.equals(sourceOrigen));
					
				}else{
					sourceDestino = showFile(repositoryDestino, path2, previousRevision);
				}
			}catch(SVNException svne){
				if(svne.getErrorMessage().getErrorCode().getCode() == 160013){
					logger.info("SVNException - Visualizando archivo nuevo");	
				}
			}
			
				
			model.addAttribute("sourceOrigen", sourceOrigen.replaceAll("\r", ""));
			model.addAttribute("sourceDestino",sourceDestino.replaceAll("\r", ""));
			model.addAttribute("origenPath", path1);
			model.addAttribute("origenRevision", request.getParameter("revision").toString());
			model.addAttribute("origenFecha", request.getParameter("fecha").toString());
			model.addAttribute("origenAutor", request.getParameter("autor").toString());
			model.addAttribute("destinoPath", origenDestino.getPath());
			model.addAttribute("destinoRevision", previousRevision);
			model.addAttribute("destinoFecha", origenDestino.getFecha());
			model.addAttribute("destinoAutor", origenDestino.getAutor());
			logger.info("Fin Controller POST ControllerSvn");
		}catch(SVNException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return "diferencias";
		}
	}
	
	@RequestMapping(value = "/SVN/SVNLIST", method = RequestMethod.GET)
	public String listadoSvn(ModelMap model, HttpServletRequest request) {
		try{
			System.out.println("Listando SVN");
			logger.info("Inicio Controller POST ControllerSvn");
//			SVNRepository repositoryOrigen = repositoryDesarrollo;
//			SVNRepository repositoryDestino = repositoryCalidad;
			init("DESARROLLO");
			List<Entry> listaPaths = showLog(repositoryOrigen, request.getParameter("rq"));
			model.addAttribute("listadoCambios", listaPaths);
		}catch(SVNException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return "diferenciaslistado";
		}
	}
	
	public List<Entry> listadoRevisiones(String requerimiento,String ambiente) {
		List<Entry> listaPaths = null;
		try{
			
//			SVNRepository repositoryOrigen = repositoryDesarrollo;
//			SVNRepository repositoryDestino = repositoryCalidad;
			init(ambiente);
			listaPaths = showLog(repositoryOrigen, requerimiento);
			
		}catch(SVNException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return listaPaths;
	}
	
	public void init(String ambiente) throws SVNException {
		if(ambiente.equals(Constants.AMBIENTE_DESARROLLO)){
			repositoryOrigen = SVNRepositoryFactory.create(SVNURL.parseURIDecoded(org.com.dm.util.Constants.SVNURLDESARROLLO));
			ISVNAuthenticationManager authDesarrollo = SVNWCUtil.createDefaultAuthenticationManager(org.com.dm.util.Constants.SVNUSUARIODESARROLLO, 
					org.com.dm.util.Constants.SVNPASSWORDDESARROLLO);
			repositoryOrigen.setAuthenticationManager(authDesarrollo);

			repositoryDestino = SVNRepositoryFactory.create(SVNURL.parseURIDecoded(org.com.dm.util.Constants.SVNURLCALIDAD));
			ISVNAuthenticationManager authCalidad = SVNWCUtil.createDefaultAuthenticationManager(org.com.dm.util.Constants.SVNUSUARIOCALIDAD, 
					org.com.dm.util.Constants.SVNPASSWORDCALIDAD);
			repositoryDestino.setAuthenticationManager(authCalidad);
		}else if(ambiente.equals(Constants.AMBIENTE_CALIDAD)){
			repositoryOrigen = SVNRepositoryFactory.create(SVNURL.parseURIDecoded(org.com.dm.util.Constants.SVNURLCALIDAD));
			ISVNAuthenticationManager authCalidad = SVNWCUtil.createDefaultAuthenticationManager(org.com.dm.util.Constants.SVNUSUARIOCALIDAD, 
					org.com.dm.util.Constants.SVNPASSWORDCALIDAD);
			repositoryOrigen.setAuthenticationManager(authCalidad);
			
			repositoryDestino = SVNRepositoryFactory.create(SVNURL.parseURIDecoded(org.com.dm.util.Constants.SVNURLPRODUCCION));
			ISVNAuthenticationManager authProduccion = SVNWCUtil.createDefaultAuthenticationManager(org.com.dm.util.Constants.SVNUSUARIOPRODUCCION, 
					org.com.dm.util.Constants.SVNPASSWORDPRODUCCION);
			repositoryDestino.setAuthenticationManager(authProduccion);
		}else if(ambiente.equals(Constants.AMBIENTE_PRODUCCION)){
			repositoryOrigen = SVNRepositoryFactory.create(SVNURL.parseURIDecoded(org.com.dm.util.Constants.SVNURLPRODUCCION));
			ISVNAuthenticationManager authProduccion = SVNWCUtil.createDefaultAuthenticationManager(org.com.dm.util.Constants.SVNUSUARIOPRODUCCION, 
					org.com.dm.util.Constants.SVNPASSWORDPRODUCCION);
			repositoryOrigen.setAuthenticationManager(authProduccion);
			
			repositoryDestino = SVNRepositoryFactory.create(SVNURL.parseURIDecoded(org.com.dm.util.Constants.SVNURLPRODUCCION));
			repositoryDestino.setAuthenticationManager(authProduccion);
		}
		

	}

	public void listEntries(SVNRepository repository, String path) throws SVNException {
		Collection entries = repository.getDir(path, -1, null, (Collection) null);
		Iterator iterator = entries.iterator();
		while (iterator.hasNext()) {
			SVNDirEntry entry = (SVNDirEntry) iterator.next();

			showFile(repository, entry.getRelativePath(), entry.getRevision());
			showProperties(repository, entry);

			if (entry.getKind() == SVNNodeKind.DIR) {
				listEntries(repository, (path.equals("")) ? entry.getName() : path + "/" + entry.getName());
			}
		}
	}

	public String showFile(SVNRepository repository, String path, long revision) throws SVNException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		repository.getFile(path, revision, null, baos);
		if (null == baos) {
			throw new NullPointerException(path + " is empty");
		}
		System.out.println(baos.toString());
		return baos.toString();
	}

	public void showProperties(SVNRepository repository, SVNDirEntry entry) throws SVNException {
		System.out.println("/" + entry.getRelativePath() + " ( author: '" + entry.getAuthor() + "'; revision: "
				+ entry.getRevision() + "; date: " + entry.getDate() + ";comment:"
				+ repository.getRevisionPropertyValue(entry.getRevision(), SVNRevisionProperty.LOG).getString() + ")");
	}

	public List<Entry> showLog(SVNRepository repository, String filterComment) throws SVNException {
		long startRevision = 0;
		long endRevision = -1; // HEAD (the latest) revision
		Collection logEntries = null;
		List<Entry> entrys = new ArrayList<Entry>();
		logEntries = repository.log(new String[] { "" }, null, startRevision, endRevision, true, true);
		
		Map<String,Entry>mapEntrys = new HashMap<String,Entry>();
		Map<String,Long>mapLastRevision = new HashMap<String,Long>();
		
		for (Iterator entries = logEntries.iterator(); entries.hasNext();) {
			SVNLogEntry logEntry = (SVNLogEntry) entries.next();
//			 System.out.println("---------------------------------------------");
//			 System.out.println("revision: " + logEntry.getRevision());
//			 System.out.println("author: " + logEntry.getAuthor());
//			 System.out.println("date: " + logEntry.getDate());
//			 System.out.println("log message: " + logEntry.getMessage());
			
			if (logEntry.getChangedPaths().size() > 0) {
				// System.out.println("changed paths:");
				Set changedPathsSet = logEntry.getChangedPaths().keySet();
				
				for (Iterator changedPaths = changedPathsSet.iterator(); changedPaths.hasNext();) {
					SVNLogEntryPath entryPath = (SVNLogEntryPath) logEntry.getChangedPaths().get(changedPaths.next());
//					 System.out.println(" " + entryPath.getType() + " " + entryPath.getPath()+ ((entryPath.getCopyPath() != null) ? " (from " +entryPath.getCopyPath() + " revision "+ entryPath.getCopyRevision() + ")" : ""));

					if (logEntry.getMessage().contains(filterComment)) {
						Long revision ;
						if(mapLastRevision.get(entryPath.getPath()) != null){
							revision = mapLastRevision.get(entryPath.getPath());
						}else{
							revision = 0L;
						}
						
						Entry entry = new Entry(entryPath.getPath(), logEntry.getRevision(), logEntry.getDate(), logEntry.getAuthor());
//						System.out.println("******** comparando revision : " + revision + " y " + logEntry.getRevision() );
						if(entry.getRevision() > revision){
							mapEntrys.put(entryPath.getPath(), entry);
//							System.out.println("************************* map :" +entryPath.getPath() + "-" + entry.getRevision());
							mapLastRevision.put(entryPath.getPath(), entry.getRevision());
						}
					}
				}

			}
		}
		for (Map.Entry<String, Entry> entry : mapEntrys.entrySet()){
//		    System.out.println(entry.getKey() + "/" + entry.getValue());
			entrys.add(entry.getValue());
		}
		return entrys;
	}

	
	private Entry getLastRevision(SVNRepository repository, String path) throws SVNException {
		
		long startRevision = 0;
		long endRevision = -1; // HEAD (the latest) revision
		Entry lastRevision = null; 
		Collection logEntries = null;
		List<Entry> revisiones = new ArrayList<Entry>(); 
		logEntries = repository.log(new String[] { path }, null, startRevision, endRevision, true, true);
		for (Iterator entries = logEntries.iterator(); entries.hasNext();) {
			SVNLogEntry logEntry = (SVNLogEntry) entries.next();
			if (logEntry.getChangedPaths().size() > 0) {
				// System.out.println("changed paths:");
				Set changedPathsSet = logEntry.getChangedPaths().keySet();

				for (Iterator changedPaths = changedPathsSet.iterator(); changedPaths.hasNext();) {
					SVNLogEntryPath entryPath = (SVNLogEntryPath) logEntry.getChangedPaths().get(changedPaths.next());
					Entry entry = new Entry(entryPath.getPath(), logEntry.getRevision(), logEntry.getDate(), logEntry.getAuthor());
					revisiones.add(entry);
				}

			}
		}
		if(revisiones.size()>0){
			lastRevision = revisiones.get(revisiones.size()-1);
		}
		return lastRevision;
	}
	

	public class Entry {
		private String path;
		private long revision;
		private Date fecha;
		private String autor;

		public Entry(String path, long revision, Date fecha, String autor) {
			super();
			this.path = path;
			this.revision = revision;
			this.fecha = fecha;
			this.autor = autor;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public long getRevision() {
			return revision;
		}

		public void setRevision(long revision) {
			this.revision = revision;
		}

		public Date getFecha() {
			return fecha;
		}

		public void setFecha(Date fecha) {
			this.fecha = fecha;
		}

		public String getAutor() {
			return autor;
		}

		public void setAutor(String autor) {
			this.autor = autor;
		}
		
	}
	
	public void loadInitialParameters(){
		Constants.SVNURL=urlSVN;
		Constants.SVNURLDESARROLLO = Constants.SVNURL + urlSVNDesarrollo;
		Constants.SVNURLCALIDAD = Constants.SVNURL + urlSVNCalidad;
		Constants.SVNURLPRODUCCION = Constants.SVNURL + urlSVNProduccion;
		
		Constants.SVNUSUARIODESARROLLO = svnUsuarioDesarrollo;
		Constants.SVNPASSWORDDESARROLLO = svnPasswordDesarrollo;
		
		Constants.SVNUSUARIOCALIDAD = svnUsuarioCalidad;
		Constants.SVNPASSWORDCALIDAD = svnPasswordCalidad;
		
		Constants.SVNUSUARIOPRODUCCION = svnUsuarioProduccion;
		Constants.SVNPASSWORDPRODUCCION = svnPasswordProduccion;
	}
	
}
