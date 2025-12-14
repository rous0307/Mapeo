package org.uacm.mapeo.presupuesto.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.uacm.mapeo.presupuesto.entidades.Etapa;
import org.uacm.mapeo.presupuesto.entidades.Presupuesto;
import org.uacm.mapeo.presupuesto.entidades.Proyecto;

@Configuration
public class ConfigDataRest implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        config.setDefaultMediaType(MediaType.APPLICATION_JSON);

        // MUY IMPORTANTE
        config.useHalAsDefaultJsonMediaType(true);

        config.exposeIdsFor(
                Proyecto.class,
                Etapa.class,
                Presupuesto.class
        );
    }
}

