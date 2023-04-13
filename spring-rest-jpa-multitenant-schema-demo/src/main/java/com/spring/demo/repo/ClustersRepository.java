package com.spring.demo.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.demo.entity.ClustersEntity;

/**
 * The Interface ClustersRepository.
 */
@Repository
public interface ClustersRepository extends JpaRepository<ClustersEntity, String> {

	
	/**
	 * Gets the by userid and clustername.
	 *
	 * @param userid the userid
	 * @param clustername the clustername
	 * @return the by userid and clustername
	 */
	@Query(value = "Select * FROM clusters WHERE userid = :userid AND clustername = :clustername", nativeQuery = true)
	ClustersEntity getByUseridAndClustername(String userid, String clustername);
	
	/**
	 * Delete by userid and clustername.
	 *
	 * @param userid the userid
	 * @param clustername the clustername
	 */
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM clusters WHERE userid = :userid AND clustername = :clustername", nativeQuery = true)
	void deleteByUseridAndClustername(String userid, String clustername);
	
}
