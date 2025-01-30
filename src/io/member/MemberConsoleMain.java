package io.member;

import java.util.List;
import java.util.Scanner;

import io.member.impl.DataMemberRepository;
import io.member.impl.FileMemberRepository;
import io.member.impl.MemoryMemberRepository;
import io.member.impl.ObjectMemberRepository;

/**
 * i/o 활용
 *  1. 회원관리예제(1) : 메모리 회원 예제
 *  2. 회원관리예제(2) : 파일 회원 예제
 *  3. 회원관리예제(3) : 데이터 회원 예제
 *  4. 회원관리예제(4) : 오브젝트 회원 예제
 */
public class MemberConsoleMain {

    // private final static MemberRepository memberRepository = new MemoryMemberRepository(); // (1)
    // private final static MemberRepository memberRepository = new FileMemberRepository(); // (2)
    // private final static MemberRepository memberRepository = new DataMemberRepository(); // (3)
    private final static MemberRepository memberRepository = new ObjectMemberRepository(); // (4)

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("1.회원 등록 | 2.회원 목록 조회 | 3.종료"); 
            System.out.print("선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // newline 제거

            switch (choice) {
                case 1:
                    registerMember(scanner);
                    break;
                case 2:
                    displayMembes(scanner);
                    break;
                case 3:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택하세요.");
            }
        }
    }

    private static void registerMember(Scanner scanner) {
        System.out.println("Id를 입력하세요 : ");
        String id = scanner.nextLine();

        System.out.println("Name를 입력하세요 : ");
        String name = scanner.nextLine();

        System.out.println("Age를 입력하세요 : ");
        int age = scanner.nextInt();
        scanner.nextLine(); // newline 제거

        Member newMember = new Member(id, name, age);
        memberRepository.add(newMember);
        System.out.println("회원이 성공적으로 등록되었습니다.");
    }

    private static void displayMembes(Scanner scanner) {
        List<Member> members = memberRepository.findAll();
        System.out.println("회원 목록:");
        for(Member member : members) {
            System.out.printf("[ID: %s, Name: %s, Age: %d]\n", member.getId(),member.getName(), member.getAge());
        }
    }
}
