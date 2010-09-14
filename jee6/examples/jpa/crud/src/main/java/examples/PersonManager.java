package examples;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PersonManager {
	
	private final EntityManager em;
	
	public PersonManager() {
		em = Util.getEntityManager();
	}
	
	public void create(Person person) {
		em.getTransaction().begin();
		em.persist(person);
		em.getTransaction().commit();
	}
	
	public void delete(Long id) {
		Person person = retrieve(id);
		
		em.getTransaction().begin();
		em.remove(person);
		em.getTransaction().commit();
	}
	
	public List<Person> retrieve() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Person> cq = cb.createQuery(Person.class);
		
		TypedQuery<Person> query = em.createQuery(cq);
		
		return query.getResultList();
	}
	
	public Person retrieve(Long id) {
		return em.find(Person.class, id);
	}
	
	public Person retrieve(String name) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Person> cq = cb.createQuery(Person.class);
		Root<Person> person = cq.from(Person.class);
		Predicate predicate = cb.equal(person.get("name"), name);
		cq.where(predicate);
		
		TypedQuery<Person> query = em.createQuery(cq);
		
		Person result;
		try {
			result = query.getSingleResult();
			
		} catch (NoResultException cause) {
			result = null;
		}
		
		return result;
	}
	
	public void update(Person person) {
		em.getTransaction().begin();
		em.merge(person);
		em.getTransaction().commit();
	}
	
}
