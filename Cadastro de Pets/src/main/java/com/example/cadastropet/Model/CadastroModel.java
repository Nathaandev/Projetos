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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Petid;
    private String name;
    private String race;
    private double weight;
    private int age;
    private String city;
    private String street;
    private int number;

    @Column(name = "endereço")
    private String endereço;

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

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
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
