package app.poll;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Size;

@ManagedBean
@SessionScoped
public class EnqueteBean {
	
	@Size(min = 1, max = 3)
	private List<String> escolhas;
	
	@Inject
	private VotoManager manager;
	
	// private void carregarVotos() {
	// List<Voto> votos = manager.obter(getIp());
	//
	// escolhas = new ArrayList<String>();
	//
	// for (Voto voto : votos) {
	// escolhas.add(voto.getOpcao());
	// }
	// }
	
	public void enviar() {
		Voto voto;
		for (String escolha : escolhas) {
			voto = new Voto();
			voto.setIp(getIp());
			voto.setHora(new Date());
			voto.setOpcao(escolha);
			
			manager.inserir(voto);
		}
		
		// carregarVotos();
	}
	
	public List<String> getEscolhas() {
		// if (escolhas == null) {
		// carregarVotos();
		// }
		
		return escolhas;
	}
	
	private String getIp() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		
		return request.getRemoteAddr();
	}
	
	public List<SelectItem> getOpcoes() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		
		list.add(new SelectItem("validation", "Bean Validation"));
		list.add(new SelectItem("cdi", "CDI"));
		list.add(new SelectItem("ejb", "EJB 3.1"));
		list.add(new SelectItem("jsf", "JSF 2"));
		list.add(new SelectItem("jpa", "JPA 2"));
		list.add(new SelectItem("servlet", "Servlet 3"));
		list.add(new SelectItem("jax-rs", "JAX-RS"));
		list.add(new SelectItem("outro", "Outras"));
		
		return list;
	}
	
	// @PostConstruct
	// public void init() {
	// carregarVotos();
	// }
	
	public void setEscolhas(List<String> escolhas) {
		this.escolhas = escolhas;
	}
}
