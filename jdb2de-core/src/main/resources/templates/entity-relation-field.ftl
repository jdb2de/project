<#-- @ftlvariable name="relation" type="org.jdb2de.core.data.RelationData" -->
<#macro entity_relation_field relations>
    <#list relations as relation>
    @ManyToOne
    <#if (relation.columns?size > 1) >
    @JoinColumns({
        <#list relation.columns as column>
        @JoinColumn(name="${column.source}", referencedColumnName="${column.target}"),
        </#list>
    })
    <#else>
    <#assign column = relation.columns[0]>
    @JoinColumn(name="${column.source}", referencedColumnName="${column.target}")
    </#if>
    private ${relation.type} ${relation.getName()};

    </#list>
</#macro>