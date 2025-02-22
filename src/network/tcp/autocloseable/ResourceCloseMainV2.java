package network.tcp.autocloseable;

// 문제점2: resource1에 대한 자원정리를 하지 못하고, 핵심 비지니스로직(call)에 대한 예외가 아닌, close에 대한 내용이 로그에 남게된다.
public class ResourceCloseMainV2 {

    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("CallException 예외 처리");
            e.printStackTrace();
        } catch (CloseException e) {
            System.out.println("CloseException 예외 처리");
            e.printStackTrace();
        }
    }

    public static void logic() throws CallException, CloseException {
        ResourceV1 resource1 = null;
        ResourceV1 resource2 = null;

        try {
            resource1 = new ResourceV1("resource1"); // 생성자 생성시 예외 발생 가정
            resource2 = new ResourceV1("resource2");

            resource1.call();
            resource2.callEx(); // CallException 발생
        } catch (CallException e) {
            throw e;
        } finally {
            System.out.println("자원 정리");
            if(resource2 != null) {
                resource2.closeEx(); // CloseException 발생!
            }
            if(resource1 != null) {
                resource1.closeEx(); // 이 코드 호출 안됨!
            }
        }
    }
}
