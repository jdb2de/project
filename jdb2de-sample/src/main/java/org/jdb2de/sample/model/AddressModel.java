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
 * <b>TABLE:</b> address
 *
 * This entity was automatically created by JDB2DE tool
 *
 * @author Rodrigo Tavares
 */
@Entity
@Table(name = "address")
@Generated(value = "jdb2de", date = "2017-09-22 00:02:49", comments = "You should not modify it by hand")
public class AddressModel implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -650912966L;

    /**
     * 
     * <b>FIELD: </b>address_id, <b>TYPE: </b>int4,
     */
    @Id
    @Column(name = "address_id", nullable = false, length = 32)
    private Integer addressId;

    /**
     * 
     * <b>FIELD: </b>address, <b>TYPE: </b>varchar,
     */
    @Basic
    @Column(name = "address", nullable = false, length = 50)
    private String address;

    /**
     * 
     * <b>FIELD: </b>address2, <b>TYPE: </b>varchar,
     */
    @Basic
    @Column(name = "address2", length = 50)
    private String address2;

    /**
     * 
     * <b>FIELD: </b>district, <b>TYPE: </b>varchar,
     */
    @Basic
    @Column(name = "district", nullable = false, length = 20)
    private String district;

    /**
     * 
     * <b>FIELD: </b>city_id, <b>TYPE: </b>int2,
     */
    @Basic
    @Column(name = "city_id", nullable = false, length = 16)
    private Integer cityId;

    /**
     * 
     * <b>FIELD: </b>postal_code, <b>TYPE: </b>varchar,
     */
    @Basic
    @Column(name = "postal_code", length = 10)
    private String postalCode;

    /**
     * 
     * <b>FIELD: </b>phone, <b>TYPE: </b>varchar,
     */
    @Basic
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    /**
     * 
     * <b>FIELD: </b>last_update, <b>TYPE: </b>timestamp,
     */
    @Basic
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;

    /**
     * 
     * <b>FIELD: </b>address_id
     * @return A {@link Integer} value
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * 
     * <b>FIELD: </b>address_id
     * @param addressId A {@link Integer} value
     */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    /**
     * 
     * <b>FIELD: </b>address
     * @return A {@link String} value
     */
    public String getAddress() {
        return address;
    }

    /**
     * 
     * <b>FIELD: </b>address
     * @param address A {@link String} value
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 
     * <b>FIELD: </b>address2
     * @return A {@link String} value
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * 
     * <b>FIELD: </b>address2
     * @param address2 A {@link String} value
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * 
     * <b>FIELD: </b>district
     * @return A {@link String} value
     */
    public String getDistrict() {
        return district;
    }

    /**
     * 
     * <b>FIELD: </b>district
     * @param district A {@link String} value
     */
    public void setDistrict(String district) {
        this.district = district;
    }

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
     * <b>FIELD: </b>postal_code
     * @return A {@link String} value
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * 
     * <b>FIELD: </b>postal_code
     * @param postalCode A {@link String} value
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * 
     * <b>FIELD: </b>phone
     * @return A {@link String} value
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 
     * <b>FIELD: </b>phone
     * @param phone A {@link String} value
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
            addressId
            ,address
            ,address2
            ,district
            ,cityId
            ,postalCode
            ,phone
            ,lastUpdate
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("addressId", addressId)
                .add("address", address)
                .add("address2", address2)
                .add("district", district)
                .add("cityId", cityId)
                .add("postalCode", postalCode)
                .add("phone", phone)
                .add("lastUpdate", lastUpdate)
                .toString();
    }
}
