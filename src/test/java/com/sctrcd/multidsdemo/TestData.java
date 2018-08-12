package com.sctrcd.multidsdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mondiamedia.cleanup.base.entity.CleanUpEntity;
import com.mondiamedia.cleanup.base.repo.CleanUpRepository;
import com.mondiamedia.cleanup.service.CleanUpService;
import com.mondiamedia.cleanup.service.CleanUpServiceReport;
import com.mondiamedia.cleanup.subscription.entity.Subscription;
import com.mondiamedia.cleanup.subscription.repo.SubscriptionRepository;
import com.mondiamedia.cleanup.user.entity.ExternalUserIdEntity;
import com.mondiamedia.cleanup.user.entity.UserEntity;
import com.mondiamedia.cleanup.user.repo.ExternalUserIdDao;
import com.mondiamedia.cleanup.user.repo.UserServiceRepository;
import com.mondiamedia.cleanup.wallet.entity.Wallet;
import com.mondiamedia.cleanup.wallet.repo.WalletRepository;

import de.arvatomobile.services.user.userservice.client.ExternalUserIdType;

public class TestData {

    private UserEntity testUser;

    private UserEntity testUser2;

    private UserEntity testUser3;

    private UserEntity testUser4;

    private UserEntity testUser5;

    private UserEntity testUser6;

    private UserEntity testUser7;

    private UserEntity testUser8;

    private UserEntity testUser9;

    private Subscription subEntity;

    private Wallet walletEntity;

    private CleanUpEntity cleanup;

    // private KissAPI kissAPIEntity;

    private List<Long> UsersId = new ArrayList<>();

    private HashMap<String, Long> testUsersMap2 = new HashMap<>();

    private HashMap<String, Long> testUsersMap3 = new HashMap<>();

    private HashMap<String, Long> testUsersMap4 = new HashMap<>();

    private HashMap<String, Long> testUsersMap1 = new HashMap<>();

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    CleanUpService testee;

    @Autowired
    CleanUpServiceReport testeee;

    @Autowired
    CleanUpRepository cleanUpDao;

    @Autowired
    UserServiceRepository userServiceDao;

    @Autowired
    SubscriptionRepository subscriptionDao;

    @Autowired
    WalletRepository walletDao;

    @Autowired
    private ExternalUserIdDao externalUserIdDao;

    // @Autowired
    // private KissAPIRepository kissAPIRepository;

    protected void generateCaseFour() {
        testUser8 = new UserEntity(10L, null);

        testUser8.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.COOKIE_ID, "test-cookie-id8"));
        testUser8.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.MSISDN, "test-msisdn8"));
        testUser8.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.UUID, "test-uuid8"));
        // save user
        userServiceDao.save(testUser8);
        //save user externalIds
        externalUserIdDao.save(testUser8.getExternalUserIds());
        Long testUserId = testUser8.getUserId();
        createDataForUser(testUserId.toString());
        testUsersMap4.put("testUser8", testUserId);

        testUser9 = new UserEntity(10L, null);

        testUser9.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.COOKIE_ID, "test-cookie-id8"));
        testUser9.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.MSISDN, "test-msisdn9"));
        testUser9.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.UUID, "test-uuid9"));
        // save user
        userServiceDao.save(testUser9);
        //save user externalIds
        externalUserIdDao.save(testUser9.getExternalUserIds());
        testUserId = testUser9.getUserId();
        createDataForUser(testUserId.toString());
        testUsersMap4.put("testUser9", testUserId);

        testee.cleanUpDuplicateUser();
    }

    private void createDataForUser(final String userId) {
        subEntity = new Subscription();
        subEntity.setUserId(userId);
        subscriptionDao.save(subEntity);

        // create wallet for user
        walletEntity = new Wallet();
        walletEntity.setUserId(userId);
        walletDao.save(walletEntity);
    }
}
