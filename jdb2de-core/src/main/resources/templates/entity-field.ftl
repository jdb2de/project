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
    <#if field.column.translatedType.lob >
    @Lob
    </#if>
    @Column(name = "${field.column.name}"<#if !field.column.columnParameter.nullable >, nullable = ${field.column.columnParameter.nullable?string('true', 'false')}</#if><#if field.column.columnParameter.size gt 0 >, length = ${field.column.columnParameter.size?c}</#if>)
    private ${field.type} ${field.name};

    </#list>
</#macro>