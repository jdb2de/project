<#-- @ftlvariable name="field" type="org.jdb2de.core.data.enitity.FieldData" -->
<#macro entity_method fields>
    <#list fields as field>
    /**
     * ${field.column.comment}
     * <b>FIELD: </b>${field.column.name}
     * @return A {@link ${field.type}} value
     */
    public ${field.type} get${field.upperName}() {
        return ${field.name};
    }

    /**
     * ${field.column.comment}
     * <b>FIELD: </b>${field.column.name}
     * @param ${field.name} A {@link ${field.type}} value
     */
    public void set${field.upperName}(${field.type} ${field.name}) {
        this.${field.name} = ${field.name};
    }

    </#list>
</#macro>