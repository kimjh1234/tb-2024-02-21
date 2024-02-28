package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class App {
    void run() {
        System.out.println("== 명언 앱 ==");
        int lastQuotationid = 0;

        List<Quotation> quotations = new ArrayList<>();
        while (true) {
            System.out.print("명령) ");

            Scanner scanner = new Scanner(System.in);
            String cmd = scanner.nextLine();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                String content = scanner.nextLine();

                System.out.print("작가 : ");
                String authorName = scanner.nextLine();
                lastQuotationid++;
                int id = lastQuotationid;

                Quotation quotation = new Quotation(id, content, authorName);
                quotations.add(quotation);

                System.out.printf("%d번 명언이 등록되었습니다.\n", lastQuotationid);
            } else if (cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");

                System.out.println("--------------------");

                if (quotations.isEmpty())
                    System.out.println("등록된 명언이 없습니다.");

                for (int i = quotations.size() - 1; i >= 0; i--) {
                    System.out.println(quotations.get(i).id + " / " + quotations.get(i).authorName + " / " + quotations.get(i).content);
                }
            } else {
                String[] etc = cmd.split("\\?id=");
                int num = Integer.parseInt(etc[1]);
                try {
                    if (etc[0].equals("삭제")) {
                        for (int i = 0; i <= quotations.size(); i++) {
                            if (quotations.get(i).id == num) {
                                quotations.remove(i);
                                System.out.println(num + "번 명언이 삭제되었습니다.");
                                break;
                            }
                        }
                    } else if (etc[0].equals("수정")) {
                        for (int i = 0; i <= quotations.size(); i++) {
                            if (quotations.get(i).id == num) {
                                System.out.printf("명언(기존) : %s\n명언 : ", quotations.get(i).content);
                                quotations.get(i).content = scanner.nextLine();
                                System.out.printf("작가(기존) : %s\n작가 : ", quotations.get(i).authorName);
                                quotations.get(i).authorName = scanner.nextLine();
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println(num + "번 명언은 존재하지 않습니다.");
                }
            }
        }
    }
}
