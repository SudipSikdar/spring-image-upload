package com.self.learning.datastore;

import com.self.learning.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {
    private  static final List<UserProfile> USER_PROFILE_LIST = new ArrayList<>();

    static {
        USER_PROFILE_LIST.add(new UserProfile(UUID.fromString("f422424d-3ccb-4d0c-8b68-bbe0c7388d73"), "sudip", null));
        USER_PROFILE_LIST.add(new UserProfile(UUID.fromString("1b825fe6-2892-443f-b7ad-bbd50e88b8a0"), "sachin", null));
    }

    public List<UserProfile> getUserProfileList() {
        return USER_PROFILE_LIST;
    }
}
