package br.com.thiagomagdalena.coursesservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum CourseCategoryEnum implements EnumSerializable {

    // --- Áreas de Especialização Clínica ---
    URGENCIA_E_EMERGENCIA("Urgência e Emergência"),
    TERAPIA_INTENSIVA_UTI("Terapia Intensiva (UTI)"),
    SAUDE_DA_MULHER_E_OBSTETRICIA("Saúde da Mulher e Obstetrícia"),
    PEDIATRIA_E_NEONATOLOGIA("Pediatria e Neonatologia"),
    CENTRO_CIRURGICO_E_CME("Centro Cirúrgico e CME"),
    CARDIOLOGIA_E_HEMODINAMICA("Cardiologia e Hemodinâmica"),
    ONCOLOGIA("Oncologia"),
    FERIDAS_E_CURATIVOS("Feridas e Curativos"),

    // --- Fundamentos e Bases ---
    FUNDAMENTOS_DE_ENFERMAGEM("Fundamentos de Enfermagem"),
    FARMACOLOGIA_APLICADA("Farmacologia Aplicada"),
    SEMIOLOGIA_E_SEMIOTECNICA("Semiologia e Semiotécnica"),

    // --- Gestão e Carreira ---
    GESTAO_E_LIDERANCA_EM_SAUDE("Gestão e Liderança em Saúde"),
    LEGISLACAO_ETICA_E_BIOETICA("Legislação, Ética e Bioética"),
    AUDITORIA_EM_ENFERMAGEM("Auditoria em Enfermagem");

    private final String code;

    public static CourseCategoryEnum findBy(final String code) {
        return Stream.of(values())
                .filter(v -> v.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String getValue() {
        return this.code;
    }
}
