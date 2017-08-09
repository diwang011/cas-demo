package com.next.cas.db.service;

import com.next.cas.db.model.User;

public interface UserService
{

    User query(String username);

}
