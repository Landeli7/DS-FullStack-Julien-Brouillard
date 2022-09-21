package fs.polytech.fsback.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fs.polytech.fsback.entities.EvalEntity;

@Repository
public interface EvaluationRepository extends JpaRepository<EvalEntity, Integer> {
    @Query("Select distinct e.nom from EvalEntity e")
    List<EvalEntity> findAllDistinctNames();
}