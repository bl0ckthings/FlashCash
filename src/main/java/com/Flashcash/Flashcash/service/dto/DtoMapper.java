package com.Flashcash.Flashcash.service.dto;

import com.Flashcash.Flashcash.repository.ContactRepository;
import com.Flashcash.Flashcash.service.SessionService;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
@AllArgsConstructor
public class DtoMapper {
    private final ContactRepository contactRepository;
    private final SessionService sessionService;
    public List<UserContactDto> getListOfContacts() {
        List<Tuple> tupleList = contactRepository.getContactsById(sessionService.getCurrentUser().getId());

        return tupleList.stream()
                .map(t -> new UserContactDto(
                        t.get(0, Integer.class),
                        t.get(1, String.class),
                        t.get(2, String.class),
                        t.get(3, String.class)
                ))
                .collect(Collectors.toList());
    }


}
