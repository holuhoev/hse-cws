package hse.holuhoev.loader;

import hse.holuhoev.domain.Faculty;
import hse.holuhoev.domain.Institute;
import hse.holuhoev.domain.QFaculty;
import hse.holuhoev.domain.QInstitute;
import hse.holuhoev.repo.*;
import hse.holuhoev.ruz.api.RuzApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DomainLoader{
    private final RuzApiService ruzApiService;
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final InstituteRepository instituteRepository;
    private final GroupRepository groupRepository;
    private final ChairRepository chairRepository;

    @Autowired
    public DomainLoader(RuzApiService ruzApiService
            , StudentRepository studentRepository
            , FacultyRepository facultyRepository
            , InstituteRepository instituteRepository
            , GroupRepository groupRepository
            , ChairRepository chairRepository) {
        this.ruzApiService = ruzApiService;
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.instituteRepository = instituteRepository;
        this.groupRepository = groupRepository;
        this.chairRepository = chairRepository;
    }

    public void run() {
        loadInstitutes();
        loadFaculties();
        loadGroups();
        loadStudents();
        loadChairs();
    }


    private void loadChairs() {
        chairRepository.deleteAll();
        chairRepository.saveAll(ruzApiService.getAllChairs());
    }

    private void loadStudents() {
        studentRepository.deleteAll();
        groupRepository.findAll()
                .parallelStream()
                .parallel()
                .forEach(group -> studentRepository.saveAll(ruzApiService.getStudents(group.getGroupOid())
                        .stream()
                        .peek(student -> {
                                    student.setGroupID(group.getGroupOid());
                                    student.setFacultyID(group.getFacultyOid());
                                    student.setInstituteID(group.getInstituteId());
                                }
                        ).collect(Collectors.toList())));
    }

    private void loadGroups() {
        groupRepository.deleteAll();
        QFaculty qFaculty = QFaculty.faculty;
        groupRepository.saveAll(ruzApiService.getGroups().stream()
                .peek(group -> {
                    Optional<Faculty> faculty = facultyRepository.findOne(qFaculty.facultyOid.eq(group.getFacultyOid()));
                    faculty.ifPresent(faculty1 -> group.setInstituteId(faculty1.getInstituteId()));
                })
                .collect(Collectors.toList()));
    }

    private void loadFaculties() {
        facultyRepository.deleteAll();
        QInstitute qInstitute = QInstitute.institute;
        List<Faculty> faculties = ruzApiService.getAllFaculties();
        faculties.forEach(faculty -> {
            Optional<Institute> institute = instituteRepository.findOne(qInstitute.name.eq(faculty.getInstitute()));
            institute.ifPresent(institute1 -> faculty.setInstituteId(institute1.getId()));
        });
        facultyRepository.saveAll(faculties);
    }

    private void loadInstitutes() {
        instituteRepository.deleteAll();
        instituteRepository.saveAll(ruzApiService.getAllFaculties().stream()
                .map(Faculty::getInstitute).collect(Collectors.toSet()).stream()
                .map(Institute::new)
                .collect(Collectors.toList()));
    }
}
