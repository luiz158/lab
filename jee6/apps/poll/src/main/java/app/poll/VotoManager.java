package app.poll;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@RequestScoped
@SuppressWarnings("serial")
public class VotoManager implements Serializable {

	private final EntityManager em;

	public VotoManager() {
		em = Util.getEntityManager();
	}

	public void inserir(Voto voto) {
		em.getTransaction().begin();
		em.persist(voto);
		em.getTransaction().commit();
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

	public void excluir(String ip) {
		em.getTransaction().begin();

		Query query = em.createQuery("delete from Voto v where v.ip = :ip");
		query.setParameter("ip", ip);
		query.executeUpdate();

		em.getTransaction().commit();
	}
}
