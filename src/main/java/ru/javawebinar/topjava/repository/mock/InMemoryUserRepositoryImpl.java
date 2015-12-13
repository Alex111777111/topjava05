package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by Maria on 12.12.2015.
 */
@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final LoggerWrapper LOG = LoggerWrapper.get(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.USER_LIST.forEach(this::save);
    }

    @Override
    public User save(User user) {
        LOG.info("save " + user.getName());
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
        }
        return repository.put(user.getId(), user);
    }

    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        repository.remove(id);
        return true;
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        return repository.get(id);
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);
        List<User> list =
                repository
                        .values()
                        .stream()
                        .filter(entry -> entry.getEmail().equals(email))
                        .collect(Collectors.toList());
        User user = list.size() > 0 ? list.get(0) : null;

        return user;

    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        List<User> list =
                repository.values()
                        .stream()
                        .sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
                        .collect(Collectors.toList());
        return list.size() > 0 ? list : null;
    }
}
