package com.mondiamedia.cleanup.user.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.arvatomobile.services.user.userservice.client.ExternalUserIdType;

/**
 * A special map class that can be used as a JAX-RS query input parameter.
 *
 * @author jtammen
 */
public class ExternalUserIdTypeToUserIdMap extends HashMap<ExternalUserIdType, List<String>> {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6561641056482959431L;

    /**
     * Constructor needed for instantiation the map from the query string.
     *
     * @param fromString The query string that should be converted to the map.
     */
    public ExternalUserIdTypeToUserIdMap(final String fromString) {
        final String[] typesAndUserIds = fromString.split(",");
        for (final String typesAndUserId : typesAndUserIds) {
            final String[] typeAndUserId = typesAndUserId.split(":");
            if (typeAndUserId.length != 2) {
                continue;
            }
            try {
                final ExternalUserIdType externalUserIdType = ExternalUserIdType.valueOf(typeAndUserId[0]);
                List<String> userIds = get(externalUserIdType);
                if (userIds == null) {
                    userIds = new ArrayList<String>();
                    put(externalUserIdType, userIds);
                }
                userIds.add(typeAndUserId[1]);
            } catch (final IllegalArgumentException ignoreMe) {
                continue;
            }
        }
    }
}
