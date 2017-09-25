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
 * 
 * <b>TABLE:</b> staff
 *
 * This entity was automatically created by JDB2DE tool
 *
 * @author Rodrigo Tavares
 */
@Entity
@Table(name = "staff")
@Generated(value = "jdb2de", date = "2017-09-24 20:44:38", comments = "You should not modify it by hand")
public class StaffModel implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -3253716894L;

    /**
     * 
     * <b>FIELD: </b>staff_id, <b>TYPE: </b>int4,
     */
    @Id
    @Column(name = "staff_id", nullable = false, length = 32)
    private Integer staffId;

    /**
     * 
     * <b>FIELD: </b>first_name, <b>TYPE: </b>varchar,
     */
    @Basic
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    /**
     * 
     * <b>FIELD: </b>last_name, <b>TYPE: </b>varchar,
     */
    @Basic
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    /**
     * 
     * <b>FIELD: </b>address_id, <b>TYPE: </b>int2,
     */
    @Basic
    @Column(name = "address_id", nullable = false, length = 16)
    private Integer addressId;

    /**
     * 
     * <b>FIELD: </b>email, <b>TYPE: </b>varchar,
     */
    @Basic
    @Column(name = "email", length = 50)
    private String email;

    /**
     * 
     * <b>FIELD: </b>store_id, <b>TYPE: </b>int2,
     */
    @Basic
    @Column(name = "store_id", nullable = false, length = 16)
    private Integer storeId;

    /**
     * 
     * <b>FIELD: </b>active, <b>TYPE: </b>bool,
     */
    @Basic
    @Column(name = "active", nullable = false)
    private Boolean active;

    /**
     * 
     * <b>FIELD: </b>username, <b>TYPE: </b>varchar,
     */
    @Basic
    @Column(name = "username", nullable = false, length = 16)
    private String username;

    /**
     * 
     * <b>FIELD: </b>password, <b>TYPE: </b>varchar,
     */
    @Basic
    @Column(name = "password", length = 40)
    private String password;

    /**
     * 
     * <b>FIELD: </b>last_update, <b>TYPE: </b>timestamp,
     */
    @Basic
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;

    /**
     * 
     * <b>FIELD: </b>picture, <b>TYPE: </b>bytea,
     */
    @Basic
    @Lob
    @Column(name = "picture")
    private byte[] picture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="address_id", referencedColumnName="address_id")
    private AddressModel address;

    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY)
    private Set<PaymentModel> paymentList;

    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY)
    private Set<RentalModel> rentalList;

    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY)
    private Set<StoreModel> storeList;

    /**
     * 
     * <b>FIELD: </b>staff_id
     * @return A {@link Integer} value
     */
    public Integer getStaffId() {
        return staffId;
    }

    /**
     * 
     * <b>FIELD: </b>staff_id
     * @param staffId A {@link Integer} value
     */
    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    /**
     * 
     * <b>FIELD: </b>first_name
     * @return A {@link String} value
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 
     * <b>FIELD: </b>first_name
     * @param firstName A {@link String} value
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * 
     * <b>FIELD: </b>last_name
     * @return A {@link String} value
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 
     * <b>FIELD: </b>last_name
     * @param lastName A {@link String} value
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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
     * <b>FIELD: </b>email
     * @return A {@link String} value
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * <b>FIELD: </b>email
     * @param email A {@link String} value
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * <b>FIELD: </b>store_id
     * @return A {@link Integer} value
     */
    public Integer getStoreId() {
        return storeId;
    }

    /**
     * 
     * <b>FIELD: </b>store_id
     * @param storeId A {@link Integer} value
     */
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    /**
     * 
     * <b>FIELD: </b>active
     * @return A {@link Boolean} value
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * 
     * <b>FIELD: </b>active
     * @param active A {@link Boolean} value
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * 
     * <b>FIELD: </b>username
     * @return A {@link String} value
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * <b>FIELD: </b>username
     * @param username A {@link String} value
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 
     * <b>FIELD: </b>password
     * @return A {@link String} value
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * <b>FIELD: </b>password
     * @param password A {@link String} value
     */
    public void setPassword(String password) {
        this.password = password;
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

    /**
     * 
     * <b>FIELD: </b>picture
     * @return A {@link byte[]} value
     */
    public byte[] getPicture() {
        return picture;
    }

    /**
     * 
     * <b>FIELD: </b>picture
     * @param picture A {@link byte[]} value
     */
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }

    public Set<PaymentModel> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(Set<PaymentModel> paymentList) {
        this.paymentList = paymentList;
    }

    public Set<RentalModel> getRentalList() {
        return rentalList;
    }

    public void setRentalList(Set<RentalModel> rentalList) {
        this.rentalList = rentalList;
    }

    public Set<StoreModel> getStoreList() {
        return storeList;
    }

    public void setStoreList(Set<StoreModel> storeList) {
        this.storeList = storeList;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
            staffId
            ,firstName
            ,lastName
            ,addressId
            ,email
            ,storeId
            ,active
            ,username
            ,password
            ,lastUpdate
            ,picture
            ,address
            ,paymentList
            ,rentalList
            ,storeList
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("staffId", staffId)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("addressId", addressId)
                .add("email", email)
                .add("storeId", storeId)
                .add("active", active)
                .add("username", username)
                .add("password", password)
                .add("lastUpdate", lastUpdate)
                .add("picture", picture)
                .add("address", address)
                .add("paymentList", paymentList)
                .add("rentalList", rentalList)
                .add("storeList", storeList)
                .toString();
    }
}
