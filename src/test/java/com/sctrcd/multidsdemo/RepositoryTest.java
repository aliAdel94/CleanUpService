package com.sctrcd.multidsdemo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mondiamedia.cleanup.MultiDsApplication;
import com.mondiamedia.cleanup.base.entity.CleanUpEntity;
import com.mondiamedia.cleanup.base.repo.CleanUpReportRepository;
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

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = "classpath:application-test.yml")
@ActiveProfiles("test")
@SpringApplicationConfiguration(classes = {MultiDsApplication.class})
public class RepositoryTest {

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
    CleanUpReportRepository cleanUpReportRepository;

    //    @Autowired
    //    KissAPIRepository kissAPIDao;


    private UserEntity testUser;

    private UserEntity testUser2;

    private UserEntity testUser3;

    private UserEntity testUser4;

    private UserEntity testUser5;

    private UserEntity testUser6;

    private UserEntity testUser7;

    private UserEntity testUser8;

    private UserEntity testUser9;

    private UserEntity testUser10;

    private UserEntity testUser11;

    private Subscription subEntity;

    private Wallet walletEntity;

    private CleanUpEntity cleanup;

    //    private KissAPI kissAPIEntity;

    private List<Long> UsersId = new ArrayList<>();

    private HashMap<String, Long> testUsersMap2 = new HashMap<>();

    private HashMap<String, Long> testUsersMap3 = new HashMap<>();

    private HashMap<String, Long> testUsersMap4 = new HashMap<>();

    private HashMap<String, Long> testUsersMap1 = new HashMap<>();

    private HashMap<String, Long> testUsersMap5 = new HashMap<>();

    @Autowired
    private ExternalUserIdDao externalUserIdDao;
    //
    // @Autowired
    //    private KissAPIRepository kissAPIRepository;

    boolean insertUsersDone = false;


    private void generateCaseFour() {
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

    private void generateCaseThree() {
        testUser4 = new UserEntity(5L, null);
        testUser4.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.MSISDN, "test-msisdn"));
        testUser4.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.COOKIE_ID, "test-cookie-id"));
        testUser4.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.UUID, "test-uuid4"));

        // save user
        userServiceDao.save(testUser4);
        //save user externalIds
        externalUserIdDao.save(testUser4.getExternalUserIds());
        Long testUserId = testUser4.getUserId();
        createDataForUser(testUserId.toString());
        UsersId.add(testUserId);
        testUsersMap2.put("testUser4", testUserId);

        testUser5 = new UserEntity(5L, null);
        testUser5.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.IMEI, "test-imei"));
        testUser5.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.MM_LOGIN_EMAIL, "test-mm-login-email"));
        testUser5.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.UUID, "test-uuid5"));

        // save user
        userServiceDao.save(testUser5);
        //save user externalIds
        externalUserIdDao.save(testUser5.getExternalUserIds());
        testUserId = testUser5.getUserId();
        createDataForUser(testUserId.toString());
        UsersId.add(testUserId);
        testUsersMap2.put("testUser5", testUserId);

        testUser6 = new UserEntity(5L, null);
        testUser6.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.IMEI, "test-imei"));
        testUser6.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.MSISDN, "test-msisdn"));
        testUser6.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.UUID, "test-uuid6"));

        // save user
        userServiceDao.save(testUser6);
        //save user externalIds
        externalUserIdDao.save(testUser6.getExternalUserIds());
        testUserId = testUser6.getUserId();
        createDataForUser(testUserId.toString());
        UsersId.add(testUserId);
        testUsersMap2.put("testUser6", testUserId);

        testee.cleanUpDuplicateUser();
    }

    private void generateCaseTwo() {
        testUser2 = new UserEntity(10L, null);

        testUser2.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.COOKIE_ID, "test-cookie-id1"));
        testUser2.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.MSISDN, "test-msisdn1"));
        testUser2.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.UUID, "test-uuid1"));
        // save user
        userServiceDao.save(testUser2);
        //save user externalIds
        externalUserIdDao.save(testUser2.getExternalUserIds());
        Long testUserId = testUser2.getUserId();
        createDataForUser(testUserId.toString());
        testUsersMap3.put("testUser2", testUserId);

        testUser7 = new UserEntity(10L, null);

        testUser7.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.COOKIE_ID, "test-cookie-id2"));
        testUser7.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.MSISDN, "test-msisdn2"));
        testUser7.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.UUID, "test-uuid2"));
        // save user
        userServiceDao.save(testUser7);
        //save user externalIds
        externalUserIdDao.save(testUser7.getExternalUserIds());
        testUserId = testUser7.getUserId();
        createDataForUser(testUserId.toString());
        testUsersMap3.put("testUser7", testUserId);

        testee.cleanUpDuplicateUser();
    }

    private void generateCaseOne() {
        testUser = new UserEntity(10L, null);

        testUser.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.COOKIE_ID, "test-cookie-id"));
        testUser.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.MSISDN, "test-msisdn"));
        testUser.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.UUID, "test-uuid"));
        // save user
        userServiceDao.save(testUser);
        //save user externalIds
        externalUserIdDao.save(testUser.getExternalUserIds());
        Long testUserId = testUser.getUserId();
        createDataForUser(testUserId.toString());
        UsersId.add(testUserId);
        testUsersMap1.put("testUser", testUserId);

        testUser3 = new UserEntity(10L, null);
        testUser3.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.MSISDN, "test-msisdn"));
        testUser3.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.IMSI, "test-imsi-3"));

        userServiceDao.save(testUser3);
        externalUserIdDao.save(testUser3.getExternalUserIds());
        testUserId = testUser3.getUserId();
        createDataForUser(testUser3.getUserId().toString());
        UsersId.add(testUser3.getUserId());
        testUsersMap1.put("testUser3", testUserId);

        testee.cleanUpDuplicateUser();
    }


    private void generateReportCaseOne() {
        testUser10 = new UserEntity(10L, null);

        testUser10.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.COOKIE_ID, "test-cookie-id"));
        testUser10.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.MSISDN, "test-msisdn10"));
        testUser10.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.UUID, "test-uuid10"));
        // save user
        userServiceDao.save(testUser10);
        //save user externalIds
        externalUserIdDao.save(testUser10.getExternalUserIds());
        Long testUserId = testUser10.getUserId();
        createDataForUser(testUserId.toString());
        UsersId.add(testUserId);
        testUsersMap5.put("testUser10", testUserId);

        testUser11 = new UserEntity(10L, null);
        testUser11.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.MSISDN, "test-msisdn"));
        testUser11.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.IMSI, "test-imsi-3"));

        userServiceDao.save(testUser11);
        externalUserIdDao.save(testUser11.getExternalUserIds());
        testUserId = testUser11.getUserId();
        createDataForUser(testUser11.getUserId().toString());
        UsersId.add(testUser11.getUserId());
        testUsersMap5.put("testUser11", testUserId);

        testUser = new UserEntity(10L, null);

        testUser.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.COOKIE_ID, "test-cookie-id"));
        testUser.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.MSISDN, "test-msisdn"));
        testUser.addExternalUserId(new ExternalUserIdEntity(ExternalUserIdType.UUID, "test-uuid"));
        // save user
        userServiceDao.save(testUser);
        //save user externalIds
        externalUserIdDao.save(testUser.getExternalUserIds());
        testUserId = testUser.getUserId();
        createDataForUser(testUserId.toString());
        UsersId.add(testUserId);
        testUsersMap5.put("testUser", testUserId);

        testeee.cleanUpDuplicateUser();
    }
    /**
     *
     */
    private void createDataForUser(final String userId) {
        subEntity = new Subscription();
        subEntity.setUserId(userId);
        subscriptionDao.save(subEntity);

        // create wallet for user
        walletEntity = new Wallet();
        walletEntity.setUserId(userId);
        walletDao.save(walletEntity);


        //        final Fongo fongo = new Fongo("mongo server 1");
        //
        //        // once you have a DB instance, you can interact with it
        //        // just like you would with a real one.
        //        final DB db = fongo.getDB("mydb");
        //        final DBCollection collection = db.getCollection("kissapi");
        // create kissApi for user
        //        kissAPIEntity = new KissAPI();
        //        kissAPIEntity.setOwner(userId);
        //        kissAPIDao.save(kissAPIEntity);
    }

    //    @Before
    //    public void callCleanUpService(){
    //        if (insertUsersDone) {
    //            return;
    //        }
    //
    //    }

    @Test
    public void cleanUpTest2DuplicateUsersInMsisdn_noDuplicate() {
        generateCaseTwo();
        final List<UserEntity> users = userServiceDao.findAll();
        assertNotNull(users);
        assertEquals(2, users.size());
        assertEquals(testUsersMap3.get("testUser2").longValue(), userServiceDao.findByUserId(testUsersMap3.get("testUser2").longValue()).getUserId().longValue());
        assertNotNull(userServiceDao.findByUserId(testUsersMap3.get("testUser7").longValue()));
        assertEquals(3, userServiceDao.findByUserId(testUsersMap3.get("testUser2").longValue()).getExternalUserIds().size());
        assertEquals(3, userServiceDao.findByUserId(testUsersMap3.get("testUser7").longValue()).getExternalUserIds().size());
        assertNotNull(subscriptionDao.findByUserId(testUsersMap3.get("testUser2").toString()));
        assertNotNull(subscriptionDao.findByUserId(testUsersMap3.get("testUser7").toString()));
        assertNotNull(walletDao.findByUserId(testUsersMap3.get("testUser2").toString()));
        assertNotNull(walletDao.findByUserId(testUsersMap3.get("testUser7").toString()));

        userServiceDao.deleteAll();
        cleanUpDao.deleteAll();
        subscriptionDao.deleteAll();
        walletDao.deleteAll();
        //kissAPIDao.deleteAll();
    }

    @Test
    public void cleanUpTest2DuplicateUsersInMsisdn_noConflictReport() {
        generateReportCaseOne();
        final List<UserEntity> users = userServiceDao.findAll();
        cleanUpReportRepository.findAll();
        assertNotNull(users);
        assertEquals(3, users.size());
        final String temp = users.get(2).getUserId().toString() + "_" + users.get(1).getUserId().toString() + "_";
        assertEquals(users.get(0).getUserId().toString(), cleanUpReportRepository.findAll().get(0).getUserId());
        assertEquals(temp, cleanUpReportRepository.findAll().get(0).getDuplictaeUsersId());
        assertEquals(3, subscriptionDao.findAll().size());
        assertEquals(3, walletDao.findAll().size());

        userServiceDao.deleteAll();
        cleanUpDao.deleteAll();
        subscriptionDao.deleteAll();
        walletDao.deleteAll();
        //kissAPIDao.deleteAll();

    }

    @Test
    public void cleanUpTest2DuplicateUsersInMsisdn_noConflict() {
        generateCaseOne();
        final List<UserEntity> users = userServiceDao.findAll();
        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals(testUsersMap1.get("testUser").longValue(), userServiceDao.findByUserId(testUsersMap1.get("testUser").longValue()).getUserId().longValue());
        assertEquals(4, userServiceDao.findByUserId(testUsersMap1.get("testUser").longValue()).getExternalUserIds().size());
        assertNull(subscriptionDao.findByUserId(testUsersMap1.get("testUser3").toString()));
        assertEquals(2, subscriptionDao.findAllSubscriptionForUserId(testUsersMap1.get("testUser").toString()).size());
        assertNull(walletDao.findByUserId(testUsersMap1.get("testUser3").toString()));
        assertEquals(2, walletDao.findAllWalletForUserId(testUsersMap1.get("testUser").toString()).size());

        userServiceDao.deleteAll();
        cleanUpDao.deleteAll();
        subscriptionDao.deleteAll();
        walletDao.deleteAll();
        //kissAPIDao.deleteAll();

    }

    @Test
    public void cleanUpTest3DuplicateUsersInMsisdn_NoConflict() {
        generateCaseThree();
        final List<UserEntity> users = userServiceDao.findAll();
        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals(testUsersMap2.get("testUser4").longValue(), userServiceDao.findByUserId(testUsersMap2.get("testUser4").longValue()).getUserId().longValue());
        assertNull(userServiceDao.findByUserId(testUsersMap2.get("testUser6").longValue()));
        assertNull(userServiceDao.findByUserId(testUsersMap2.get("testUser5").longValue()));
        assertEquals(7, userServiceDao.findByUserId(testUsersMap2.get("testUser4").longValue()).getExternalUserIds().size());
        assertEquals(3, subscriptionDao.findAllSubscriptionForUserId(testUsersMap2.get("testUser4").toString()).size());
        assertNull(subscriptionDao.findByUserId(testUsersMap2.get("testUser6").toString()));
        assertNull(subscriptionDao.findByUserId(testUsersMap2.get("testUser5").toString()));
        assertEquals(3, walletDao.findAllWalletForUserId(testUsersMap2.get("testUser4").toString()).size());
        assertNull(walletDao.findByUserId(testUsersMap2.get("testUser6").toString()));
        assertNull(walletDao.findByUserId(testUsersMap2.get("testUser5").toString()));
        assertEquals(0 , cleanUpDao.findByUserId(testUsersMap2.get("testUser6").longValue()).size());

        userServiceDao.deleteAll();
        cleanUpDao.deleteAll();
        subscriptionDao.deleteAll();
        walletDao.deleteAll();
        //kissAPIDao.deleteAll();
    }

    @Test
    public void cleanUpTest2DuplicateUsersInMsisdn_Conflict() {
        generateCaseFour();
        final List<UserEntity> users = userServiceDao.findAll();
        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals(testUsersMap4.get("testUser8").longValue(), userServiceDao.findByUserId(testUsersMap4.get("testUser8").longValue()).getUserId().longValue());
        assertNull(userServiceDao.findByUserId(testUsersMap4.get("testUser9").longValue()));
        //FIXME
        assertEquals(5, userServiceDao.findByUserId(testUsersMap4.get("testUser8").longValue()).getExternalUserIds().size());
        assertEquals(2, subscriptionDao.findAllSubscriptionForUserId(testUsersMap4.get("testUser8").toString()).size());
        assertNull(subscriptionDao.findByUserId(testUsersMap4.get("testUser9").toString()));
        assertEquals(2, walletDao.findAllWalletForUserId(testUsersMap4.get("testUser8").toString()).size());
        assertNull(walletDao.findByUserId(testUsersMap4.get("testUser9").toString()));
        assertEquals(0,cleanUpDao.findByUserId(testUsersMap4.get("testUser9").longValue()).size());

        userServiceDao.deleteAll();
        cleanUpDao.deleteAll();
        subscriptionDao.deleteAll();
        walletDao.deleteAll();
        //kissAPIDao.deleteAll();
    }


}
