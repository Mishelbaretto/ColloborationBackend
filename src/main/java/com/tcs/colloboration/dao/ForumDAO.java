package com.tcs.colloboration.dao;

import java.util.List;

import com.tcs.colloboration.model.Forum;

public interface ForumDAO {
	public boolean addForum(Forum forum);
	public boolean deleteForum(Forum forum);
	
	public List<Forum> listApprovedForums();
	public List<Forum> listAllForums();
	public boolean approveForum(Forum forum);
	public boolean rejectForum(Forum forum);
	public Forum getForum(int forumId);
	public boolean incLike(int forumId);

}
