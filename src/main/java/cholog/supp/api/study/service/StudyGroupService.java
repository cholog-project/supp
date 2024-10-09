package cholog.supp.api.study.service;

import cholog.supp.api.study.dto.CreateStudyGroupRequest;
import cholog.supp.db.study.StudyGroup;
import cholog.supp.db.study.StudyGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StudyGroupService {

    private final StudyGroupRepository studyGroupRepository;

    public void createStudyGroup(CreateStudyGroupRequest request) {
        studyGroupRepository.save(new StudyGroup(request.studyName(), request.organization()));
    }
}
