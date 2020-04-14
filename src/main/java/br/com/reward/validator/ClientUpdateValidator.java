package br.com.reward.validator;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.reward.annotation.ClientUpdate;
import br.com.reward.dto.ClientUpdateDTO;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientUpdateDTO> {

    // @Autowired
    // private ClienteRepository repo;
    
	@Override
	public void initialize(ClientUpdate ann) {
	}

	@Override
	public boolean isValid(ClientUpdateDTO dto, ConstraintValidatorContext context) {

		Map<String, String> errorsMap = new HashMap<>();

        if (dto.getEmail() != null && !EmailValidator.validate(dto.getEmail())) {
			errorsMap.put("email", "Invalid email");
		}

		if (dto.getName() != null && dto.getName().length() == 0) {
			errorsMap.put("name", "Invalid name");
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