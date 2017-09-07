<#-- @ftlvariable name="param" type="org.jdb2de.core.data.ParametersData" -->
<#-- @ftlvariable name="entity" type="org.jdb2de.core.data.enitity.EntityData" -->
<#include "entity-serial-uid.ftl">
<#include "entity-field.ftl">
<#include "entity-method.ftl">
<#include "entity-hash-code.ftl">
<#include "entity-to-string.ftl">
<#include "entity-equals.ftl">
<#setting datetime_format="yyyy-MM-dd HH:mm:ss">
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
<@entity_serial_uid uid=entity.serialUid />
<@entity_field fields=entity.fields />
<@entity_method fields=entity.fields />
<@entity_equals />
<@entity_hash_code fields=entity.fields />
<@entity_to_string fields=entity.fields />
}
