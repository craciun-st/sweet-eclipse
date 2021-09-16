package com.codecool.sweeteclipse.model.user;

import java.util.Set;

public class PublicUserData extends AbstractAncestorOfUser {

    public PublicUserData() {
    }

    public PublicUserData(Long id, String name, Set<UserRole> roles) {
        super(id, name, roles);
    }
}
