package com.example.block11uploaddownloadfiles.repository;
import com.example.block11uploaddownloadfiles.domain.Fichero;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface FicheroRepository extends JpaRepository<Fichero, Integer> {
    List<Fichero> findByName(String name);
    List<Fichero> findAll();
}
