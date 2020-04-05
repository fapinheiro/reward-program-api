package br.com.reward.enums;

import java.util.stream.Stream;

public enum TransactionStatusEnum {
    
    CREATED (1, "Created"),
    PENDING (2, "Pending"), 
    PROCESSING (3, "Processing"), 
    EXPIRED (4, "Expired"), 
    CANCELLED (5, "Cancelled"),
    PROCESSED (6, "Processed");

    private Integer codigo;
    private String descricao;

    private TransactionStatusEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TransactionStatusEnum toEnum(Integer codigo) {
        return Stream.of(TransactionStatusEnum.values())
            .filter( status -> status.codigo.equals(codigo) )
            .findFirst()
            .orElseThrow( () -> new IllegalArgumentException("Invalid id: " + codigo));
	}
}
