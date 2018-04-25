package ru.ivmiit.service.models;

import lombok.*;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "team")
@Entity
@Table(name = "musicians")
public class Musician {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String instrument;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Band team;
}
