package com.cpa.entity;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserStatistics {
	long noOfEntries;

	int noOfUniqueUsers;

	List<User> userList;

	public long getNoOfEntries() {
		return noOfEntries;
	}

	public void setNoOfEntries(long totalCount) {
		this.noOfEntries = totalCount;
	}

	public int getNoOfUniqueUsers() {
		return noOfUniqueUsers;
	}

	public void setNoOfUniqueUsers(int noOfUniqueUsers) {
		this.noOfUniqueUsers = noOfUniqueUsers;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

}
