package network.tcp.autocloseable;

// 문제점1 : 자원 정리를 하지 못한채 코드 종료, 생성자 생성시 발생하는 예외에 대한 처리 미흡
public class ResourceCloseMainV1 {

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
        ResourceV1 resource1 = new ResourceV1("resource1");
        ResourceV1 resource2 = new ResourceV1("resource2");

        resource1.call();
        resource2.callEx();

        System.out.println("자원 정리"); // 호출 안됨
        resource2.closeEx();
        resource1.closeEx();
    }
}
