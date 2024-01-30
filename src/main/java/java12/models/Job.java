package java12.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Job {
    private Long job_id;
    private String job_position;
    //    ("Mentor","Management","Instructor") ушундай маанилер берилсин
    private String profession;
    //    ("Java","JavaScript")
    private String description;
    //    ("Backend developer","Fronted developer")
    private int experience;
//    (1,2,3........) опыт работы
}
