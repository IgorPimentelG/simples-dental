package com.simplesdental.api.domain.enums;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum Specialization {
    TESTER("Tester"),
    SUPORTE("Suporte"),
    DESIGNER("Designer"),
    DESENVOLVEDOR("Desenolvedor");

    private final String description;
}
