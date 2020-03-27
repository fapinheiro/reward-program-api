package br.com.reward.enums;

import java.util.stream.Stream;

public enum IndicationStatusEnum {
    
    CREATED (1, "Created"),
    SENT (2, "Sent"), 
    RESENT (3, "ReSent"), 
    ACCEPTED (4, "Accepted"), 
    CONVERTED (5, "Converted"), 
    EXPIRED (6, "Expirou"), 
    CANCELED (7, "Cancelled");

    private Integer codigo;
    private String descricao;

    private IndicationStatusEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static IndicationStatusEnum toEnum(Integer codigo) {
        return Stream.of(IndicationStatusEnum.values())
            .filter( indicacao -> indicacao.codigo.equals(codigo) )
            .findFirst()
            .orElseThrow( () -> new IllegalArgumentException("Id inválido: " + codigo));
	}
}
