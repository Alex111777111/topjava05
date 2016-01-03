package ru.javawebinar.topjava.repository.datajpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Maria on 01.01.2016.
 */
public interface ProxyUserMealRepository extends JpaRepository<UserMeal, Integer> {

    @Transactional
    @Modifying
    @Query(name = UserMeal.DELETE)
        // @Query("DELETE FROM UserMeal um WHERE um.id=:id")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Transactional
    @Query(name = UserMeal.UPDATE)
    UserMeal save(UserMeal userMeal);

    @Modifying
    @Query(name = UserMeal.GET)
    List<UserMeal> findOne(@Param("id") int id, @Param("userId") int userId);

    @Modifying
    @Query(name = UserMeal.ALL_SORTED)
    List<UserMeal> findAll(@Param("userId") int userIid);

    @Modifying
    @Query(name = UserMeal.GET_BETWEEN)
    List<UserMeal> findBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userIid);

  /*//  UPDATE DepartmentEntity d SET d.name=:name where d.id = :id"
    @Query("UPDATE UserMeal um SET um.dateTime = :dateTime, um.user =:user, um.calories =:calories, um.description = :description WHERE um.id = :id AND um.user.id=:userId")
    List<UserMeal> update(@Param("id") int id, @Param("userId") int userId, @Param("dateTime") LocalDateTime dateTime, @Param("user") User user, @Param("calories") int calories, @Param("description") String description);
*/
}
