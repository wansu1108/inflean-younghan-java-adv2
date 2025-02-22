package network.tcp.autocloseable;

// 문제점3: 핵심 로직에 대한 로그 남기기, resource1,2자원 정리 코드 동작 => BUT! 코드가 너무 지저분하다.
public class ResourceCloseMainV3 {

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
                try {
                    resource2.closeEx(); // CloseException 발생!
                } catch (CloseException e) {
                    // 보통 자원정리에 대한 예외처리를 할 수 있는게 없으므로, 로그정도만 남겨놓는다.
                    System.out.println("close ex: " + e);
                }
            }
            if(resource1 != null) {
                try {
                    resource1.closeEx(); 
                } catch (CloseException e) {
                    System.out.println("close ex: " + e);
                }
            }
        }
    }
}
