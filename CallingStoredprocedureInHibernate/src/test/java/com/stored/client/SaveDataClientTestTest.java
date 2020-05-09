package com.stored.client;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import com.stored.entities.Person;
import com.stored.entities.Phone;
import com.stored.entities.PhoneType;
import com.stored.util.HibernateUtil;

class SaveDataClientTestTest {

	@Test
	void test() {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		try {
			session = sf.openSession();
			session.beginTransaction();
			
			Person person1 = new Person();
			person1.setName("A");
			person1.setNickName("A");
			person1.setAddress("Hyderabad");
			person1.setCreatedOn(new Date());
			person1.setVersion(1);
			
			Phone phone1 = new Phone();
			phone1.setNumber("888637");
			phone1.setType(PhoneType.MOBILE);
			phone1.setPerson(person1);
			
			person1.getPhones().add(phone1);
			
			
			Person person2 = new Person();
			person2.setName("B");
			person2.setNickName("S");
			person2.setAddress("Street");
			person2.setCreatedOn(new Date());
			person2.setVersion(1);
			
			Phone phone2 = new Phone();
			phone2.setNumber("809865430");
			phone2.setType(PhoneType.MOBILE);
			phone2.setPerson(person2);
			
			Phone phone3 = new Phone();
			phone3.setNumber("022909742");
			phone3.setType(PhoneType.LAND_LINE);
			phone3.setPerson(person2);
			
			person2.getPhones().add(phone2);
			person2.getPhones().add(phone3);
			
			session.save(person1);
			session.save(person2);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
		}

		
	}

}
