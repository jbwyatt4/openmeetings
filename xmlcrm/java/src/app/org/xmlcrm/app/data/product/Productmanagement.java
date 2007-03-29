package org.xmlcrm.app.data.product;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import org.xmlcrm.app.hibernate.beans.basic.Navisub;
import org.xmlcrm.app.hibernate.beans.contact.Contacts;
import org.xmlcrm.app.hibernate.beans.shop.products;
import org.xmlcrm.app.hibernate.beans.user.Users;
import org.xmlcrm.app.hibernate.utils.HibernateUtil;
import org.xmlcrm.app.remote.ResHandler;

public class Productmanagement {
	private ResHandler ResHandler;

	public Productmanagement(ResHandler handler) {
		super();
		// TODO Auto-generated constructor stub
		ResHandler = handler;
	}

	public products[] getProductsByCat() {
		products products[] = new products[1];
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session
					.createQuery("select c from products as c group by c.category");
			int count = query.list().size();
			products = new products[count];
			int k = 0;
			for (Iterator it2 = query.iterate(); it2.hasNext();) {
				products[k] = (products) it2.next();
				k++;
			}
			tx.commit();
			HibernateUtil.closeSession(idf);
		} catch (HibernateException ex) {
			products[0] = new products();
			products[0].setCategory("Error: " + ex);
		} catch (Exception ex2) {
			products[0] = new products();
			products[0].setCategory("Error: " + ex2);
		}
		return products;
	}

	public products[] searchProduct(String searchstring) {
		products products[] = new products[1];
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Criteria crit = session.createCriteria(products.class);
			System.out.println("SearchString: " + searchstring);
			crit.add(Restrictions.ilike("title", "%" + searchstring + "%"));
			crit.setMaxResults(10);
			List contactsZ = crit.list();
			int count = contactsZ.size();
			products = new products[count];
			int k = 0;
			for (Iterator it = contactsZ.iterator(); it.hasNext();) {
				products[k] = (products) it.next();
				System.out.println("SearchArtnumm: "
						+ products[k].getArtnumber());
				k++;
			}
			HibernateUtil.closeSession(idf);
		} catch (HibernateException ex) {
			products[0] = new products();
			products[0].setCategory("Error: " + ex);
		} catch (Exception ex2) {
			products[0] = new products();
			products[0].setCategory("Error: " + ex2);
		}
		return products;
	}

	public products[] getProductsByCat(int start, int max, String cat) {
		products products[] = new products[1];
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session
					.createQuery("select c from products as c where c.category = :category");
			query.setString("category", cat);
			query.setFirstResult(start);
			query.setMaxResults(max);
			int count = query.list().size();
			products = new products[count];
			int k = 0;
			for (Iterator it2 = query.iterate(); it2.hasNext();) {
				products[k] = (products) it2.next();
				k++;
			}
			tx.commit();
			HibernateUtil.closeSession(idf);
		} catch (HibernateException ex) {
			products[0] = new products();
			products[0].setCategory("Error: " + ex);
		} catch (Exception ex2) {
			products[0] = new products();
			products[0].setCategory("Error: " + ex2);
		}
		return products;
	}

	public products[] getAllProductsByCat(String cat) {
		products products[] = new products[1];
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session
					.createQuery("select c from products as c where c.category = :category");
			query.setString("category", cat);
			int count = query.list().size();
			products = new products[count];
			int k = 0;
			for (Iterator it2 = query.iterate(); it2.hasNext();) {
				products[k] = (products) it2.next();
				k++;
			}
			tx.commit();
			HibernateUtil.closeSession(idf);
		} catch (HibernateException ex) {
			products[0] = new products();
			products[0].setCategory("Error: " + ex);
		} catch (Exception ex2) {
			products[0] = new products();
			products[0].setCategory("Error: " + ex2);
		}
		return products;
	}

	public products getProductsByID(int artnumber) {
		products products = new products();
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session
					.createQuery("select c from products as c where c.artnumber = :artnumber");
			query.setInteger("artnumber", artnumber);
			for (Iterator it2 = query.iterate(); it2.hasNext();) {
				products = (products) it2.next();
			}
			tx.commit();
			HibernateUtil.closeSession(idf);
		} catch (HibernateException ex) {
			products.setCategory("Error: " + ex);
		} catch (Exception ex2) {
			products.setCategory("Error: " + ex2);
		}
		return products;
	}
}
