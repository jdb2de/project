<#-- @ftlvariable name="relation" type="org.jdb2de.core.data.RelationData" -->
<#macro entity_relation_many_field manyRelations>
    <#list manyRelations as relation>
    @OneToMany(mappedBy = "${relation.mappedBy}", fetch = FetchType.LAZY)
    private Set<${relation.type}> ${relation.getName()};

    </#list>
</#macro>