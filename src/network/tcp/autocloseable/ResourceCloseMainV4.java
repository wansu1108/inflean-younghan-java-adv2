package network.tcp.autocloseable;

// try-with-resources를 통한 자원정리 => 6가지 장점을 확인하자.
// close를 할 때 발생하는 예외는, 비지니스 핵심예외(CallException) 내부 Suppressed에 넣어서 같이 전달해준다.
public class ResourceCloseMainV4 {

    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            // Suppressed 예외 알아보기
            Throwable[] throwables = e.getSuppressed();
            for(Throwable throwable : throwables) {
                System.out.println("suppressedEx = " + throwable);
            }

            System.out.println("CallException 예외 처리");
            e.printStackTrace();
        } catch (CloseException e) {
            System.out.println("CloseException 예외 처리");
            e.printStackTrace();
        }
    }

    public static void logic() throws CallException, CloseException {
        try (
            ResourceV2 resource1 = new ResourceV2("resource1");
            ResourceV2 resource2 = new ResourceV2("resource2");
        ) {
            resource1.call();
            resource2.callEx();

            // try with resource는 구문이 끝날 때 예외처리를 실행
            // 예외처리는 선언순서와 반대로 진행하고, try with resource는 순서를 보장해서 자원정리를 한다.
            // resource2.close();
            // resource1.close();
        } catch (CallException e) {
            // 자원 정리가 끝나면, catch블록이 실행된다.
            System.out.println("ex: " + e);
            throw e; // CallException
        }
    }
}
