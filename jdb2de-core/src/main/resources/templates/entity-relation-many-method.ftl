<#-- @ftlvariable name="relation" type="org.jdb2de.core.data.RelationData" -->
<#macro entity_relation_many_method manyRelations>
    <#list manyRelations as relation>
    public Set<${relation.type}> get${relation.upperName}() {
        return ${relation.name};
    }

    public void set${relation.upperName}(Set<${relation.type}> ${relation.name}) {
        this.${relation.name} = ${relation.name};
    }

    </#list>
</#macro>