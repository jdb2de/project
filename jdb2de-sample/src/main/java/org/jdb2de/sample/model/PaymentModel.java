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
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * <b>TABLE:</b> payment
 *
 * This entity was automatically created by JDB2DE tool
 *
 * @author Rodrigo Tavares
 */
@Entity
@Table(name = "payment")
@Generated(value = "jdb2de", date = "2017-09-26 00:09:00", comments = "You should not modify it by hand")
public class PaymentModel implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -2173393514L;

    /**
     * 
     * <b>FIELD: </b>payment_id, <b>TYPE: </b>int4,
     */
    @Id
    @Column(name = "payment_id", nullable = false, length = 32)
    private Integer paymentId;

    /**
     * 
     * <b>FIELD: </b>customer_id, <b>TYPE: </b>int2,
     */
    @Basic
    @Column(name = "customer_id", nullable = false, length = 16)
    private Integer customerId;

    /**
     * 
     * <b>FIELD: </b>staff_id, <b>TYPE: </b>int2,
     */
    @Basic
    @Column(name = "staff_id", nullable = false, length = 16)
    private Integer staffId;

    /**
     * 
     * <b>FIELD: </b>rental_id, <b>TYPE: </b>int4,
     */
    @Basic
    @Column(name = "rental_id", nullable = false, length = 32)
    private Integer rentalId;

    /**
     * 
     * <b>FIELD: </b>amount, <b>TYPE: </b>numeric,
     */
    @Basic
    @Column(name = "amount", nullable = false, length = 5)
    private BigDecimal amount;

    /**
     * 
     * <b>FIELD: </b>payment_date, <b>TYPE: </b>timestamp,
     */
    @Basic
    @Column(name = "payment_date", nullable = false)
    private Date paymentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id", referencedColumnName="customer_id")
    private CustomerModel customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="rental_id", referencedColumnName="rental_id")
    private RentalModel rental;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="staff_id", referencedColumnName="staff_id")
    private StaffModel staff;

    /**
     * 
     * <b>FIELD: </b>payment_id
     * @return A {@link Integer} value
     */
    public Integer getPaymentId() {
        return paymentId;
    }

    /**
     * 
     * <b>FIELD: </b>payment_id
     * @param paymentId A {@link Integer} value
     */
    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
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
     * <b>FIELD: </b>amount
     * @return A {@link BigDecimal} value
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 
     * <b>FIELD: </b>amount
     * @param amount A {@link BigDecimal} value
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 
     * <b>FIELD: </b>payment_date
     * @return A {@link Date} value
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * 
     * <b>FIELD: </b>payment_date
     * @param paymentDate A {@link Date} value
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public RentalModel getRental() {
        return rental;
    }

    public void setRental(RentalModel rental) {
        this.rental = rental;
    }

    public StaffModel getStaff() {
        return staff;
    }

    public void setStaff(StaffModel staff) {
        this.staff = staff;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
            paymentId
            ,customerId
            ,staffId
            ,rentalId
            ,amount
            ,paymentDate
            ,customer
            ,rental
            ,staff
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("paymentId", paymentId)
                .add("customerId", customerId)
                .add("staffId", staffId)
                .add("rentalId", rentalId)
                .add("amount", amount)
                .add("paymentDate", paymentDate)
                .add("customer", customer)
                .add("rental", rental)
                .add("staff", staff)
                .toString();
    }
}
