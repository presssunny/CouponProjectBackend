package com.sunny.LogIn;

import com.sunny.LogIn.Ex.TokenExpiredException;
import lombok.Getter;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ClientSession {
    @Getter
    private final UUID uuid;
    private long lastAccessMillis;
    private final int tokenExpirationMinutes;
    @Getter
    private final int type;


    private ClientSession(UUID uuid, long lastAccessMillis, int tokenExpirationMinutes, int type) {
        this.uuid = uuid;
        this.lastAccessMillis = lastAccessMillis;
        this.tokenExpirationMinutes = tokenExpirationMinutes;
        this.type = type;
    }

    public static ClientSession ofNow(UUID uuid, int tokenExpirationMinutes, int type) {
        return new ClientSession(uuid, System.currentTimeMillis(), tokenExpirationMinutes, type);
    }

    public boolean expired() {
        long idleTimeMillis = System.currentTimeMillis() - lastAccessMillis;
        long idleTimeMinutes = TimeUnit.MILLISECONDS.toMinutes(idleTimeMillis);
        return idleTimeMinutes > tokenExpirationMinutes;
    }

    public ClientSession accessOrThrow() {
        if ((expired())) {
            throw new TokenExpiredException("Token expired");
        }
        lastAccessMillis = System.currentTimeMillis();
        return this;
    }


}

