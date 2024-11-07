package it.jobtitles;

import com.companyname.services.employees.api.behavior.FindAllJobTitles;
import com.companyname.services.employees.api.model.JobTitleDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FindAllJobTitlesIT {

    @Autowired
    private FindAllJobTitles findAllJobTitles;

    @Test
    void findsAllJobTitles() {
        List<JobTitleDetails> theJobTitles = findAllJobTitles.findAllJobTitles();

        assertThat(theJobTitles).isNotEmpty();
        assertThat(theJobTitles).hasSize(4);
        assertThat(theJobTitles.get(0).getName()).isEqualTo("Software Engineer");
    }
}
