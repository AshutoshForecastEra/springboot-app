package com.spring.demo.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.demo.model.ClusterPojo;
import com.spring.demo.model.Response;
import com.spring.demo.service.ClusterService;

/**
 * The Class ClusterController.
 */
@RestController
public class ClusterController {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ClusterController.class);
	
	/** The cluster service. */
	@Autowired
	private ClusterService clusterService;
	
	/**
	 * Gets the all clusters.
	 *
	 * @return the all clusters
	 */
	@GetMapping(path = "/clusters", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Response> getAllClusters(){
		LOGGER.info("getAllClusters invoked..");
		List<ClusterPojo> clusters = clusterService.getAllClusters();
		LOGGER.info("All clusters: {}", clusters);
		final Response resp = new Response("OK", "Clusters retrieved");
		resp.setAdditionalProperty("clusters", clusters);
		final URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(clusters).toUri();
		return ResponseEntity.created(location).body(resp); 
	}
	
	/**
	 * Creates the cluster.
	 *
	 * @param cluster the cluster
	 * @return the response entity
	 */
	@PostMapping(path = "/clusters/createCluster", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Response> createCluster(@RequestBody ClusterPojo cluster) {
		LOGGER.info("createCluster invoked for: '{}'", cluster);
		final Response resp = clusterService.createCluster(cluster);
		return new ResponseEntity<Response>(resp, HttpStatus.OK);
	}
	
	/**
	 * Delete cluster.
	 *
	 * @param userid from request header the userid
	 * @param clustername the clustername
	 * @return the response entity
	 */
	@DeleteMapping(path = "/clusters/deleteCluster/{clustername}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Response> deleteCluster(@RequestHeader("userid") String userid,
			@PathVariable String clustername) {
		LOGGER.info("delete cluster- '{}' invoked for userid: {}", clustername, userid);
		final Response resp = clusterService.deleteCluster(clustername, userid);
		return new ResponseEntity<Response>(resp, HttpStatus.OK);
	}
}
