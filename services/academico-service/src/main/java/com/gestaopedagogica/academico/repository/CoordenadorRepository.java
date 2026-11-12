package com.gestaopedagogica.academico.repository;

import java.util.UUID;

import com.gestaopedagogica.academico.entity.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordenadorRepository extends JpaRepository<Coordenador, UUID> {
}
