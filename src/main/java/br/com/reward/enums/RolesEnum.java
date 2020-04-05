package br.com.reward.enums;

import java.util.stream.Stream;

public enum RolesEnum {

    ADMIN(1, "ROLE_ADMIN"),
	USER(2, "ROLE_USER");

	private Integer codigo;
	private String descricao;

	private RolesEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao () {
		return descricao;
	}

	public static RolesEnum toEnum(Integer codigo) {

        return Stream.of(RolesEnum.values())
            .filter( role -> role.codigo.equals(codigo) )
            .findFirst()
            .orElseThrow( () -> new IllegalArgumentException("Invalid id: " + codigo));

    }
    
}