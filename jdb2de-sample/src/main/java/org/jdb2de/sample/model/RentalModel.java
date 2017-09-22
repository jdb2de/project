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
 * <b>TABLE:</b> rental
 *
 * This entity was automatically created by JDB2DE tool
 *
 * @author Rodrigo Tavares
 */
@Entity
@Table(name = "rental")
@Generated(value = "jdb2de", date = "2017-09-22 00:02:50", comments = "You should not modify it by hand")
public class RentalModel implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -2243937606L;

    /**
     * 
     * <b>FIELD: </b>rental_id, <b>TYPE: </b>int4,
     */
    @Id
    @Column(name = "rental_id", nullable = false, length = 32)
    private Integer rentalId;

    /**
     * 
     * <b>FIELD: </b>rental_date, <b>TYPE: </b>timestamp,
     */
    @Basic
    @Column(name = "rental_date", nullable = false)
    private Date rentalDate;

    /**
     * 
     * <b>FIELD: </b>inventory_id, <b>TYPE: </b>int4,
     */
    @Basic
    @Column(name = "inventory_id", nullable = false, length = 32)
    private Integer inventoryId;

    /**
     * 
     * <b>FIELD: </b>customer_id, <b>TYPE: </b>int2,
     */
    @Basic
    @Column(name = "customer_id", nullable = false, length = 16)
    private Integer customerId;

    /**
     * 
     * <b>FIELD: </b>return_date, <b>TYPE: </b>timestamp,
     */
    @Basic
    @Column(name = "return_date")
    private Date returnDate;

    /**
     * 
     * <b>FIELD: </b>staff_id, <b>TYPE: </b>int2,
     */
    @Basic
    @Column(name = "staff_id", nullable = false, length = 16)
    private Integer staffId;

    /**
     * 
     * <b>FIELD: </b>last_update, <b>TYPE: </b>timestamp,
     */
    @Basic
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;

    /**
     * 
     * <b>FIELD: </b>rental_id
     * @return A {@link Integer} value
     */
    public Integer getRentalId() {
        return rentalId;
    }

    /**
     * 
     * <b>FIELD: </b>rental_id
     * @param rentalId A {@link Integer} value
     */
    public void setRentalId(Integer rentalId) {
        this.rentalId = rentalId;
    }

    /**
     * 
     * <b>FIELD: </b>rental_date
     * @return A {@link Date} value
     */
    public Date getRentalDate() {
        return rentalDate;
    }

    /**
     * 
     * <b>FIELD: </b>rental_date
     * @param rentalDate A {@link Date} value
     */
    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    /**
     * 
     * <b>FIELD: </b>inventory_id
     * @return A {@link Integer} value
     */
    public Integer getInventoryId() {
        return inventoryId;
    }

    /**
     * 
     * <b>FIELD: </b>inventory_id
     * @param inventoryId A {@link Integer} value
     */
    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    /**
     * 
     * <b>FIELD: </b>customer_id
     * @return A {@link Integer} value
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * 
     * <b>FIELD: </b>customer_id
     * @param customerId A {@link Integer} value
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 
     * <b>FIELD: </b>return_date
     * @return A {@link Date} value
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     * 
     * <b>FIELD: </b>return_date
     * @param returnDate A {@link Date} value
     */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

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
            rentalId
            ,rentalDate
            ,inventoryId
            ,customerId
            ,returnDate
            ,staffId
            ,lastUpdate
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("rentalId", rentalId)
                .add("rentalDate", rentalDate)
                .add("inventoryId", inventoryId)
                .add("customerId", customerId)
                .add("returnDate", returnDate)
                .add("staffId", staffId)
                .add("lastUpdate", lastUpdate)
                .toString();
    }
}
