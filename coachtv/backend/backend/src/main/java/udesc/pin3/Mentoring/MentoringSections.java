package udesc.pin3.Mentoring;

import java.util.Arrays;
import java.util.List;

public class MentoringSections {

    public static final String MUSIC = "Música";
    public static final String PROGRAMMING = "Programação";
    public static final String ELETRONICS = "Eletrônica";
    public static final String ROBOTIC = "Robótica";
    public static final String FINANCES = "Finanças";
    public static final String BIOLOGY = "Biologia";
    public static final String CHEMISTRY = "Química";
    public static final String FOOD = "Alimentação";

    public static List<String> getSections(){
        return Arrays.asList(MUSIC, PROGRAMMING, ELETRONICS, ROBOTIC, FINANCES, BIOLOGY, CHEMISTRY, FOOD);
    }

}
