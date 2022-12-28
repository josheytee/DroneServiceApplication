package com.dronetask.medication;

import com.dronetask.drone.Drone;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "medications")
public class Medication {

    @Id
    @SequenceGenerator(name = "medication_sequence", sequenceName = "medication_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medication_sequence")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drone_id", nullable = true)
    @JsonBackReference
    private Drone drone;

    //    name (allowed only letters, numbers, ‘-‘, ‘_’);
    private String name;

    //    weight;
    private Double weight;

    //    code (allowed only upper case letters, underscore and numbers)
    private String code;

    public Medication(Long id, String name, Double weight, String code, String image_url, Drone drone) {
        this.id = id;
        this.drone = drone;
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.image_url = image_url;
    }

    public Medication( String name, Double weight, String code, String image_url,Drone drone) {
        this.drone = drone;
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.image_url = image_url;
    }

    //    image (picture of the medication case).
    private String image_url;

    public Medication() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }


    @Override
    public String toString() {
        return "Medication{" +
                "id=" + id +
                ", drone=" + drone +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", code='" + code + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }
}
