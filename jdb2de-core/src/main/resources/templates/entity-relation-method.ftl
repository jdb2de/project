<#-- @ftlvariable name="relation" type="org.jdb2de.core.data.RelationData" -->
<#macro entity_relation_method relations>
    <#list relations as relation>
    public ${relation.type} get${relation.upperName}() {
        return ${relation.name};
    }

    public void set${relation.upperName}(${relation.type} ${relation.name}) {
        this.${relation.name} = ${relation.name};
    }

    </#list>
</#macro>