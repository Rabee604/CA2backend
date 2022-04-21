package dtos;

import entities.Boat;
import entities.Owner;

import java.util.HashSet;
import java.util.Set;

public class OwnerDTO {

    private Long id;
    private String name;
    private String address;
    private String phone;
    private Set<String> BoatDTO = new HashSet<>();
    public OwnerDTO(Owner owner) {
        this.id = owner.getId();
        this.name = owner.getName();
        this.address = owner.getAddress();
        this.phone = owner.getPhone();
        for (Boat p : owner.getBoats()) {
            this.BoatDTO.add("id:" + p.getId() + ", number:" + p.getBrand() + ", description:" + p.getName());
        }
    }

    public OwnerDTO(Long id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "OwnerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", BoatDTO=" + BoatDTO +
                '}';
    }
}



