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
import org.postgresql.geometric.*;
import org.postgresql.util.PGInterval;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * Customer registration
 * <b>TABLE:</b> data_types
 *
 * This entity was automatically created by JDB2DE tool
 *
 * @author Rodrigo Tavares
 */
@Entity
@Table(name = "data_types")
@Generated(value = "jdb2de", date = "2017-09-24 20:44:38", comments = "You should not modify it by hand")
public class DataTypesModel implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -3084963068L;

    /**
     * 
     * <b>FIELD: </b>id, <b>TYPE: </b>int4,
     */
    @Id
    @Column(name = "id", nullable = false, length = 32)
    private Integer id;

    /**
     * Signed eight-byte integer
     * <b>FIELD: </b>tp_bigint, <b>TYPE: </b>int8,
     */
    @Basic
    @Column(name = "tp_bigint", length = 64)
    private Long tpBigint;

    /**
     * Auto incrementing eight-byte integer
     * <b>FIELD: </b>tp_bigserial, <b>TYPE: </b>int8,
     */
    @Basic
    @Column(name = "tp_bigserial", nullable = false, length = 64)
    private Long tpBigserial;

    /**
     * Fixed-length bit string
     * <b>FIELD: </b>tp_bit, <b>TYPE: </b>bit,
     */
    @Basic
    @Column(name = "tp_bit", length = 1)
    private Boolean tpBit;

    /**
     * Variable-length bit string
     * <b>FIELD: </b>tp_bit_varying, <b>TYPE: </b>varbit,
     */
    @Basic
    @Column(name = "tp_bit_varying", length = 10)
    private Object tpBitVarying;

    /**
     * Logical Boolean (true/false)
     * <b>FIELD: </b>tp_boolean, <b>TYPE: </b>bool,
     */
    @Basic
    @Column(name = "tp_boolean")
    private Boolean tpBoolean;

    /**
     * Rectangular box on a plane
     * <b>FIELD: </b>tp_box, <b>TYPE: </b>box,
     */
    @Basic
    @Column(name = "tp_box")
    private PGbox tpBox;

    /**
     * Binary data ("byte array")
     * <b>FIELD: </b>tp_bytea, <b>TYPE: </b>bytea,
     */
    @Basic
    @Lob
    @Column(name = "tp_bytea")
    private byte[] tpBytea;

    /**
     * Fixed-length character string
     * <b>FIELD: </b>tp_character, <b>TYPE: </b>bpchar,
     */
    @Basic
    @Column(name = "tp_character", length = 20)
    private String tpCharacter;

    /**
     * Variable-length character string
     * <b>FIELD: </b>tp_character_varying, <b>TYPE: </b>varchar,
     */
    @Basic
    @Column(name = "tp_character_varying", length = 30)
    private String tpCharacterVarying;

    /**
     * IPv4 or IPv6 network address
     * <b>FIELD: </b>tp_cidr, <b>TYPE: </b>cidr,
     */
    @Basic
    @Column(name = "tp_cidr")
    private Object tpCidr;

    /**
     * Circle on a plane
     * <b>FIELD: </b>tp_circle, <b>TYPE: </b>circle,
     */
    @Basic
    @Column(name = "tp_circle")
    private PGcircle tpCircle;

    /**
     * Calendar date (year, month, day)
     * <b>FIELD: </b>tp_date, <b>TYPE: </b>date,
     */
    @Basic
    @Column(name = "tp_date")
    private Date tpDate;

    /**
     * Double precision floating-point number (8 bytes)
     * <b>FIELD: </b>tp_double_precision, <b>TYPE: </b>float8,
     */
    @Basic
    @Column(name = "tp_double_precision", length = 53)
    private BigDecimal tpDoublePrecision;

    /**
     * IPv4 or IPv6 host address
     * <b>FIELD: </b>tp_inet, <b>TYPE: </b>inet,
     */
    @Basic
    @Column(name = "tp_inet")
    private Object tpInet;

    /**
     * Signed four-byte integer
     * <b>FIELD: </b>tp_integer, <b>TYPE: </b>int4,
     */
    @Basic
    @Column(name = "tp_integer", length = 32)
    private Integer tpInteger;

    /**
     * Time span
     * <b>FIELD: </b>tp_interval, <b>TYPE: </b>interval,
     */
    @Basic
    @Column(name = "tp_interval")
    private PGInterval tpInterval;

    /**
     * JSON data
     * <b>FIELD: </b>tp_json, <b>TYPE: </b>json,
     */
    @Basic
    @Column(name = "tp_json")
    private String tpJson;

    /**
     * Infinite line on a plane
     * <b>FIELD: </b>tp_line, <b>TYPE: </b>line,
     */
    @Basic
    @Column(name = "tp_line")
    private PGline tpLine;

    /**
     * Line segment on a plane
     * <b>FIELD: </b>tp_lseg, <b>TYPE: </b>lseg,
     */
    @Basic
    @Column(name = "tp_lseg")
    private PGlseg tpLseg;

    /**
     * MAC (Media Access Control) address
     * <b>FIELD: </b>tp_macaddr, <b>TYPE: </b>macaddr,
     */
    @Basic
    @Column(name = "tp_macaddr")
    private Object tpMacaddr;

    /**
     * Currency amount
     * <b>FIELD: </b>tp_money, <b>TYPE: </b>money,
     */
    @Basic
    @Column(name = "tp_money")
    private BigDecimal tpMoney;

    /**
     * Exact numeric of selectable precision
     * <b>FIELD: </b>tp_numeric, <b>TYPE: </b>numeric,
     */
    @Basic
    @Column(name = "tp_numeric", length = 10)
    private BigDecimal tpNumeric;

    /**
     * Geometric path on a plane
     * <b>FIELD: </b>tp_path, <b>TYPE: </b>path,
     */
    @Basic
    @Column(name = "tp_path")
    private PGpath tpPath;

    /**
     * Geometric point on a plane
     * <b>FIELD: </b>tp_point, <b>TYPE: </b>point,
     */
    @Basic
    @Column(name = "tp_point")
    private PGpoint tpPoint;

    /**
     * Closed geometric path on a plane
     * <b>FIELD: </b>tp_polygon, <b>TYPE: </b>polygon,
     */
    @Basic
    @Column(name = "tp_polygon")
    private PGpolygon tpPolygon;

    /**
     * Single precision floating-point number (4 bytes)
     * <b>FIELD: </b>tp_real, <b>TYPE: </b>float4,
     */
    @Basic
    @Column(name = "tp_real", length = 24)
    private BigDecimal tpReal;

    /**
     * Signed two-byte integer
     * <b>FIELD: </b>tp_smallint, <b>TYPE: </b>int2,
     */
    @Basic
    @Column(name = "tp_smallint", length = 16)
    private Integer tpSmallint;

    /**
     * Auto incrementing two-byte integer
     * <b>FIELD: </b>tp_smallserial, <b>TYPE: </b>int2,
     */
    @Basic
    @Column(name = "tp_smallserial", nullable = false, length = 16)
    private Integer tpSmallserial;

    /**
     * Auto incrementing four-byte integer
     * <b>FIELD: </b>tp_serial, <b>TYPE: </b>int4,
     */
    @Basic
    @Column(name = "tp_serial", nullable = false, length = 32)
    private Integer tpSerial;

    /**
     * Variable-length character string
     * <b>FIELD: </b>tp_text, <b>TYPE: </b>text,
     */
    @Basic
    @Column(name = "tp_text")
    private String tpText;

    /**
     * Time of day (no time zone)
     * <b>FIELD: </b>tp_time_without_time_zone, <b>TYPE: </b>time,
     */
    @Basic
    @Column(name = "tp_time_without_time_zone")
    private Date tpTimeWithoutTimeZone;

    /**
     * Time of day, including time zone
     * <b>FIELD: </b>tp_time_with_time_zone, <b>TYPE: </b>timetz,
     */
    @Basic
    @Column(name = "tp_time_with_time_zone")
    private Date tpTimeWithTimeZone;

    /**
     * Date and time (no time zone)
     * <b>FIELD: </b>tp_timestamp_without_time_zone, <b>TYPE: </b>timestamp,
     */
    @Basic
    @Column(name = "tp_timestamp_without_time_zone")
    private Date tpTimestampWithoutTimeZone;

    /**
     * Date and time, including time zone
     * <b>FIELD: </b>tp_timestamp_with_time_zone, <b>TYPE: </b>timestamptz,
     */
    @Basic
    @Column(name = "tp_timestamp_with_time_zone")
    private Date tpTimestampWithTimeZone;

    /**
     * Text search query
     * <b>FIELD: </b>tp_tsquery, <b>TYPE: </b>tsquery,
     */
    @Basic
    @Column(name = "tp_tsquery")
    private String tpTsquery;

    /**
     * Text search document
     * <b>FIELD: </b>tp_tsvector, <b>TYPE: </b>tsvector,
     */
    @Basic
    @Column(name = "tp_tsvector")
    private String tpTsvector;

    /**
     * User-level transaction ID snapshot
     * <b>FIELD: </b>tp_txid_snapshot, <b>TYPE: </b>txid_snapshot,
     */
    @Basic
    @Column(name = "tp_txid_snapshot")
    private Object tpTxidSnapshot;

    /**
     * Universally unique identifier
     * <b>FIELD: </b>tp_uuid, <b>TYPE: </b>uuid,
     */
    @Basic
    @Column(name = "tp_uuid")
    private UUID tpUuid;

    /**
     * XML data
     * <b>FIELD: </b>tp_xml, <b>TYPE: </b>xml,
     */
    @Basic
    @Column(name = "tp_xml")
    private String tpXml;

    /**
     * 
     * <b>FIELD: </b>id
     * @return A {@link Integer} value
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * <b>FIELD: </b>id
     * @param id A {@link Integer} value
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Signed eight-byte integer
     * <b>FIELD: </b>tp_bigint
     * @return A {@link Long} value
     */
    public Long getTpBigint() {
        return tpBigint;
    }

    /**
     * Signed eight-byte integer
     * <b>FIELD: </b>tp_bigint
     * @param tpBigint A {@link Long} value
     */
    public void setTpBigint(Long tpBigint) {
        this.tpBigint = tpBigint;
    }

    /**
     * Auto incrementing eight-byte integer
     * <b>FIELD: </b>tp_bigserial
     * @return A {@link Long} value
     */
    public Long getTpBigserial() {
        return tpBigserial;
    }

    /**
     * Auto incrementing eight-byte integer
     * <b>FIELD: </b>tp_bigserial
     * @param tpBigserial A {@link Long} value
     */
    public void setTpBigserial(Long tpBigserial) {
        this.tpBigserial = tpBigserial;
    }

    /**
     * Fixed-length bit string
     * <b>FIELD: </b>tp_bit
     * @return A {@link Boolean} value
     */
    public Boolean getTpBit() {
        return tpBit;
    }

    /**
     * Fixed-length bit string
     * <b>FIELD: </b>tp_bit
     * @param tpBit A {@link Boolean} value
     */
    public void setTpBit(Boolean tpBit) {
        this.tpBit = tpBit;
    }

    /**
     * Variable-length bit string
     * <b>FIELD: </b>tp_bit_varying
     * @return A {@link Object} value
     */
    public Object getTpBitVarying() {
        return tpBitVarying;
    }

    /**
     * Variable-length bit string
     * <b>FIELD: </b>tp_bit_varying
     * @param tpBitVarying A {@link Object} value
     */
    public void setTpBitVarying(Object tpBitVarying) {
        this.tpBitVarying = tpBitVarying;
    }

    /**
     * Logical Boolean (true/false)
     * <b>FIELD: </b>tp_boolean
     * @return A {@link Boolean} value
     */
    public Boolean getTpBoolean() {
        return tpBoolean;
    }

    /**
     * Logical Boolean (true/false)
     * <b>FIELD: </b>tp_boolean
     * @param tpBoolean A {@link Boolean} value
     */
    public void setTpBoolean(Boolean tpBoolean) {
        this.tpBoolean = tpBoolean;
    }

    /**
     * Rectangular box on a plane
     * <b>FIELD: </b>tp_box
     * @return A {@link PGbox} value
     */
    public PGbox getTpBox() {
        return tpBox;
    }

    /**
     * Rectangular box on a plane
     * <b>FIELD: </b>tp_box
     * @param tpBox A {@link PGbox} value
     */
    public void setTpBox(PGbox tpBox) {
        this.tpBox = tpBox;
    }

    /**
     * Binary data ("byte array")
     * <b>FIELD: </b>tp_bytea
     * @return A {@link byte[]} value
     */
    public byte[] getTpBytea() {
        return tpBytea;
    }

    /**
     * Binary data ("byte array")
     * <b>FIELD: </b>tp_bytea
     * @param tpBytea A {@link byte[]} value
     */
    public void setTpBytea(byte[] tpBytea) {
        this.tpBytea = tpBytea;
    }

    /**
     * Fixed-length character string
     * <b>FIELD: </b>tp_character
     * @return A {@link String} value
     */
    public String getTpCharacter() {
        return tpCharacter;
    }

    /**
     * Fixed-length character string
     * <b>FIELD: </b>tp_character
     * @param tpCharacter A {@link String} value
     */
    public void setTpCharacter(String tpCharacter) {
        this.tpCharacter = tpCharacter;
    }

    /**
     * Variable-length character string
     * <b>FIELD: </b>tp_character_varying
     * @return A {@link String} value
     */
    public String getTpCharacterVarying() {
        return tpCharacterVarying;
    }

    /**
     * Variable-length character string
     * <b>FIELD: </b>tp_character_varying
     * @param tpCharacterVarying A {@link String} value
     */
    public void setTpCharacterVarying(String tpCharacterVarying) {
        this.tpCharacterVarying = tpCharacterVarying;
    }

    /**
     * IPv4 or IPv6 network address
     * <b>FIELD: </b>tp_cidr
     * @return A {@link Object} value
     */
    public Object getTpCidr() {
        return tpCidr;
    }

    /**
     * IPv4 or IPv6 network address
     * <b>FIELD: </b>tp_cidr
     * @param tpCidr A {@link Object} value
     */
    public void setTpCidr(Object tpCidr) {
        this.tpCidr = tpCidr;
    }

    /**
     * Circle on a plane
     * <b>FIELD: </b>tp_circle
     * @return A {@link PGcircle} value
     */
    public PGcircle getTpCircle() {
        return tpCircle;
    }

    /**
     * Circle on a plane
     * <b>FIELD: </b>tp_circle
     * @param tpCircle A {@link PGcircle} value
     */
    public void setTpCircle(PGcircle tpCircle) {
        this.tpCircle = tpCircle;
    }

    /**
     * Calendar date (year, month, day)
     * <b>FIELD: </b>tp_date
     * @return A {@link Date} value
     */
    public Date getTpDate() {
        return tpDate;
    }

    /**
     * Calendar date (year, month, day)
     * <b>FIELD: </b>tp_date
     * @param tpDate A {@link Date} value
     */
    public void setTpDate(Date tpDate) {
        this.tpDate = tpDate;
    }

    /**
     * Double precision floating-point number (8 bytes)
     * <b>FIELD: </b>tp_double_precision
     * @return A {@link BigDecimal} value
     */
    public BigDecimal getTpDoublePrecision() {
        return tpDoublePrecision;
    }

    /**
     * Double precision floating-point number (8 bytes)
     * <b>FIELD: </b>tp_double_precision
     * @param tpDoublePrecision A {@link BigDecimal} value
     */
    public void setTpDoublePrecision(BigDecimal tpDoublePrecision) {
        this.tpDoublePrecision = tpDoublePrecision;
    }

    /**
     * IPv4 or IPv6 host address
     * <b>FIELD: </b>tp_inet
     * @return A {@link Object} value
     */
    public Object getTpInet() {
        return tpInet;
    }

    /**
     * IPv4 or IPv6 host address
     * <b>FIELD: </b>tp_inet
     * @param tpInet A {@link Object} value
     */
    public void setTpInet(Object tpInet) {
        this.tpInet = tpInet;
    }

    /**
     * Signed four-byte integer
     * <b>FIELD: </b>tp_integer
     * @return A {@link Integer} value
     */
    public Integer getTpInteger() {
        return tpInteger;
    }

    /**
     * Signed four-byte integer
     * <b>FIELD: </b>tp_integer
     * @param tpInteger A {@link Integer} value
     */
    public void setTpInteger(Integer tpInteger) {
        this.tpInteger = tpInteger;
    }

    /**
     * Time span
     * <b>FIELD: </b>tp_interval
     * @return A {@link PGInterval} value
     */
    public PGInterval getTpInterval() {
        return tpInterval;
    }

    /**
     * Time span
     * <b>FIELD: </b>tp_interval
     * @param tpInterval A {@link PGInterval} value
     */
    public void setTpInterval(PGInterval tpInterval) {
        this.tpInterval = tpInterval;
    }

    /**
     * JSON data
     * <b>FIELD: </b>tp_json
     * @return A {@link String} value
     */
    public String getTpJson() {
        return tpJson;
    }

    /**
     * JSON data
     * <b>FIELD: </b>tp_json
     * @param tpJson A {@link String} value
     */
    public void setTpJson(String tpJson) {
        this.tpJson = tpJson;
    }

    /**
     * Infinite line on a plane
     * <b>FIELD: </b>tp_line
     * @return A {@link PGline} value
     */
    public PGline getTpLine() {
        return tpLine;
    }

    /**
     * Infinite line on a plane
     * <b>FIELD: </b>tp_line
     * @param tpLine A {@link PGline} value
     */
    public void setTpLine(PGline tpLine) {
        this.tpLine = tpLine;
    }

    /**
     * Line segment on a plane
     * <b>FIELD: </b>tp_lseg
     * @return A {@link PGlseg} value
     */
    public PGlseg getTpLseg() {
        return tpLseg;
    }

    /**
     * Line segment on a plane
     * <b>FIELD: </b>tp_lseg
     * @param tpLseg A {@link PGlseg} value
     */
    public void setTpLseg(PGlseg tpLseg) {
        this.tpLseg = tpLseg;
    }

    /**
     * MAC (Media Access Control) address
     * <b>FIELD: </b>tp_macaddr
     * @return A {@link Object} value
     */
    public Object getTpMacaddr() {
        return tpMacaddr;
    }

    /**
     * MAC (Media Access Control) address
     * <b>FIELD: </b>tp_macaddr
     * @param tpMacaddr A {@link Object} value
     */
    public void setTpMacaddr(Object tpMacaddr) {
        this.tpMacaddr = tpMacaddr;
    }

    /**
     * Currency amount
     * <b>FIELD: </b>tp_money
     * @return A {@link BigDecimal} value
     */
    public BigDecimal getTpMoney() {
        return tpMoney;
    }

    /**
     * Currency amount
     * <b>FIELD: </b>tp_money
     * @param tpMoney A {@link BigDecimal} value
     */
    public void setTpMoney(BigDecimal tpMoney) {
        this.tpMoney = tpMoney;
    }

    /**
     * Exact numeric of selectable precision
     * <b>FIELD: </b>tp_numeric
     * @return A {@link BigDecimal} value
     */
    public BigDecimal getTpNumeric() {
        return tpNumeric;
    }

    /**
     * Exact numeric of selectable precision
     * <b>FIELD: </b>tp_numeric
     * @param tpNumeric A {@link BigDecimal} value
     */
    public void setTpNumeric(BigDecimal tpNumeric) {
        this.tpNumeric = tpNumeric;
    }

    /**
     * Geometric path on a plane
     * <b>FIELD: </b>tp_path
     * @return A {@link PGpath} value
     */
    public PGpath getTpPath() {
        return tpPath;
    }

    /**
     * Geometric path on a plane
     * <b>FIELD: </b>tp_path
     * @param tpPath A {@link PGpath} value
     */
    public void setTpPath(PGpath tpPath) {
        this.tpPath = tpPath;
    }

    /**
     * Geometric point on a plane
     * <b>FIELD: </b>tp_point
     * @return A {@link PGpoint} value
     */
    public PGpoint getTpPoint() {
        return tpPoint;
    }

    /**
     * Geometric point on a plane
     * <b>FIELD: </b>tp_point
     * @param tpPoint A {@link PGpoint} value
     */
    public void setTpPoint(PGpoint tpPoint) {
        this.tpPoint = tpPoint;
    }

    /**
     * Closed geometric path on a plane
     * <b>FIELD: </b>tp_polygon
     * @return A {@link PGpolygon} value
     */
    public PGpolygon getTpPolygon() {
        return tpPolygon;
    }

    /**
     * Closed geometric path on a plane
     * <b>FIELD: </b>tp_polygon
     * @param tpPolygon A {@link PGpolygon} value
     */
    public void setTpPolygon(PGpolygon tpPolygon) {
        this.tpPolygon = tpPolygon;
    }

    /**
     * Single precision floating-point number (4 bytes)
     * <b>FIELD: </b>tp_real
     * @return A {@link BigDecimal} value
     */
    public BigDecimal getTpReal() {
        return tpReal;
    }

    /**
     * Single precision floating-point number (4 bytes)
     * <b>FIELD: </b>tp_real
     * @param tpReal A {@link BigDecimal} value
     */
    public void setTpReal(BigDecimal tpReal) {
        this.tpReal = tpReal;
    }

    /**
     * Signed two-byte integer
     * <b>FIELD: </b>tp_smallint
     * @return A {@link Integer} value
     */
    public Integer getTpSmallint() {
        return tpSmallint;
    }

    /**
     * Signed two-byte integer
     * <b>FIELD: </b>tp_smallint
     * @param tpSmallint A {@link Integer} value
     */
    public void setTpSmallint(Integer tpSmallint) {
        this.tpSmallint = tpSmallint;
    }

    /**
     * Auto incrementing two-byte integer
     * <b>FIELD: </b>tp_smallserial
     * @return A {@link Integer} value
     */
    public Integer getTpSmallserial() {
        return tpSmallserial;
    }

    /**
     * Auto incrementing two-byte integer
     * <b>FIELD: </b>tp_smallserial
     * @param tpSmallserial A {@link Integer} value
     */
    public void setTpSmallserial(Integer tpSmallserial) {
        this.tpSmallserial = tpSmallserial;
    }

    /**
     * Auto incrementing four-byte integer
     * <b>FIELD: </b>tp_serial
     * @return A {@link Integer} value
     */
    public Integer getTpSerial() {
        return tpSerial;
    }

    /**
     * Auto incrementing four-byte integer
     * <b>FIELD: </b>tp_serial
     * @param tpSerial A {@link Integer} value
     */
    public void setTpSerial(Integer tpSerial) {
        this.tpSerial = tpSerial;
    }

    /**
     * Variable-length character string
     * <b>FIELD: </b>tp_text
     * @return A {@link String} value
     */
    public String getTpText() {
        return tpText;
    }

    /**
     * Variable-length character string
     * <b>FIELD: </b>tp_text
     * @param tpText A {@link String} value
     */
    public void setTpText(String tpText) {
        this.tpText = tpText;
    }

    /**
     * Time of day (no time zone)
     * <b>FIELD: </b>tp_time_without_time_zone
     * @return A {@link Date} value
     */
    public Date getTpTimeWithoutTimeZone() {
        return tpTimeWithoutTimeZone;
    }

    /**
     * Time of day (no time zone)
     * <b>FIELD: </b>tp_time_without_time_zone
     * @param tpTimeWithoutTimeZone A {@link Date} value
     */
    public void setTpTimeWithoutTimeZone(Date tpTimeWithoutTimeZone) {
        this.tpTimeWithoutTimeZone = tpTimeWithoutTimeZone;
    }

    /**
     * Time of day, including time zone
     * <b>FIELD: </b>tp_time_with_time_zone
     * @return A {@link Date} value
     */
    public Date getTpTimeWithTimeZone() {
        return tpTimeWithTimeZone;
    }

    /**
     * Time of day, including time zone
     * <b>FIELD: </b>tp_time_with_time_zone
     * @param tpTimeWithTimeZone A {@link Date} value
     */
    public void setTpTimeWithTimeZone(Date tpTimeWithTimeZone) {
        this.tpTimeWithTimeZone = tpTimeWithTimeZone;
    }

    /**
     * Date and time (no time zone)
     * <b>FIELD: </b>tp_timestamp_without_time_zone
     * @return A {@link Date} value
     */
    public Date getTpTimestampWithoutTimeZone() {
        return tpTimestampWithoutTimeZone;
    }

    /**
     * Date and time (no time zone)
     * <b>FIELD: </b>tp_timestamp_without_time_zone
     * @param tpTimestampWithoutTimeZone A {@link Date} value
     */
    public void setTpTimestampWithoutTimeZone(Date tpTimestampWithoutTimeZone) {
        this.tpTimestampWithoutTimeZone = tpTimestampWithoutTimeZone;
    }

    /**
     * Date and time, including time zone
     * <b>FIELD: </b>tp_timestamp_with_time_zone
     * @return A {@link Date} value
     */
    public Date getTpTimestampWithTimeZone() {
        return tpTimestampWithTimeZone;
    }

    /**
     * Date and time, including time zone
     * <b>FIELD: </b>tp_timestamp_with_time_zone
     * @param tpTimestampWithTimeZone A {@link Date} value
     */
    public void setTpTimestampWithTimeZone(Date tpTimestampWithTimeZone) {
        this.tpTimestampWithTimeZone = tpTimestampWithTimeZone;
    }

    /**
     * Text search query
     * <b>FIELD: </b>tp_tsquery
     * @return A {@link String} value
     */
    public String getTpTsquery() {
        return tpTsquery;
    }

    /**
     * Text search query
     * <b>FIELD: </b>tp_tsquery
     * @param tpTsquery A {@link String} value
     */
    public void setTpTsquery(String tpTsquery) {
        this.tpTsquery = tpTsquery;
    }

    /**
     * Text search document
     * <b>FIELD: </b>tp_tsvector
     * @return A {@link String} value
     */
    public String getTpTsvector() {
        return tpTsvector;
    }

    /**
     * Text search document
     * <b>FIELD: </b>tp_tsvector
     * @param tpTsvector A {@link String} value
     */
    public void setTpTsvector(String tpTsvector) {
        this.tpTsvector = tpTsvector;
    }

    /**
     * User-level transaction ID snapshot
     * <b>FIELD: </b>tp_txid_snapshot
     * @return A {@link Object} value
     */
    public Object getTpTxidSnapshot() {
        return tpTxidSnapshot;
    }

    /**
     * User-level transaction ID snapshot
     * <b>FIELD: </b>tp_txid_snapshot
     * @param tpTxidSnapshot A {@link Object} value
     */
    public void setTpTxidSnapshot(Object tpTxidSnapshot) {
        this.tpTxidSnapshot = tpTxidSnapshot;
    }

    /**
     * Universally unique identifier
     * <b>FIELD: </b>tp_uuid
     * @return A {@link UUID} value
     */
    public UUID getTpUuid() {
        return tpUuid;
    }

    /**
     * Universally unique identifier
     * <b>FIELD: </b>tp_uuid
     * @param tpUuid A {@link UUID} value
     */
    public void setTpUuid(UUID tpUuid) {
        this.tpUuid = tpUuid;
    }

    /**
     * XML data
     * <b>FIELD: </b>tp_xml
     * @return A {@link String} value
     */
    public String getTpXml() {
        return tpXml;
    }

    /**
     * XML data
     * <b>FIELD: </b>tp_xml
     * @param tpXml A {@link String} value
     */
    public void setTpXml(String tpXml) {
        this.tpXml = tpXml;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
            id
            ,tpBigint
            ,tpBigserial
            ,tpBit
            ,tpBitVarying
            ,tpBoolean
            ,tpBox
            ,tpBytea
            ,tpCharacter
            ,tpCharacterVarying
            ,tpCidr
            ,tpCircle
            ,tpDate
            ,tpDoublePrecision
            ,tpInet
            ,tpInteger
            ,tpInterval
            ,tpJson
            ,tpLine
            ,tpLseg
            ,tpMacaddr
            ,tpMoney
            ,tpNumeric
            ,tpPath
            ,tpPoint
            ,tpPolygon
            ,tpReal
            ,tpSmallint
            ,tpSmallserial
            ,tpSerial
            ,tpText
            ,tpTimeWithoutTimeZone
            ,tpTimeWithTimeZone
            ,tpTimestampWithoutTimeZone
            ,tpTimestampWithTimeZone
            ,tpTsquery
            ,tpTsvector
            ,tpTxidSnapshot
            ,tpUuid
            ,tpXml
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("tpBigint", tpBigint)
                .add("tpBigserial", tpBigserial)
                .add("tpBit", tpBit)
                .add("tpBitVarying", tpBitVarying)
                .add("tpBoolean", tpBoolean)
                .add("tpBox", tpBox)
                .add("tpBytea", tpBytea)
                .add("tpCharacter", tpCharacter)
                .add("tpCharacterVarying", tpCharacterVarying)
                .add("tpCidr", tpCidr)
                .add("tpCircle", tpCircle)
                .add("tpDate", tpDate)
                .add("tpDoublePrecision", tpDoublePrecision)
                .add("tpInet", tpInet)
                .add("tpInteger", tpInteger)
                .add("tpInterval", tpInterval)
                .add("tpJson", tpJson)
                .add("tpLine", tpLine)
                .add("tpLseg", tpLseg)
                .add("tpMacaddr", tpMacaddr)
                .add("tpMoney", tpMoney)
                .add("tpNumeric", tpNumeric)
                .add("tpPath", tpPath)
                .add("tpPoint", tpPoint)
                .add("tpPolygon", tpPolygon)
                .add("tpReal", tpReal)
                .add("tpSmallint", tpSmallint)
                .add("tpSmallserial", tpSmallserial)
                .add("tpSerial", tpSerial)
                .add("tpText", tpText)
                .add("tpTimeWithoutTimeZone", tpTimeWithoutTimeZone)
                .add("tpTimeWithTimeZone", tpTimeWithTimeZone)
                .add("tpTimestampWithoutTimeZone", tpTimestampWithoutTimeZone)
                .add("tpTimestampWithTimeZone", tpTimestampWithTimeZone)
                .add("tpTsquery", tpTsquery)
                .add("tpTsvector", tpTsvector)
                .add("tpTxidSnapshot", tpTxidSnapshot)
                .add("tpUuid", tpUuid)
                .add("tpXml", tpXml)
                .toString();
    }
}
