package com.simplesdental.api.domain.entities.professional;

import com.simplesdental.api.domain.entities.contact.Contact;
import com.simplesdental.api.domain.enums.Specialization;
import com.simplesdental.api.domain.shared.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_profissionais")
public class Professional extends BaseEntity {

    private String nome;
    private LocalDate nascimento;

    @Enumerated(EnumType.STRING)
    private Specialization cargo;

    @OneToMany(mappedBy = "profissional")
    private List<Contact> contatos = new ArrayList<>();

    public void addContact(Contact contact) {
        this.contatos.add(contact);
    }

    public void removeContact(Contact contact) {
        this.contatos.remove(contact);
    }

    public List<Contact> getContatos() {
        return Collections.unmodifiableList(contatos.stream()
          .filter(contact -> contact.getDeletedDate() == null)
          .toList()
        );
    }
}
