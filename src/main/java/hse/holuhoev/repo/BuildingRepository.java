package hse.holuhoev.repo;

import hse.holuhoev.domain.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BuildingRepository extends JpaRepository<Building, Integer>, QuerydslPredicateExecutor<Building> {
}
