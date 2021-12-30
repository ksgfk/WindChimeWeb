package com.github.ksgfk.windchimeweb.service;

import com.github.ksgfk.windchimeweb.entity.User;
import com.github.ksgfk.windchimeweb.mapper.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class UserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    private UserMapper users;

    public static class UserCache {
        private Instant loginTime;
        private User user;

        public UserCache(User user) {
            this.user = user;
            loginTime = Instant.now();
        }

        public Instant getLoginTime() {
            return loginTime;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public User getUser() {
            return user;
        }

        public void setLoginTime(Instant loginTime) {
            this.loginTime = loginTime;
        }
    }

    @Value("600")
    private int expireSecond;
    private final ConcurrentMap<UUID, UserCache> userCache;
    private final ConcurrentMap<String, UUID> accIndex;

    public UserService() {
        userCache = new ConcurrentHashMap<>();
        accIndex = new ConcurrentHashMap<>();
    }

    public Optional<UUID> login(User user) {
        if (accIndex.containsKey(user.getAccount())) {
            return Optional.of(accIndex.get(user.getAccount()));
        }
        UUID uuid = UUID.randomUUID();
        userCache.put(uuid, new UserCache(user));
        accIndex.put(user.getAccount(), uuid);
        logger.info("user {} login", user.getAccount());
        return Optional.of(uuid);
    }

    public boolean logout(UUID uuid) {
        UserCache cache = userCache.remove(uuid);
        if (cache == null) {
            return false;
        }
        accIndex.remove(cache.getUser().getAccount());
        logger.info("user {} logout", cache.getUser().getAccount());
        return true;
    }

    public Optional<User> getUser(UUID uuid) {
        UserCache user = userCache.get(uuid);
        if (user == null) {
            return Optional.empty();
        }
        User old = user.getUser();
        User refresh = users.selectById(old.getId());
        user.setUser(refresh);
        user.setLoginTime(Instant.now());
        return Optional.of(refresh);
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    public void autoLogout() {
        Instant now = Instant.now();
        List<UUID> willRemove = new ArrayList<>();
        synchronized (userCache) {
            synchronized (accIndex) {
                for (var p : userCache.entrySet()) {
                    Duration duration = Duration.between(p.getValue().getLoginTime(), now);
                    if (Math.abs(duration.toSeconds()) >= expireSecond) {
                        willRemove.add(p.getKey());
                    }
                }
                for (UUID uuid : willRemove) {
                    UserCache cache = userCache.remove(uuid);
                    accIndex.remove(cache.getUser().getAccount());
                }
            }
        }
        logger.info("clear {} expired user", willRemove.size());
    }
}
