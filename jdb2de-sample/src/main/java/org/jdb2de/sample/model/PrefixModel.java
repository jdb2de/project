/*
 * *********************************************************************
 * Copyright 2017 - JDB2DE Database to Java Documented Entity
 * *********************************************************************
 *                          http://jdb2de.org
 *                  https://github.com/jdb2de/project
 * *********************************************************************
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jdb2de.sample.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Prefix table
 * <b>TABLE:</b> tb_prefix
 *
 * This entity was automatically created by JDB2DE tool
 *
 * @author Rodrigo Tavares
 */
@Entity
@Table(name = "tb_prefix")
@Generated(value = "jdb2de", date = "2017-09-26 23:02:22", comments = "You should not modify it by hand")
public class PrefixModel implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -1542833826L;

    /**
     * Prefix table identification
     * <b>FIELD: </b>id, <b>TYPE: </b>int4,
     */
    @Id
    @Column(name = "id", nullable = false, length = 32)
    private Integer id;

    /**
     * Description field
     * <b>FIELD: </b>description, <b>TYPE: </b>varchar,
     */
    @Basic
    @Column(name = "description", nullable = false, length = 10)
    private String description;

    @OneToMany(mappedBy = "Prefix", fetch = FetchType.LAZY)
    private Set<PrefixRelationModel> prefixRelationPrefixList;

    /**
     * Prefix table identification
     * <b>FIELD: </b>id
     * @return A {@link Integer} value
     */
    public Integer getId() {
        return id;
    }

    /**
     * Prefix table identification
     * <b>FIELD: </b>id
     * @param id A {@link Integer} value
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Description field
     * <b>FIELD: </b>description
     * @return A {@link String} value
     */
    public String getDescription() {
        return description;
    }

    /**
     * Description field
     * <b>FIELD: </b>description
     * @param description A {@link String} value
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public Set<PrefixRelationModel> getPrefixRelationPrefixList() {
        return prefixRelationPrefixList;
    }

    public void setPrefixRelationPrefixList(Set<PrefixRelationModel> prefixRelationPrefixList) {
        this.prefixRelationPrefixList = prefixRelationPrefixList;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
            id
            ,description
            ,prefixRelationPrefixList
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("description", description)
                .add("prefixRelationPrefixList", prefixRelationPrefixList)
                .toString();
    }
}
