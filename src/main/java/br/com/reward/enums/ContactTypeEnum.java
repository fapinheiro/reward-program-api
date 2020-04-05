package br.com.reward.enums;

import java.util.stream.Stream;

public enum ContactTypeEnum {

    CELLPHONE (1, "Phone"),
    EMAIL (2, "E-mail");

    private Integer codigo;
    private String descricao;

    private ContactTypeEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static ContactTypeEnum toEnum(Integer codigo) {
        return Stream.of(ContactTypeEnum.values())
            .filter( type -> type.codigo.equals(codigo) )
            .findFirst()
            .orElseThrow( () -> new IllegalArgumentException("Invalid id: " + codigo));
	}
}
