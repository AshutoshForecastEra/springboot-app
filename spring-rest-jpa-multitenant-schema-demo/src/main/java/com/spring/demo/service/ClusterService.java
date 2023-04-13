package com.spring.demo.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.spring.demo.model.ClusterPojo;
import com.spring.demo.model.Response;

/**
 * The Interface ClusterService.
 */
@Component
public interface ClusterService {

	/**
	 * Gets the all clusters.
	 *
	 * @return the all clusters
	 */
	List<ClusterPojo> getAllClusters();
	
	/**
	 * Creates the cluster.
	 *
	 * @param clusterPojo the cluster pojo
	 * @return the response
	 */
	Response createCluster(ClusterPojo clusterPojo);
		
	/**
	 * Delete cluster.
	 *
	 * @param clusterName the cluster name
	 * @param userid the userid
	 * @return the response
	 */
	Response deleteCluster(String clusterName, String userid);
}
