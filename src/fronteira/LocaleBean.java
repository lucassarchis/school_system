package fronteira;

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class LocaleBean {

	private Locale locale;

	@PostConstruct
	public void init() {
		locale = new Locale("pt");
	}

	public Locale getLocale() {
		return locale;
	}

	public String getLanguage() {
		return locale.getLanguage();
	}

	public void setLanguage(String language) {
		locale = new Locale(language);
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}
	
	public void setLanguagePT() {
		setLanguage("pt");
	}
	
	public void setLanguageEN() {
		setLanguage("en");
	}
	

}
