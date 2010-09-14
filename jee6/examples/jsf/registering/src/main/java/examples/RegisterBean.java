package examples;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class RegisterBean {
	
	public Register register;
	
	private void addMessage(String id, String message) {
		FacesMessage facesMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(id, facesMessage);
	}
	
	public Register getRegister() {
		return register;
	}
	
	private boolean hasQueuedMessages() {
		return FacesContext.getCurrentInstance().getMessageList().size() > 0;
	}
	
	@PostConstruct
	public void init() {
		register = new Register();
	}
	
	public String save() {
		validateName();
		validateCellPhone();
		
		String outcome = null;
		
		if (!hasQueuedMessages()) {
			outcome = "/result.xhtml";
		}
		
		return outcome;
	}
	
	private void validateCellPhone() {
		if (register.getCellPhone() == null || register.getCellPhone().trim().equals("")) {
			addMessage("cellPhone", "O número do celular deve ser preenchido");
		}
		
		Pattern p = Pattern.compile("^(\\(\\d{2}\\) )?\\d{4}-\\d{4}$");
		Matcher m = p.matcher(register.getCellPhone());
		if (!m.matches()) {
			addMessage("cellPhone", "O número do celular deve ter o seguinte formato: (XX) XXXX-XXXX");
		}
	}
	
	private void validateName() {
		if (register.getName() == null || register.getName().trim().equals("")) {
			addMessage("name", "O nome deve ser preenchido");
		}
	}
}
