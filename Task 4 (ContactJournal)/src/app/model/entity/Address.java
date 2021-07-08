package app.model.entity;


public class Address {
    private String postalCode;
    private String city;
    private String street;
    private int house;
    private int flat;

    public Address() {
    }

    public Address(String postalCode, String city, String street, int house, int flat) {
        this.postalCode = postalCode;
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    public String toString() {
        String[] addressItems = new String[]{postalCode, city, street, String.valueOf(house), String.valueOf(flat)};
        return String.join(", ", addressItems);
    }
}
