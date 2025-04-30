package annotation.java;

public class OverrrideMain {
    static class A {
        public void call() {
            System.out.println("A.call()");
        }
    }

    static class B extends A {
        // 메소드를 오버라이드 할 때, 오타가 나는 경우, 컴파일오류 단계에서 확인할 수 있다.
        // @Override // 주석 제거 시, 오류 발생
        public void callllll(){
            System.out.println("B.callllll()");
        }
    }

    public static void main(String[] args) {
        A aClass = new B();
        aClass.call();
    }
}
