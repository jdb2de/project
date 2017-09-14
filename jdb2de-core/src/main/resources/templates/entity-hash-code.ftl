<#-- @ftlvariable name="fields" type="java.util.List<org.jdb2de.core.data.FieldData>" -->
<#macro entity_hash_code fields>
    @Override
    public int hashCode() {
        return Objects.hashCode(
<#assign comma = "">
<#list fields as field>
            ${comma}${field.name}
            <#assign comma = ",">
</#list>
        );
    }
</#macro>