package org.bosonit.ej3SpringBoot;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class AppConfig {
    @Value("${spring.profiles.active}")
    private String name;

    @Value("${bd.url}")
    private String url;

    public String getName() {
        return name;
    }
    public String getUrl() {
        return url;
    }
}
