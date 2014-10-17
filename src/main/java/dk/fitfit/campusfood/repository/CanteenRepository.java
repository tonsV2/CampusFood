package dk.fitfit.campusfood.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dk.fitfit.campusfood.model.Canteen;

@Repository
public interface CanteenRepository extends CrudRepository<Canteen, Long>{
}
