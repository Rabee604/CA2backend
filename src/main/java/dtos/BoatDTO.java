package dtos;

import entities.Boat;

public class BoatDTO {
    private Long id;
    private String brand;
    private String make;
    private String name;

    private OwnerDTO ownerDTO;
    public BoatDTO(Boat boat) {
        this.id = boat.getId();
        this.brand= boat.getBrand();
        this.make = boat.getMake();
        this.name = boat.getName();
        this.ownerDTO=new OwnerDTO(boat.getOwner());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public OwnerDTO getOwnerDTO() {
        return ownerDTO;
    }

    public void setOwnerDTO(OwnerDTO ownerDTO) {
        this.ownerDTO = ownerDTO;
    }
}


