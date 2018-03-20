package hse.holuhoev.loader;

import hse.holuhoev.domain.*;
import hse.holuhoev.repo.*;
import hse.holuhoev.ruz.api.RuzApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DomainLoader {
    private final RuzApiService ruzApiService;
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final InstituteRepository instituteRepository;
    private final GroupRepository groupRepository;
    private final ChairRepository chairRepository;
    private final LecturerRepository lecturerRepository;
    private final BuildingRepository buildingRepository;
    private final Logger logger = LoggerFactory.getLogger(DomainLoader.class);

    @Autowired
    public DomainLoader(RuzApiService ruzApiService
            , StudentRepository studentRepository
            , FacultyRepository facultyRepository
            , InstituteRepository instituteRepository
            , GroupRepository groupRepository
            , ChairRepository chairRepository, LecturerRepository lecturerRepository, BuildingRepository buildingRepository) {
        this.ruzApiService = ruzApiService;
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.instituteRepository = instituteRepository;
        this.groupRepository = groupRepository;
        this.chairRepository = chairRepository;
        this.lecturerRepository = lecturerRepository;
        this.buildingRepository = buildingRepository;
    }

    public void run() {
        logger.info("Domain loader starts.");
        loadInstitutes();
        loadFaculties();
        loadGroups();
        loadStudents();
        loadChairs();
        loadLecturers();
        loadBuildings();
        logger.info("Domain loader ends.");
    }

    private void loadBuildings() {
        logger.info("Buildings loader starts");
        buildingRepository.deleteAll();
        buildingRepository.saveAll(ruzApiService.getAllBuildings());
        logger.info("Buildings loader ends");
    }

    private void loadLecturers() {
        logger.info("Lecturer loader starts");
        lecturerRepository.deleteAll();
        lecturerRepository.saveAll(ruzApiService.getAllLecturers());
        logger.info("Lecturer loader ends");
    }


    private void loadChairs() {
        logger.info("Chairs loader starts");
        chairRepository.deleteAll();
        chairRepository.saveAll(ruzApiService.getAllChairs());
        logger.info("Chairs loader ends");
    }

    private void loadStudents() {
        logger.info("Students loader starts");
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
        logger.info("Students loader ends");
    }

    private void loadGroups() {
        logger.info("Groups loader starts");
        groupRepository.deleteAll();
        QFaculty qFaculty = QFaculty.faculty;
        groupRepository.saveAll(ruzApiService.getGroups().stream()
                .peek(group -> {
                    Optional<Faculty> faculty = facultyRepository.findOne(qFaculty.facultyOid.eq(group.getFacultyOid()));
                    faculty.ifPresent(faculty1 -> group.setInstituteId(faculty1.getInstituteId()));
                })
                .collect(Collectors.toList()));
        logger.info("Groups loader ends");
    }

    private void loadFaculties() {
        logger.info("Faculties loader starts");
        facultyRepository.deleteAll();
        QInstitute qInstitute = QInstitute.institute;
        List<Faculty> faculties = ruzApiService.getAllFaculties();
        faculties.forEach(faculty -> {
            Optional<Institute> institute = instituteRepository.findOne(qInstitute.name.eq(faculty.getInstitute()));
            institute.ifPresent(institute1 -> faculty.setInstituteId(institute1.getId()));
        });
        facultyRepository.saveAll(faculties);
        logger.info("Faculties loader ends");
    }

    private void loadInstitutes() {
        logger.info("Institutes loader starts");
        instituteRepository.deleteAll();
        instituteRepository.saveAll(ruzApiService.getAllFaculties().stream()
                .map(Faculty::getInstitute).collect(Collectors.toSet()).stream()
                .map(Institute::new)
                .collect(Collectors.toList()));
        logger.info("Institutes loader ends");
    }
}
