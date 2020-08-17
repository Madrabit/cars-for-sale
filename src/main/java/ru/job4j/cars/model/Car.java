package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author madrabit on 03.08.2020
 * @version 1$
 * @since 0.1
 */
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brand;
    private String model;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"))
    private Engine engine;

    private String transmission;
    private String body;
    private String drive;
    private int year;
    private int mileage;
    private int price;
    private String image;

    public static Car of (String brand, String model, Engine engine, String transmission, String body, String drive, int year, int mileage, int price, String image) {
        Car car = new Car();
        car.brand = brand;
        car.model = model;
        car.engine = engine;
        car.transmission = transmission;
        car.body = body;
        car.drive = drive;
        car.year = year;
        car.mileage = mileage;
        car.price = price;
        car.image = image;
        return car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                year == car.year &&
                mileage == car.mileage &&
                price == car.price &&
                Objects.equals(brand, car.brand) &&
                Objects.equals(model, car.model) &&
                Objects.equals(engine, car.engine) &&
                Objects.equals(transmission, car.transmission) &&
                Objects.equals(body, car.body) &&
                Objects.equals(drive, car.drive) &&
                Objects.equals(image, car.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, engine, transmission, body, drive, year, mileage, price, image);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", engine=" + engine +
                ", transmission='" + transmission + '\'' +
                ", body='" + body + '\'' +
                ", drive='" + drive + '\'' +
                ", year=" + year +
                ", mileage=" + mileage +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }
}
