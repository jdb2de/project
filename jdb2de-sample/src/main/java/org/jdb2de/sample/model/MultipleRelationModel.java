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
 * Multiple relation
 * <b>TABLE:</b> multiple_relation
 *
 * Automatically created by JDB2DE tool
 * @author Rodrigo Tavares
 */
@Entity
@Table(name = "multiple_relation", schema = "public", catalog = "public")
@Generated(value = "jdb2de", date = "2017-10-01 23:12:20", comments = "You should not modify it by hand")
public class MultipleRelationModel implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -1486361942L;

    /**
     * Multiple relation identification
     * <b>FIELD: </b>id, <b>TYPE: </b>int4,
     */
    @Id
    @Column(name = "id", nullable = false, length = 32)
    private Integer id;

    /**
     * Main relation
     * <b>FIELD: </b>simple_id, <b>TYPE: </b>int4,
     */
    @Basic
    @Column(name = "simple_id", nullable = false, length = 32)
    private Integer simpleId;

    /**
     * Relation one
     * <b>FIELD: </b>simple_one_id, <b>TYPE: </b>int4,
     */
    @Basic
    @Column(name = "simple_one_id", nullable = false, length = 32)
    private Integer simpleOneId;

    /**
     * Relation two
     * <b>FIELD: </b>simple_two_id, <b>TYPE: </b>int4,
     */
    @Basic
    @Column(name = "simple_two_id", nullable = false, length = 32)
    private Integer simpleTwoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="simple_id", referencedColumnName="id")
    private SimpleModel simple;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="simple_one_id", referencedColumnName="id")
    private SimpleModel simpleOne;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="simple_two_id", referencedColumnName="id")
    private SimpleModel simpleTwo;

    /**
     * Multiple relation identification
     * <b>FIELD: </b>id
     * @return A {@link Integer} value
     */
    public Integer getId() {
        return id;
    }

    /**
     * Multiple relation identification
     * <b>FIELD: </b>id
     * @param id A {@link Integer} value
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Main relation
     * <b>FIELD: </b>simple_id
     * @return A {@link Integer} value
     */
    public Integer getSimpleId() {
        return simpleId;
    }

    /**
     * Main relation
     * <b>FIELD: </b>simple_id
     * @param simpleId A {@link Integer} value
     */
    public void setSimpleId(Integer simpleId) {
        this.simpleId = simpleId;
    }

    /**
     * Relation one
     * <b>FIELD: </b>simple_one_id
     * @return A {@link Integer} value
     */
    public Integer getSimpleOneId() {
        return simpleOneId;
    }

    /**
     * Relation one
     * <b>FIELD: </b>simple_one_id
     * @param simpleOneId A {@link Integer} value
     */
    public void setSimpleOneId(Integer simpleOneId) {
        this.simpleOneId = simpleOneId;
    }

    /**
     * Relation two
     * <b>FIELD: </b>simple_two_id
     * @return A {@link Integer} value
     */
    public Integer getSimpleTwoId() {
        return simpleTwoId;
    }

    /**
     * Relation two
     * <b>FIELD: </b>simple_two_id
     * @param simpleTwoId A {@link Integer} value
     */
    public void setSimpleTwoId(Integer simpleTwoId) {
        this.simpleTwoId = simpleTwoId;
    }

    public SimpleModel getSimple() {
        return simple;
    }

    public void setSimple(SimpleModel simple) {
        this.simple = simple;
    }

    public SimpleModel getSimpleOne() {
        return simpleOne;
    }

    public void setSimpleOne(SimpleModel simpleOne) {
        this.simpleOne = simpleOne;
    }

    public SimpleModel getSimpleTwo() {
        return simpleTwo;
    }

    public void setSimpleTwo(SimpleModel simpleTwo) {
        this.simpleTwo = simpleTwo;
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
            ,simpleOneId
            ,simpleTwoId
            ,simple
            ,simpleOne
            ,simpleTwo
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("simpleId", simpleId)
                .add("simpleOneId", simpleOneId)
                .add("simpleTwoId", simpleTwoId)
                .add("simple", simple)
                .add("simpleOne", simpleOne)
                .add("simpleTwo", simpleTwo)
                .toString();
    }
}
