package com.unitTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.cpa.entity.User;
import com.cpa.entity.UserStatistics;
import com.cpa.service.impl.UserServiceImpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
public class TestCase {

	@InjectMocks
	UserServiceImpl api;

	@Test
	public void getAllUserSuccess() {

		try {
			ObjectMapper mapper = new ObjectMapper();

			File configFile = new File(getClass().getResource("/static/resource.json").getFile());
			System.out.println(">>>>>>>>>" + configFile);
			List<User> userList = mapper.readValue(configFile, new TypeReference<List<User>>() {
			});

			when(api.getAllUser()).thenReturn(userList);

			UserStatistics respObj = new UserStatistics();
			respObj.setNoOfEntries(userList.size());
			int uniqueUser = api.getUniqueCount(userList);
			respObj.setNoOfUniqueUsers(uniqueUser);
			respObj.setUserList(userList);
			final List<User> response = api.updateUser(2, userList);
			assertNotNull(response);

			assertEquals(respObj.getNoOfEntries(), ((UserStatistics) response).getNoOfEntries());
			assertEquals(respObj.getNoOfUniqueUsers(), ((UserStatistics) response).getNoOfUniqueUsers());
			assertEquals(respObj.getUserList(), ((UserStatistics) response).getUserList());
		} catch (Exception e) {

		}

	}

	@Test
	public void getUserWhenListIsEmpty() {
		try {
			List<User> listOfUser = new ArrayList<>();
			when(api.getAllUser()).thenReturn(listOfUser);
			final List<User> response = api.updateUser(3, listOfUser);
			assertNull(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void modifyUserWhenListIsEmpty() {
		try {
			List<User> listOfUser = new ArrayList<>();

			final List<User> response = api.updateUser(3, listOfUser);

			assertNull(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void modifyUserWhenIndexIsNegative() {

		try {
			ObjectMapper mapper = new ObjectMapper();
			File configFile = new File(getClass().getResource("/static/User_1.json").getFile());

			List<User> listOfUser = mapper.readValue(configFile, new TypeReference<List<User>>() {
			});

			final List<User> response = api.updateUser(-1, listOfUser);

			assertNull(response);
		} catch (Exception e) {

		}

	}

	@Test
	public void tallyNoOfUsersTestWhenListIsEmpty() {

		try {
			List<User> listOfUser = new ArrayList<>();

			final Integer noOfUniqueUsers = (int) api.getCount(listOfUser);

			assertThat(noOfUniqueUsers).isZero();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
