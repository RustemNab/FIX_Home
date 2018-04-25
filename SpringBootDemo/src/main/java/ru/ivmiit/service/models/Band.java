package ru.ivmiit.service.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ivmiit.service.forms.BandForm;

import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bands")
public class Band {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String genre;

    @OneToMany(mappedBy = "team")
    private List<Musician> musicians;

    public static Band from(BandForm form) {
        return Band.builder()
                .name(form.getName())
                .genre(form.getGenre())
                .build();
    }
}
