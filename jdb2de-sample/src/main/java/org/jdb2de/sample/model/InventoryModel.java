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
 * <b>TABLE:</b> inventory
 *
 * This entity was automatically created by JDB2DE tool
 *
 * @author Rodrigo Tavares
 */
@Entity
@Table(name = "inventory")
@Generated(value = "jdb2de", date = "2017-09-22 00:02:50", comments = "You should not modify it by hand")
public class InventoryModel implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -1839440278L;

    /**
     * 
     * <b>FIELD: </b>inventory_id, <b>TYPE: </b>int4,
     */
    @Id
    @Column(name = "inventory_id", nullable = false, length = 32)
    private Integer inventoryId;

    /**
     * 
     * <b>FIELD: </b>film_id, <b>TYPE: </b>int2,
     */
    @Basic
    @Column(name = "film_id", nullable = false, length = 16)
    private Integer filmId;

    /**
     * 
     * <b>FIELD: </b>store_id, <b>TYPE: </b>int2,
     */
    @Basic
    @Column(name = "store_id", nullable = false, length = 16)
    private Integer storeId;

    /**
     * 
     * <b>FIELD: </b>last_update, <b>TYPE: </b>timestamp,
     */
    @Basic
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;

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
     * <b>FIELD: </b>film_id
     * @return A {@link Integer} value
     */
    public Integer getFilmId() {
        return filmId;
    }

    /**
     * 
     * <b>FIELD: </b>film_id
     * @param filmId A {@link Integer} value
     */
    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
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
            inventoryId
            ,filmId
            ,storeId
            ,lastUpdate
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("inventoryId", inventoryId)
                .add("filmId", filmId)
                .add("storeId", storeId)
                .add("lastUpdate", lastUpdate)
                .toString();
    }
}
