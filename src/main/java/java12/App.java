package java12;

import java12.config.Configuraion;
import java12.models.Employee;
import java12.models.Job;
import java12.services.EmployeeService;
import java12.services.EmployeeServiceImpl;
import java12.services.JobService;
import java12.services.JobServiceImpl;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        JobService jobService = new JobServiceImpl();
        Job job = new Job();
//        System.out.println( "Hello World!" );
//        Configuraion.getConnection();

//       1-method CREATE JOB TABLE
//        jobService.createJobTable();

//        2-method CREATE EMPLOYEE TABLE
//        employeeService.createEmployee();

//        3-method ADD JOB
//        job.setJob_position("Mentor");
//        job.setProfession("Java");
//        job.setDescription("Backend developer");
//        job.setExperience(2);
//        jobService.addJob(job);

//    4-method ADD EMPLOYEE
//        Employee employee = new Employee();
//        employee.setFirstName("Asan");
//        employee.setLastName("Asanov");
//        employee.setAge(23);
//        employee.setEmail("asan@gmail.com");
//        employee.setJob_id(1);
//        employeeService.addEmployee(employee);

//        5-method DROP EMPLOYEE TABLE
//        employeeService.dropTable();

//        6-method CLEAN EMPLOYEE TABLE;
//        employeeService.cleanTable();

//        7-mtehod UPDATE EMPLOYEE TABLE
//        employeeService.updateEmployee( 2L,new Employee("Nurkamil","Kamchiev",22,"n@gmail.com",1));

//        8-method GET ALL EMPLOYEES
//        System.out.println(employeeService.getAllEmployees());

//        9-method  FIND BY EMPLOYEE EMAIL
//        String email = "n@gmail.com";
//        System.out.println(employeeService.findByEmail(email));

//        10-method  GET EMPLOYEE BY ID
//        System.out.println(employeeService.getEmployeeById(3L));

//        11-method  GET EMPLOYEE BY POSITION
//        System.out.println(employeeService.getEmployeeByPosition("Mentor"));

//        12-method  GET JOB BY ID
//        System.out.println(jobService.getJobById(1L));

//        13-method   SORT BY EXPERIENCE
//        System.out.println(jobService.sortByExperience("desc"));

//        14-method  GET JOB BY EMPLOYEE ID
//        System.out.println(jobService.getJobByEmployeeId(2L));

//        15- method  DELETE COLUMN DESCRIPTION
//        jobService.deleteDescriptionColumn();

//            16-method ADD COLUMN DESCRIPTION
        jobService.addColumnDescription();


    }
}
