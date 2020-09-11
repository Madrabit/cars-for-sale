package ru.job4j.cars.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author madrabit on 03.08.2020
 * @version 1$
 * @since 0.1
 */
@Entity
@Table(name = "advertisement")
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"))
    private Car car;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"))
    private User user;

    private boolean status;

    private LocalDate date;

    public static Advertisement of (Car car, User user) {
        Advertisement advertisement = new Advertisement();
        advertisement.car = car;
        advertisement.user = user;
        advertisement.date = LocalDate.now();
        return advertisement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advertisement that = (Advertisement) o;
        return id == that.id &&
                status == that.status &&
                Objects.equals(car, that.car) &&
                Objects.equals(user, that.user) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, car, user, status, date);
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "id=" + id +
                ", car=" + car +
                ", user=" + user +
                ", status=" + status +
                ", date=" + date +
                '}';
    }
}
