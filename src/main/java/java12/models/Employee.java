package java12.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
    private Long employee_id;

    private String firstName;

    private String lastName;
    private int age;

    private String email;
    private int job_id ;
//    (reference)

    public Employee(String firstName, String lastName, int age, String email, int job_id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.job_id = job_id;
    }
}
