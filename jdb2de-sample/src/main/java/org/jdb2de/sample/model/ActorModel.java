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
 * Actor list
 * <b>TABLE:</b> actor
 *
 * This entity was automatically created by JDB2DE tool
 *
 * @author Rodrigo Tavares
 */
@Entity
@Table(name = "actor")
@Generated(value = "jdb2de", date = "2017-09-24 02:13:32", comments = "You should not modify it by hand")
public class ActorModel implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2134291576L;

    /**
     * Actor identification
     * <b>FIELD: </b>actor_id, <b>TYPE: </b>int4,
     */
    @Id
    @Column(name = "actor_id", nullable = false, length = 32)
    private Integer actorId;

    /**
     * Actor fist name
     * <b>FIELD: </b>first_name, <b>TYPE: </b>varchar,
     */
    @Basic
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    /**
     * Actor last name
     * <b>FIELD: </b>last_name, <b>TYPE: </b>varchar,
     */
    @Basic
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    /**
     * Last update
     * <b>FIELD: </b>last_update, <b>TYPE: </b>timestamp,
     */
    @Basic
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;

    /**
     * Actor identification
     * <b>FIELD: </b>actor_id
     * @return A {@link Integer} value
     */
    public Integer getActorId() {
        return actorId;
    }

    /**
     * Actor identification
     * <b>FIELD: </b>actor_id
     * @param actorId A {@link Integer} value
     */
    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    /**
     * Actor fist name
     * <b>FIELD: </b>first_name
     * @return A {@link String} value
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Actor fist name
     * <b>FIELD: </b>first_name
     * @param firstName A {@link String} value
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Actor last name
     * <b>FIELD: </b>last_name
     * @return A {@link String} value
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Actor last name
     * <b>FIELD: </b>last_name
     * @param lastName A {@link String} value
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
            actorId
            ,firstName
            ,lastName
            ,lastUpdate
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("actorId", actorId)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("lastUpdate", lastUpdate)
                .toString();
    }
}
