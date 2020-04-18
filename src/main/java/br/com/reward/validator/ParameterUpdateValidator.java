package br.com.reward.validator;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.reward.annotation.ParameterUpdate;
import br.com.reward.dto.ParameterDTO;

public class ParameterUpdateValidator implements ConstraintValidator<ParameterUpdate, ParameterDTO> {

    // @Autowired
    // private ClienteRepository repo;
    
	@Override
	public void initialize(ParameterUpdate ann) {
	}

	@Override
	public boolean isValid(ParameterDTO dto, ConstraintValidatorContext context) {

		Map<String, String> errorsMap = new HashMap<>();

        if (dto.getIndicationExpiration() != null && dto.getIndicationExpiration() < 1) {
			errorsMap.put("indicationExpiration", "Invalid value");
		}

		if (dto.getRequestExpiration() != null && dto.getRequestExpiration() < 1) {
			errorsMap.put("requestExpiration", "Invalid value");
		}
			
		if (dto.getScoreExpiration() != null && dto.getScoreExpiration() < 1) {
			errorsMap.put("scoreExpiration", "Invalid value");
		}

        // Cliente aux = repo.findByEmail(objDto.getEmail());
		// if (aux != null) {
		// 	errorsMap.put("email", "Email já existente");
        // }
        
        for (Map.Entry<String, String> entry : errorsMap.entrySet()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(entry.getValue())
                    .addPropertyNode(entry.getKey())
					.addConstraintViolation();
        }
		
		return errorsMap.isEmpty();
	}
}