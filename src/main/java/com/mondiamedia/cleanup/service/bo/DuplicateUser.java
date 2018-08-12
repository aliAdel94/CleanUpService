package com.mondiamedia.cleanup.service.bo;

import java.util.HashSet;
import java.util.Set;

import com.mondiamedia.cleanup.base.entity.CleanUpEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DuplicateUser {

    private Long userId;

    private Set<CleanUpEntity> cleanUpEntitySet;

    public Set<CleanUpEntity> getCleanUpEntitySet() {
        if(cleanUpEntitySet == null){
            setCleanUpEntitySet(new HashSet<>());
        }
        return cleanUpEntitySet;
    }


    @Override
    public int hashCode() {
        return getUserId().hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof CleanUpEntity) || obj == null) {
            return false;
        }
        final CleanUpEntity cleanUpEntity = (CleanUpEntity) obj;

        return cleanUpEntity.getUserId().equals(getUserId());

    }

}
