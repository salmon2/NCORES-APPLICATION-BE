package com.ncores.plaluvs.service;

import com.ncores.plaluvs.controller.UserElements;
import com.ncores.plaluvs.domain.Elements;
import com.ncores.plaluvs.exception.ErrorCode;
import com.ncores.plaluvs.exception.PlaluvsException;
import com.ncores.plaluvs.repository.UserElementsRepository;
import com.ncores.plaluvs.repository.UserRepository;
import com.ncores.plaluvs.repository.elements.ElementsRepository;
import com.ncores.plaluvs.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ElementsService {

    private final UserElementsRepository userElementsRepository;
    private final UserRepository userRepository;
    private final ElementsRepository elementsRepository;

    @Transactional
    public String elementsMark(UserDetailsImpl userDetails, Long elements) throws PlaluvsException {
        UserDetailsImpl.UserCheck(userDetails);

        Elements findElements = elementsRepository.findById(elements).orElseThrow(
                () -> new PlaluvsException(ErrorCode.ELEMENT_NOT_FOUND)
        );

        UserElements findUserElements = userElementsRepository.findByUserAndElements(userDetails.getUser(), findElements);

        if(findUserElements == null){
            UserElements userElements = new UserElements(userDetails.getUser(), findElements);
            userElementsRepository.save(userElements);
            return "즐겨찾기 등록 완료";
        }
        else{
            userElementsRepository.delete(findUserElements);
            return "즐겨찾기 삭제 완료";
        }
    }
}
