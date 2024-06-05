package com.example.proyecto_final.backWeb.mensajes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepostoryLlamadas  extends CrudRepository<Mensaje, Long> {
}
