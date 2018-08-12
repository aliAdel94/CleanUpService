package com.mondiamedia.cleanup.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mondiamedia.cleanup.base.entity.CleanUpEntity;
import com.mondiamedia.cleanup.service.bo.DuplicateUser;
import com.mondiamedia.cleanup.subscription.repo.SubscriptionRepository;
import com.mondiamedia.cleanup.user.entity.ExternalUserIdEntity;
import com.mondiamedia.cleanup.user.entity.UserEntity;
import com.mondiamedia.cleanup.user.repo.ExternalUserIdDao;
import com.mondiamedia.cleanup.wallet.repo.WalletRepository;

@Component
public class CleanUpService extends CleanUpServiceAbstract {

    @Autowired
    private SubscriptionRepository subscriptionEntityRepository;

    @Autowired
    private ExternalUserIdDao externalUserIdDao;

    @Autowired
    private WalletRepository walletDao;


    /**
     * @param userEntity
     * @param userEntity.getExternalUserIds()
     */
    @Override
    @Transactional
    protected void mergeUserEntityWithCleanupUserID(final UserEntity userEntity, final DuplicateUser duplicateUser) {
        final UserEntity originalUserEntity = userServiceDao.findByUserId(duplicateUser.getUserId());
        for (final ExternalUserIdEntity duplicatedExternalUserIdEntity : userEntity.getExternalUserIds()) {
            final CleanUpEntity uniqueUserIdentityEntity = getNewUniqueUserIdentity(duplicateUser, userEntity, duplicatedExternalUserIdEntity);
            final boolean added = duplicateUser.getCleanUpEntitySet().add(uniqueUserIdentityEntity);
            if (added) {
                // TODO clone new model mapper and set id with null
                final ExternalUserIdEntity newExternalUserIdEntity = new ModelMapper()//
                        .map(duplicatedExternalUserIdEntity, ExternalUserIdEntity.class);
                newExternalUserIdEntity.setExternalUserIdId(null);
                originalUserEntity.addExternalUserId(newExternalUserIdEntity);
                externalUserIdDao.save(newExternalUserIdEntity);
            }
        }
        cleanUpDao.save(duplicateUser.getCleanUpEntitySet());
        externalUserIdDao.save(originalUserEntity.getExternalUserIds());
        subscriptionEntityRepository.update(duplicateUser.getUserId().toString(), userEntity.getUserId().toString());
        walletDao.update(duplicateUser.getUserId().toString(), userEntity.getUserId().toString());
        // TODO make h2 database for mongoDb
        // kissAPIDaoImpl.update(userId.toString(), updatedUserId.toString());
        userServiceDao.delete(userEntity.getUserId());
    }
}
