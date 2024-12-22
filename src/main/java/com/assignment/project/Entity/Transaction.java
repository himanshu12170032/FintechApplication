package com.assignment.project.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private String type;  // "TRANSFER", "DEPOSIT"
    private String status; // "PENDING", "COMPLETED"
    private Date date;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}

