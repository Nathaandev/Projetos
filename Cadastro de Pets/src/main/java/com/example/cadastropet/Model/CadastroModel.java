package com.example.cadastropet.Model;
import com.example.cadastropet.Enum.CatOrDog;
import com.example.cadastropet.Enum.MascOrFem;
import com.example.cadastropet.dtos.CadastroRecordDTO;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Pets")
public class CadastroModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Petid;
    private String race;
    private double weight;
    private int age;


    @Transient
    private String firstname;
    @Transient
    private String lastname;
    @Transient
    private String city;
    @Transient
    private String street;
    @Transient
    private int number;

    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;

    @Enumerated(EnumType.STRING)
    private CatOrDog type;

    @Enumerated(EnumType.STRING)
    private MascOrFem gender;

    public CatOrDog getType() {
        return type;
    }

    public void setType(CatOrDog type) {
        this.type = type;
    }

    public MascOrFem getGender() {
        return gender;
    }

    public void setGender(MascOrFem gender) {
        this.gender = gender;
    }

    public Long getPetid() {
        return Petid;
    }

    public void setPetid(Long petid) {
        Petid = petid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
