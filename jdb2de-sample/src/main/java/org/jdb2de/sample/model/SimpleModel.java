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
import java.util.Set;

/**
 * Simple
 * <b>TABLE:</b> simple
 *
 * This entity was automatically created by JDB2DE tool
 *
 * @author Rodrigo Tavares
 */
@Entity
@Table(name = "simple")
@Generated(value = "jdb2de", date = "2017-09-26 23:02:22", comments = "You should not modify it by hand")
public class SimpleModel implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1386750750L;

    /**
     * Simple identification
     * <b>FIELD: </b>id, <b>TYPE: </b>int4,
     */
    @Id
    @Column(name = "id", nullable = false, length = 32)
    private Integer id;

    /**
     * Creation date
     * <b>FIELD: </b>creation_date, <b>TYPE: </b>date,
     */
    @Basic
    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @OneToMany(mappedBy = "Simple", fetch = FetchType.LAZY)
    private Set<MultipleRelationModel> multipleRelationSimpleList;

    @OneToMany(mappedBy = "SimpleOne", fetch = FetchType.LAZY)
    private Set<MultipleRelationModel> multipleRelationSimpleOneList;

    @OneToMany(mappedBy = "SimpleTwo", fetch = FetchType.LAZY)
    private Set<MultipleRelationModel> multipleRelationSimpleTwoList;

    /**
     * Simple identification
     * <b>FIELD: </b>id
     * @return A {@link Integer} value
     */
    public Integer getId() {
        return id;
    }

    /**
     * Simple identification
     * <b>FIELD: </b>id
     * @param id A {@link Integer} value
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Creation date
     * <b>FIELD: </b>creation_date
     * @return A {@link Date} value
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Creation date
     * <b>FIELD: </b>creation_date
     * @param creationDate A {@link Date} value
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Set<MultipleRelationModel> getMultipleRelationSimpleList() {
        return multipleRelationSimpleList;
    }

    public void setMultipleRelationSimpleList(Set<MultipleRelationModel> multipleRelationSimpleList) {
        this.multipleRelationSimpleList = multipleRelationSimpleList;
    }

    public Set<MultipleRelationModel> getMultipleRelationSimpleOneList() {
        return multipleRelationSimpleOneList;
    }

    public void setMultipleRelationSimpleOneList(Set<MultipleRelationModel> multipleRelationSimpleOneList) {
        this.multipleRelationSimpleOneList = multipleRelationSimpleOneList;
    }

    public Set<MultipleRelationModel> getMultipleRelationSimpleTwoList() {
        return multipleRelationSimpleTwoList;
    }

    public void setMultipleRelationSimpleTwoList(Set<MultipleRelationModel> multipleRelationSimpleTwoList) {
        this.multipleRelationSimpleTwoList = multipleRelationSimpleTwoList;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
            id
            ,creationDate
            ,multipleRelationSimpleList
            ,multipleRelationSimpleOneList
            ,multipleRelationSimpleTwoList
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("creationDate", creationDate)
                .add("multipleRelationSimpleList", multipleRelationSimpleList)
                .add("multipleRelationSimpleOneList", multipleRelationSimpleOneList)
                .add("multipleRelationSimpleTwoList", multipleRelationSimpleTwoList)
                .toString();
    }
}
