package hse.holuhoev.loader;

import hse.holuhoev.domain.Faculty;
import hse.holuhoev.domain.Institute;
import hse.holuhoev.domain.QInstitute;
import hse.holuhoev.repo.FacultyRepository;
import hse.holuhoev.repo.GroupRepository;
import hse.holuhoev.repo.InstituteRepository;
import hse.holuhoev.repo.StudentRepository;
import hse.holuhoev.ruz.api.RuzApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Evgeny Kholukhoev
 */
@Component
public class DataBaseLoader implements CommandLineRunner {
    private final RuzApiService ruzApiService;
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final InstituteRepository instituteRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public DataBaseLoader(RuzApiService ruzApiService
            , StudentRepository studentRepository
            , FacultyRepository facultyRepository
            , InstituteRepository instituteRepository
            , GroupRepository groupRepository) {
        this.ruzApiService = ruzApiService;
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.instituteRepository = instituteRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        loadInstitutes();
        loadFaculties();

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
        Set<String> institutes = new HashSet<>();
        ruzApiService.getAllFaculties()
                .forEach(faculty -> {
                    // TODO: Replace with StringUtils
                    if (faculty.getInstitute() != null && !faculty.getInstitute().isEmpty()) {
                        institutes.add(faculty.getInstitute());
                    }
                });
        instituteRepository.saveAll(institutes.stream()
                .map(Institute::new)
                .collect(Collectors.toList()
                ));
    }

}
