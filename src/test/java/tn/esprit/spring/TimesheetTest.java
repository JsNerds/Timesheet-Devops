package tn.esprit.spring;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.TimesheetRepository;
import tn.esprit.spring.services.ITimesheetService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimesheetTest {

    @Autowired
    private ITimesheetService sTimesheet;

    @Autowired
    private TimesheetRepository timesheetRepo;

    @Test
    public void testAddMission() {
        Mission mission = new Mission("Inspection", "sur terrain");
        int id = sTimesheet.ajouterMission((mission));

        Assert.assertNotNull(sTimesheet.getMissionById(id));
    }

}
