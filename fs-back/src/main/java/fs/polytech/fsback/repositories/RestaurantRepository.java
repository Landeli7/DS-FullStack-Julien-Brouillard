package fs.polytech.fsback.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fs.polytech.fsback.entities.RestaurantEntity;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Integer> {
    @Query("Select distinct r.nom from RestaurantEntity r")
    List<RestaurantEntity> findAllDistinctNames();
}