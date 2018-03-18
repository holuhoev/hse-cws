package hse.holuhoev.loader;

import hse.holuhoev.repo.StudentWorkloadRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkloadLoader {
    private final Logger logger = LoggerFactory.getLogger(WorkloadLoader.class);
    private final StudentWorkloadRepository studentWorkloadRepository;

    @Autowired
    public WorkloadLoader(StudentWorkloadRepository studentWorkloadRepository) {
        this.studentWorkloadRepository = studentWorkloadRepository;
    }

    public void run() {
        logger.info("Workload loader starts.");
        studentWorkloadLoad();
        logger.info("Workload loader ends.");
    }

    private void studentWorkloadLoad() {
        logger.info("Student workload loader starts.");
        studentWorkloadRepository.deleteAll();
        // TODO:
        logger.info("Student workload loader ends.");
    }

    private void lecturerWorkloadLoad() {
        logger.info("Lecturer workload loader starts.");
        // TODO:
        logger.info("Lecturer workload loader ends.");
    }
}
