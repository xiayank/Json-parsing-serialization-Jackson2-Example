/**
 * Created by NIC on 10/27/17.
 */

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Jackson2Example {
    public static void main(String[] args) {
        Jackson2Example obj = new Jackson2Example();
        obj.run();
    }

    private void run() {
        ObjectMapper mapper = new ObjectMapper();

        Staff staff = createDummyObject();

        try {
            //Serialization
            //Convert object to JSON string and save into a file directly
            //mapper.writeValue(new File("D:\\staff.json"), staff);

            // Convert object to JSON string
            String jsonInString = mapper.writeValueAsString(staff);
            System.out.println(jsonInString);

            // Convert object to JSON string and pretty print
            jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(staff);
            System.out.println(jsonInString);

            //jsonDeserialization
            // Convert JSON string from file to Object
            Staff staff2 = mapper.readValue(new File("D:\\staff.json"), Staff.class);
            System.out.println(staff2);

            // Convert JSON string to Object
            String json = "{\"name\":\"mkyong\",\"salary\":7500,\"skills\":[\"java\",\"python\"]}";
            Staff staff1 = mapper.readValue(json, Staff.class);
            String stringStaff = mapper.writeValueAsString(staff1);
            System.out.println(stringStaff);
            String prettyStaff1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(staff1);
            System.out.println(prettyStaff1);


        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Staff createDummyObject() {

        Staff staff = new Staff();

        staff.setName("mkyong");
        staff.setAge(33);
        staff.setPosition("Developer");
        staff.setSalary(new BigDecimal("7500"));

        List<String> skills = new ArrayList<String>();
        skills.add("java");
        skills.add("python");

        staff.setSkills(skills);

        return staff;

    }
    /*
    private String jsonSerialization(Staff staff){
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(staff);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonInString;

    }

    private Staff jsonDeserialization(String jsonString){
        ObjectMapper mapper = new ObjectMapper();
        Staff staff = null;
        try {
            staff = mapper.readValue(jsonString, Staff.class);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return staff;
    }
    */

}
