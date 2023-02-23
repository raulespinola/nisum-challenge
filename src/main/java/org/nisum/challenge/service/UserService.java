package org.nisum.challenge.service;

import org.nisum.challenge.core.model.UserCreationModel;
import org.nisum.challenge.core.model.UserModel;

public interface UserService {
    UserCreationModel createUser(UserModel userRequest);
}
