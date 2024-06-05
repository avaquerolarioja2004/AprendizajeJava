package com.example.spring_batch.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ResultadoTiempoRepository extends JpaRepository<ResultadoTiempo, Long> {
}
