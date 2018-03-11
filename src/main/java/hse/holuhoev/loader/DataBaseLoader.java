package hse.holuhoev.loader;

import hse.holuhoev.domain.Institute;
import hse.holuhoev.repo.FacultyRepository;
import hse.holuhoev.repo.GroupRepository;
import hse.holuhoev.repo.InstituteRepository;
import hse.holuhoev.repo.StudentRepository;
import hse.holuhoev.ruz.api.RuzApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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
        loadFacultiesAndInstitutes();

    }

    private void loadFacultiesAndInstitutes() {
        // TODO: сохранять айдишник института в факультет
        Set<String> instituteSet = new HashSet<>();
        ruzApiService.getAllFaculties().forEach(faculty -> {
            instituteSet.add(faculty.getInstitute());
            facultyRepository.save(faculty);
        });
        instituteRepository.saveAll(
                instituteSet.stream()
                        .map(Institute::new)
                        .collect(Collectors.toList())
        );
    }

}
