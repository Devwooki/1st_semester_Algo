package second.contest;
        import java.util.Random;
        import java.util.Scanner;

public class game {
    static String answer;
    static String input = "";
    public static void main(String[] args) {
        answer = makeRandomAnwer();
        System.out.println("게임을 시작하겠습니다.");
        while (!input.equals(answer) || !input.equals("0")) {
            System.out.println("정답 : " + answer);
            Scanner sc = new Scanner(System.in);
            System.out.print("숫자를 입력해주세요(포기시  0) : ");
            input = sc.nextLine();
            if (!checkUseful(input)) continue;
            String result = startGame(input);
            System.out.print("입력한 값: " + input + ", 결과: ");
            System.out.println(result);
            if (result.equals("3스트라이크 ")) break;
        }
        if (input.equals("0")) {
            System.out.println("포기하셨습니다.");
            System.out.println("정답 : " + answer);
        } else {
            System.out.println("정답입니다.");
            System.out.println("정답 : " + answer);
        }
    }

    private static boolean checkUseful(String input) {
        if (input.length() != 3) {
            System.out.println("입력 조건에 맞지 않습니다.");
            return false;
        }
        char a = input.charAt(0);
        char b = input.charAt(1);
        char c = input.charAt(2);
        int inputNum;
        try {
            inputNum = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("정수가 아닙니다. 수의 중복이 없는 123 ~ 987 사이의 수를 입력해주세요");
            return false;
        }
        if (inputNum < 111 || inputNum > 999) {
            System.out.println("범위를 벗어났습니다. 수의 중복이 없는 123 ~ 987 사이의 수를 입력해주세요");
            return false;
        } else if (a == b || a == c || b == c) {
            System.out.println("중복이 있습니다. 수의 중복이 없는 123 ~ 987 사이의 수를 입력해주세요 ");
            return false;
        }
        return true;
    }

    // 456
    private static String startGame(String input) { // 123
        StringBuilder result = new StringBuilder();
        int checkStrike = 0, checkBall = 0;
        INPUT:
        for (int i = 0; i < 3; i++) {
            if (input.charAt(i) == answer.charAt(i)) checkStrike++;
        }

        for(int i=0;i<3;++i) {
            for(int j=0;j<3;++j) {
                if(i == j) continue;
                if(input.charAt(i) == answer.charAt(j)) checkBall++;
            }
        }
        if (checkStrike > 0) result.append(checkStrike + "스트라이크 ");
        if (checkBall > 0) result.append(checkBall + "볼 ");
        if (checkStrike == 0 && checkBall == 0) result.append("아웃");
        return result.toString();
    }

    public static String makeRandomAnwer() {
        Random random = new Random();
        String a = "", b = "", c = "";
        while (a.equals(b) || a.equals(c) || b.equals(c)) {
            a = String.valueOf(random.nextInt(9) + 1);
            b = String.valueOf(random.nextInt(9) + 1);
            c = String.valueOf(random.nextInt(9) + 1);
        }
        String answer = a + b + c;
        return answer;
    }
}
