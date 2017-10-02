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
package org.jdb2de.sample.model.pk;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.jdb2de.sample.model.CompositeModel;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Composite primary key for {@link CompositeModel}
 *
 * Automatically created by JDB2DE tool
 * @author Rodrigo Tavares
 */
@Generated(value = "jdb2de", date = "2017-10-01 23:12:20", comments = "You should not modify it by hand")
public class CompositePK implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1447743905L;

    /**
     * Composite primary key identification
     * <b>FIELD: </b>id, <b>TYPE: </b>int4,
     */
    @Id
    @Column(name = "id", nullable = false, length = 32)
    private Integer id;

    /**
     * Simple table identification
     * <b>FIELD: </b>simple_id, <b>TYPE: </b>int4,
     */
    @Id
    @Column(name = "simple_id", nullable = false, length = 32)
    private Integer simpleId;

    /**
     * Composite primary key identification
     * <b>FIELD: </b>id
     * @return A {@link Integer} value
     */
    public Integer getId() {
        return id;
    }

    /**
     * Composite primary key identification
     * <b>FIELD: </b>id
     * @param id A {@link Integer} value
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Simple table identification
     * <b>FIELD: </b>simple_id
     * @return A {@link Integer} value
     */
    public Integer getSimpleId() {
        return simpleId;
    }

    /**
     * Simple table identification
     * <b>FIELD: </b>simple_id
     * @param simpleId A {@link Integer} value
     */
    public void setSimpleId(Integer simpleId) {
        this.simpleId = simpleId;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
            id
            ,simpleId
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("simpleId", simpleId)
                .toString();
    }
}
