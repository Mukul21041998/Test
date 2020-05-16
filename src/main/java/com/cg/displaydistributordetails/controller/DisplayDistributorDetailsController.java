package com.cg.displaydistributordetails.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.displaydistributordetails.model.DisplayDistributorDetails;
import com.cg.displaydistributordetails.service.DisplayDistributorDetailsService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class DisplayDistributorDetailsController {

	@Autowired
	private DisplayDistributorDetailsService service;

	Logger logger = LoggerFactory.getLogger(DisplayDistributorDetailsController.class);

//	Get the date from the database on the basis of specific supplier id

	@GetMapping("/getdetails/{supplierId}")
	@HystrixCommand(fallbackMethod = "fallbackgetDetails")
	public List<DisplayDistributorDetails> getDistributorDetails(@PathVariable int supplierId) {
		logger.info("Getting distributor details");
		return service.getDistributorDetails(supplierId);

	}
	
	public List<DisplayDistributorDetails> fallbackgetDetails(@PathVariable int supplierId){
		logger.error("Operation failed");
		return  new ArrayList<>();
	}

}
