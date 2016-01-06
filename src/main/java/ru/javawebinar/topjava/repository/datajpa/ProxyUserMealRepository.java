package ru.javawebinar.topjava.repository.datajpa;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Maria on 01.01.2016.
 */
@Transactional(readOnly = true)
public interface ProxyUserMealRepository extends JpaRepository<UserMeal, Integer> {

    @Transactional
    @Modifying
    //  @Query(name = UserMeal.DELETE)
    @Query("DELETE FROM UserMeal m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Transactional
        // @Query(name = UserMeal.UPDATE)
    UserMeal save(UserMeal userMeal);

    @Modifying
    //  @Query(name = UserMeal.GET)
    @Query("SELECT m FROM UserMeal m WHERE m.id=:id AND m.user.id=:userId")
    List<UserMeal> findOne(@Param("id") int id, @Param("userId") int userId);

    @Modifying
    //@Query(name = UserMeal.ALL_SORTED)
    @Query("SELECT m FROM UserMeal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC")
    List<UserMeal> findAll(@Param("userId") int userIid);

    @Modifying
    // @Query(name = UserMeal.GET_BETWEEN)
    @Query("SELECT m from UserMeal m WHERE m.user.id=:userId AND m.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC")
    List<UserMeal> findBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userIid);


    @Query("SELECT m FROM UserMeal m LEFT JOIN FETCH m.user WHERE m.id=:id AND m.user.id=:userId")
    List<UserMeal> findMealWithUser(@Param("id") int id, @Param("userId") int userId);
  /*  @Transactional
    @Query(name = UserMeal.UPDATE)
    List<UserMeal> update(@Param("datetime") LocalDateTime dateTime, @Param("id") int id, @Param("userId") int userId, *//*@Param("user") User user,*//* @Param("calories") int calories, @Param("description") String description);
*/
}
