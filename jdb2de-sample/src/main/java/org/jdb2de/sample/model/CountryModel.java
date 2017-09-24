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
 * 
 * <b>TABLE:</b> country
 *
 * This entity was automatically created by JDB2DE tool
 *
 * @author Rodrigo Tavares
 */
@Entity
@Table(name = "country")
@Generated(value = "jdb2de", date = "2017-09-24 02:13:32", comments = "You should not modify it by hand")
public class CountryModel implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1783647862L;

    /**
     * 
     * <b>FIELD: </b>country_id, <b>TYPE: </b>int4,
     */
    @Id
    @Column(name = "country_id", nullable = false, length = 32)
    private Integer countryId;

    /**
     * 
     * <b>FIELD: </b>country, <b>TYPE: </b>varchar,
     */
    @Basic
    @Column(name = "country", nullable = false, length = 50)
    private String country;

    /**
     * 
     * <b>FIELD: </b>last_update, <b>TYPE: </b>timestamp,
     */
    @Basic
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;

    /**
     * 
     * <b>FIELD: </b>country_id
     * @return A {@link Integer} value
     */
    public Integer getCountryId() {
        return countryId;
    }

    /**
     * 
     * <b>FIELD: </b>country_id
     * @param countryId A {@link Integer} value
     */
    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    /**
     * 
     * <b>FIELD: </b>country
     * @return A {@link String} value
     */
    public String getCountry() {
        return country;
    }

    /**
     * 
     * <b>FIELD: </b>country
     * @param country A {@link String} value
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 
     * <b>FIELD: </b>last_update
     * @return A {@link Date} value
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     * 
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
            countryId
            ,country
            ,lastUpdate
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("countryId", countryId)
                .add("country", country)
                .add("lastUpdate", lastUpdate)
                .toString();
    }
}
