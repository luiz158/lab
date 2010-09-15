package app.poll;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@RequestScoped
public class VotoManager {
	
	private final EntityManager em;
	
	public VotoManager() {
		em = Util.getEntityManager();
	}
	
	public void inserir(Voto voto) {
		List<Voto> votos = obter(voto.getIp());
		
		if (votos.size() > 2 && !votos.contains(voto)) {
			FacesMessage message = new FacesMessage("Já consta no banco de dados que você votou 3 vezes");
			FacesContext.getCurrentInstance().addMessage("op", message);
			
		} else {
			em.getTransaction().begin();
			em.persist(voto);
			em.getTransaction().commit();
		}
	}
	
	public List<Voto> obter(String ip) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Voto> cq = cb.createQuery(Voto.class);
		Root<Voto> person = cq.from(Voto.class);
		Predicate predicate = cb.equal(person.get("ip"), ip);
		cq.where(predicate);
		
		TypedQuery<Voto> query = em.createQuery(cq);
		
		return query.getResultList();
	}
}
