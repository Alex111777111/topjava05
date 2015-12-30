package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkisline
 * Date: 26.08.2014
 */


@Repository
@Transactional(readOnly = true)
public class JpaUserMealRepositoryImpl implements UserMealRepository {


    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public UserMeal save(UserMeal userMeal, int userId) {
        User ref = em.getReference(User.class, userId);
        userMeal.setUser(ref);
        if (userMeal.isNew()) {
            em.persist(userMeal);
            return userMeal;
        } else {
            if (get(userMeal.getId(), userId) != null) {
                return em.merge(userMeal);
            } else {
                return null;
            }
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        Query query = em.createNamedQuery(UserMeal.DELETE);
        query.setParameter("id", id);
        query.setParameter("userId", userId);
        return query.executeUpdate() != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        UserMeal meal = em.find(UserMeal.class, id);
        return meal.getUser().getId() == userId ? meal : null;
    }


    @Override
    public List<UserMeal> getAll(int userId) {
        return em.createNamedQuery(UserMeal.ALL_SORTED, UserMeal.class).setParameter("userId", userId).getResultList();
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(UserMeal.ALL_SORTED_BETWEEN, UserMeal.class).setParameter("userId", userId).setParameter("dateStart", startDate).setParameter("dateEnd", endDate).getResultList();
    }
}