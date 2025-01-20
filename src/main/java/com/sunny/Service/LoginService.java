package com.sunny.Service;

import com.sunny.LogIn.ClientSession;

public interface LoginService {

    ClientSession createSession(String email, String password, int type);

    String generateToken();}
