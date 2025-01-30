package io.member.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import io.member.Member;
import io.member.MemberRepository;

// 자바의 List<Member> 형태로 파일에 저장할 수 있다
// 많은 단점으로, 더이상 사용하지 않는다.
public class ObjectMemberRepository implements MemberRepository {

    private static final String FILE_PATH = "temp/member-obj.dat";

    @Override
    public void add(Member member) {
        List<Member> members = findAll();
        members.add(member);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(members);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Member> findAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            // 직렬화는 많은 문제로 인해 현재 사용하지 않는다. 있다는것만 알아두자.
            Object findObject = ois.readObject(); // 컬렉션 형태로 저장했기 때문에 타입체크를 하지않아도 된다.
            return (List<Member>) findObject;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
}
