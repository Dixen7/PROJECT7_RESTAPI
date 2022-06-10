package com.nnk.springboot.domain;

import java.sql.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@DynamicUpdate
@Entity
@Table(name = "curvepoint")
public class CurvePoint {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @NotNull(message = "CurveId is mandatory")
    @Digits(fraction = 0, integer = 10)
    @Positive(message = "CurveId must be positive")
    private Integer curveId;
    private Timestamp asOfDate;
    @NotNull(message = "Term is mandatory")
    @Digits(fraction = 2, integer = 10)
    @Positive(message = "Term must be positive")
    private Double term;
    @NotNull(message = "Value is mandatory")
    @Digits(fraction = 2, integer = 10)
    @Positive(message = "Value must be positive")
    private Double value;
    private Timestamp creationDate;


    public CurvePoint() {
    }

    public CurvePoint(@NotNull Integer curveId, @NotBlank Double term,
                      @NotBlank Double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }
}
