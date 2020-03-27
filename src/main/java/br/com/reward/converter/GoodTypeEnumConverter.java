package br.com.reward.converter;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.reward.enums.GoodTypeEnum;

@Converter(autoApply = true)
public class GoodTypeEnumConverter implements AttributeConverter<GoodTypeEnum, Integer> {
  
    @Override
    public Integer convertToDatabaseColumn(GoodTypeEnum typeEnum) {
        if (typeEnum == null) {
            return null;
        }
        return typeEnum.getCodigo();
    }
 
    @Override
    public GoodTypeEnum convertToEntityAttribute(Integer codigo) {
        return GoodTypeEnum.toEnum(codigo);
    }
}