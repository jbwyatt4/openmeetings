package org.xmlcrm.app.data.product;

import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.xmlcrm.app.hibernate.beans.shop.zahlungsarten;
import org.xmlcrm.app.hibernate.utils.HibernateUtil;
import org.xmlcrm.utils.math.Calender;

public class Zahlungsmanagement {

	private Calender CalenderI;

	public Zahlungsmanagement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public zahlungsarten getZahlungsartenByID(int ZAHLUNGS_ID) {
		zahlungsarten zahlungsarten = new zahlungsarten();
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session
					.createQuery("select c from zahlungsarten as c where c.ZAHLUNGS_ID = :ZAHLUNGS_ID");
			query.setInteger("ZAHLUNGS_ID", ZAHLUNGS_ID);
			for (Iterator it2 = query.iterate(); it2.hasNext();) {
				zahlungsarten = (zahlungsarten) it2.next();
			}
			tx.commit();
			HibernateUtil.closeSession(idf);
			zahlungsarten.setStarttimeDE(CalenderI.getDatumMili(zahlungsarten
					.getStarttime()));
			zahlungsarten.setUpdatetimeDE(CalenderI.getDatumMili(zahlungsarten
					.getUpdatetime()));
		} catch (HibernateException ex) {
			zahlungsarten.setComment("Error: "+ex);
		} catch (Exception ex2) {
			zahlungsarten.setComment("Error: " +ex2);
		}
		return zahlungsarten;
	}

	public zahlungsarten[] getAllZahlungsarten(long user_level) {
		zahlungsarten zahlungsarten[] = new zahlungsarten[1];
		if (user_level > 1) {
			try {
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				Query query = session
						.createQuery("select c from zahlungsarten as c where c.freigeschalten = :freigeschalten");
				query.setInteger("freigeschalten", 1);
				int count = query.list().size();
				zahlungsarten = new zahlungsarten[count];
				int k = 0;
				for (Iterator it2 = query.iterate(); it2.hasNext();) {
					zahlungsarten[k] = (zahlungsarten) it2.next();
					k++;
				}
				tx.commit();
				HibernateUtil.closeSession(idf);
				for (int vars = 0; vars < zahlungsarten.length; vars++) {
					zahlungsarten[vars].setStarttimeDE(CalenderI
							.getDatumMili(zahlungsarten[vars].getStarttime()));
					zahlungsarten[vars].setUpdatetimeDE(CalenderI
							.getDatumMili(zahlungsarten[vars].getUpdatetime()));
				}
			} catch (HibernateException ex) {
				zahlungsarten[0] = new zahlungsarten();
				zahlungsarten[0].setComment("Error: "+ex);
			} catch (Exception ex2) {
				zahlungsarten[0] = new zahlungsarten();
				zahlungsarten[0].setComment("Error: " +ex2);
			}
		} else {
			zahlungsarten[0] = new zahlungsarten();
			zahlungsarten[0].setComment("Permission denied");
		}
		return zahlungsarten;
	}
}
