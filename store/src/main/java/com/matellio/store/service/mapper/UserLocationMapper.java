package com.matellio.store.service.mapper;


import com.matellio.store.domain.*;
import com.matellio.store.service.dto.UserLocationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserLocation} and its DTO {@link UserLocationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserLocationMapper extends EntityMapper<UserLocationDTO, UserLocation> {



    default UserLocation fromId(String id) {
        if (id == null) {
            return null;
        }
        UserLocation userLocation = new UserLocation();
        userLocation.setId(id);
        return userLocation;
    }
}
