package ru.ivmiit.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.ivmiit.service.models.Musician;

import java.util.List;


public interface MusiciansRepository extends JpaRepository<Musician, Long> {
    List<Musician> findAllByTeam_name(String nameTeam);

    @Query(nativeQuery = true, value = "SELECT * FROM musicians WHERE instrument = ?1;")
    List<Musician> findAllByInstrument(String model);
}