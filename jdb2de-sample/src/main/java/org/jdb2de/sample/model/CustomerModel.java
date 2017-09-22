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
 * Customer registration
 * <b>TABLE:</b> customer
 *
 * This entity was automatically created by JDB2DE tool
 *
 * @author Rodrigo Tavares
 */
@Entity
@Table(name = "customer")
@Generated(value = "jdb2de", date = "2017-09-22 00:02:49", comments = "You should not modify it by hand")
public class CustomerModel implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -1190346298L;

    /**
     * Customer identification
     * <b>FIELD: </b>customer_id, <b>TYPE: </b>int4,
     */
    @Id
    @Column(name = "customer_id", nullable = false, length = 32)
    private Integer customerId;

    /**
     * Store identification
     * <b>FIELD: </b>store_id, <b>TYPE: </b>int2,
     */
    @Basic
    @Column(name = "store_id", nullable = false, length = 16)
    private Integer storeId;

    /**
     * Customer first name
     * <b>FIELD: </b>first_name, <b>TYPE: </b>varchar,
     */
    @Basic
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    /**
     * Customer last name
     * <b>FIELD: </b>last_name, <b>TYPE: </b>varchar,
     */
    @Basic
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    /**
     * Customer email address
     * <b>FIELD: </b>email, <b>TYPE: </b>varchar,
     */
    @Basic
    @Column(name = "email", length = 50)
    private String email;

    /**
     * Customer address identification
     * <b>FIELD: </b>address_id, <b>TYPE: </b>int2,
     */
    @Basic
    @Column(name = "address_id", nullable = false, length = 16)
    private Integer addressId;

    /**
     * Indicate if customer is active (boolean)
     * <b>FIELD: </b>activebool, <b>TYPE: </b>bool,
     */
    @Basic
    @Column(name = "activebool", nullable = false)
    private Boolean activebool;

    /**
     * Creation date
     * <b>FIELD: </b>create_date, <b>TYPE: </b>date,
     */
    @Basic
    @Column(name = "create_date", nullable = false)
    private Date createDate;

    /**
     * Last update
     * <b>FIELD: </b>last_update, <b>TYPE: </b>timestamp,
     */
    @Basic
    @Column(name = "last_update")
    private Date lastUpdate;

    /**
     * Indicate if customer is active (integer)
     * <b>FIELD: </b>active, <b>TYPE: </b>int4,
     */
    @Basic
    @Column(name = "active", length = 32)
    private Integer active;

    /**
     * Customer identification
     * <b>FIELD: </b>customer_id
     * @return A {@link Integer} value
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * Customer identification
     * <b>FIELD: </b>customer_id
     * @param customerId A {@link Integer} value
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * Store identification
     * <b>FIELD: </b>store_id
     * @return A {@link Integer} value
     */
    public Integer getStoreId() {
        return storeId;
    }

    /**
     * Store identification
     * <b>FIELD: </b>store_id
     * @param storeId A {@link Integer} value
     */
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    /**
     * Customer first name
     * <b>FIELD: </b>first_name
     * @return A {@link String} value
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Customer first name
     * <b>FIELD: </b>first_name
     * @param firstName A {@link String} value
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Customer last name
     * <b>FIELD: </b>last_name
     * @return A {@link String} value
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Customer last name
     * <b>FIELD: </b>last_name
     * @param lastName A {@link String} value
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Customer email address
     * <b>FIELD: </b>email
     * @return A {@link String} value
     */
    public String getEmail() {
        return email;
    }

    /**
     * Customer email address
     * <b>FIELD: </b>email
     * @param email A {@link String} value
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Customer address identification
     * <b>FIELD: </b>address_id
     * @return A {@link Integer} value
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * Customer address identification
     * <b>FIELD: </b>address_id
     * @param addressId A {@link Integer} value
     */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    /**
     * Indicate if customer is active (boolean)
     * <b>FIELD: </b>activebool
     * @return A {@link Boolean} value
     */
    public Boolean getActivebool() {
        return activebool;
    }

    /**
     * Indicate if customer is active (boolean)
     * <b>FIELD: </b>activebool
     * @param activebool A {@link Boolean} value
     */
    public void setActivebool(Boolean activebool) {
        this.activebool = activebool;
    }

    /**
     * Creation date
     * <b>FIELD: </b>create_date
     * @return A {@link Date} value
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Creation date
     * <b>FIELD: </b>create_date
     * @param createDate A {@link Date} value
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    /**
     * Indicate if customer is active (integer)
     * <b>FIELD: </b>active
     * @return A {@link Integer} value
     */
    public Integer getActive() {
        return active;
    }

    /**
     * Indicate if customer is active (integer)
     * <b>FIELD: </b>active
     * @param active A {@link Integer} value
     */
    public void setActive(Integer active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
            customerId
            ,storeId
            ,firstName
            ,lastName
            ,email
            ,addressId
            ,activebool
            ,createDate
            ,lastUpdate
            ,active
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customerId", customerId)
                .add("storeId", storeId)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("email", email)
                .add("addressId", addressId)
                .add("activebool", activebool)
                .add("createDate", createDate)
                .add("lastUpdate", lastUpdate)
                .add("active", active)
                .toString();
    }
}
