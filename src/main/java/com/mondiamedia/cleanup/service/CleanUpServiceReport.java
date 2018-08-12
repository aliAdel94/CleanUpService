package com.mondiamedia.cleanup.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mondiamedia.cleanup.base.entity.CleanUpEntity;
import com.mondiamedia.cleanup.base.entity.CleanUpReportEntity;
import com.mondiamedia.cleanup.base.repo.CleanUpReportRepository;
import com.mondiamedia.cleanup.service.bo.DuplicateUser;
import com.mondiamedia.cleanup.user.entity.ExternalUserIdEntity;
import com.mondiamedia.cleanup.user.entity.UserEntity;

@Component
public class CleanUpServiceReport extends CleanUpServiceAbstract {

    @Autowired
    private CleanUpReportRepository CleanUpServiceReport;

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
            }
        }
        final CleanUpReportEntity cleanUpReportEntity2 = CleanUpServiceReport.findByUserId(duplicateUser.getUserId().toString());
        if (cleanUpReportEntity2 != null) {
            CleanUpServiceReport.update(duplicateUser.getUserId().toString(), cleanUpReportEntity2.getDuplictaeUsersId() + userEntity.getUserId() + "_");

        } else {
            final CleanUpReportEntity cleanUpReportEntity = new CleanUpReportEntity();
            cleanUpReportEntity.setUserId(duplicateUser.getUserId().toString());
            cleanUpReportEntity.setDuplictaeUsersId(userEntity.getUserId().toString() + "_");
            CleanUpServiceReport.save(cleanUpReportEntity);
        }
        cleanUpDao.save(duplicateUser.getCleanUpEntitySet());
    }

}
