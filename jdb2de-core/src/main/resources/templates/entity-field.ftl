<#-- @ftlvariable name="fields" type="java.util.List<org.jdb2de.core.data.FieldData>" -->
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
    @Column(name = "${field.column.name}", nullable = ${field.column.nullable?string('true', 'false')}<#if field.column.size gt 0 >, length = ${field.column.size}</#if>)
    private ${field.type} ${field.name};

    </#list>
</#macro>