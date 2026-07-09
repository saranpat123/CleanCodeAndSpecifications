package lab;

/**
 * Test runner — checks "Part A" (implementation) automatically.
 * Part B (writing specs) is subjective; grade it manually using the checklist in the Google Classroom.
 *
 * In VSCode: open this file and click the Run button (assertions are enabled via .vscode/settings.json).
 */
public class TestRunner {
    static int pass = 0, fail = 0;

    // helper เดียว: รับข้อความ + ผลลัพธ์ true/false
    static void check(String name, boolean ok) {
        if (ok) { pass++; System.out.println("  [PASS] " + name); }
        else    { fail++; System.out.println("  [FAIL] " + name); }
    }

    public static void main(String[] a) {
        boolean ea = false;
        assert ea = true;
        if (!ea) System.out.println("!! Assertions are OFF: run with -ea, otherwise Q3 is not fully checked\n");

        System.out.println("== Q1: StringUtils.countVowels ==");
        check("'Hello World' = 3", StringUtils.countVowels("Hello World") == 3);
        check("'AEIOUaeiou' = 10", StringUtils.countVowels("AEIOUaeiou") == 10);
        check("'' = 0", StringUtils.countVowels("") == 0);
        check("'xyz' = 0", StringUtils.countVowels("xyz") == 0);
        // ทดสอบว่า null ต้อง throw IllegalArgumentException
        boolean threw1 = false;
        try {
            StringUtils.countVowels(null);
        } catch (IllegalArgumentException e) {
            threw1 = true;
        }
        check("null -> throws IllegalArgumentException", threw1);

        System.out.println("== Q2: Payment.canCharge ==");
        check("null -> false", Payment.canCharge(null)==false);
        check("inactive -> false", Payment.canCharge(new User(false, 100))==false);
        check("balance 0 -> false", Payment.canCharge(new User(true, 0))==false);
        check("active + balance>0 -> true", Payment.canCharge(new User(true, 100))==true);

        System.out.println("== Q3: BankAccount.withdraw ==");
        BankAccount acc = new BankAccount(100);
        acc.withdraw(30);
        check("withdraw 30 from 100 -> 70", acc.getBalance() == 70);

        // ถอน 0 ต้อง throw IllegalArgumentException
        boolean threw2 = false;
        try {
            acc.withdraw(0);
        } catch (IllegalArgumentException e) {
            threw2 = true;
        }
        check("withdraw 0 -> throws IllegalArgumentException", threw2);

        // ถอนติดลบ ต้อง throw IllegalArgumentException
        boolean threw3 = false;
        try {
            acc.withdraw(-5);
        } catch (IllegalArgumentException e) {
            threw3 = true;
        }
        check("withdraw -5 -> throws IllegalArgumentException", threw3);

        // ถอนเกินยอด ต้อง throw AssertionError (ตรวจได้เฉพาะเมื่อเปิด -ea)
        if (ea) {
            boolean threw4 = false;
            try {
                new BankAccount(50).withdraw(100);
            } catch (AssertionError e) {
                threw4 = true;
            }
            check("withdraw over balance -> throws AssertionError", threw4);
        }

        System.out.println("\n==================================");
        System.out.printf("Part A: PASS %d / FAIL %d%n", pass, fail);
        System.out.println("==================================\n");

        System.out.println("(Part B = writing specs, graded manually via checklist)");
        int arr[] = {7,10,5,10};
        Specs.firstIndexOf(arr, 10);
        Specs.circleArea(5.5);
        Specs.normalize(" Hello World ");

        if (fail > 0) System.exit(1);
    }
}
