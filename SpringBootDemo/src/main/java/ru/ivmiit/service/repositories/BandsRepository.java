package ru.ivmiit.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivmiit.service.models.Band;

import java.util.List;


public interface BandsRepository extends JpaRepository<Band, Long> {
    List<Band> findAllByName(String Name);
}
