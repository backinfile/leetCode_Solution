import java.util.Arrays;

public class Solution_1700 {
    public int countStudents(int[] students, int[] sandwiches) {
        int total = students.length;
        int rectNum = Arrays.stream(students).sum();
        int roundNum = total - rectNum;

        for (int i = 0; i < students.length; i++) {
            if (sandwiches[i] == 0 && roundNum > 0) {
                roundNum--;
            } else if (sandwiches[i] == 1 && rectNum > 0) {
                rectNum--;
            } else {
                break;
            }
        }
        return roundNum + rectNum;
    }
}
