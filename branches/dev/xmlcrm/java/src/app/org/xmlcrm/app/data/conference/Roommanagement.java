package org.xmlcrm.app.data.conference;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import org.xmlcrm.app.hibernate.beans.rooms.*;
import org.xmlcrm.app.hibernate.beans.user.*;
import org.xmlcrm.app.hibernate.utils.HibernateUtil;
import org.xmlcrm.app.data.basic.AuthLevelmanagement;
import org.xmlcrm.app.data.user.Organisationmanagement;
import org.xmlcrm.app.data.user.Usermanagement;
import org.xmlcrm.app.hibernate.beans.domain.Organisation_Users;

/**
 * 
 * @author swagner
 *
 */
public class Roommanagement {

	private static final Log log = LogFactory.getLog(Roommanagement.class);

	private static Roommanagement instance;

	private Roommanagement() {}

	public static synchronized Roommanagement getInstance() {
		if (instance == null) {
			instance = new Roommanagement();
		}
		return instance;
	}
	
	/**
	 * add a new Record to the table roomtypes
	 * @param name
	 * @return ID of new created roomtype or null
	 */
	public Long addRoomType(String name){
		try {
			RoomTypes rtype = new RoomTypes();
			rtype.setName(name);
			rtype.setStarttime(new Date());
			rtype.setDeleted("false");
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			long returnId = (Long) session.save(rtype);
			tx.commit();
			HibernateUtil.closeSession(idf);
			return returnId;
		} catch (HibernateException ex) {
			log.error("[addRoomType] "+ex);
		} catch (Exception ex2) {
			log.error("[addRoomType] "+ex2);
		}
		return null;
	}
	
	/**
	 * get all availible RoomTypes
	 * @return List of RoomTypes
	 */
	public List getAllRoomTypes(long USER_LEVEL){
		try {
			if (AuthLevelmanagement.getInstance().checkAdminLevel(USER_LEVEL)){
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				Query query = session.createQuery("select c from RoomTypes as c where c.deleted != :deleted");
				query.setString("deleted", "true");
				List ll = query.list();
				tx.commit();
				HibernateUtil.closeSession(idf);
				return ll;
			}
		} catch (HibernateException ex) {
			log.error("[getAllRoomTypes] "+ex);
		} catch (Exception ex2) {
			log.error("[getAllRoomTypes] "+ex2);
		}
		return null;
	}
	
	/**
	 * Get a RoomTypes-Object by its id
	 * @param roomtypes_id
	 * @return RoomTypes-Object or NULL
	 */
	public RoomTypes getRoomTypesById(long roomtypes_id){
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("select c from RoomTypes as c where c.roomtypes_id = :roomtypes_id AND c.deleted != :deleted");
			query.setLong("roomtypes_id",roomtypes_id);
			query.setString("deleted", "true");
			List ll = query.list();
			tx.commit();
			HibernateUtil.closeSession(idf);
			if (ll.size()>0){
				return (RoomTypes) ll.get(0);
			}
		} catch (HibernateException ex) {
			log.error("[getRoomTypesById] "+ex);
		} catch (Exception ex2) {
			log.error("[getRoomTypesById] "+ex2);
		}
		return null;
	}	
	
	/**
	 * Get a Rooms-Object or NULL
	 * @param rooms_id
	 * @return Rooms-Object or NULL
	 */
	public Rooms getRoomById(long rooms_id){
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("select c from Rooms as c where c.rooms_id = :rooms_id AND c.deleted != :deleted");
			query.setLong("rooms_id", rooms_id);
			query.setString("deleted", "true");
			List ll = query.list();
			tx.commit();
			HibernateUtil.closeSession(idf);
			if (ll.size()>0){
				return (Rooms) ll.get(0);
			}
		} catch (HibernateException ex) {
			log.error("[getRoomById] "+ex);
		} catch (Exception ex2) {
			log.error("[getRoomById] "+ex2);
		}
		return null;
	}	
	
	/**
	 * get all rooms which are availible for public
	 * @param USER_LEVEL
	 * @param roomtypes_id
	 * @return
	 */
	public List getPublicRooms(long USER_LEVEL, long roomtypes_id){
		try {
			if (AuthLevelmanagement.getInstance().checkUserLevel(USER_LEVEL)){
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				Criteria crit = session.createCriteria(Rooms.class);
				Criteria subcriteriaRoomType = crit.createCriteria("roomtype");
				subcriteriaRoomType.add(Restrictions.eq("roomtypes_id", roomtypes_id));
				crit.add(Restrictions.eq("ispublic", true));
				crit.add(Restrictions.eq("deleted", "false"));
				List ll = crit.list();
				tx.commit();
				HibernateUtil.closeSession(idf);
				return ll;
			}
		} catch (HibernateException ex) {
			log.error("[getRoomsByOrganisation] "+ex);
		} catch (Exception ex2) {
			log.error("[getRoomsByOrganisation] "+ex2);
		}
		return null;
	}	
	
	/**
	 * adds a new Record to the table rooms
	 * @param name
	 * @param roomtypes_id
	 * @param ispublic
	 * @return id of the newly created room or NULL
	 */
	public Long addRoom(long USER_LEVEL,String name, long roomtypes_id, boolean ispublic){
		try {
			if (AuthLevelmanagement.getInstance().checkAdminLevel(USER_LEVEL)){
				Rooms r = new Rooms();
				r.setName(name);
				r.setStarttime(new Date());
				r.setRoomtype(this.getRoomTypesById(roomtypes_id));
				r.setIspublic(ispublic);
				r.setDeleted("false");
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				long returnId = (Long) session.save(r);
				tx.commit();
				HibernateUtil.closeSession(idf);
				return returnId;
			}
		} catch (HibernateException ex) {
			log.error("[addRoom] "+ex);
		} catch (Exception ex2) {
			log.error("[addRoom] "+ex2);
		}
		return null;
	}
	
	/**
	 * adds a new record to the table rooms_organisation
	 * @param rooms_id
	 * @param organisation_id
	 * @return the id of the newly created Rooms_Organisation or NULL
	 */
	public Long addRoomToOrganisation(long USER_LEVEL, long rooms_id, long organisation_id){
		try {
			if (AuthLevelmanagement.getInstance().checkAdminLevel(USER_LEVEL)){
				Rooms_Organisation rOrganisation = new Rooms_Organisation();
				rOrganisation.setRoom(this.getRoomById(rooms_id));
				log.error("addRoomToOrganisation rooms "+rOrganisation.getRoom().getName());
				rOrganisation.setStarttime(new Date());
				rOrganisation.setOrganisation(Organisationmanagement.getInstance().getOrganisationById(organisation_id));
				rOrganisation.setDeleted("false");
				
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				long returnId = (Long) session.save(rOrganisation);
				tx.commit();
				HibernateUtil.closeSession(idf);
				return returnId;
			}
		} catch (HibernateException ex) {
			log.error("[addRoomToOrganisation] "+ex);
		} catch (Exception ex2) {
			log.error("[addRoomToOrganisation] "+ex2);
		}
		return null;
	}
	
	/**
	 * 
	 * @param rooms_organisation_id
	 * @return
	 */
	public Rooms_Organisation getRoomsOrganisationById(long rooms_organisation_id){
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			
			Criteria crit = session.createCriteria(Rooms_Organisation.class);
			crit.add(Restrictions.eq("rooms_organisation_id", rooms_organisation_id));
			List ll = crit.list();
			
			tx.commit();
			HibernateUtil.closeSession(idf);
			if (ll.size()>0){
				return (Rooms_Organisation)ll.get(0);
			}
		} catch (HibernateException ex) {
			log.error("[getRoomsByOrganisation] "+ex);
		} catch (Exception ex2) {
			log.error("[getRoomsByOrganisation] "+ex2);
		}
		return null;
	}	
	
	/**
	 * get List of Rooms_Organisation by organisation and roomtype
	 * @param USER_LEVEL
	 * @param organisation_id
	 * @param roomtypes_id
	 * @return
	 */
	public List getRoomsOrganisationByOrganisationIdAndRoomType(long USER_LEVEL,long organisation_id, long roomtypes_id){
		try {
			if (AuthLevelmanagement.getInstance().checkModLevel(USER_LEVEL)){
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				
				Criteria crit = session.createCriteria(Rooms_Organisation.class);
				Criteria subcrit = crit.createCriteria("organisation");
				subcrit.add(Restrictions.eq("organisation_id", organisation_id));
				Criteria subcritRoom = crit.createCriteria("room");
				Criteria subCritRoomType = subcritRoom.createCriteria("roomtype");
				subCritRoomType.add(Restrictions.eq("roomtypes_id", roomtypes_id));
				crit.add(Restrictions.eq("deleted", "false"));
				List ll = crit.list();
				
				tx.commit();
				HibernateUtil.closeSession(idf);
				
				return ll;
			}
		} catch (HibernateException ex) {
			log.error("[getRoomsByOrganisation] "+ex);
		} catch (Exception ex2) {
			log.error("[getRoomsByOrganisation] "+ex2);
		}
		return null;
	}
	
	/**
	 * Gets all rooms by an organisation
	 * @param organisation_id
	 * @return list of Rooms_Organisation with Rooms as Sub-Objects or null
	 */
	public List getRoomsOrganisationByOrganisationId(long USER_LEVEL,long organisation_id){
		try {
			if (AuthLevelmanagement.getInstance().checkModLevel(USER_LEVEL)){
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				
				Criteria crit = session.createCriteria(Rooms_Organisation.class);
				Criteria subcrit = crit.createCriteria("organisation");
				subcrit.add(Restrictions.eq("organisation_id", organisation_id));
				crit.add(Restrictions.eq("deleted", "false"));
				List ll = crit.list();
				
				tx.commit();
				HibernateUtil.closeSession(idf);
				
				return ll;
			}
		} catch (HibernateException ex) {
			log.error("[getRoomsByOrganisation] "+ex);
		} catch (Exception ex2) {
			log.error("[getRoomsByOrganisation] "+ex2);
		}
		return null;
	}
	
	/**
	 * 
	 * @param organisation_id
	 * @return
	 */
	public List getRoomsOrganisationByRoomsId(long rooms_id){
		try {
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Rooms_Organisation.class);
			Criteria subcrit = crit.createCriteria("room");
			subcrit.add(Restrictions.eq("rooms_id", rooms_id));
			crit.add(Restrictions.eq("deleted", "false"));
			List ll = crit.list();
			tx.commit();
			HibernateUtil.closeSession(idf);
			return ll;
		} catch (HibernateException ex) {
			log.error("[getRoomsByOrganisation] "+ex);
		} catch (Exception ex2) {
			log.error("[getRoomsByOrganisation] "+ex2);
		}
		return null;
	}
	
	/**
	 * 
	 * @param user_id
	 * @param rooms_id
	 * @return
	 */
	private boolean checkUserOrgRoom(long user_id, long rooms_id){
		try {
			
			Users us = Usermanagement.getInstance().getUser(user_id);
			Set s = us.getOrganisation_users();
		
			for (Iterator it = s.iterator(); it.hasNext();){
				Organisation_Users orgUsers = (Organisation_Users) it.next();
				long organisation_id = orgUsers.getOrganisation().getOrganisation_id();
				List ll = this.getRoomsOrganisationByOrganisationId(3, organisation_id);
				for (Iterator it2 = ll.iterator(); it2.hasNext(); ){
					Rooms_Organisation roomOrg = (Rooms_Organisation) it2.next();
					if(roomOrg.getRoom().getRooms_id()==rooms_id){
						return true;
					}
				}
			}
			
		} catch (HibernateException ex) {
			log.error("[checkUserOrgRoom] "+ex);
		} catch (Exception ex2) {
			log.error("[checkUserOrgRoom] "+ex2);
		}
		return false;
	}
	
	/**
	 * 
	 * @param user_id
	 * @param USER_LEVEL
	 * @param rooms_id
	 * @param roomtypes_id
	 * @param name
	 * @param ispublic
	 * @param comment
	 * @return
	 */
	public Rooms updateRoomsSelf(long user_id, long USER_LEVEL, long rooms_id, long roomtypes_id, String name, boolean ispublic, String comment){
		try {
			if (AuthLevelmanagement.getInstance().checkModLevel(USER_LEVEL)){
				
				//TODO: testen
				if (this.checkUserOrgRoom(user_id, rooms_id)){
					
					Rooms r = this.getRoomById(rooms_id);
					r.setComment(comment);
					r.setIspublic(ispublic);
					r.setName(name);
					r.setRoomtype(this.getRoomTypesById(roomtypes_id));
					r.setUpdatetime(new Date());
					Object idf = HibernateUtil.createSession();
					Session session = HibernateUtil.getSession();
					Transaction tx = session.beginTransaction();
					session.update(r);
					tx.commit();
					HibernateUtil.closeSession(idf);
				}
			}
		} catch (HibernateException ex) {
			log.error("[updateRoom] "+ex);
		} catch (Exception ex2) {
			log.error("[updateRoom] "+ex2);
		}
		return null;
	}
	
	/**
	 * Update a Record in the rooms table
	 * @param rooms_id
	 * @param roomtypes_id
	 * @param name
	 * @param ispublic
	 * @param comment
	 * @return
	 */
	public Rooms updateRoom(long USER_LEVEL, long rooms_id, long roomtypes_id, String name, boolean ispublic, String comment){
		try {
			if (AuthLevelmanagement.getInstance().checkAdminLevel(USER_LEVEL)){
				Rooms r = this.getRoomById(rooms_id);
				r.setComment(comment);
				r.setIspublic(ispublic);
				r.setName(name);
				r.setRoomtype(this.getRoomTypesById(roomtypes_id));
				r.setUpdatetime(new Date());
				Object idf = HibernateUtil.createSession();
				Session session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				session.update(r);
				tx.commit();
				HibernateUtil.closeSession(idf);
			}
		} catch (HibernateException ex) {
			log.error("[updateRoom] "+ex);
		} catch (Exception ex2) {
			log.error("[updateRoom] "+ex2);
		}
		return null;
	}
	
	/**
	 * delete all Rooms_Organisations and Room by a given room_id
	 * @param rooms_id
	 */
	public void deleteRoomById(long USER_LEVEL, long rooms_id){
		try {
			if (AuthLevelmanagement.getInstance().checkAdminLevel(USER_LEVEL)){
				this.deleteAllRoomsOrganisationOfRoom(rooms_id);
				this.deleteRoom(this.getRoomById(rooms_id));
			}
		} catch (HibernateException ex) {
			log.error("[deleteRoomById] "+ex);
		} catch (Exception ex2) {
			log.error("[deleteRoomById] "+ex2);
		}
	}
	
	/**
	 * deletes a Room by given Room-Object
	 * @param r
	 */
	public void deleteRoom(Rooms r){
		try {			
			r.setDeleted("true");
			r.setUpdatetime(new Date());
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.update(r);
			tx.commit();
			HibernateUtil.closeSession(idf);
		} catch (HibernateException ex) {
			log.error("[deleteRoomsOrganisation] "+ex);
		} catch (Exception ex2) {
			log.error("[deleteRoomsOrganisation] "+ex2);
		}
	}
	
	/**
	 * delete all Rooms_Organisation by a rooms_id
	 * @param rooms_id
	 */
	public void deleteAllRoomsOrganisationOfRoom(long rooms_id){
		try {
			List ll = this.getRoomsOrganisationByRoomsId(rooms_id);
			for (Iterator it = ll.iterator(); it.hasNext();){
				Rooms_Organisation rOrg = (Rooms_Organisation) it.next();
				this.deleteRoomsOrganisation(rOrg);
			}
		} catch (HibernateException ex) {
			log.error("[deleteAllRoomsOrganisationOfRoom] "+ex);
		} catch (Exception ex2) {
			log.error("[deleteAllRoomsOrganisationOfRoom] "+ex2);
		}
	}	
	
	/**
	 * Delete all room of a given Organisation
	 * @param organisation_id
	 */
	public void deleteAllRoomsOrganisationOfOrganisation(long organisation_id){
		try {
			List ll = this.getRoomsOrganisationByOrganisationId(3,organisation_id);
			for (Iterator it = ll.iterator(); it.hasNext();){
				Rooms_Organisation rOrg = (Rooms_Organisation) it.next();
				this.deleteRoomsOrganisation(rOrg);
			}
		} catch (HibernateException ex) {
			log.error("[deleteAllRoomsOfOrganisation] "+ex);
		} catch (Exception ex2) {
			log.error("[deleteAllRoomsOfOrganisation] "+ex2);
		}
	}
	
	/**
	 * Delete a Rooms_Organisation by its id
	 * @param rooms_organisation_id
	 */
	public void deleteRoomsOrganisationByID(long rooms_organisation_id){
		try {
			Rooms_Organisation rOrg = this.getRoomsOrganisationById(rooms_organisation_id);
			this.deleteRoomsOrganisation(rOrg);
		} catch (HibernateException ex) {
			log.error("[deleteRoomsOrganisationByID] "+ex);
		} catch (Exception ex2) {
			log.error("[deleteRoomsOrganisationByID] "+ex2);
		}
	}
	
	/**
	 * delete a Rooms_Organisation-Object
	 * @param rOrg
	 */
	public void deleteRoomsOrganisation(Rooms_Organisation rOrg){
		try {			
			rOrg.setDeleted("true");
			rOrg.setUpdatetime(new Date());
			Object idf = HibernateUtil.createSession();
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.update(rOrg);
			tx.commit();
			HibernateUtil.closeSession(idf);
		} catch (HibernateException ex) {
			log.error("[deleteRoomsOrganisation] "+ex);
		} catch (Exception ex2) {
			log.error("[deleteRoomsOrganisation] "+ex2);
		}
	}
	
}
