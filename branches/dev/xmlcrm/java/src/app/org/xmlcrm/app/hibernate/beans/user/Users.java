package org.xmlcrm.app.hibernate.beans.user;

import java.util.Date;
import org.xmlcrm.app.hibernate.beans.adresses.Emails;

/**
 * 
 * @hibernate.class table="users"
 *
 */
public class Users {
	
	private Long user_id;
	private Long adresses_id;
	private Date age;
	private Integer availible;
	private String firstname;
	private Date lastlogin;
	private String lastname;
	private Long lasttrans;
	private Long level_id;
	private String login;
	private String password;
	private Date regdate;
	private Integer status;
	private Integer title_id;
	private Date starttime;
	private Date updatetime;
	private String pictureuri;
	private Boolean deleted;
	private Long language_id;
	
	private Userlevel userlevel;
    private Userdata userdata[];
	private Userdata rechnungsaddressen;
	private Userdata lieferadressen;
    private Usergroups[] usergroups; 
    
    
    //TODO: Fehlende adressids für rechnung und lieferadresse

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    /**
     * @hibernate.property
     *  column="adresses_id"
     *  type="long"
     */  
	public Long getAdresses_id() {
		return adresses_id;
	}
	public void setAdresses_id(Long adresses_id) {
		this.adresses_id = adresses_id;
	}
    
    /**
     * @hibernate.property
     *  column="age"
     *  type="java.util.Date"
     */  
	public Date getAge() {
		return age;
	}
	public void setAge(Date age) {
		this.age = age;
	}
    
    /**
     * @hibernate.property
     *  column="availible"
     *  type="int"
     */  
	public Integer getAvailible() {
		return availible;
	}
	public void setAvailible(Integer availible) {
		this.availible = availible;
	}

    /**
     * @hibernate.property
     *  column="firstname"
     *  type="string"
     */ 	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
    
    /**
     * @hibernate.property
     *  column="lastlogin"
     *  type="java.util.Date"
     */ 
	public Date getLastlogin() {
		return lastlogin;
	}
	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}

    /**
     * @hibernate.property
     *  column="lastname"
     *  type="string"
     */ 
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
    
    /**
     * @hibernate.property
     *  column="lasttrans"
     *  type="long"
     */ 
	public Long getLasttrans() {
		return lasttrans;
	}
	public void setLasttrans(Long lasttrans) {
		this.lasttrans = lasttrans;
	}
    
    /**
     * @hibernate.property
     *  column="level_id"
     *  type="long"
     */ 
	public Long getLevel_id() {
		return level_id;
	}
	public void setLevel_id(Long level_id) {
		this.level_id = level_id;
	}
	
	public Userdata getLieferadressen() {
		return lieferadressen;
	}
	public void setLieferadressen(Userdata lieferadressen) {
		this.lieferadressen = lieferadressen;
	}

    /**
     * @hibernate.property
     *  column="login"
     *  type="string"
     */ 
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

    /**
     * @hibernate.property
     *  column="password"
     *  type="string"
     */ 	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Userdata getRechnungsaddressen() {
		return rechnungsaddressen;
	}
	public void setRechnungsaddressen(Userdata rechnungsaddressen) {
		this.rechnungsaddressen = rechnungsaddressen;
	}
    
    /**
     * @hibernate.property
     *  column="regdate"
     *  type="java.util.Date"
     */ 
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
    
    /**
     * @hibernate.property
     *  column="status"
     *  type="int"
     */ 
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
    
    /**
     * @hibernate.property
     *  column="title_id"
     *  type="int"
     */
	public Integer getTitle_id() {
		return title_id;
	}
	public void setTitle_id(Integer title_id) {
		this.title_id = title_id;
	}
	
    /**
     * 
     * @hibernate.id
     *  column="user_id"
     *  generator-class="increment"
     */ 	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Userdata[] getUserdata() {
		return userdata;
	}
	public void setUserdata(Userdata[] userdata) {
		this.userdata = userdata;
	}
	public Usergroups[] getUsergroups() {
		return usergroups;
	}
	public void setUsergroups(Usergroups[] usergroups) {
		this.usergroups = usergroups;
	}
	
	public Userlevel getUserlevel() {
		return userlevel;
	}
	public void setUserlevel(Userlevel userlevel) {
		this.userlevel = userlevel;
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
     *  type="boolean"
     */	
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

    /**
     * @hibernate.property
     *  column="pictureuri"
     *  type="string"
     */	
	public String getPictureuri() {
		return pictureuri;
	}
	public void setPictureuri(String pictureuri) {
		this.pictureuri = pictureuri;
	}

    /**
     * @hibernate.property
     *  column="language_id"
     *  type="long"
     */	
	public Long getLanguage_id() {
		return language_id;
	}
	public void setLanguage_id(Long language_id) {
		this.language_id = language_id;
	}
	
	
    
	
}
