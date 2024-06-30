//
//
//public class FaceImageSelector {
//
//    public static void main(String[] args) {
//        String firstName = "Vahe";
//        String lastName = "Aleksanyan";
//
//        int start = computeStartingIndex(firstName, lastName);
//        System.out.println("Starting index: " + start);
//    }
//
//    public static int computeStartingIndex(String firstName, String lastName) {
//        // Step 1: Compute a, b, c
//        int a = (int) lastName.charAt(0) - (int) 'A';
//        int b = (int) lastName.charAt(1) - (int) 'a';
//        int c = (int) firstName.charAt(0) - (int) 'A';
//
//        // Step 2: Compute x
//        double x;
//        if (b < 2) {
//            x = Math.pow(a, 2) + Math.pow(5 * b, 2) + Math.pow(c, 2);
//        } else if (c < 17) {
//            x = Math.pow(2 * a, 2) + Math.pow(b, 2) + Math.pow(3 * c, 2);
//        } else {
//            x = Math.pow(a, 2) + Math.pow(2.5 * b, 2) + Math.pow(c, 2);
//        }
//
//        // Step 3: Compute y
//        double y;
//        if (b < 2) {
//            y = Math.pow(x, 2.0 / 3.0);
//        } else if (c < 17) {
//            y = 0.037 * x + 8.2;
//        } else {
//            y = 6.4 * Math.pow(x, 0.41);
//        }
//
//        // Step 4: Compute start
//        int start = (int) (y / 8 + 0.5) * 8 + 1;
//        return start;
//    }
//}
