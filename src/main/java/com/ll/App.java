package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class App {
    int lastQuotationid;
    Scanner scanner;
    List<Quotation> quotations;
    int num_delete_or_change;

    App() {
        lastQuotationid = 0;
        scanner = new Scanner(System.in);
        quotations = new ArrayList<>();
    }

    void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            if (cmd.equals("종료")) {
                break;

            } else if (cmd.equals("등록")) {
                actionWrite();

            } else if (cmd.equals("목록")) {
                actionList();

            } else if (cmd.equals("삭제")) {
                System.out.println("삭제를 원하시면 삭제?id=(번호)를 입력해주세요.");

            } else if (cmd.equals("수정")) {
                System.out.println("수정을 원하시면 수정?id=(번호)를 입력해주세요.");

            } else if (cmd.contains("?id=")) {
                String[] etc = cmd.split("\\?id=");
                try {
                    num_delete_or_change = Integer.parseInt(etc[1]);
                    if (etc[0].equals("삭제")) {
                        actionDelete();
                    } else if (etc[0].equals("수정")) {
                        actionChange();
                    } else {
                        System.out.println("잘못 입력하셨습니다.");
                        System.out.println("[ 삭제?id=(번호) / 수정?id=(번호) ] 중 하나로 입력해주세요.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("올바른 번호(1보다 큰 정수)를 입력해주세요.");
                }
            } else {
                System.out.println("잘못 입력하셨습니다.");
                System.out.println("[ 등록 / 목록 / 삭제 / 수정 ] 중 하나로 입력해주세요.");
            }
        }
    }

    void actionWrite() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.print("작가 : ");
        String authorName = scanner.nextLine();
        lastQuotationid++;
        int id = lastQuotationid;

        Quotation quotation = new Quotation(id, content, authorName);
        quotations.add(quotation);

        System.out.printf("%d번 명언이 등록되었습니다.\n", lastQuotationid);
    }

    void actionList() {
        System.out.println("번호 / 작가 / 명언");

        System.out.println("--------------------");

        if (quotations.isEmpty())
            System.out.println("등록된 명언이 없습니다.");

        for (int i = quotations.size() - 1; i >= 0; i--) {
            System.out.println(quotations.get(i).id + " / " + quotations.get(i).authorName + " / " + quotations.get(i).content);
        }
    }

    void actionDelete() {
        try {
            for (int i = 0; i <= quotations.size(); i++) {
                if (quotations.get(i).id == num_delete_or_change) {
                    quotations.remove(i);
                    System.out.println(num_delete_or_change + "번 명언이 삭제되었습니다.");
                    break;
                }
            }
        } catch (IndexOutOfBoundsException e1) {
            System.out.println(num_delete_or_change + "번 명언은 존재하지 않습니다.");
        }
    }

    void actionChange() {
        try {
            for (int i = 0; i <= quotations.size(); i++) {
                if (quotations.get(i).id == num_delete_or_change) {
                    System.out.printf("명언(기존) : %s\n명언 : ", quotations.get(i).content);
                    quotations.get(i).content = scanner.nextLine();
                    System.out.printf("작가(기존) : %s\n작가 : ", quotations.get(i).authorName);
                    quotations.get(i).authorName = scanner.nextLine();
                    break;
                }
            }
        } catch (IndexOutOfBoundsException e2) {
            System.out.println(num_delete_or_change + "번 명언은 존재하지 않습니다.");
        }
    }
}