package com.cpa.service;

import java.util.List;

import com.cpa.entity.User;

public interface UserService {

	public List<User> getAllUser() throws Exception;

	public long getCount(List<User> user) throws Exception;

	public Integer getUniqueCount(List<User> user) throws Exception;

	public List<User> updateUser(int index, List<User> us) throws Exception;

}
