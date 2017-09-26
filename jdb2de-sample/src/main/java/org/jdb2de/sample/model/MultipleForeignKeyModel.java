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
 * Sample multiple foreign key table
 * <b>TABLE:</b> multiple_foreign_key
 *
 * This entity was automatically created by JDB2DE tool
 *
 * @author Rodrigo Tavares
 */
@Entity
@Table(name = "multiple_foreign_key")
@Generated(value = "jdb2de", date = "2017-09-26 00:09:00", comments = "You should not modify it by hand")
public class MultipleForeignKeyModel implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -277894260L;

    /**
     * Staff identification
     * <b>FIELD: </b>staff_id, <b>TYPE: </b>int4,
     */
    @Id
    @Column(name = "staff_id", nullable = false, length = 32)
    private Integer staffId;

    /**
     * Staff identification one
     * <b>FIELD: </b>staff_one_id, <b>TYPE: </b>int4,
     */
    @Basic
    @Column(name = "staff_one_id", nullable = false, length = 32)
    private Integer staffOneId;

    /**
     * Staff identification two
     * <b>FIELD: </b>staff_two_id, <b>TYPE: </b>int4,
     */
    @Basic
    @Column(name = "staff_two_id", nullable = false, length = 32)
    private Integer staffTwoId;

    /**
     * Staff identification three
     * <b>FIELD: </b>staff_three_id, <b>TYPE: </b>int4,
     */
    @Basic
    @Column(name = "staff_three_id", nullable = false, length = 32)
    private Integer staffThreeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="staff_id", referencedColumnName="staff_id")
    private StaffModel staff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="staff_one_id", referencedColumnName="staff_id")
    private StaffModel staffOne;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="staff_two_id", referencedColumnName="staff_id")
    private StaffModel staffTwo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="staff_three_id", referencedColumnName="staff_id")
    private StaffModel staffThree;

    /**
     * Staff identification
     * <b>FIELD: </b>staff_id
     * @return A {@link Integer} value
     */
    public Integer getStaffId() {
        return staffId;
    }

    /**
     * Staff identification
     * <b>FIELD: </b>staff_id
     * @param staffId A {@link Integer} value
     */
    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    /**
     * Staff identification one
     * <b>FIELD: </b>staff_one_id
     * @return A {@link Integer} value
     */
    public Integer getStaffOneId() {
        return staffOneId;
    }

    /**
     * Staff identification one
     * <b>FIELD: </b>staff_one_id
     * @param staffOneId A {@link Integer} value
     */
    public void setStaffOneId(Integer staffOneId) {
        this.staffOneId = staffOneId;
    }

    /**
     * Staff identification two
     * <b>FIELD: </b>staff_two_id
     * @return A {@link Integer} value
     */
    public Integer getStaffTwoId() {
        return staffTwoId;
    }

    /**
     * Staff identification two
     * <b>FIELD: </b>staff_two_id
     * @param staffTwoId A {@link Integer} value
     */
    public void setStaffTwoId(Integer staffTwoId) {
        this.staffTwoId = staffTwoId;
    }

    /**
     * Staff identification three
     * <b>FIELD: </b>staff_three_id
     * @return A {@link Integer} value
     */
    public Integer getStaffThreeId() {
        return staffThreeId;
    }

    /**
     * Staff identification three
     * <b>FIELD: </b>staff_three_id
     * @param staffThreeId A {@link Integer} value
     */
    public void setStaffThreeId(Integer staffThreeId) {
        this.staffThreeId = staffThreeId;
    }

    public StaffModel getStaff() {
        return staff;
    }

    public void setStaff(StaffModel staff) {
        this.staff = staff;
    }

    public StaffModel getStaffOne() {
        return staffOne;
    }

    public void setStaffOne(StaffModel staffOne) {
        this.staffOne = staffOne;
    }

    public StaffModel getStaffTwo() {
        return staffTwo;
    }

    public void setStaffTwo(StaffModel staffTwo) {
        this.staffTwo = staffTwo;
    }

    public StaffModel getStaffThree() {
        return staffThree;
    }

    public void setStaffThree(StaffModel staffThree) {
        this.staffThree = staffThree;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
            staffId
            ,staffOneId
            ,staffTwoId
            ,staffThreeId
            ,staff
            ,staffOne
            ,staffTwo
            ,staffThree
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("staffId", staffId)
                .add("staffOneId", staffOneId)
                .add("staffTwoId", staffTwoId)
                .add("staffThreeId", staffThreeId)
                .add("staff", staff)
                .add("staffOne", staffOne)
                .add("staffTwo", staffTwo)
                .add("staffThree", staffThree)
                .toString();
    }
}
