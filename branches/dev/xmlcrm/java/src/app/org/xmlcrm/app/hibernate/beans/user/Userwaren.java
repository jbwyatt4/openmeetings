package org.xmlcrm.app.hibernate.beans.user;

import java.util.Date;
import org.xmlcrm.app.hibernate.beans.shop.lieferarten;
import org.xmlcrm.app.hibernate.beans.shop.products;
import org.xmlcrm.app.hibernate.beans.shop.transstatus;
import org.xmlcrm.app.hibernate.beans.shop.zahlungsarten;

/**
 * 
 * @hibernate.class table="userwaren"
 *
 */
public class Userwaren {
	
	private Long userwaren_id;
	private Long user_id;
	private Long article_id;	
	private Date starttime;
	private Date updatetime;
	private Long status_id;
	private Integer menge;	
	private transstatus transstatus;	
	private Integer zahlungs_id;
	private Integer liefer_id;	
	private String comment;
	private String deleted;

	private zahlungsarten zahlungsarten;
	private lieferarten lieferarten;	
	private products products;
	
	public Userwaren() {
		super();
		// TODO Auto-generated constructor stub
	}

    /**
     * @hibernate.property
     *  column="article_id"
     *  type="long"
     */
	public Long getArticle_id() {
		return article_id;
	}
	public void setArticle_id(Long article_id) {
		this.article_id = article_id;
	}

    /**
     * @hibernate.property
     *  column="comment"
     *  type="string"
     */ 
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

    /**
     * @hibernate.property
     *  column="liefer_id"
     *  type="int"
     */
	public Integer getLiefer_id() {
		return liefer_id;
	}
	public void setLiefer_id(Integer liefer_id) {
		this.liefer_id = liefer_id;
	}

	public lieferarten getLieferarten() {
		return lieferarten;
	}
	public void setLieferarten(lieferarten lieferarten) {
		this.lieferarten = lieferarten;
	}

    /**
     * @hibernate.property
     *  column="menge"
     *  type="int"
     */
	public Integer getMenge() {
		return menge;
	}
	public void setMenge(Integer menge) {
		this.menge = menge;
	}

	public products getProducts() {
		return products;
	}
	public void setProducts(products products) {
		this.products = products;
	}

    /**
     * @hibernate.property
     *  column="status_id"
     *  type="long"
     */
	public Long getStatus_id() {
		return status_id;
	}
	public void setStatus_id(Long status_id) {
		this.status_id = status_id;
	}

	public transstatus getTransstatus() {
		return transstatus;
	}
	public void setTransstatus(transstatus transstatus) {
		this.transstatus = transstatus;
	}

    
    /**
     * @hibernate.property
     *  column="starttime"
     *  type="java.util.Date"
     */  	
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
    
    /**
     * @hibernate.property
     *  column="updatetime"
     *  type="java.util.Date"
     */  	
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
    /**
     * @hibernate.property
     *  column="deleted"
     *  type="string"
     */	
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

    /**
     * @hibernate.property
     *  column="user_id"
     *  type="long"
     */ 
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
    
    /**
     * 
     * @hibernate.id
     *  column="userwaren_id"
     *  generator-class="increment"
     */ 
	public Long getUserwaren_id() {
		return userwaren_id;
	}
	public void setUserwaren_id(Long userwaren_id) {
		this.userwaren_id = userwaren_id;
	}

    /**
     * @hibernate.property
     *  column="zahlungs_id"
     *  type="int"
     */ 
	public Integer getZahlungs_id() {
		return zahlungs_id;
	}
	public void setZahlungs_id(Integer zahlungs_id) {
		this.zahlungs_id = zahlungs_id;
	}

	public zahlungsarten getZahlungsarten() {
		return zahlungsarten;
	}
	public void setZahlungsarten(zahlungsarten zahlungsarten) {
		this.zahlungsarten = zahlungsarten;
	}

}
