<#-- @ftlvariable name="field" type="org.jdb2de.core.data.FieldData" -->
<#-- @ftlvariable name="relation" type="org.jdb2de.core.data.RelationData" -->
<#macro entity_hash_code fields oneRelations manyRelations>
    @Override
    public int hashCode() {
        return Objects.hashCode(
        <#assign comma = "">
        <#list fields as field>
            ${comma}${field.name}
            <#assign comma = ",">
        </#list>
        <#list oneRelations as relation>
            ${comma}${relation.name}
            <#assign comma = ",">
        </#list>
        <#list manyRelations as relation>
            ${comma}${relation.name}
            <#assign comma = ",">
        </#list>
        );
    }
</#macro>