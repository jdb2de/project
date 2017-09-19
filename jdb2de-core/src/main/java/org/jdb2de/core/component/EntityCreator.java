package org.jdb2de.core.component;

import com.google.common.base.Preconditions;
import freemarker.template.Configuration;
import org.jdb2de.core.data.ParameterData;
import org.jdb2de.core.model.TableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EntityCreator {

    private String compositePkPath;
    private final ParameterData parameters;
    private final Configuration freemarkerConfig;


    @Autowired
    public EntityCreator(ParameterData parameters, Configuration freemarkerConfig) {
        this.parameters = parameters;
        this.freemarkerConfig = freemarkerConfig;
    }

    public void create(String compositePkPath, List<TableModel> tables) {
        this.compositePkPath = compositePkPath;
        Preconditions.checkNotNull(compositePkPath, "Composite PK path can not be null");
    }
}
