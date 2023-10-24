package com.enviro.assessment.grd001.nhlahlamathye.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class investor {

    private String name;
    private int age;
    private String address;
    private String contactInfo;
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Id
    public Long getId() {
        return id;
    }
}
