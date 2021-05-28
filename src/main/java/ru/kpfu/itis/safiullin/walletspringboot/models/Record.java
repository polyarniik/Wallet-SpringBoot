package ru.kpfu.itis.safiullin.walletspringboot.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Bank bank;

    @OneToOne
    private Category category;

    @OneToOne
    private Account account;
    private float sum;
    private String description;
    private Date date;

    @ManyToMany(mappedBy = "records")
    private List<Tag> tags;
}
