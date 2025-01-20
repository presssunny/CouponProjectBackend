package com.sunny.LogIn;

import com.sunny.LogIn.Ex.NoClientSessionException;
import com.sunny.LogIn.Ex.NoPermissionToConnectException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SessionManager {
    private final Map<String, ClientSession> tokens;

    public UUID handleCompanyToken(String token) {
        ClientSession clientSession = tokens.get(token);

        if (clientSession == null) {
            throw new NoClientSessionException(String.format("no session for %S", token));
        }

        if (clientSession.getType() == 0) {
            throw new NoPermissionToConnectException("you don't have the permission to connect");
        }
        return clientSession.accessOrThrow()
                .getUuid();
    }

    public UUID handleCustomerToken(String token) {
        ClientSession clientSession = tokens.get(token);


        if (clientSession == null) {
            throw new NoClientSessionException(String.format("no session for %S", token));
        }

        if (clientSession.getType() == 1) {
            throw new NoPermissionToConnectException("you don't have the permission to connect");
        }
        return clientSession.accessOrThrow()
                .getUuid();
    }


}