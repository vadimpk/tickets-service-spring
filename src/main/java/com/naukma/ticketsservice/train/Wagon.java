package com.naukma.ticketsservice.train;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "wagons")
public class Wagon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    private long id;

    @Column(unique = true, nullable = false)
    @Pattern(regexp = "[a-zA-z0-9]{3,16}", message = "wagon name length must be of 3 to 16 element with no special parameter")
//    @NotBlank
    private String name;

    @Column(nullable = false)
    @Min(10)
    @Max(200)
    private int numberOfSeats;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "train_id", referencedColumnName = "id")
    private Train train;

    public Wagon() {

    }

    public Wagon(String name, int numberOfSeats, Train train){
        this.name = name;
        this.numberOfSeats =numberOfSeats;
        this.train = train;
    }


    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSits) {
        this.numberOfSeats = numberOfSits;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public void setName(String name) {
        this.name = name;
    }

}
