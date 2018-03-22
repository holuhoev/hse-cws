package hse.holuhoev.loader;

import hse.holuhoev.domain.*;
import hse.holuhoev.repo.*;
import hse.holuhoev.ruz.api.RuzApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.LinkedList;
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
    private final PairRepository pairRepository;
    private final Logger logger = LoggerFactory.getLogger(DomainLoader.class);

    @Autowired
    public DomainLoader(RuzApiService ruzApiService
            , StudentRepository studentRepository
            , FacultyRepository facultyRepository
            , InstituteRepository instituteRepository
            , GroupRepository groupRepository
            , ChairRepository chairRepository, LecturerRepository lecturerRepository, BuildingRepository buildingRepository, PairRepository pairRepository) {
        this.ruzApiService = ruzApiService;
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.instituteRepository = instituteRepository;
        this.groupRepository = groupRepository;
        this.chairRepository = chairRepository;
        this.lecturerRepository = lecturerRepository;
        this.buildingRepository = buildingRepository;
        this.pairRepository = pairRepository;
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
        loadPairs();
        logger.info("Domain loader ends.");
    }


    private void loadBuildings() {
        logger.info("Buildings loader starts");
        buildingRepository.deleteAll();
        buildingRepository.saveAll(ruzApiService.getAllBuildings()
                .stream()
                .peek(building -> building.setCity(CityType.getCityType(building.getAddress())))
                .collect(Collectors.toList()));
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

    private void loadPairs() {
        logger.info("Pairs loader starts");
        pairRepository.deleteAll();
        List<Pair> pairs = new LinkedList<>();
        // TODO: Вынести в проперти файл pairs.json
        pairs.add(new Pair(CityType.MOSCOW, 1, LocalTime.of(9, 0), LocalTime.of(10, 20)));
        pairs.add(new Pair(CityType.MOSCOW, 2, LocalTime.of(10, 30), LocalTime.of(11, 50)));
        pairs.add(new Pair(CityType.MOSCOW, 3, LocalTime.of(12, 10), LocalTime.of(13, 30)));
        pairs.add(new Pair(CityType.MOSCOW, 4, LocalTime.of(13, 40), LocalTime.of(15, 0)));
        pairs.add(new Pair(CityType.MOSCOW, 5, LocalTime.of(15, 10), LocalTime.of(16, 30)));
        pairs.add(new Pair(CityType.MOSCOW, 6, LocalTime.of(16, 40), LocalTime.of(18, 0)));
        pairs.add(new Pair(CityType.MOSCOW, 7, LocalTime.of(18, 10), LocalTime.of(19, 30)));
        pairs.add(new Pair(CityType.MOSCOW, 8, LocalTime.of(19, 40), LocalTime.of(21, 0)));

        pairs.add(new Pair(CityType.SAINT_PETERBURG, 1, LocalTime.of(9, 0), LocalTime.of(10, 20)));
        pairs.add(new Pair(CityType.SAINT_PETERBURG, 2, LocalTime.of(10, 30), LocalTime.of(11, 50)));
        pairs.add(new Pair(CityType.SAINT_PETERBURG, 3, LocalTime.of(12, 10), LocalTime.of(13, 30)));
        pairs.add(new Pair(CityType.SAINT_PETERBURG, 4, LocalTime.of(13, 40), LocalTime.of(15, 0)));
        pairs.add(new Pair(CityType.SAINT_PETERBURG, 5, LocalTime.of(15, 20), LocalTime.of(16, 40)));
        pairs.add(new Pair(CityType.SAINT_PETERBURG, 6, LocalTime.of(16, 50), LocalTime.of(18, 10)));
        pairs.add(new Pair(CityType.SAINT_PETERBURG, 7, LocalTime.of(18, 20), LocalTime.of(19, 40)));
        pairs.add(new Pair(CityType.SAINT_PETERBURG, 8, LocalTime.of(19, 50), LocalTime.of(21, 10)));

        pairs.add(new Pair(CityType.NIZHNIY_NOVGOROD, 1, LocalTime.of(8, 0), LocalTime.of(9, 20)));
        pairs.add(new Pair(CityType.NIZHNIY_NOVGOROD, 2, LocalTime.of(9, 30), LocalTime.of(10, 50)));
        pairs.add(new Pair(CityType.NIZHNIY_NOVGOROD, 3, LocalTime.of(11, 10), LocalTime.of(12, 30)));
        pairs.add(new Pair(CityType.NIZHNIY_NOVGOROD, 4, LocalTime.of(12, 40), LocalTime.of(14, 0)));
        pairs.add(new Pair(CityType.NIZHNIY_NOVGOROD, 5, LocalTime.of(14, 20), LocalTime.of(15, 40)));
        pairs.add(new Pair(CityType.NIZHNIY_NOVGOROD, 6, LocalTime.of(15, 50), LocalTime.of(17, 10)));
        pairs.add(new Pair(CityType.NIZHNIY_NOVGOROD, 7, LocalTime.of(17, 30), LocalTime.of(18, 50)));
        pairs.add(new Pair(CityType.NIZHNIY_NOVGOROD, 8, LocalTime.of(19, 0), LocalTime.of(19, 20)));

        pairs.add(new Pair(CityType.PERM, 1, LocalTime.of(8, 10), LocalTime.of(9, 30)));
        pairs.add(new Pair(CityType.PERM, 2, LocalTime.of(9, 40), LocalTime.of(11, 0)));
        pairs.add(new Pair(CityType.PERM, 3, LocalTime.of(11, 30), LocalTime.of(12, 50)));
        pairs.add(new Pair(CityType.PERM, 4, LocalTime.of(13, 0), LocalTime.of(14, 20)));
        pairs.add(new Pair(CityType.PERM, 5, LocalTime.of(14, 30), LocalTime.of(15, 50)));
        pairs.add(new Pair(CityType.PERM, 6, LocalTime.of(16, 0), LocalTime.of(17, 20)));
        pairs.add(new Pair(CityType.PERM, 7, LocalTime.of(17, 30), LocalTime.of(18, 50)));
        pairs.add(new Pair(CityType.PERM, 8, LocalTime.of(19, 0), LocalTime.of(20, 20)));
        pairRepository.saveAll(pairs);
        logger.info("Pairs loader ends");
    }
}
