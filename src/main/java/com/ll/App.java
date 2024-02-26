package com.ll;

import java.util.Scanner;

class App {
    void run() {
        System.out.println("프로그램 실행");
        System.out.println("== 명언 앱 ==");
        for ( ; ; ){
            System.out.print("명령 ) ");
            Scanner s = new Scanner(System.in);
            String 명령 = s.nextLine();
            if (명령.equals("종료")) {
                break;
            } else if (명령.equals("등록")){
                System.out.print("명언 : ");
                String 명언 = s.nextLine();
                System.out.print("작가 : ");
                String 작가 = s.nextLine();
            }
        }

    }
}
