package com.oelprince.entity;

/*
 * The MIT License
 *
 * Copyright 2018 oelprince.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author oelprince
 */

@Entity
@Table(catalog = "user_profile", schema = "person_address", name="person_address")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonAddress.findAll", query = "SELECT p FROM PersonAddress p"),
    @NamedQuery(name = "PersonAddress.findByPersonId", query = "SELECT p FROM PersonAddress p WHERE p.personId = :personId"),
    @NamedQuery(name = "PersonAddress.findByAddressId", query = "SELECT p FROM PersonAddress p WHERE p.addressId = :addressId"),
    @NamedQuery(name = "PersonAddress.findByPersonAddressId", query= "SELECT p FROM PersonAddress p WHERE p.addressId = :addressId AND p.personId = :personId")})
public class PersonAddress implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(max = 36)
    @Column(name = "person_id")
    private String personId;
    @Size(max = 36)
    @Column(name = "address_id")
    private String addressId;
     

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
    
    
    
    
}
