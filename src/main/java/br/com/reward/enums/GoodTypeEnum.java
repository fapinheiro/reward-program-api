package br.com.reward.enums;

import java.util.stream.Stream;

public enum GoodTypeEnum {

    CAR (1, "Car"),
    BIKE (2, "Bike"), 
    HOUSE (3, "House"), 
    SERVICES (4, "Services");

    private Integer codigo;
    private String descricao;

    private GoodTypeEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static GoodTypeEnum toEnum(Integer codigo) {
        return Stream.of(GoodTypeEnum.values())
            .filter( good -> good.codigo.equals(codigo) )
            .findFirst()
            .orElseThrow( () -> new IllegalArgumentException("Id inválido: " + codigo));
	}
}
