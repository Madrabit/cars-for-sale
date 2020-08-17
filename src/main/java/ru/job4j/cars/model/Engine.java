package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author madrabit on 03.08.2020
 * @version 1$
 * @since 0.1
 */
@Entity
@Table(name = "engine")
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double displacement;
    private int horsepower;
    private String fuel;


    public static Engine of (double displacement, int horsepower, String fuel) {
        Engine engine = new Engine();
        engine.displacement = displacement;
        engine.horsepower = horsepower;
        engine.fuel = fuel;
        return engine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDisplacement() {
        return displacement;
    }

    public void setDisplacement(double displacement) {
        this.displacement = displacement;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engine engine = (Engine) o;
        return id == engine.id &&
                Double.compare(engine.displacement, displacement) == 0 &&
                horsepower == engine.horsepower &&
                Objects.equals(fuel, engine.fuel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, displacement, horsepower, fuel);
    }

    @Override
    public String toString() {
        return "Engine{" +
                "id=" + id +
                ", displacement=" + displacement +
                ", horsepower=" + horsepower +
                ", fuel='" + fuel + '\'' +
                '}';
    }
}
