<#-- @ftlvariable name="param" type="org.jdb2de.core.data.ParameterData" -->
<#-- @ftlvariable name="entity" type="org.jdb2de.core.data.EntityData" -->
<#-- @ftlvariable name="compositeName" type="java.lang.String" -->
<#include "entity-serial-uid.ftl">
<#include "entity-field.ftl">
<#include "entity-method.ftl">
<#include "entity-hash-code.ftl">
<#include "entity-to-string.ftl">
<#include "entity-equals.ftl">
<#include "entity-relation-one-field.ftl">
<#include "entity-relation-one-method.ftl">
<#include "entity-relation-many-field.ftl">
<#include "entity-relation-many-method.ftl">
<#setting datetime_format="yyyy-MM-dd HH:mm:ss">
/*
<#list param.copyright as line>
 * ${line}
</#list>
 */
package ${entity.packageName};

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.annotation.Generated;
import java.io.Serializable;
<#if (entity.manyRelations?size >0)>
import java.util.Set;
</#if>

import javax.persistence.*;
<#if entity.table.compositeKey>
import ${param.compositePrimaryKeyPackage}.${compositeName};
</#if>
<#list entity.imports as import>
import ${import};
</#list>

/**
 * ${entity.table.comment}
 * <b>TABLE:</b> ${entity.table.name}
 *
 * Automatically created by JDB2DE tool
 * @author ${param.author}
 */
@Entity
<#if entity.table.compositeKey>
@IdClass(${compositeName}.class)
</#if>
@Table(name = "${entity.table.name}"<#if entity.table.schema??>, schema = "${entity.table.schema}"</#if><#if entity.table.catalog??>, catalog = "${entity.table.catalog}"</#if>)
@Generated(value = "jdb2de", date = "${.now?datetime}", comments = "You should not modify it by hand")
public class ${entity.name} implements Serializable {
<@entity_serial_uid uid=entity.serialUid />
<@entity_field fields=entity.fields />
<@entity_relation_one_field oneRelations=entity.oneRelations />
<@entity_relation_many_field manyRelations=entity.manyRelations />
<@entity_method fields=entity.fields />
<@entity_relation_one_method oneRelations=entity.oneRelations />
<@entity_relation_many_method manyRelations=entity.manyRelations />
<@entity_equals />
<@entity_hash_code fields=entity.fields oneRelations=entity.oneRelations manyRelations=entity.manyRelations />
<@entity_to_string fields=entity.fields oneRelations=entity.oneRelations manyRelations=entity.manyRelations />
}
