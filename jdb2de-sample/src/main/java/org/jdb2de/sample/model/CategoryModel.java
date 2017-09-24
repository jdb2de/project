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
import java.util.Date;

/**
 * Movie categories
 * <b>TABLE:</b> category
 *
 * This entity was automatically created by JDB2DE tool
 *
 * @author Rodrigo Tavares
 */
@Entity
@Table(name = "category")
@Generated(value = "jdb2de", date = "2017-09-24 02:13:32", comments = "You should not modify it by hand")
public class CategoryModel implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -520310906L;

    /**
     * Category identification
     * <b>FIELD: </b>category_id, <b>TYPE: </b>int4,
     */
    @Id
    @Column(name = "category_id", nullable = false, length = 32)
    private Integer categoryId;

    /**
     * Category name
     * <b>FIELD: </b>name, <b>TYPE: </b>varchar,
     */
    @Basic
    @Column(name = "name", nullable = false, length = 25)
    private String name;

    /**
     * Last update
     * <b>FIELD: </b>last_update, <b>TYPE: </b>timestamp,
     */
    @Basic
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;

    /**
     * Category identification
     * <b>FIELD: </b>category_id
     * @return A {@link Integer} value
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * Category identification
     * <b>FIELD: </b>category_id
     * @param categoryId A {@link Integer} value
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Category name
     * <b>FIELD: </b>name
     * @return A {@link String} value
     */
    public String getName() {
        return name;
    }

    /**
     * Category name
     * <b>FIELD: </b>name
     * @param name A {@link String} value
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Last update
     * <b>FIELD: </b>last_update
     * @return A {@link Date} value
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Last update
     * <b>FIELD: </b>last_update
     * @param lastUpdate A {@link Date} value
     */
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
            categoryId
            ,name
            ,lastUpdate
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("categoryId", categoryId)
                .add("name", name)
                .add("lastUpdate", lastUpdate)
                .toString();
    }
}
