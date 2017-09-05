<#-- @ftlvariable name="param" type="org.jdb2de.core.data.ParametersData" -->
<#-- @ftlvariable name="entity" type="org.jdb2de.core.data.enitity.EntityData" -->
<#setting datetime_format="yyyy-MM-dd HH:mm:ss">
<#assign comma = "">
/*
<#list entity.copyright as line>
 * ${line}
</#list>
 */
package ${param.entityPackage};

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.annotation.Generated;
import java.io.Serializable;

import javax.persistence.*;

<#if entity.table.compositeKey>
import ${param.idPackage}.${entity.name}PK;
</#if>

/**
 * ${entity.table.comment}
 * <b>TABLE:</b> ${entity.table.name}
 *
 * This entity was automatically created by JDB2DE tool
 *
 * @author ${entity.author}
 */
@Entity
<#if entity.table.compositeKey>
@IdClass(${entity.name}PK.class)
</#if>
@Table(name = "${entity.table.name}"<#if param.schema??>, schema = "${param.schema}"</#if><#if param.catalog??>, catalog = "${param.catalog}"}</#if>)
@Generated(value = "jdb2de", date = "${.now?datetime}", comments = "You should not modify it by hand")
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
    <#if field.column.primaryKey >
    @Id
    <#else>
    @Basic
    </#if>
    @Column(name = "${field.column.name}", nullable = ${field.column.nullable?string('true', 'false')}<#if field.column.size gt 0 >, length = ${field.column.size}</#if>)
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
