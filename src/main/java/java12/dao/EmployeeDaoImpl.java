package java12.dao;

import java12.config.Configuraion;
import java12.models.Employee;
import java12.models.Job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDaoImpl implements EmployeeDao {
    private final Connection connection = Configuraion.getConnection();

    @Override
    public void createEmployee() {
        String query = """
                create table if not exists employees(
                employee_id serial primary key,
                first_name varchar,
                last_name varchar,
                age int,
                email varchar,
                job_id INT REFERENCES jobs(job_id)
                );
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int result = preparedStatement.executeUpdate();
            if (result >= 0) {
                System.out.println("Success !");
            } else {
                System.out.println("Not success !");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addEmployee(Employee employee) {
        String query = """
                insert into employees(
                first_name,last_name,age,email,job_id)
                values(?,?,?,?,?);
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setInt(5, employee.getJob_id());
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                System.out.println("Success!!!");
            } else {
                System.out.println("Not succes!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
//    public void saveUser(String name, String lastName, byte age) {
//        String query = """
//                insert into users(name,lastName,age)
//                values(?,?,?);
//                """;
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
//            preparedStatement.setString(1,name);
//            preparedStatement.setString(2, lastName);
//            preparedStatement.setInt(3, age);

    @Override
    public void dropTable() {
        String query = "drop table if exists employees;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int i = preparedStatement.executeUpdate();
            if (i >= 0) {
                System.out.println("Success");
            } else {
                System.out.println("Errorbek");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void cleanTable() {
        String query = "truncate table employees;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int i = preparedStatement.executeUpdate();
            if (i >= 0) {
                System.out.println("Success");
            } else {
                System.out.println("Error");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
        String query = """
                update employees set
                first_name = ?,
                 last_name = ?,
                age = ?,
                 email = ?,
                 job_id = ?
                where employee_id =?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setLong(5, employee.getJob_id());
            preparedStatement.setLong(6, id);
            int i = preparedStatement.executeUpdate();
            if (i >= 0) {
                System.out.println("Updated!");
            } else {
                System.out.println("Not success!!!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        String query = """
                select * from employees;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployee_id(resultSet.getLong("employee_id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setAge(resultSet.getInt("age"));
                employee.setEmail(resultSet.getString("email"));
                employee.setJob_id(resultSet.getInt("job_id"));
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }
//       public List<User> getAllUsers() {
//        List<User> users = new ArrayList<>();
//        String query = """
//                select * from users;
//                """;
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()){
//                User user = new User();
//                user.setId(resultSet.getLong("id"));
//                user.setName(resultSet.getString("name"));
//                user.setLastName(resultSet.getString("lastName"));
//                user.setAge(resultSet.getByte("age"));
//                users.add(user);
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return users;
//    }

    @Override
    public Employee findByEmail(String email) {
        Employee employee = new Employee();
        String query = """
                select * from employees where email = ?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    employee.setEmployee_id(resultSet.getLong("employee_id"));
                    employee.setFirstName(resultSet.getString("first_name"));
                    employee.setLastName(resultSet.getString("last_name"));
                    employee.setAge(resultSet.getInt("age"));
                    employee.setEmail(resultSet.getString("email"));
                    employee.setJob_id(resultSet.getInt("job_id"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employee;
    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        Map<Employee, Job> employeeJobMap = new HashMap<>();
        String query = """
                select * from employees e join jobs j on e.job_id = j.job_id where e.employee_id = ?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, employeeId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Employee employee = new Employee();
                    Job job = new Job();
                    employee.setEmployee_id(resultSet.getLong("employee_id"));
                    employee.setFirstName(resultSet.getString("first_name"));
                    employee.setLastName(resultSet.getString("last_name"));
                    employee.setAge(resultSet.getInt("age"));
                    employee.setEmail(resultSet.getString("email"));
                    employee.setJob_id(resultSet.getInt("job_id"));
                    job.setJob_id(resultSet.getLong("job_id"));
                    job.setJob_position(resultSet.getString("job_position"));
                    job.setProfession(resultSet.getString("profession"));
                    job.setDescription(resultSet.getString("description"));
                    job.setExperience(resultSet.getInt("experience"));
                    employeeJobMap.put(employee,job);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return employeeJobMap;
    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        Employee employee = new Employee();
        List<Employee > employees = new ArrayList<>();
        String query = """
                select * from employees e join jobs j on e.job_id = j.job_id where job_position = ?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, position);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    employee.setEmployee_id(resultSet.getLong("employee_id"));
                    employee.setFirstName(resultSet.getString("first_name"));
                    employee.setLastName(resultSet.getString("last_name"));
                    employee.setAge(resultSet.getInt("age"));
                    employee.setEmail(resultSet.getString("email"));
                    employee.setJob_id(resultSet.getInt("job_id"));
                    employees.add(employee);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employees;
    }
};
