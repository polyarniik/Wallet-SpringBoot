package ru.kpfu.itis.safiullin.walletspringboot.models;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String color;
    private float amount;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
