package com.spring.demo.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class ClustersEntity.
 */
@Entity
//@Table(name = "clusters", schema="web1")
@Table(name = "clusters")
public class ClustersEntity implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The clustername. */
	private String clustername;
	
	/** The clusterdescription. */
	private String clusterdescription;
	
	/** The userid. */
	@Id
	private String userid;
	
	/**
	 * Gets the clustername.
	 *
	 * @return the clustername
	 */
	public String getClustername() {
		return clustername;
	}
	
	/**
	 * Sets the clustername.
	 *
	 * @param clustername the new clustername
	 */
	public void setClustername(String clustername) {
		this.clustername = clustername;
	}
	
	/**
	 * Gets the clusterdescription.
	 *
	 * @return the clusterdescription
	 */
	public String getClusterdescription() {
		return clusterdescription;
	}
	
	/**
	 * Sets the clusterdescription.
	 *
	 * @param clusterdescription the new clusterdescription
	 */
	public void setClusterdescription(String clusterdescription) {
		this.clusterdescription = clusterdescription;
	}
	
	/**
	 * Gets the userid.
	 *
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}
	
	/**
	 * Sets the userid.
	 *
	 * @param userid the new userid
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(clusterdescription, clustername, userid);
	}
	
	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClustersEntity other = (ClustersEntity) obj;
		return Objects.equals(clusterdescription, other.clusterdescription)
				&& Objects.equals(clustername, other.clustername) && Objects.equals(userid, other.userid);
	}

}
