<#-- @ftlvariable name="fields" type="java.util.List<org.jdb2de.core.data.enitity.FieldData>" -->
<#macro entity_to_string fields>

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
        <#list fields as field>
                .add("${field.name}", ${field.name})
        </#list>
                .toString();
    }
</#macro>