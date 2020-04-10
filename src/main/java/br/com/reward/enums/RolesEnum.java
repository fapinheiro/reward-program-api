package br.com.reward.enums;

import java.util.stream.Stream;

public enum RolesEnum {

	CREATE(1, "ROLE_CREATE"),
	UPDATE(2, "ROLE_UPDATE"),
	VIEW(3, "ROLE_VIEW"),
	DELETE(4, "ROLE_DELETE"),
	EXPORT(5, "ROLE_EXPORT");

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