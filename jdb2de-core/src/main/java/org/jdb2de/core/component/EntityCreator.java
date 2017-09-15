package org.jdb2de.core.component;

import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityCreator {

    @Autowired
    public EntityCreator(Configuration freemarkerConfig) {
        this.freemarkerConfig = freemarkerConfig;
    }

    private final Configuration freemarkerConfig;
}
