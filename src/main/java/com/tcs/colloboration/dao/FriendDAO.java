package com.tcs.colloboration.dao;

import java.util.List;

import com.tcs.colloboration.model.Friend;
import com.tcs.colloboration.model.User;

public interface FriendDAO {
	public List<Friend> showFriendList(String emailID);
	public List<Friend> showPendingRequest(String emailID);
	public List<User> showSuggestedFriend(String emailID);
	
	public boolean sendFriendRequest(Friend friend);
	public boolean acceptFriendRequest(int friendID);
	public boolean deleteFriendRequest(int friendID);

}
