package com.stored.client;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import com.stored.util.HibernateUtil;

class CallStoredProcedureInHibernateClientTestTest {

	@Test
	void test() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			StoredProcedureQuery procedureQuery = session.createStoredProcedureQuery("sp_count_phones");
			procedureQuery.registerStoredProcedureParameter( "personId", Long.class, ParameterMode.IN);
			procedureQuery.registerStoredProcedureParameter( "phoneCount", Long.class, ParameterMode.OUT);

			procedureQuery.setParameter("personId", 1L);

			procedureQuery.execute();
			Long phoneCount = (Long) procedureQuery.getOutputParameterValue("phoneCount");
			
			System.out.println("Phone Count:"+phoneCount);
		}
	}
	}


