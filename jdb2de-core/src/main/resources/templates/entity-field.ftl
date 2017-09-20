<#-- @ftlvariable name="fields" type="java.util.List<org.jdb2de.core.data.FieldData>" -->
<#-- @ftlvariable name="field" type="org.jdb2de.core.data.FieldData" -->
<#macro entity_field fields>
    <#list fields as field>
    /**
     * ${field.column.comment}
     * <b>FIELD: </b>${field.column.name}, <b>TYPE: </b>${field.column.type},
     */
    <#if field.column.primaryKey >
    @Id
    <#else>
    @Basic
    </#if>
    @Column(name = "${field.column.name}", nullable = ${field.column.columnParameter.nullable?string('true', 'false')}<#if field.column.columnParameter.size gt 0 >, length = ${field.column.columnParameter.size}</#if>)
    private ${field.type} ${field.name};

    </#list>
</#macro>