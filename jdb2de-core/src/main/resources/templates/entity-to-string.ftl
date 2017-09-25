<#-- @ftlvariable name="field" type="org.jdb2de.core.data.FieldData" -->
<#-- @ftlvariable name="relation" type="org.jdb2de.core.data.RelationData" -->
<#macro entity_to_string fields oneRelations manyRelations>

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
        <#list fields as field>
                .add("${field.name}", ${field.name})
        </#list>
        <#list oneRelations as relation>
                .add("${relation.name}", ${relation.name})
        </#list>
        <#list manyRelations as relation>
                .add("${relation.name}", ${relation.name})
        </#list>
                .toString();
    }
</#macro>