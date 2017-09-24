package org.jdb2de.core.data;

import java.io.Serializable;

public class AbstractFieldData implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -5395522330654922854L;

    private String name;
    private String upperName;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpperName() {
        return upperName;
    }

    public void setUpperName(String upperName) {
        this.upperName = upperName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
