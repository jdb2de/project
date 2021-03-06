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

/**
 * Prefix relation table
 * <b>TABLE:</b> tb_prefix_relation
 *
 * Automatically created by JDB2DE tool
 * @author Rodrigo Tavares
 */
@Entity
@Table(name = "tb_prefix_relation", schema = "public", catalog = "public")
@Generated(value = "jdb2de", date = "2017-10-01 23:12:20", comments = "You should not modify it by hand")
public class PrefixRelationModel implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -435137498L;

    /**
     * Prefix table relation identification
     * <b>FIELD: </b>id, <b>TYPE: </b>int4,
     */
    @Id
    @Column(name = "id", nullable = false, length = 32)
    private Integer id;

    /**
     * Prefix table identification
     * <b>FIELD: </b>prefix_id, <b>TYPE: </b>int4,
     */
    @Basic
    @Column(name = "prefix_id", nullable = false, length = 32)
    private Integer prefixId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="prefix_id", referencedColumnName="id")
    private PrefixModel prefix;

    /**
     * Prefix table relation identification
     * <b>FIELD: </b>id
     * @return A {@link Integer} value
     */
    public Integer getId() {
        return id;
    }

    /**
     * Prefix table relation identification
     * <b>FIELD: </b>id
     * @param id A {@link Integer} value
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Prefix table identification
     * <b>FIELD: </b>prefix_id
     * @return A {@link Integer} value
     */
    public Integer getPrefixId() {
        return prefixId;
    }

    /**
     * Prefix table identification
     * <b>FIELD: </b>prefix_id
     * @param prefixId A {@link Integer} value
     */
    public void setPrefixId(Integer prefixId) {
        this.prefixId = prefixId;
    }

    public PrefixModel getPrefix() {
        return prefix;
    }

    public void setPrefix(PrefixModel prefix) {
        this.prefix = prefix;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
            id
            ,prefixId
            ,prefix
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("prefixId", prefixId)
                .add("prefix", prefix)
                .toString();
    }
}
