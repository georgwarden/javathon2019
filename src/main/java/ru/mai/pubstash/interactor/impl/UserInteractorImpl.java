package ru.mai.pubstash.interactor.impl;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mai.pubstash.entity.User;
import ru.mai.pubstash.interactor.UserInteractor;
import ru.mai.pubstash.repository.UserRepository;
import ru.mai.pubstash.util.Result;

import javax.validation.constraints.NotNull;

@Service
public class UserInteractorImpl implements UserInteractor {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInteractorImpl.class);
    private final UserRepository userRepository;

    @Autowired
    public UserInteractorImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Result<User> createUser(User user) {
        Preconditions.checkArgument(user != null);
        LOGGER.debug("Create User: ", user);
        return Result.retrieve(() -> userRepository.saveAndFlush(user));

    }

    @Override
    public Result<User> findUserByNickname(@NotNull String nickname) {
        Preconditions.checkArgument(nickname.isEmpty());
        return Result.retrieve(() -> userRepository.findUserByNickname(nickname));
    }

    @Override
    public Result<User> findUserById(long id) {
        return Result.retrieve(() -> userRepository.findUserById(id));
    }
}

