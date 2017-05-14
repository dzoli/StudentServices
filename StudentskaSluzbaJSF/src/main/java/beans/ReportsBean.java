package beans;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import customBean.ProfesorBrPredmeta;
import customBean.StudentBrojPolozenih;
import manager.ProfesorManager;
import manager.StudentManager;
import model.Departman;
import model.Student;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean
@SessionScoped
public class ReportsBean {
	private HashMap<String, Object> params;
	private JREmptyDataSource emptyDataSource;
	private JRDataSource dataSource;
	private ServletContext context;
	private JasperPrint jasperPrint;
	private String reportsDirectory;
	private String jasperFile;
	private HttpServletResponse response;
	private StudentManager sm;
	private ProfesorManager profm;
	// parametri za studente po departmanu grupisanih neke godine studija
	private String godinaStudija;
	// parametri za studente i broj polozenih ispita
	private List<Departman> sviDepartmani1;
	private Integer izabraniDep;
	// parametri za profesore i predmente, isti kao za predhodni
	private Integer izabraniDep1;

	public ReportsBean() {
		params = new HashMap<String, Object>();
		sm = new StudentManager();
		profm = new ProfesorManager();
		sviDepartmani1 = profm.getAllDepartmans();
	}

	public void generateReport(String reportType) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			ExternalContext externalContext = facesContext.getExternalContext();
			context = (ServletContext) externalContext.getContext();
			reportsDirectory = context.getRealPath("/") + "WEB-INF/classes/jasper/";
			response = (HttpServletResponse) externalContext.getResponse();
			// if(reportType.equals("ZaduzenjaKnjige")){
			// Knjiga k = km.getKnjigaForId(idKnjige);
			// naslov = k.getNaslov();
			// autor = k.getAutor();
			// godinaIzdanja = k.getGodinaIzdanja();
			// izdavac = k.getIzdavac();
			// params.put("naslov", naslov);
			// params.put("autor", autor);
			// params.put("godinaIzdanja", godinaIzdanja);
			// params.put("izdavac", izdavac);
			// List<Zaduzenje> data = km.getZaduzenjesForBook(k);
			// jasperFile = reportsDirectory + "ZaduzenjaKnjige.jasper";
			// if (data.isEmpty()) {
			// jasperPrint = JasperFillManager.fillReport(jasperFile, params,
			// emptyDataSource);
			// } else {
			// dataSource = new JRBeanCollectionDataSource(data);
			// jasperPrint = JasperFillManager.fillReport(jasperFile, params,
			// dataSource);
			// }
			// }

			if (reportType.equals("StudentiSaDepartmana")) {
				Integer godStudija = Integer.parseInt(godinaStudija);
				params.put("datum", new Date());
				params.put("godinaStudija", godStudija); // svi studenti te
															// godine
				List<Student> data = sm.getStudentsSortByDep(godStudija);
				jasperFile = reportsDirectory + "StudentiSaDepartmana.jasper";
				if (data.isEmpty()) {
					jasperPrint = JasperFillManager.fillReport(jasperFile, params, emptyDataSource);
				} else {
					dataSource = new JRBeanCollectionDataSource(data);
					jasperPrint = JasperFillManager.fillReport(jasperFile, params, dataSource);
				}
			}

			if (reportType.equals("StudentiBrojPolozenih")) {
				Departman selDep = profm.getDepartmandForID(izabraniDep);
				params.put("izabraniDep", selDep.getNaziv());
				params.put("datum", new Date());
				List<StudentBrojPolozenih> data = sm.getStudentBrojPolozenihBeans(selDep);
				jasperFile = reportsDirectory + "StudentiBrojPolozenih.jasper";
				if (data.isEmpty()) {
					jasperPrint = JasperFillManager.fillReport(jasperFile, params, emptyDataSource);
				} else {
					dataSource = new JRBeanCollectionDataSource(data);
					jasperPrint = JasperFillManager.fillReport(jasperFile, params, dataSource);
				}
			}

			if (reportType.equals("ProfesoriPredmeti")) {
				Departman selDep = profm.getDepartmandForID(izabraniDep1);
				params.put("izabraniDep1", selDep.getNaziv());
				params.put("datum", new Date());
				List<ProfesorBrPredmeta> data = sm.getProfesorsForDepartmanSortByProf(selDep);
				jasperFile = reportsDirectory + "ProfesoriPredmeti.jasper";
				if (data.isEmpty()) {
					jasperPrint = JasperFillManager.fillReport(jasperFile, params, emptyDataSource);
				} else {
					dataSource = new JRBeanCollectionDataSource(data);
					jasperPrint = JasperFillManager.fillReport(jasperFile, params, dataSource);
				}
			}

			ServletOutputStream servletOutputStream = response.getOutputStream();
			response.setContentType("application/pdf");
			JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
			servletOutputStream.flush();
			servletOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			facesContext.responseComplete();
		}
	}

	public String getIzvestaj1Page() {
		return "StudentiPoDepartmanimaReport?faces-redirect=true";
	}

	public String getIzvestaj2Page() {
		return "StudentiBrojPolozenih?faces-redirect=true";
	}

	public String getIzvestaj3Page() {
		return "ProfesoriPredmeti?faces-redirect=true";
	}

	public String getGodinaStudija() {
		return godinaStudija;
	}

	public void setGodinaStudija(String godinaStudija) {
		this.godinaStudija = godinaStudija;
	}

	public List<Departman> getSviDepartmani1() {
		return sviDepartmani1;
	}

	public void setSviDepartmani1(List<Departman> sviDepartmani1) {
		this.sviDepartmani1 = sviDepartmani1;
	}

	public Integer getIzabraniDep() {
		return izabraniDep;
	}

	public void setIzabraniDep(Integer izabraniDep) {
		this.izabraniDep = izabraniDep;
	}

	public Integer getIzabraniDep1() {
		return izabraniDep1;
	}

	public void setIzabraniDep1(Integer izabraniDep1) {
		this.izabraniDep1 = izabraniDep1;
	}
}
