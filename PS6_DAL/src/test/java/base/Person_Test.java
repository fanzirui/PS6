package base;


import static org.junit.Assert.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.IntegerProperty;



public class Person_Test {
	
	private static PersonDomainModel Person1 = new PersonDomainModel();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		
		Person1.setPersonID(UUID.randomUUID());
		Person1.setFirstName("Gougou");
		Person1.setLastName("Wu");
		Person1.setBirthday(new Date(0));
		Person1.setCity("Newark");
		Person1.setStreet("26 Montague Rd");
		Person1.setPostalCode(19711);
		
		
		
		PersonDomainModel Temper = PersonDAL.getPerson(Person1.getPersonID());

		assertNull(Temper);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		PersonDAL.deletePerson(Person1.getPersonID());
		
	}
	

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void AddPerson() {

		PersonDAL.addPerson(Person1);		
		PersonDomainModel Temper = PersonDAL.getPerson(Person1.getPersonID());

		assertNotNull(Temper);
		
		PersonDAL.deletePerson(Person1.getPersonID());
	}
	
	@Test
	public void UpdateDeletePersonTest(){		

		PersonDAL.addPerson(Person1);
		
		PersonDomainModel per2 = PersonDAL.getPerson(Person1.getPersonID());
		
		per2.setFirstName("Bob");
		
		PersonDAL.updatePerson(per2);
		
		
		PersonDomainModel per3 = PersonDAL.getPerson(Person1.getPersonID());
		
		assertTrue(per2.getFirstName().equals(per3.getFirstName()));
		assertFalse(Person1.getFirstName().equals(per3.getFirstName()));
		
		PersonDAL.deletePerson(Person1.getPersonID());
		
		PersonDomainModel per4 = PersonDAL.getPerson(Person1.getPersonID());
		
		assertNull(per4);

	}

	
	@Test
	public void GetpersonsTest(){
		PersonDAL.addPerson(Person1);
		assertNotNull(PersonDAL.getPersons());
		ArrayList<PersonDomainModel> Persons = PersonDAL.getPersons();
		int i=0;
		for(PersonDomainModel per: Persons){
			if (per.getPersonID().equals(Person1.getPersonID())){
				i+=1;
			}
		}
		
		
		// i must be equal to 1 because this UUID is unique.
		assertTrue(i==1);
	}
	

}