<#-- @ftlvariable name="param" type="org.jdb2de.core.data.ParameterData" -->
<#-- @ftlvariable name="composite" type="org.jdb2de.core.data.CompositePrimaryKeyData" -->
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
package ${param.compositePrimaryKeyPackage};

import ${composite.entity.packageName}.${composite.entity.name};

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.annotation.Generated;
import java.io.Serializable;

import javax.persistence.*;
<#list composite.imports as import>
import ${import};
</#list>

/**
 * Composite primary key for {@link ${composite.entity.name}}
 *
 * Automatically created by JDB2DE tool
 * @author ${param.author}
 */
@Generated(value = "jdb2de", date = "${.now?datetime}", comments = "You should not modify it by hand")
public class ${composite.name} implements Serializable {
<@entity_serial_uid uid=composite.serialUid />
<@entity_field fields=composite.fields />
<@entity_method fields=composite.fields />
<@entity_equals />
<@entity_hash_code fields=composite.fields oneRelations=composite.emptyList manyRelations=composite.emptyList />
<@entity_to_string fields=composite.fields oneRelations=composite.emptyList manyRelations=composite.emptyList />
}
