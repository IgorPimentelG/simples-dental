package com.simplesdental.api.domain.entities.contact;

import com.simplesdental.api.domain.entities.professional.Professional;
import com.simplesdental.api.domain.shared.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_contatos")
public class Contact extends BaseEntity {

    private String nome;
    private String contato;

    @ManyToOne
    @JoinColumn(name = "profissional_id", nullable = false)
    private Professional profissional;
}