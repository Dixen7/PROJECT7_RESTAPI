package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@DynamicUpdate
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "Moodys Rating is mandatory")
    private String moodysRating;
    @NotBlank(message = "SandP Rating is mandatory")
    private String sandPRating;
    @NotBlank(message = "Fitch Rating is mandatory")
    private String fitchRating;
    @NotNull(message = "Order Number is mandatory")
    @Digits(fraction = 0, integer = 10)
    @Positive(message = "Order Number must be positive")
    private Integer orderNumber;


    public Rating() {
    }

    public Rating(@NotBlank String moodysRating, @NotBlank String sandPRating,
                  @NotBlank String fitchRating, @NotNull Integer orderNumber) {
        this.moodysRating = moodysRating;
        this.sandPRating = sandPRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }

}