package fronteira;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "logoutBean")
@ViewScoped
public class LogoutBean {
	
	public LogoutBean() {
	}

	public void realizarLogout() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getExternalContext().invalidateSession();
        String outcome = "index.xhtml?faces-redirect=true";
        try {
			facesContext.getExternalContext().redirect(outcome);
		} catch (IOException e) {
			e.printStackTrace();
		}
	};
}
