package com.example.proyecto_final.backWeb.mensajes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Mensaje")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Mensaje {
    @Id
    private String idMensaje;
    private String mensaje;
}

