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
 * Available film
 * <b>TABLE:</b> film
 *
 * This entity was automatically created by JDB2DE tool
 *
 * @author Rodrigo Tavares
 */
@Entity
@Table(name = "film")
@Generated(value = "jdb2de", date = "2017-09-24 02:13:32", comments = "You should not modify it by hand")
public class FilmModel implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 996376058L;

    /**
     * Film identification
     * <b>FIELD: </b>film_id, <b>TYPE: </b>int4,
     */
    @Id
    @Column(name = "film_id", nullable = false, length = 32)
    private Integer filmId;

    /**
     * Film title
     * <b>FIELD: </b>title, <b>TYPE: </b>varchar,
     */
    @Basic
    @Column(name = "title", nullable = false, length = 255)
    private String title;

    /**
     * Film description
     * <b>FIELD: </b>description, <b>TYPE: </b>text,
     */
    @Basic
    @Column(name = "description")
    private String description;

    /**
     * Film release year
     * <b>FIELD: </b>release_year, <b>TYPE: </b>int2,
     */
    @Basic
    @Column(name = "release_year", length = 16)
    private Integer releaseYear;

    /**
     * Language identification
     * <b>FIELD: </b>language_id, <b>TYPE: </b>int2,
     */
    @Basic
    @Column(name = "language_id", nullable = false, length = 16)
    private Integer languageId;

    /**
     * Rental duration
     * <b>FIELD: </b>rental_duration, <b>TYPE: </b>int2,
     */
    @Basic
    @Column(name = "rental_duration", nullable = false, length = 16)
    private Integer rentalDuration;

    /**
     * Rental rate
     * <b>FIELD: </b>rental_rate, <b>TYPE: </b>numeric,
     */
    @Basic
    @Column(name = "rental_rate", nullable = false, length = 4)
    private BigDecimal rentalRate;

    /**
     * Rental length
     * <b>FIELD: </b>length, <b>TYPE: </b>int2,
     */
    @Basic
    @Column(name = "length", length = 16)
    private Integer length;

    /**
     * Replacement cost
     * <b>FIELD: </b>replacement_cost, <b>TYPE: </b>numeric,
     */
    @Basic
    @Column(name = "replacement_cost", nullable = false, length = 5)
    private BigDecimal replacementCost;

    /**
     * Rating
     * <b>FIELD: </b>rating, <b>TYPE: </b>varchar,
     */
    @Basic
    @Column(name = "rating", length = 1)
    private String rating;

    /**
     * Last update
     * <b>FIELD: </b>last_update, <b>TYPE: </b>timestamp,
     */
    @Basic
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;

    /**
     * Special features
     * <b>FIELD: </b>special_features, <b>TYPE: </b>_text,
     */
    @Basic
    @Column(name = "special_features")
    private String specialFeatures;

    /**
     * Full text
     * <b>FIELD: </b>fulltext, <b>TYPE: </b>tsvector,
     */
    @Basic
    @Column(name = "fulltext", nullable = false)
    private String fulltext;

    @ManyToOne
    @JoinColumn(name="language_id", referencedColumnName="language_id")
    private LanguageModel language;

    /**
     * Film identification
     * <b>FIELD: </b>film_id
     * @return A {@link Integer} value
     */
    public Integer getFilmId() {
        return filmId;
    }

    /**
     * Film identification
     * <b>FIELD: </b>film_id
     * @param filmId A {@link Integer} value
     */
    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    /**
     * Film title
     * <b>FIELD: </b>title
     * @return A {@link String} value
     */
    public String getTitle() {
        return title;
    }

    /**
     * Film title
     * <b>FIELD: </b>title
     * @param title A {@link String} value
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Film description
     * <b>FIELD: </b>description
     * @return A {@link String} value
     */
    public String getDescription() {
        return description;
    }

    /**
     * Film description
     * <b>FIELD: </b>description
     * @param description A {@link String} value
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Film release year
     * <b>FIELD: </b>release_year
     * @return A {@link Integer} value
     */
    public Integer getReleaseYear() {
        return releaseYear;
    }

    /**
     * Film release year
     * <b>FIELD: </b>release_year
     * @param releaseYear A {@link Integer} value
     */
    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Language identification
     * <b>FIELD: </b>language_id
     * @return A {@link Integer} value
     */
    public Integer getLanguageId() {
        return languageId;
    }

    /**
     * Language identification
     * <b>FIELD: </b>language_id
     * @param languageId A {@link Integer} value
     */
    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    /**
     * Rental duration
     * <b>FIELD: </b>rental_duration
     * @return A {@link Integer} value
     */
    public Integer getRentalDuration() {
        return rentalDuration;
    }

    /**
     * Rental duration
     * <b>FIELD: </b>rental_duration
     * @param rentalDuration A {@link Integer} value
     */
    public void setRentalDuration(Integer rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    /**
     * Rental rate
     * <b>FIELD: </b>rental_rate
     * @return A {@link BigDecimal} value
     */
    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    /**
     * Rental rate
     * <b>FIELD: </b>rental_rate
     * @param rentalRate A {@link BigDecimal} value
     */
    public void setRentalRate(BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }

    /**
     * Rental length
     * <b>FIELD: </b>length
     * @return A {@link Integer} value
     */
    public Integer getLength() {
        return length;
    }

    /**
     * Rental length
     * <b>FIELD: </b>length
     * @param length A {@link Integer} value
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     * Replacement cost
     * <b>FIELD: </b>replacement_cost
     * @return A {@link BigDecimal} value
     */
    public BigDecimal getReplacementCost() {
        return replacementCost;
    }

    /**
     * Replacement cost
     * <b>FIELD: </b>replacement_cost
     * @param replacementCost A {@link BigDecimal} value
     */
    public void setReplacementCost(BigDecimal replacementCost) {
        this.replacementCost = replacementCost;
    }

    /**
     * Rating
     * <b>FIELD: </b>rating
     * @return A {@link String} value
     */
    public String getRating() {
        return rating;
    }

    /**
     * Rating
     * <b>FIELD: </b>rating
     * @param rating A {@link String} value
     */
    public void setRating(String rating) {
        this.rating = rating;
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
     * Special features
     * <b>FIELD: </b>special_features
     * @return A {@link String} value
     */
    public String getSpecialFeatures() {
        return specialFeatures;
    }

    /**
     * Special features
     * <b>FIELD: </b>special_features
     * @param specialFeatures A {@link String} value
     */
    public void setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    /**
     * Full text
     * <b>FIELD: </b>fulltext
     * @return A {@link String} value
     */
    public String getFulltext() {
        return fulltext;
    }

    /**
     * Full text
     * <b>FIELD: </b>fulltext
     * @param fulltext A {@link String} value
     */
    public void setFulltext(String fulltext) {
        this.fulltext = fulltext;
    }

    public LanguageModel getLanguage() {
        return language;
    }

    public void setLanguage(LanguageModel language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
            filmId
            ,title
            ,description
            ,releaseYear
            ,languageId
            ,rentalDuration
            ,rentalRate
            ,length
            ,replacementCost
            ,rating
            ,lastUpdate
            ,specialFeatures
            ,fulltext
            ,language
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("filmId", filmId)
                .add("title", title)
                .add("description", description)
                .add("releaseYear", releaseYear)
                .add("languageId", languageId)
                .add("rentalDuration", rentalDuration)
                .add("rentalRate", rentalRate)
                .add("length", length)
                .add("replacementCost", replacementCost)
                .add("rating", rating)
                .add("lastUpdate", lastUpdate)
                .add("specialFeatures", specialFeatures)
                .add("fulltext", fulltext)
                .add("language", language)
                .toString();
    }
}
