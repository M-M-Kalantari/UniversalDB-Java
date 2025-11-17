package UniversalDB.hibernateDB;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Class_Member {

    @Id
    private static Integer id;
    private static String name;
    private static String family;

    public static Integer getId() {
        return id;
    }

    public static void setId(Integer id) {
        Class_Member.id = id;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Class_Member.name = name;
    }

    public static String getFamily() {
        return family;
    }

    public static void setFamily(String family) {
        Class_Member.family = family;
    }
}
