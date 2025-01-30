package io.member;

import java.io.Serializable;

// 역직렬화를 위해, 아무런 기능이 없는 Serializable 인터페이스(마커 인터페이스)를 구현하였다.
// 이런게 있었다 정도로 이해하면 된다.(여러 문제점과 대안으로 인해, 현재는 사용하지 않는다)
public class Member implements Serializable {
    
    private String id;
    private String name;
    private Integer age;

    public Member() {
    }

    public Member(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    @Override
    public String toString() {
            return "Member{" +
                    "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", age=" + age +
        '}';
    }
}
