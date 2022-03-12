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
        USER_PROFILE_LIST.add(new UserProfile(UUID.randomUUID(), "sudip", null));
        USER_PROFILE_LIST.add(new UserProfile(UUID.randomUUID(), "sachin", null));
    }

    public List<UserProfile> getUserProfileList() {
        return USER_PROFILE_LIST;
    }
}
