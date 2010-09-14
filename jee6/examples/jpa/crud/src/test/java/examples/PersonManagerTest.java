package examples;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PersonManagerTest {
	
	private static Database database;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		database = new Database("crud", 9001);
		database.start();
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		database.stop();
	}
	
	private PersonManager manager;
	
	private void clean() {
		List<Person> persons = manager.retrieve();
		
		for (Person person : persons) {
			manager.delete(person.getId());
		}
	}
	
	@Before
	public void setUp() throws Exception {
		manager = new PersonManager();
	}
	
	@After
	public void tearDown() throws Exception {
		clean();
	}
	
	@Test
	public void testCache() {
		Person person = new Person("person", new Date());
		manager.create(person);
		
		for (int i = 0; i < 100; i++) {
			manager.retrieve(person.getId());
			Util.getEntityManager().clear();
		}
	}
	
	@Test
	public void testCreate() {
		Person person = new Person("person1", new Date());
		
		manager.create(person);
		Person persisted = manager.retrieve(person.getId());
		
		Assert.assertNotNull(persisted);
		Assert.assertEquals(persisted.getName(), person.getName());
	}
	
	@Test
	public void testDelete() {
		Person person = new Person("person1", new Date());
		manager.create(person);
		manager.delete(person.getId());
		
		Person persisted = manager.retrieve(person.getId());
		
		Assert.assertNull(persisted);
	}
	
	@Test
	public void testRetrieveAll() {
		int COUNT = 10;
		
		for (int i = 1; i <= COUNT; i++) {
			manager.create(new Person("person" + i, new Date()));
		}
		
		Assert.assertEquals(manager.retrieve().size(), COUNT);
	}
	
	@Test
	public void testRetrieveById() {
		Person person = new Person("person1", new Date());
		manager.create(person);
		
		Person persisted = manager.retrieve(person.getId());
		Assert.assertNotNull(persisted);
	}
	
	@Test
	public void testRetrieveStringByName() {
		Person person;
		Person persisted;
		
		persisted = manager.retrieve("person1");
		Assert.assertNull(persisted);
		
		person = new Person("person1", new Date());
		manager.create(person);
		
		persisted = manager.retrieve("person1");
		Assert.assertNotNull(persisted);
		
		persisted = manager.retrieve("fail");
		Assert.assertNull(persisted);
	}
	
	@Test
	public void testUpdate() {
		Person person = new Person("person1", new Date());
		manager.create(person);
		
		person.setName("person2");
		manager.update(person);
		
		Person persisted = manager.retrieve(person.getId());
		Assert.assertEquals(persisted.getName(), person.getName());
	}
}
