package br.com.reward.validator;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.reward.annotation.IndicationUpdate;
import br.com.reward.dto.IndicationUpdateDTO;

public class IndicationUpdateValidator implements ConstraintValidator<IndicationUpdate, IndicationUpdateDTO> {

    // @Autowired
    // private ClienteRepository repo;
    
	@Override
	public void initialize(IndicationUpdate ann) {
	}

	@Override
	public boolean isValid(IndicationUpdateDTO dto, ConstraintValidatorContext context) {

		Map<String, String> errorsMap = new HashMap<>();

        if (dto.getEmail() != null && !EmailValidator.validate(dto.getEmail())) {
			errorsMap.put("email", "Invalid email");
		}

		if (dto.getName() != null && dto.getName().length() == 0) {
			errorsMap.put("name", "Invalid name");
		}
				
		if (dto.getPhone() != null && dto.getPhone().length() == 0) {
			errorsMap.put("phone", "Invalid phone");
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