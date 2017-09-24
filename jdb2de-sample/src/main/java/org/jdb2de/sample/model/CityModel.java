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
 * <b>TABLE:</b> city
 *
 * This entity was automatically created by JDB2DE tool
 *
 * @author Rodrigo Tavares
 */
@Entity
@Table(name = "city")
@Generated(value = "jdb2de", date = "2017-09-24 02:13:32", comments = "You should not modify it by hand")
public class CityModel implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 958457580L;

    /**
     * 
     * <b>FIELD: </b>city_id, <b>TYPE: </b>int4,
     */
    @Id
    @Column(name = "city_id", nullable = false, length = 32)
    private Integer cityId;

    /**
     * 
     * <b>FIELD: </b>city, <b>TYPE: </b>varchar,
     */
    @Basic
    @Column(name = "city", nullable = false, length = 50)
    private String city;

    /**
     * 
     * <b>FIELD: </b>country_id, <b>TYPE: </b>int2,
     */
    @Basic
    @Column(name = "country_id", nullable = false, length = 16)
    private Integer countryId;

    /**
     * 
     * <b>FIELD: </b>last_update, <b>TYPE: </b>timestamp,
     */
    @Basic
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;

    @ManyToOne
    @JoinColumn(name="country_id", referencedColumnName="country_id")
    private CountryModel country;

    /**
     * 
     * <b>FIELD: </b>city_id
     * @return A {@link Integer} value
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * 
     * <b>FIELD: </b>city_id
     * @param cityId A {@link Integer} value
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * 
     * <b>FIELD: </b>city
     * @return A {@link String} value
     */
    public String getCity() {
        return city;
    }

    /**
     * 
     * <b>FIELD: </b>city
     * @param city A {@link String} value
     */
    public void setCity(String city) {
        this.city = city;
    }

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

    public CountryModel getCountry() {
        return country;
    }

    public void setCountry(CountryModel country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
            cityId
            ,city
            ,countryId
            ,lastUpdate
            ,country
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("cityId", cityId)
                .add("city", city)
                .add("countryId", countryId)
                .add("lastUpdate", lastUpdate)
                .add("country", country)
                .toString();
    }
}
