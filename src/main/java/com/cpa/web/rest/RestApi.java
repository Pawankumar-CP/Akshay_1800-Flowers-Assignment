package com.cpa.web.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.cpa.entity.User;
import com.cpa.entity.UserStatistics;
import com.cpa.kafka.impl.KafkaProducer;
import com.cpa.service.UserService;
import com.cpa.service.impl.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = { "http://localhost" })
public class RestApi {

	@Autowired
	WebClient.Builder webClient;

	@Autowired
	UserService serviceJson;

	@Autowired
	KafkaProducer producer;

	@Autowired
	UserStatistics uw;

	@GetMapping(value = "/getFinalList")
	public UserStatistics finalList(@RequestParam(value = "index") int index) {
		try {
			System.out.println("method called");

			List<User> u = serviceJson.getAllUser();
			long totalCount = serviceJson.getCount(u);
			List<User> us = serviceJson.updateUser(index, u);
			int noOfUniqueUser = serviceJson.getUniqueCount(u);

			uw.setNoOfEntries(totalCount);
			uw.setNoOfUniqueUsers(noOfUniqueUser);
			uw.setUserList(us);

			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

			String modifyFinalData = ow.writeValueAsString(uw.getUserList());

			producer.sendMessage(modifyFinalData);

			return uw;
		} catch (Exception e) {
			// TODO Auto-generated catch block e.printStackTrace(); }
			e.printStackTrace();
		}
		return null;
	}
}
