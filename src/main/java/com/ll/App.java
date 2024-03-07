package com.ll;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class App {
    int lastQuotationid;
    Scanner scanner;
    List<Quotation> quotations;
    int num_delete_or_change;
    int num_search;
    String AuthorName_search;
    String content_search;

    App() {
        lastQuotationid = 0;
        scanner = new Scanner(System.in);
        quotations = new ArrayList<>();
    }

    void run() {
        System.out.println("== 명언 앱 ==");
        System.out.println("[ 등록 / 목록 / 삭제 / 수정 / 검색 / 종료 ] 중 원하시는 메뉴를 입력해주세요.");
        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            if (cmd.equals("종료")) {
                break;

            } else if (cmd.equals("등록")) {
                actionWrite();

            } else if (cmd.equals("목록")) {
                actionList();

            } else if (cmd.contains("삭제") || cmd.contains("수정")) {
                actionDeleteOrChange(cmd);

            } else if (cmd.equals("검색")) {
                actionSearch();

            } else {
                System.out.println("잘못 입력하셨습니다.");
                System.out.println("[ 등록 / 목록 / 삭제 / 수정 / 검색 / 종료 ] 중 하나를 입력해주세요.");

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

        System.out.println(lastQuotationid + "번 명언이 등록되었습니다.");
        System.out.println("[ 등록 / 목록 / 삭제 / 수정 / 검색 / 종료 ] 중 원하시는 메뉴를 입력해주세요.");
    }

    void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("--------------------");

        if (quotations.isEmpty()) {
            System.out.println("등록된 명언이 없습니다.");
        }

        for (int i = quotations.size() - 1; i >= 0; i--) {
            System.out.println(quotations.get(i).id + " / " + quotations.get(i).authorName + " / " + quotations.get(i).content);
        }

        System.out.println("[ 등록 / 목록 / 삭제 / 수정 / 검색 / 종료 ] 중 원하시는 메뉴를 입력해주세요.");
    }

    void actionDeleteOrChange(String cmd) {
        if (cmd.equals("삭제")) {
            System.out.println("삭제를 원하시면 삭제?id=(번호)를 입력해주세요.");

        } else if (cmd.equals("수정")) {
            System.out.println("수정을 원하시면 수정?id=(번호)를 입력해주세요.");

        }
        if (cmd.contains("?id=")) {
            String[] etc = cmd.split("\\?id=");

            try {
                num_delete_or_change = Integer.parseInt(etc[1]);

                if (etc[0].equals("삭제")) {
                    actionDelete();

                } else if (etc[0].equals("수정")) {
                    actionChange();

                } else {
                    System.out.println("잘못 입력하셨습니다.");
                    System.out.println("올바른 메뉴를 입력해주세요.");

                }
            } catch (NumberFormatException e) {
                System.out.println("올바른 번호(1보다 큰 정수)를 입력해주세요.");

            }
        }
    }

    void actionDelete() {
        try {
            for (int i = 0; i <= quotations.size(); i++) {
                if (quotations.get(i).id == num_delete_or_change) {
                    quotations.remove(i);
                    System.out.println(num_delete_or_change + "번 명언이 삭제되었습니다.");
                    System.out.println("[ 등록 / 목록 / 삭제 / 수정 / 검색 / 종료 ] 중 원하시는 메뉴를 입력해주세요.");
                    break;

                }
            }
        } catch (IndexOutOfBoundsException e1) {
            System.out.println(num_delete_or_change + "번 명언은 존재하지 않습니다.");
            System.out.println("[ 등록 / 목록 / 삭제 / 수정 / 검색 / 종료 ] 중 원하시는 메뉴를 입력해주세요.");

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
                    System.out.println("[ 등록 / 목록 / 삭제 / 수정 / 검색 / 종료 ] 중 원하시는 메뉴를 입력해주세요.");
                    break;

                }
            }
        } catch (IndexOutOfBoundsException e2) {
            System.out.println(num_delete_or_change + "번 명언은 존재하지 않습니다.");
            System.out.println("[ 등록 / 목록 / 삭제 / 수정 / 검색 / 종료 ] 중 원하시는 메뉴를 입력해주세요.");

        }
    }

    void actionSearch() {
        System.out.println("[ 번호 / 작가 / 명언 / 검색종료 ] 중 원하시는 검색 메뉴를 입력해주세요.");

        for (; ; ) {
            System.out.print("메뉴) ");
            String searchMenu = scanner.nextLine();

            if (searchMenu.equals("번호")) {
                actionSearchNumber();
                break;

            } else if (searchMenu.equals("작가")) {
                actionSearchAuthorName();
                break;

            } else if (searchMenu.equals("명언")) {
                actionSearchContent();
                break;

            } else if (searchMenu.equals("검색종료")) {
                actionSearchEnd();
                break;

            } else {
                System.out.println("잘못 입력하셨습니다.");
                System.out.println("[ 번호 / 작가 / 명언 / 검색종료 ] 중 하나를 입력해주세요.");

            }
        }
    }

    void actionSearchNumber() {
        System.out.println("검색하고자 하는 번호를 입력해주세요.");
        System.out.print("번호) ");

        try {
            num_search = scanner.nextInt();
            String trash = scanner.nextLine();

            for (int i = 0; i <= quotations.size(); i++) {
                if (quotations.get(i).id == num_search) {
                    System.out.println("번호 / 작가 / 명언");
                    System.out.println("--------------------");
                    System.out.println(quotations.get(i).id + " / " + quotations.get(i).authorName + " / " + quotations.get(i).content);
                    System.out.println("[ 등록 / 목록 / 삭제 / 수정 / 검색 / 종료 ] 중 원하시는 메뉴를 입력해주세요.");
                    break;

                }
            }
        } catch (InputMismatchException e) {
            System.out.println("올바른 번호를 입력해주세요.");

        } catch (IndexOutOfBoundsException e) {
            System.out.println(num_search + "번 명언은 존재하지 않습니다.");
            System.out.println("[ 등록 / 목록 / 삭제 / 수정 / 검색 / 종료 ] 중 원하시는 메뉴를 입력해주세요.");

        }
    }

    void actionSearchAuthorName() {
        System.out.println("검색하고자 하는 작가를 입력해주세요.");
        System.out.print("작가) ");
        int a = 0;

        try {
            AuthorName_search = scanner.nextLine();

            for (int i = 0; i <= quotations.size(); i++) {
                if (quotations.get(i).authorName.equals(AuthorName_search)) {
                    if (a == 0) {
                        System.out.println("번호 / 작가 / 명언");
                        System.out.println("--------------------");
                        a++;

                    }
                    System.out.println(quotations.get(i).id + " / " + quotations.get(i).authorName + " / " + quotations.get(i).content);

                }
            }
        } catch (IndexOutOfBoundsException e) {
            if (a == 0) {
                System.out.println("'" + AuthorName_search + "' 작가의 명언은 존재하지 않습니다.");

            }
            System.out.println("[ 등록 / 목록 / 삭제 / 수정 / 검색 / 종료 ] 중 원하시는 메뉴를 입력해주세요.");

        }
    }

    void actionSearchContent() {
        System.out.println("검색하고자 하는 명언을 입력해주세요.");
        System.out.print("명언) ");

        try {
            content_search = scanner.nextLine();

            for (int i = 0; i <= quotations.size(); i++) {
                if (quotations.get(i).content.equals(content_search)) {
                    System.out.println("번호 / 작가 / 명언");
                    System.out.println("--------------------");
                    System.out.println(quotations.get(i).id + " / " + quotations.get(i).authorName + " / " + quotations.get(i).content);
                    System.out.println("[ 등록 / 목록 / 삭제 / 수정 / 검색 / 종료 ] 중 원하시는 메뉴를 입력해주세요.");
                    break;

                }
            }
        } catch (InputMismatchException e) {
            System.out.println("올바른 명언을 입력해주세요.");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("'" + content_search + "' 명언은 존재하지 않습니다.");
            System.out.println("[ 등록 / 목록 / 삭제 / 수정 / 검색 / 종료 ] 중 원하시는 메뉴를 입력해주세요.");

        }
    }

    void actionSearchEnd() {
        System.out.println("검색을 종료합니다.");
        System.out.println("[ 등록 / 목록 / 삭제 / 수정 / 검색 / 종료 ] 중 원하시는 메뉴를 입력해주세요.");
gi
    }
}