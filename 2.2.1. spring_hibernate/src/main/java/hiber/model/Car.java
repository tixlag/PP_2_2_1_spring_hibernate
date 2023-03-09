package hiber.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "cars")

public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    private String model;
    @Column
    private int series;


    public Car(String m, int s) {
        model = m;
        series = s;
    }

    public Car() {

    }

    // Геттеры, сеттеры вообще нужны, если у нас DI и IoC?

    public Long getId() {return id;}

    public String getMaodel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return model + " " + series;
    }
}
