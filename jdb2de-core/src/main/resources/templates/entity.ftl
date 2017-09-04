<#-- @ftlvariable name="entity" type="org.jdb2de.core.data.enitity.EntityData" -->
<#assign comma = "">
/*
<#list entity.copyright as line>
 * ${line}
</#list>
 */
package ${entity.entityPackage};

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;

/**
 * ${entity.table.comment}
 * <b>TABLE:</b> ${entity.table.name}
 * @author ${entity.author}
 */
public class ${entity.name} implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = ${entity.serialUid}L;

<#list entity.fields as field>
    /**
     * ${field.column.comment}
     * <b>FIELD: </b>${field.column.name}
     */
    private ${field.type.name} ${field.name};

</#list>

<#list entity.fields as field>
    /**
     * ${field.column.comment}
     * <b>FIELD: </b>${field.column.name}
     * @return A {@link ${field.type.name}} value
     */
    public ${field.type.name} get${field.upperName}() {
        return ${field.name};
    }

    /**
     * ${field.column.comment}
     * <b>FIELD: </b>${field.column.name}
     * @param ${field.name} A {@link ${field.type.name}} value
     */
    public void set${field.upperName}(${field.type.name} ${field.name}) {
        this.${field.name} = ${field.name};
    }

</#list>
    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
<#assign comma = "">
<#list entity.fields as field>
            ${comma}${field.name}
            <#assign comma = ",">
</#list>
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
<#list entity.fields as field>
                .add("${field.name}", ${field.name})
</#list>
                .toString();
    }
}
