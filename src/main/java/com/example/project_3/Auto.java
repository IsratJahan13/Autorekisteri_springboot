package com.example.project_3;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.AbstractPersistable;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NamedEntityGraph(name = "Auto.omistajat", attributeNodes = { @NamedAttributeNode("omistajat") })

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auto extends AbstractPersistable<Long> {
    private String valmistenumero;
    private String rekisterinumero;
    private String merkki;
    private String malli;
    private int valmistusvuosi;

    @ManyToMany(mappedBy = "autot")
    private List<Omistaja> omistajat = new ArrayList<>();
}
