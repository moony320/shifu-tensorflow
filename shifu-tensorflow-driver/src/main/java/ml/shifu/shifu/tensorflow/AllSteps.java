package ml.shifu.shifu.tensorflow;

import ml.shifu.shifu.ShifuCLI;

import java.security.Permission;

public final class AllSteps {
    private AllSteps() {
    }

    public static void main(String[] args) {
        forbidSystemExitCall();

        runShifu("stats");
        runShifu("norm");
        runShifu("varsel");
        runShifu("train");
        runShifu("eval");
        enableSystemExitCall();
    }

    private static void runShifu(String cmd) {
        try {
            ShifuCLI.main(new String[]{cmd});
        } catch (ExitTrappedException e) {

        }
    }

    private static class ExitTrappedException extends RuntimeException {
    }

    private static void forbidSystemExitCall() {
        final SecurityManager securityManager = new SecurityManager() {
            public void checkPermission(Permission perm) {
                if (perm.getName().contains("exitVM")) {
                    throw new ExitTrappedException();
                }
            }
        };
        System.setSecurityManager(securityManager);
    }

    private static void enableSystemExitCall() {
        System.setSecurityManager(null);
    }


}
