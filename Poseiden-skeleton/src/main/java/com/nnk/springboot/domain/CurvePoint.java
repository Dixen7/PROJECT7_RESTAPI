package com.nnk.springboot.domain;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Entity
@Table(name = "curvepoint")
public class CurvePoint {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer id;

    @NotNull(message = "Curve Id must not be null")
    @PositiveOrZero
    Integer curveId;

    @CreationTimestamp
    @DateTimeFormat(pattern="yyyy-MM-dd")
    LocalDate asOfDate;

    Double term;
    Double value;

    @CreationTimestamp
    @DateTimeFormat(pattern="yyyy-MM-dd")
    LocalDate creationDate;

    public CurvePoint() {
    }

    public CurvePoint(Integer curveId, Double term, Double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }

}
