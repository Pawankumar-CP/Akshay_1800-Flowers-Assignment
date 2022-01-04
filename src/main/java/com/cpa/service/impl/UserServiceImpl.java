package com.cpa.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

import com.cpa.entity.User;
import com.cpa.service.UserService;

import reactor.core.publisher.Flux;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	WebClient.Builder webClient;
	
	@Value(value = "${db.json.url}")
	String url;

	public List<User> getAllUser() throws Exception {

		Flux<User> userFlux = webClient.build().get().uri(url).retrieve()
				.bodyToFlux(User.class);

		List<User> user = userFlux.collect(Collectors.toList()).share().block();

		return user;

	}

	public long getCount(List<User> us) throws Exception {

		long count = us.stream().count();
		return count;

	}

	public Integer getUniqueCount(List<User> user) throws Exception {

		Set<User> un = new LinkedHashSet<>();
		List<Integer> unCount = user.stream().map(u -> u.getUserId()).distinct().collect(Collectors.toList());

		return unCount.size();

	}

	public List<User> updateUser(int index, List<User> us) throws Exception {

		String str = "1800-flowers";

		List<User> finalUser = new ArrayList<User>(us.size());
		if ((index <= us.size()) && (index >= 1)) {

			String bodyContent = us.get(index).getBody();
			String titleContent = us.get(index).getTitle();

			String replaceTitle = titleContent.replace(titleContent, str);
			String replaceBody = bodyContent.replace(bodyContent, str);

			us.get(index).setBody(replaceBody);
			us.get(index).setTitle(replaceTitle);

			for (User i : us) {
				finalUser.add(i);
			}

		}
		return finalUser;

	}
}
