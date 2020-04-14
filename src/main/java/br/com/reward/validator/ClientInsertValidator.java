package br.com.reward.validator;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.reward.annotation.ClientInsert;
import br.com.reward.dto.ClientRequestDTO;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientRequestDTO> {

    // @Autowired
    // private ClienteRepository repo;
    
	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(ClientRequestDTO dto, ConstraintValidatorContext context) {

		Map<String, String> errorsMap = new HashMap<>();

		// Validar as datas, docs, etc.
		Date current = new Date();
        if (dto.getBirthDate().after( current )) {
			errorsMap.put("birthDate", "Invalid date");
		}

		dto.getIdentifications().forEach( identification -> {

			if (identification.getEmissionDate().after(current) || 
				identification.getEmissionDate().after(identification.getValidDate())) {
				errorsMap.put("emissionDate", "Invalid date");
			}

			if (identification.getValidDate().before(current)) {
				errorsMap.put("validDate", "Invalid date");
			}

		});
				

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