package br.com.reward.enums;

import java.util.stream.Stream;

public enum TransactionTypeEnum {

    DEBIT (1, "Debit"),
    CREDIT (2, "Credit");

    private Integer codigo;
    private String descricao;

    private TransactionTypeEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TransactionTypeEnum toEnum(Integer codigo) {
        return Stream.of(TransactionTypeEnum.values())
            .filter( type -> type.codigo.equals(codigo) )
            .findFirst()
            .orElseThrow( () -> new IllegalArgumentException("Invalid id: " + codigo));
	}
}
