package java12.dao;

import java12.config.Configuraion;
import java12.models.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDaoImpl implements JobDao {
    private final Connection connection = Configuraion.getConnection();

    @Override
    public void createJobTable() {
        String query = """
                create table if not exists jobs(
                job_id serial primary key ,
                job_position varchar,
                profession varchar,
                description varchar,
                experience int
                );
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int i = preparedStatement.executeUpdate();
            if (i >= 0) {
                System.out.println("Success created!!!");
            } else {
                System.out.println("Not success !");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void addJob(Job job) {
        String query = """
                insert into jobs(
                job_position,
                profession,
                description,
                experience
                )values(?,?,?,?);
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, job.getJob_position());
            preparedStatement.setString(2, job.getProfession());
            preparedStatement.setString(3, job.getDescription());
            preparedStatement.setInt(4, job.getExperience());
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                System.out.println("Added!");
            } else {
                System.out.println("Not added!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Job getJobById(Long jobId) {
        Job job = new Job();
        String query = """
                select * from jobs where job_id = ?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, jobId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    job.setJob_id(resultSet.getLong("job_id"));
                    job.setJob_position(resultSet.getString("job_position"));
                    job.setProfession(resultSet.getString("profession"));
                    job.setDescription(resultSet.getString("description"));
                    job.setExperience(resultSet.getInt("experience"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return job;
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        List<Job> jobs = new ArrayList<>();
        Job job = new Job();
        if (ascOrDesc.equalsIgnoreCase("asc")) {
            String query = """
                    select * from jobs order by job_position;
                    """;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    job.setJob_id(resultSet.getLong("job_id"));
                    job.setJob_position(resultSet.getString("job_position"));
                    job.setProfession(resultSet.getString("profession"));
                    job.setExperience(resultSet.getInt("experience"));
                    job.setDescription(resultSet.getString("description"));
                    jobs.add(job);
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else if (ascOrDesc.equalsIgnoreCase("desc")) {
            String query = """
                    select * from jobs order by job_position desc;
                    """;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    job.setJob_id(resultSet.getLong("job_id"));
                    job.setJob_position(resultSet.getString("job_position"));
                    job.setProfession(resultSet.getString("profession"));
                    job.setExperience(resultSet.getInt("experience"));
                    job.setDescription(resultSet.getString("description"));
                    jobs.add(job);
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return jobs;
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        Job job = null;
        String query = """
                select * from jobs j join employees e on j.job_id = e.job_id where e.employee_id = ?; 
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, employeeId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    job = new Job();
                    job.setJob_id(resultSet.getLong("job_id"));
                    job.setJob_position(resultSet.getString("job_position"));
                    job.setProfession(resultSet.getString("profession"));
                    job.setDescription(resultSet.getString("description"));
                    job.setExperience(resultSet.getInt("experience"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return job;
    }

    @Override
    public void deleteDescriptionColumn() {
        String query = """
                alter table jobs drop column description; 
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int i = preparedStatement.executeUpdate();
            if (i >= 0) {
                System.out.println("Success!!!");
            } else {
                System.out.println("Not success!!!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void addColumnDescription() {
        String qu = """
                alter table jobs add column description varchar; 
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(qu);) {
            int i = preparedStatement.executeUpdate();
            if (i >= 0) {
                System.out.println("Success");
            } else {
                System.out.println("not success");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


}

