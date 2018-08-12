package com.mondiamedia.cleanup.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mondiamedia.cleanup.base.entity.CleanUpEntity;
import com.mondiamedia.cleanup.base.repo.CleanUpRepository;
import com.mondiamedia.cleanup.base.repo.PageNumberRepository;
import com.mondiamedia.cleanup.service.bo.DuplicateUser;
import com.mondiamedia.cleanup.user.entity.ExternalUserIdEntity;
import com.mondiamedia.cleanup.user.entity.UserEntity;
import com.mondiamedia.cleanup.user.repo.UserServiceRepository;

/**
 * @author Ali.Ismail
 *
 *will use this service to cleanUp all users that have matches with
 *same externelIDType and externelIDTypeValue in the same affiliateId
 */
/**
 * @author Ali.Ismail
 */
@Component
public abstract class CleanUpServiceAbstract {

    @Autowired
    protected UserServiceRepository userServiceDao;

    @Autowired
    protected CleanUpRepository cleanUpDao;

    @Autowired
    protected PageNumberRepository pageNum;

    /**
     * service to call from shell
     */
    public void cleanUpDuplicateUser() {
        final int pageNumber1 = pageNum.findOne(1L).getPageNum();
        System.out.println(pageNumber1);
        Page<UserEntity> users = findAll(pageNumber1);
        for (int i = pageNumber1 + 1; i <= users.getTotalPages(); i++) {
            for (final UserEntity userEntity : users) {
                final DuplicateUser[] duplicateUserMap = getOldUniqueUserIdentities(userEntity);
                checkOnDuplicateUserMap(userEntity, duplicateUserMap);
            }
            pageNum.update(i);
            users = findAll(i);
        }
    }

    @Transactional(readOnly = true)
    Page<UserEntity> findAll(final int pageNum) {
        final Pageable pageable = new PageRequest(pageNum, 100);
        return userServiceDao.findAll(pageable);
    }

    /**
     * @param userEntity
     * @param duplicateUserMap
     */
    private void checkOnDuplicateUserMap(final UserEntity userEntity, final DuplicateUser[] duplicateUserMap) {
        if (duplicateUserMap.length == 0) {
            createUniqueIdentites(userEntity);
        } else {
            mergeDuplicateUsers(userEntity, duplicateUserMap);
        }
    }
    protected void mergeDuplicateUsers(final UserEntity userEntity, final DuplicateUser[] duplicateUserArray) {
        final DuplicateUser originalDuplicateUser = duplicateUserArray[0];

        mergeUserEntityWithCleanupUserID(userEntity, originalDuplicateUser);

        for (int i = 1; i < duplicateUserArray.length; i++) {
            final UserEntity duplicatedUniqueUserEntity = userServiceDao.findByUserId(duplicateUserArray[i].getUserId());
            mergeUserEntityWithCleanupUserID(duplicatedUniqueUserEntity, originalDuplicateUser);
            cleanUpDao.deleteByUserId(duplicatedUniqueUserEntity.getUserId());
        }
    }

    protected abstract void mergeUserEntityWithCleanupUserID(UserEntity userEntity, DuplicateUser originalDuplicateUser);

    /**
     * @param userEntity
     * @param externalIds save the uniqueUserEntity in CleanUpEntity DB
     */
    protected void createUniqueIdentites(final UserEntity userEntity) {
        userEntity.getExternalUserIds().stream()//
        .map(externalUserIdEntity -> getNewUniqueUserIdentity(null,userEntity, externalUserIdEntity))//
        .forEach(uniqueUserIdentityEntity -> cleanUpDao.save(uniqueUserIdentityEntity));
    }

    /**
     * @param userEntity
     * @param externalIds search on uniqueUserEntities to get all duplicate users
     * @return list of UniqueuserEntities
     */
    private DuplicateUser[] getOldUniqueUserIdentities(final UserEntity userEntity) {
        // TODO impl set
        final Map<Long, DuplicateUser> duplicateUserMap = new HashMap<>();

        for (final ExternalUserIdEntity externalUserIdEntity : userEntity.getExternalUserIds()) {
            final CleanUpEntity uniqueUserIdentityEntity = getNewUniqueUserIdentity(null ,userEntity, externalUserIdEntity);
            final CleanUpEntity userIdentityEntity = cleanUpDao.findByAffiliateIdAndExternalUserTypeIdAndExternalUserTypeValue(
                    uniqueUserIdentityEntity.getAffiliateId(), uniqueUserIdentityEntity.getExternalUserTypeId(),
                    uniqueUserIdentityEntity.getExternalUserTypeValue());
            if (userIdentityEntity != null) {
                DuplicateUser duplicateUser = duplicateUserMap.get(userIdentityEntity.getUserId());
                if (duplicateUser == null) {
                    duplicateUser = new DuplicateUser();
                    duplicateUser.setUserId(userIdentityEntity.getUserId());
                    duplicateUser.getCleanUpEntitySet().addAll(cleanUpDao.findByUserId(userIdentityEntity.getUserId()));
                    duplicateUserMap.put(userIdentityEntity.getUserId(), duplicateUser);
                }
                duplicateUser.getCleanUpEntitySet().add(userIdentityEntity);
            }
        }
        return duplicateUserMap.values().toArray(new DuplicateUser[0]);
    }

    /**
     * @param duplicateUser
     * @param userEntity
     * @param externalUserIdEntity create new object of cleanup entity which refere to one user using one externalIdtype
     */
    protected CleanUpEntity getNewUniqueUserIdentity(final DuplicateUser duplicateUser, final UserEntity userEntity,
            final ExternalUserIdEntity externalUserIdEntity) {
        final CleanUpEntity uniqueUserIdentityEntity = new CleanUpEntity();
        uniqueUserIdentityEntity.setUserId(duplicateUser!= null ? duplicateUser.getUserId() : userEntity.getUserId());
        uniqueUserIdentityEntity.setAffiliateId(userEntity.getAffiliateId());
        uniqueUserIdentityEntity.setExternalUserType(externalUserIdEntity.getExternalUserIdTypeId());
        uniqueUserIdentityEntity.setExternalUserTypeValue(externalUserIdEntity.getUserId());
        return uniqueUserIdentityEntity;
    }

}
