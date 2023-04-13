package com.spring.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.demo.entity.ClustersEntity;
import com.spring.demo.model.ClusterPojo;
import com.spring.demo.model.Response;
import com.spring.demo.repo.ClustersRepository;
import com.spring.demo.service.ClusterService;

/**
 * The Class ClusterServiceImpl.
 */
@Component
public class ClusterServiceImpl implements ClusterService {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ClusterServiceImpl.class);
	
	/** The clusters repository. */
	@Autowired
	private ClustersRepository clustersRepository;

	/**
	 * Creates the cluster.
	 *
	 * @param clusterPojo the cluster pojo
	 * @return the response
	 */
	@Override
	public Response createCluster(ClusterPojo clusterPojo) {
		LOGGER.info("createCluster service invoked..");
		ClustersEntity entity = new ClustersEntity();
		BeanUtils.copyProperties(clusterPojo, entity);
		String userid = clusterPojo.getUserid();
		String clustername = clusterPojo.getClustername();
		ClustersEntity cluster = clustersRepository.getByUseridAndClustername(userid, clustername);
		Response resp = null;
		if (cluster != null && cluster.getUserid().equals(userid)
				&& cluster.getClustername().equals(clustername)) {
			resp = new Response(String.format("Cluster %s already exist for userid: %s.", clustername, userid), "Conflict");
		} else {
			clustersRepository.save(entity);
			resp = new Response("Cluster created successfully.", "Created");
		}
		LOGGER.info("createCluster service ended.");
		return resp;
	}

	/**
	 * Delete cluster.
	 *
	 * @param clusterName the cluster name
	 * @param userid the userid
	 * @return the response
	 */
	@Override
	public Response deleteCluster(String clusterName, String userid) {
		LOGGER.info("deleteCluster service invoked..");
		ClustersEntity entity = clustersRepository.getByUseridAndClustername(userid, clusterName);
		Response resp = null;
		if (entity != null && entity.getUserid().equals(userid) && entity.getClustername().equals(clusterName)) {
			clustersRepository.deleteByUseridAndClustername(userid, clusterName);
			resp = new Response(String.format("Cluster %s deleted for userid: %s.", clusterName, userid), "OK");
		} else {
			resp = new Response(String.format("Cluster %s not found for userid: %s.", clusterName, userid), "Not Found");
		}
		LOGGER.info("deleteCluster service ended.");
		return resp;
	}

	/**
	 * Gets the all clusters.
	 *
	 * @return the all clusters
	 */
	@Override
	public List<ClusterPojo> getAllClusters() {
		LOGGER.info("getAllClusters service invoked..");
		List<ClustersEntity> clusters = clustersRepository.findAll();
		List<ClusterPojo> clustersList = new ArrayList<>();
		for (ClustersEntity source : clusters) {
			ClusterPojo target = new ClusterPojo();
			BeanUtils.copyProperties(source, target);
			clustersList.add(target);
		}
		LOGGER.info("getAllClusters service ended.");
		return clustersList;
	}
}
