package ru.mai.pubstash.interactor;

import ru.mai.pubstash.entity.Party;
import ru.mai.pubstash.entity.User;
import ru.mai.pubstash.util.Result;

/**
 * Сервис с бизнес логикой связанной с пользователями
 */
public interface UserInteractor {

    /**
     * Метод создает нового пользователя в приложении
     * @param user
     * @return юзер
     */
    Result<User> createUser(final User user);

    /**
     * Метод ищет пользователя по нику
     * @param nickname
     * @return юзер
     */
    Result<User> findUserByNickname(final String nickname);

    /**
     * Метод ищет пользователя по Id
     * @param id
     * @return юзер
     */
    Result<User> findUserById(final long id);
}
